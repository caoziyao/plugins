package com.zel.commonutils;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
public class FileUtils {

    public static void test() throws IOException {
        String fileName = "test.txt";
        System.out.println("File.separator:" + File.separator);
        File testFile = new File("D:" + File.separator + "filepath" + File.separator + "test" + File.separator + fileName);
        File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
        String fileParentPath = testFile.getParent();//返回的是String类型
        System.out.println("fileParent:" + fileParent);
        System.out.println("fileParentPath:" + fileParentPath);
        if (!fileParent.exists()) {
            fileParent.mkdirs();// 能创建多级目录
        }
        if (!testFile.exists()) {
            testFile.createNewFile();//有路径才能创建文件
        }

        System.out.println(testFile);

        String path = testFile.getPath();
        String absolutePath = testFile.getAbsolutePath();//得到文件/文件夹的绝对路径
        String getFileName = testFile.getName();//得到文件/文件夹的名字
        System.out.println("path:" + path);
        System.out.println("absolutePath:" + absolutePath);
        System.out.println("getFileName:" + getFileName);

    }

    /**
     * 文件名
     * @param filename
     * @return
     */
    public static String caselsh(String filename) {
        // String fileName=file.getName();
        return filename.substring(0, filename.lastIndexOf("."));
    }

    /**
     * 文件名后缀
     * @param filename
     * @return
     */
    public static String subffix(String filename) {
        // String fileName=file.getName();
        return  filename.substring(filename.lastIndexOf("."), filename.length());
    }

    public static File write(String filePath, String content) throws IOException {
        File file = new File(filePath);

        // 创建目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close();

        return file;
    }

    public static String readLine(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuffer buffer = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            buffer.append(str);
        }
        reader.close();
        return buffer.toString();
    }

    /**
     * 读取文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String read(String filePath) throws IOException {
        File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent);
    }

    /**
     * 重命名
     * @param newname
     * @return
     */
    public static boolean rename(String oldPath, String newname) {
        File file = new File(oldPath);

        String newPath = file.getAbsolutePath().replace(file.getName(), newname);
        File newFile = new File(newPath);

        System.out.println("重命名: " + newPath);
        return file.renameTo(newFile);
    }

    /**
     * 创建文件目录，dest是文件
     * @param dest
     */
    public static void checkAndCreateParentDir(File dest){
        //判断目标文件所在的目录是否存在
        if (!dest.getParentFile().exists()) {
            //创建目录
            dest.getParentFile().mkdirs();
        }
    }

    public static String getFilePath(String suffix) {
        //String date = DateUtil.format(new Date(), DateUtil.YMD).replaceAll("-", "");
        //String filename = UUID.randomUUID().toString().replaceAll("-","") + suffix;
        //String dir = "./cache";
        //// 生成目录
        //String filePath = dir + File.separator + date + File.separator + filename;
        //return filePath;
        return null;
    }


    /**
     * 判断文件是否存在，不存在则创建
     * @param file
     * @throws IOException
     */
    public static boolean createFile(File file) throws IOException{
        boolean flag = false;
        // 创建文件夹
        String path = file.getAbsolutePath();
        int index = path.lastIndexOf("/");
        path = path.substring(0, index);
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
            flag = true;
        }

        // 创建文件
        if (!file.exists()) {
            file.createNewFile();
            flag = true;
        }
        return flag;
    }

    /**
     * 当前路径
     * @return
     * @throws IOException
     */
    public static File currentPath() throws IOException {
        File directory = new File(".");
        return directory.getCanonicalFile();
    }

    /**
     * 读取二进制文件
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static byte[] dataFrom(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(StrUtil.format("file:{} not found", filePath));
        }
        Long filelength = file.length();
        byte[] bytes = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(bytes);
        in.close();
        return bytes;
    }
}
