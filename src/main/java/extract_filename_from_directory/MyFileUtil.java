package extract_filename_from_directory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class MyFileUtil {
    //存储文件信息，hashset不会包含重复项
    private static Set<File> fileSet = new HashSet<File>();
    private static List<File> files;

    //--------------获取文件---------------------
    /**
     * 获取目录下所有文件
     * 采用递归的方法，将目录下的文件名提取出来
     * @param rootDir
     * @return
     */
    public static Set<File> listFiles(File rootDir){
        //检查目录是否为空
        if(rootDir == null || rootDir.listFiles() == null){
            return fileSet;
        }
        //遍历目录，将文件添加到hashset中
        for(File fileOrDir : rootDir.listFiles()){
            if(fileOrDir.isFile()){
                fileSet.add(fileOrDir);
            }else{
                fileSet.addAll(listFiles(fileOrDir));
            }
        }
        return fileSet;
    }

    /**
     * 获取目录下文件
     * 三个参数进行配置：文件目录、扩展名、递归与否
     * @param rootDir
     * @return
     */
    public static List<File> listFilesCommons(File rootDir){
        files = (List<File>)FileUtils.listFiles(rootDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        return files;
    }

    /**
     * 一次性读取文件内容
     * 使用Java8的方法
     * @param file
     * @return
     */
    public static Stream<String> getFileStream(String file){
        try (Stream<String> stream = Files.lines(Paths.get(file))){
            stream.forEach(System.out::println);
            return stream;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 一次性读取文件内容
     * 使用Apache.commons.IO
     * @param fileName
     * @return
     */
    public static String getFileStreamCommons(String fileName){
        File file = new File(fileName);
        String text = null;
        try{
            text = FileUtils.readFileToString(file, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 提取PDF中的内容
     * 使用Apache Tika工具
     * @param fileName
     */
    public static String convertPDF(String fileName){
        try(InputStream stream = new FileInputStream(fileName)){
            //允许所有类型的文档转换
            AutoDetectParser parser = new AutoDetectParser();
            //处理正文 使用-1忽略允许包含的最多字符(100000个字符)
            BodyContentHandler handler = new BodyContentHandler(-1);

            Metadata metadata = new Metadata();
            parser.parse(stream, handler, metadata, new ParseContext());
            return handler.toString();
        }catch (IOException e){
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    //--------------清洗文件---------------------

    /**
     *使用正则表达式清洗ASCII文本文件
     * @param text
     * @return
     */
    public static String cleanText(String text){
        //去掉ASCII字符
        text = text.replaceAll("[^p{ASCII}]", "");
        //将空格合并为一个空格
        text = text.replaceAll("s+", "");
        //清除ASCII控制字符
        text = text.replaceAll("p\\{Cntrl}", "");
        //去除ASCII非打印字符
        text = text.replaceAll("[^p{Print}]", "");
        //移除Unicode非打印字符
        text = text.replaceAll("p\\{C}", "");

        return text;
    }
}