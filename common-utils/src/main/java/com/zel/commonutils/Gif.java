package com.zel.commonutils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Gif implements IGIF {

    @Override
    public void gif() throws IOException {
        System.out.println("dsfw 2");

        //Date date = DateUtil.nightOf(new Date());
        //System.out.println(date);

        //File file = new File("abc.txt");
        //if (!file.exists()) {
        //    file.createNewFile();
        //}
        //FileUtils.write(file.getPath(), "vvv");
    }

    public static void main(String[] args) {
        System.out.println("main gif");
        System.out.println(StringUtils.isBlank(null));;
    }
}
