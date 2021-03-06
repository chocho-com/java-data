package extract_filename_from_directory;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
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

    /**
     * 使用Univocity解析CSV文件
     * .csv以逗号为数据分隔符
     * @param fileName
     */
    public static void parseCSV(String fileName){
        //配置对象
        CsvParserSettings parserSettings = new CsvParserSettings();
        //开启解析器自动检查功能，自动侦测包含何种行分隔符序列
        parserSettings.setLineSeparatorDetectionEnabled(true);

        RowListProcessor rowProcessor = new RowListProcessor();
        parserSettings.setRowProcessor(rowProcessor);
        //将第一解析行作为每个列的标题
        parserSettings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(parserSettings);
        //解析
        parser.parse(new File(fileName));
        String headers[] = rowProcessor.getHeaders();
        for(String h : headers){
            System.out.print(h + " ");
        }
        System.out.println();
        List<String[]> rows = rowProcessor.getRows();
        for(int i = 0; i < rows.size(); i++){
            System.out.println(Arrays.asList(rows.get(i)));
        }
    }

    /**
     * 使用Univocity解析TSV文件
     * * .tsv以TAB为数据分隔符
     * @param fileName
     */
    public static void parseTSV(String fileName){
        TsvParserSettings settings = new TsvParserSettings();
        //行分隔符 \n或者n
        settings.getFormat().setLineSeparator("\n");
        TsvParser parser = new TsvParser(settings);
        //解析tsv文件
        List<String[]> allRows = parser.parseAll(new File(fileName));
        for(int i = 0; i < allRows.size(); i++) {
            System.out.println(Arrays.asList(allRows.get(i)));
        }
    }

    /**
     * 使用JDOM解析XML文件
     * @param fileName
     */
    public static void parseXML(String fileName){
        SAXBuilder builder = new SAXBuilder();
        File file = new File(fileName);
        try {
            Document document = builder.build(file);
            //创建根元素
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("author");
            for(int i = 0; i < list.size(); i++){
                Element node = (Element) list.get(i);
                //获取子元素的文本内容
                System.out.println("First Name : " +
                        node.getChildText("firstname"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JDOMException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用JSON-simple编写Json文件
     * @param outFileName
     */
    public static void writeJson(String outFileName){
        //创建Json数据
        JSONObject object = new JSONObject();
        object.put("book", "《Java数据科学》");
        object.put("author", "chocho");
        JSONArray list = new JSONArray();
        list.add("书评1");
        list.add("书评2");
        list.add("书评3");
        object.put("messages", list);

        //输出文件
        try(FileWriter file = new FileWriter(outFileName)) {
            file.write(object.toJSONString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用JSON-simple读取Json文件
     * @param fileName
     */
    public static void readJson(String fileName){
        JSONParser parser = new JSONParser();
        try{
            JSONObject object = (JSONObject)parser.parse(new FileReader(fileName));
            String book = (String)object.get("book");
            String author = (String)object.get("author");
            JSONArray messages = (JSONArray)object.get("messages");
            System.out.println("book=" + book);
            System.out.println("author=" + author);
            messages.forEach(System.out::println);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用Jsoup从URL中提取Web数据
     * @param url
     */
    public static void extractDataWithJsoup(String url){
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect(url)  //链接url
                    .timeout(10 * 1000)          //超时设置
                    .userAgent("chocho")       //用户代理名称
                    .ignoreHttpErrors(true)    //忽略链接错误
                    .get();                    //获取

        }catch (IOException e){
            e.printStackTrace();
        }


        if(doc != null){
            //标题
            String title = doc.title();
            //正文
            String text = doc.body().text();
            System.out.println("title=" + title);
            System.out.println("text=" + text);

            //超链接
            Elements links = doc.select("a[href]");
            //处理链接
            for(org.jsoup.nodes.Element link : links){
                String linkHref = link.attr("href");
                String linkText = link.text();
                String linkOuterHtml = link.outerHtml();
                String linkInnerHtml = link.html();
                System.out.println(linkHref + "t" + linkText + "t" +
                        linkOuterHtml + "t" + linkInnerHtml);
            }
        }
    }
}
