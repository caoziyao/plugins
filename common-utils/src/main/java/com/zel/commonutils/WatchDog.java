package com.zel.commonutils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import static com.sun.jmx.mbeanserver.Util.cast;
import static java.nio.file.StandardWatchEventKinds.*;

//javac -encoding UTF-8 XXX.java
public class WatchDog {

    //public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    //    MyClassLoader mcl = new MyClassLoader() ;
    //    System.out.println(mcl.getParent());
    //    Class<?> personClass = mcl.findClass("E:\\aa\\Gif.class");
    //    Object person =  personClass.newInstance() ;
    //
    //    Method sayHelloMethod = personClass.getMethod("gif") ;
    //    sayHelloMethod.invoke(person) ;
    //}

    public static void test2() throws Exception {
        //File file = new File("E:\\aa\\json.jar");
        //URLClassLoader loader = new URLClassLoader(new URL[]{ file.toURI().toURL() });

        //MyClassLoader mcl = new MyClassLoader();
        //mcl.loadJar("E:\\aa\\json.jar");
        //Class clazz = loader.loadClass("com.zel.commonutils.Gif");
        Class<?> clazz = Class.forName("com.zel.commonutils.Gif");

        Object person = clazz.newInstance();

        Method sayHelloMethod = clazz.getMethod("gif") ;
        sayHelloMethod.invoke(person) ;
        //Gif driver = (Gif) clazz.newInstance();
        //driver.gif();
    }

    public static void test3()  throws Exception {
        //第二种
        //URL url1 = new URL("file:E:\\aa\\json.jar");
        //URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread()
        //        .getContextClassLoader());

        File file = new File("E:\\aa\\json.jar");
        URLClassLoader loader = new URLClassLoader(new URL[]{ file.toURI().toURL() });

        Class<?> myClass1 = loader.loadClass("com.zel.commonutils.Gif");
        IGIF action1 = (IGIF) myClass1.newInstance();
        action1.gif();

    }

    public static void test() throws Exception {
        MyClassLoader mcl = new MyClassLoader();
        System.out.println(mcl.getParent());
        Class<?> personClass = mcl.findClass("E:\\aa\\com\\zel\\commonutils\\Gif.class");
        //Class<?> personClass = mcl.findClass("E:\\aa\\Gif.class");
        //Class<?> personClass = mcl.findClass("E:\\aa\\json.jar");
        Object person = personClass.newInstance();

        Method sayHelloMethod = personClass.getMethod("gif") ;
        sayHelloMethod.invoke(person) ;
        //Gif gif = (Gif)
    }


    public static void pre() {
        /**
         * 1, 打成 .class 包到 target/classes 中
         * 2，target/classes 打成 jar 包
         * 3, 运行
         */

        //String root = "common-utils/src/main/java";
        String shell1 = "javac ./common-utils/src/main/java/* -d common-utils/target/classes/";
        String shell2 = "jar -cvfm gif.jar MANIFEST.MF  -C  target/classes/ .";
        String shell3 = "java -jar gif.jar";
    }

    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path root = Paths.get(".", "common-utils", "src", "main", "java");

