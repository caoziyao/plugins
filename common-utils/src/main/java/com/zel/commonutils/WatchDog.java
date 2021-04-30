//package com.zel.commonutils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.nio.file.*;
//import static com.sun.jmx.mbeanserver.Util.cast;
//import static java.nio.file.StandardWatchEventKinds.*;
//
////javac -encoding UTF-8 XXX.java
//public class WatchDog {
//
//
//    public static void test2() throws Exception {
//        Class<?> clazz = Class.forName("com.zel.commonutils.Gif");
//        Object person = clazz.newInstance();
//        Method sayHelloMethod = clazz.getMethod("gif") ;
//        sayHelloMethod.invoke(person) ;
//    }
//
//    public static void test3()  throws Exception {
//        File file = new File("E:\\aa\\json.jar");
//        URLClassLoader loader = new URLClassLoader(new URL[]{ file.toURI().toURL() });
//
//        Class<?> myClass1 = loader.loadClass("com.zel.commonutils.Gif");
//        //IGIF action1 = (IGIF) myClass1.newInstance();
//        //action1.gif();
//
//    }
//
//    public static void test() throws Exception {
//        MyClassLoader mcl = new MyClassLoader();
//        System.out.println(mcl.getParent());
//        Class<?> personClass = mcl.findClass("E:\\aa\\com\\zel\\commonutils\\Gif.class");
//        Object person = personClass.newInstance();
//
//        Method sayHelloMethod = personClass.getMethod("gif") ;
//        sayHelloMethod.invoke(person) ;
//    }
//
//    /**@PARAM command指exe程序所在路径**/
//    public static String executeCmd(String command) throws IOException {
//        System.err.println("Execute command : " + command);
//        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec("cmd /c start " + command);
//        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
//        String line = null;
//        StringBuilder build = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            System.err.println(line);
//            build.append(line);
//        }
//        return build.toString();
//    }
//
//
//    public static void pre() throws IOException {
//        /**
//         * 1, mvn 打成 jar 包 D:\gua\maven3\bin\mvn package
//         * 2, 运行  java -jar common-utils-1.0-SNAPSHOT.jar
//         */
//        String shell1 = "D:\\gua\\maven3\\bin\\mvn package";
//        System.out.println(shell1);
//        executeCmd(shell1);
//        String shell2 = "java -jar E:\\working\\mycode\\plugins\\common-utils\\target\\common-utils-1.0-SNAPSHOT.jar";
//        System.out.println(shell2);
//        Runtime.getRuntime().exec(shell2);
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException, Exception {
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//
//        Path root = Paths.get(".", "common-utils", "src", "main", "java");
//
//        Files.walk(root).forEach((path) -> {
//            if (Files.isDirectory(path)) {
//                try {
//                    // 注册
//                    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        while (true) {
//            // 拉取一个WatchKey。当触发监听的事件时，就会产生一个WatchKey，此WatchKey封装了事件信息。
//            WatchKey watchKey = watchService.take();
//
//            // 使用循环是因为这一个WatchKey中可能有多个文件变化了，比如Ctrl+A全选，然后删除，只触发了一个WatchKey，但有多个文件变化了
//            for (WatchEvent event : watchKey.pollEvents()) {
//                /**
//                 watchKey.pollEvents()  获取此次WatchKey中所有变化了的文件的信息，以List（列表）形式返回，一个WatchEvent就是一个元素，封装了一个变化了的文件的信息
//                 event.context()  获取文件名
//                 event.kind()  获取发生的事件类型
//                 因为只能为文件夹注册监听，如果要监听某些指定的文件，可以在增强的for循环中，先根据event.context()判断是否是指定的文件，是才处理。
//
//                 jar cvf json.jar *
//                 */
//                // System.out.println(event.context() + "发生了 " + event.kind() + " 事件！");
//
//                WatchEvent.Kind kind = event.kind();
//                //if (kind == ENTRY_MODIFY) {
//                    System.out.println(event.context() + " MODIFY");
//
//                    //获取监听Path
//                    Path path = cast(event.context());
//                    //构造完整路径
//                    Path fullPath = Paths.get(root.toString(), path.toString());
//                    pre();
//
//
//            }
//
//            // 虽然是while()循环，但WatchKey和ByteBuffer一样，使用完要重置状态，才能继续用。
//            // 如果不重置，WatchKey使用一次过后就不能再使用，即只能监听到一次文件变化。
//            watchKey.reset();
//        }
//    }
//
//    //public static void main(String[] args) throws IOException, InterruptedException {
//    //    //创建一个文件系统的监听服务
//    //    WatchService watchService= FileSystems.getDefault().newWatchService();
//    //
//    //    File directory = new File(".");
//    //    File canonicalFile = directory.getCanonicalFile();
//    //    //Path path = Paths.get("E:\\working\\mycode\\plugins\\common-utils\\src\\main\\java\\com\\zel\\commonutils");
//    //    //Path path = Paths.get(canonicalFile.toURI());
//    //
//    //    Path path = Paths.get(".", "common-utils", "src", "main", "java", "com", "zel", "commonutils");
//    //
//    //    //为该文件夹注册监听, 监听新建、修改、删除事件。只能为文件夹（目录）注册监听，不能为单个文件注册监听
//    //    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
//    //
//    //    //编写事件处理
//    //    while (true){  //一直监听
//    //        //拉取一个WatchKey。当触发监听的事件时，就会产生一个WatchKey，此WatchKey封装了事件信息。
//    //        WatchKey watchKey=watchService.take();
//    //
//    //        //使用循环是因为这一个WatchKey中可能有多个文件变化了，比如Ctrl+A全选，然后删除，只触发了一个WatchKey，但有多个文件变化了
//    //        for (WatchEvent event:watchKey.pollEvents()){
//    //            System.out.println(event.context()+"发生了"+event.kind()+"事件！");
//    //            /*
//    //            watchKey.pollEvents()  获取此次WatchKey中所有变化了的文件的信息，以List（列表）形式返回，一个WatchEvent就是一个元素，封装了一个变化了的文件的信息
//    //            event.context()  获取文件名
//    //            event.kind()  获取发生的事件类型
//    //
//    //            因为只能为文件夹注册监听，如果要监听某些指定的文件，可以在增强的for循环中，先根据event.context()判断是否是指定的文件，是才处理。
//    //             */
//    //            WatchEvent.Kind kind = event.kind();
//    //            System.out.println("kind"+ kind);
//    //            // ENTRY_MODIFY
//    //            if (kind == ENTRY_DELETE) {
//    //                System.out.println("run "+ kind);
//    //                File directory2 = new File(".");
//    //                File canonicalFile2 = directory2.getCanonicalFile();
//    //                System.out.println(canonicalFile2);
//    //
//    //                //System.out.println(Paths.get(".").get());
//    //
//    //                Gif gif = new Gif();
//    //                gif.gif();
//    //            }
//    //        }
//    //
//    //        //虽然是while()循环，但WatchKey和ByteBuffer一样，使用完要重置状态，才能继续用。
//    //        //如果不重置，WatchKey使用一次过后就不能再使用，即只能监听到一次文件变化。
//    //        watchKey.reset();
//    //    }
//    //}
//}
