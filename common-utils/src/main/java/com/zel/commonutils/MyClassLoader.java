package com.zel.commonutils;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    private static final String SUFFIX = ".class";

    public String getClassPath(String className, String[] paths){
        for(String path : paths){
            if(className.contains(".")){
                className = className.replaceAll(".", File.separator);
            }
            String classPath = path + className + SUFFIX;
            File classFile = new File(classPath);
            if(classFile.exists()){
                return classPath;
            }
        }
        return null;
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {

    }


    public void loadJar(String jarPath) throws MalformedURLException {
        File jarFile = new File(jarPath); // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹

        if (jarFile.exists() == false) {
            System.out.println("jar file not found.");
            return;
        }

        //获取类加载器的addURL方法，准备动态调用
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }

        // 获取方法的访问权限，保存原始值
        boolean accessible = method.isAccessible();
        try {
            //修改访问权限为可写
            if (accessible == false) {
                method.setAccessible(true);
            }

            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

            //获取jar文件的url路径
            java.net.URL url = jarFile.toURI().toURL();

            //jar路径加入到系统url路径里
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //回写访问权限
            method.setAccessible(accessible);
        }
    }


    protected Class<?> findClass(String dir) throws ClassNotFoundException {
        File classFile = new File(dir);
        if(!classFile.exists()){
            throw new ClassNotFoundException(classFile.getPath() + " 不存在") ;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        ByteBuffer bf = ByteBuffer.allocate(1024) ;
        FileInputStream fis = null ;
        FileChannel fc = null ;
        try {
            fis = new FileInputStream(classFile) ;
            fc = fis.getChannel() ;
            while(fc.read(bf) > 0){
                bf.flip() ;
                bos.write(bf.array(),0 , bf.limit()) ;
                bf.clear() ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fis.close() ;
                fc.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(bos.toByteArray() , 0 , bos.toByteArray().length);
    }


    protected void ffff(String name) throws Exception {
        //动态加载class文件
        //1.创建一个File文件对象：路径是存放class文件目录：要保证class的包目录是完整的：可以是压缩包
        //File file = new File(" E:\\working\\mycode\\plugins\\common-utils\\target\\classes\\com\\zel\\commonutils\\Gif.class");
        File file = new File(" E:\\aa\\com\\zel\\commonutils\\Gif.class");

        //2.获取URL对象
        URL url = file.toURI().toURL();

        //3.创建URL类加载器
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

        //4.通过urlClassLoader加载器调用loadClass方法传入类名动态加载class文件并获取class对象:会初始化静态块
        Class<?> classs = urlClassLoader.loadClass("com.zel.commonutils.Gif");

        //5.通过class对象创建实例
        Object newInstance = classs.newInstance();

        //*通过如下方式也可以加载class文件  : 中间为true在加载时会初始化静态块  false不会
        Class<?> thread = Class.forName ("com.zel.commonutils.Gif", false, urlClassLoader);
        Object newInstancethread = thread.newInstance();

        //两者的区别：URLClassLoader可以加载任意路径的class文件, Class.forName和ClassLoader是只能加载classPath路径bin目录下的class文件
        System.out.println(newInstance);
        System.out.println(newInstancethread);

        //Gif gif = (Gif)newInstance;
        //gif.gif();

    }

    //
    //public Class loadClass(String name) throws ClassNotFoundException {
    //    //if(!"reflection.MyObject".equals(name)) {
    //    //    return super.loadClass(name);
    //    //}
    //
    //
    //    try {
    //        String url = "file:E:\\working\\mycode\\plugins\\common-utils\\target\\classes\\com\\zel\\commonutils\\Gif.class";
    //        URL myUrl = new URL(url);
    //        URLConnection connection = myUrl.openConnection();
    //        InputStream input = connection.getInputStream();
    //        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    //        int data = input.read();
    //
    //        while(data != -1){
    //            buffer.write(data);
    //            data = input.read();
    //        }
    //
    //        input.close();
    //
    //        byte[] clazz = buffer.toByteArray();
    //        System.out.println("clazz"+ clazz);
    //        return defineClass( clazz, 0, clazz.length);
    //
    //        //return defineClass("reflection.MyObject",
    //        //        classData, 0, classData.length);
    //
    //    } catch (MalformedURLException e) {
    //        e.printStackTrace();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //
    //    return null;
    //}

}