        Files.walk(root).forEach((path) -> {
            if (Files.isDirectory(path)) {
                try {
                    // 注册
                    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true) {
            // 拉取一个WatchKey。当触发监听的事件时，就会产生一个WatchKey，此WatchKey封装了事件信息。
            WatchKey watchKey = watchService.take();

            // 使用循环是因为这一个WatchKey中可能有多个文件变化了，比如Ctrl+A全选，然后删除，只触发了一个WatchKey，但有多个文件变化了
            for (WatchEvent event : watchKey.pollEvents()) {
                /**
                 watchKey.pollEvents()  获取此次WatchKey中所有变化了的文件的信息，以List（列表）形式返回，一个WatchEvent就是一个元素，封装了一个变化了的文件的信息
                 event.context()  获取文件名
                 event.kind()  获取发生的事件类型
                 因为只能为文件夹注册监听，如果要监听某些指定的文件，可以在增强的for循环中，先根据event.context()判断是否是指定的文件，是才处理。

                 jar cvf json.jar *
                 */
                // System.out.println(event.context() + "发生了 " + event.kind() + " 事件！");

                WatchEvent.Kind kind = event.kind();
                //if (kind == ENTRY_MODIFY) {
                    System.out.println(event.context() + " MODIFY");

                    //获取监听Path
                    Path path = cast(event.context());
                    //构造完整路径
                    Path fullPath = Paths.get(root.toString(), path.toString());
                    //System.out.println(path + "||| " + fullPath + "dff" + root);

                    //String shell = "javac E:\\working\\mycode\\plugins\\common-utils\\src\\main\\java\\com\\zel\\commonutils\\Gif.java -d E:\\working\\mycode\\plugins\\common-utils\\target\\classes";
                    //String shell = "javac " + fullPath.toAbsolutePath();
                    //String shell = "javac E:\\working\\mycode\\plugins\\common-utils\\src\\main\\java\\com\\zel\\commonutils\\Gif.java -d E:\\aa";
                    //
                    //System.out.println("shll " + shell);
                    //Runtime.getRuntime().exec(shell);
                    //String shell = "javac "
                    //Gif gif = new Gif();
                    //gif.gif(); E:\working\mycode\plugins\common-utils\target\classes

                    //test();
                    //test2();
                test3();

                    //ClassLoader classLoader = WatchDog.class.getClassLoader();

                    //MyClassLoader myClassLoader = new MyClassLoader();
                    //myClassLoader.ffff("");
                    //try {
                   //
                   //    Class clz = Class.forName("com.zel.commonutils.Gif");
                   //    Gif apple = (Gif)clz.newInstance();
                   //    apple.gif();
                   //
                   //    //String shell2 = "e: && cd E:\\working\\mycode\\plugins\\common-utils\\target\\classes && java com.zel.commonutils.Gif";
                   //    //System.out.println("shll " + shell2);
                   //    //Runtime.getRuntime().exec(shell2);
                   //} catch (Exception e) {
                   //    e.printStackTrace();
                   //}
                //}
            }

            // 虽然是while()循环，但WatchKey和ByteBuffer一样，使用完要重置状态，才能继续用。
            // 如果不重置，WatchKey使用一次过后就不能再使用，即只能监听到一次文件变化。
            watchKey.reset();
        }
    }

    //public static void main(String[] args) throws IOException, InterruptedException {
    //    //创建一个文件系统的监听服务
    //    WatchService watchService= FileSystems.getDefault().newWatchService();
    //
    //    File directory = new File(".");
    //    File canonicalFile = directory.getCanonicalFile();
    //    //Path path = Paths.get("E:\\working\\mycode\\plugins\\common-utils\\src\\main\\java\\com\\zel\\commonutils");
    //    //Path path = Paths.get(canonicalFile.toURI());
    //
    //    Path path = Paths.get(".", "common-utils", "src", "main", "java", "com", "zel", "commonutils");
    //
    //    //为该文件夹注册监听, 监听新建、修改、删除事件。只能为文件夹（目录）注册监听，不能为单个文件注册监听
    //    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
    //
    //    //编写事件处理
    //    while (true){  //一直监听
    //        //拉取一个WatchKey。当触发监听的事件时，就会产生一个WatchKey，此WatchKey封装了事件信息。
    //        WatchKey watchKey=watchService.take();
    //
    //        //使用循环是因为这一个WatchKey中可能有多个文件变化了，比如Ctrl+A全选，然后删除，只触发了一个WatchKey，但有多个文件变化了
    //        for (WatchEvent event:watchKey.pollEvents()){
    //            System.out.println(event.context()+"发生了"+event.kind()+"事件！");
    //            /*
    //            watchKey.pollEvents()  获取此次WatchKey中所有变化了的文件的信息，以List（列表）形式返回，一个WatchEvent就是一个元素，封装了一个变化了的文件的信息
    //            event.context()  获取文件名
    //            event.kind()  获取发生的事件类型
    //
    //            因为只能为文件夹注册监听，如果要监听某些指定的文件，可以在增强的for循环中，先根据event.context()判断是否是指定的文件，是才处理。
    //             */
    //            WatchEvent.Kind kind = event.kind();
    //            System.out.println("kind"+ kind);
    //            // ENTRY_MODIFY
    //            if (kind == ENTRY_DELETE) {
    //                System.out.println("run "+ kind);
    //                File directory2 = new File(".");
    //                File canonicalFile2 = directory2.getCanonicalFile();
    //                System.out.println(canonicalFile2);
    //
    //                //System.out.println(Paths.get(".").get());
    //
    //                Gif gif = new Gif();
    //                gif.gif();
    //            }
    //        }
    //
    //        //虽然是while()循环，但WatchKey和ByteBuffer一样，使用完要重置状态，才能继续用。
    //        //如果不重置，WatchKey使用一次过后就不能再使用，即只能监听到一次文件变化。
    //        watchKey.reset();
    //    }
    //}
}
