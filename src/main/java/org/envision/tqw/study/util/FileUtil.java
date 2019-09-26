package org.envision.tqw.study.util;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName: FileUtil
 * @Description:
 *          文件操作类
 * @Author: qiwei.tan
 * @Date: 2019/8/26 15:23
 * @Version: 1.0
 */
public class FileUtil {

    private static ArrayList<String> fileInputStrings = null;
    private static String fileRead = null;
    /**
     * 针对txt文件读取
     * @param path
     * @return
     */
    public static String readFileByPath(String path) throws IOException {
        String temp;
        BufferedReader br = getBufferReader(path);
        StringBuffer content = new StringBuffer();
        while ((temp=br.readLine())!=null)
            content = content.append(temp);
        return content.toString();
    }

    public static String readFileByLine(String path,int line) throws Exception {

        if(line < 1)
           throw new Exception("行数不能小于1") ;
        if(isReadThisFile(path))
            return fileInputStrings.get(line-1);
        else{
            fileRead = path;
            String temp;
            fileInputStrings = new ArrayList<String>();
            BufferedReader br = getBufferReader(path);
            while ((temp=br.readLine())!=null)
                fileInputStrings.add(temp);
        }
        return fileInputStrings.get(line-1);
    }

    private static BufferedReader getBufferReader(String path) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        if(inputStream == null)
            throw new IOException("路径下的文件不存在！");
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(inputStream,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码格式不支持！");
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(in);
        return br;
    }

    public static int getFileTotalLine(String path) throws IOException {
        if(isReadThisFile(path))
            return fileInputStrings.size();
        else {
            fileRead = path;
            String temp;
            fileInputStrings = new ArrayList<String>();
            BufferedReader br = getBufferReader(path);
            while ((temp=br.readLine())!=null)
                fileInputStrings.add(temp);
        }
        return fileInputStrings.size();
    }

    private static boolean isReadThisFile(String path){
        boolean isOrigin;//判断是不是首次使用的源文件
        if(fileRead == null)
            isOrigin = false;
        else
            isOrigin = fileRead.equals(path);
        return isOrigin && fileInputStrings != null;
    }

}
