package com.zel.commonutils;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 运行shell脚本
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/17
 */
public class ShellUtil {
    /**
     * 运行shell脚本
     *
     * @param shell 需要运行的shell脚本
     */
    public static void exec(String shell) {
        try {
            Runtime.getRuntime().exec(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行shell脚本 new String[]方式
     *
     * @param shell 需要运行的shell脚本
     */
    public static void execWithArg(String shell) {
        try {
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", shell}, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 运行shell并获得结果，注意：如果sh中含有awk,一定要按new String[]{"/bin/sh","-c",shStr}写,才可以获得流
     *
     * @param shStr 需要执行的shell
     * @return
     */
    public static List<String> run(String shStr) {
        List<String> strList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(shStr);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            int processComplete = process.waitFor();
            while ((line = input.readLine()) != null) {
                strList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    public static List<String> runWithArg(String shStr, String[] envp, File dir) {
        List<String> strList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", shStr}, envp, dir);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            int processComplete = process.waitFor();
            while ((line = input.readLine()) != null) {
                strList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }
}
