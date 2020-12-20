package com.zel;

import java.io.*;
import java.nio.file.Files;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
public class FileUtils {

    public static void write(String filePath, String content) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close();
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

    public static String read(String filePath) throws IOException {
        File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent);
    }
}
