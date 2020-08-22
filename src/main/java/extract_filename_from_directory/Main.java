package extract_filename_from_directory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "H:\\Java数据科学\\files";
        String file = "E:\\记做ssm项目踩过的坑\\关于sql模糊查询的坑.txt";
        String pdf = "D:\\Java面试系统复习\\数据结构  C语言版  第2版.pdf";
        String csv = "H:\\Java数据科学\\testCSV.csv";
        String tsv = "H:\\Java数据科学\\testTSV.tsv";
        String pom = "H:\\Java数据科学\\pom.xml";

        //1.Java的文件提取方法
        //new Main().method1(path);

        //2.Commons IO的文件提取方法
        //new Main().method2(path);

        //3.Java8的文件内容一次性提取方法
        //new Main().method3(file);

        //4.Commons IO的文件内容一次性提取方法
        //new Main().method4(file);

        //5.Apache Tika 提取PDF文件内容
        //new Main().method5(file);

        //6.正则表达式清洗文本
        //new Main().method6(file);

        //7.Unicocity解析CSV文件
        //new Main().method7(csv);

        //8.Unicocity解析TSV文件
        //new Main().method8(tsv);

        //9.JDOM解析XML文件
        new Main().method9(pom);
    }

    void method1(String path){
        for(File f1 : MyFileUtil.listFiles(new File(path))){
            System.out.println("file: " + f1.getAbsolutePath());
        }
    }
    void method2(String path){
        for(File f2 : MyFileUtil.listFilesCommons(new File(path))){
            System.out.println("file: " + f2.getAbsolutePath());
        }
    }
    void method3(String file){
        MyFileUtil.getFileStream(file);
    }
    void method4(String file){
        System.out.println(MyFileUtil.getFileStreamCommons(file));
    }
    void method5(String fileName){
        System.out.println(MyFileUtil.convertPDF(fileName).length());
    }
    void method6(String fileName){
        String text = MyFileUtil.getFileStreamCommons(fileName);
        System.out.println(MyFileUtil.cleanText(text));
    }
    void method7(String fileName){
        MyFileUtil.parseCSV(fileName);
    }
    void method8(String fileName){
        MyFileUtil.parseTSV(fileName);
    }
    void method9(String fileName){
        MyFileUtil.parseXML(fileName);
    }
}
