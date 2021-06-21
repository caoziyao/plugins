package com.zel.awesome;

import java.util.*;

public class AwesomApplication {
    public static void main(String[] args) {
        System.out.println("hhhl");
        // 根据使用时间排序 将最近打印的设备排前
        //List<Integer> bandingList = new ArrayList<>();
        //bandingList.add(12);
        //bandingList.add(12);
        //
        //Collections.sort(bandingList, new Comparator<Integer>() {
        //    @Override
        //    public int compare(Integer o1, Integer o2) {
        //        if (o1!=null && o2 !=null) {
        //            System.out.println("v");
        //            return (o2).compareTo((o1));
        //        }
        //        System.out.println("jj");
        //        return 1;
        //    }
        //});

        List<Integer> a = Arrays.asList(1, 2, 2, 3, 4, 5, 5, 6);

        List<Integer> b = new ArrayList<>();
        for (int i = 0; i <a.size(); i ++) {
            if (!b.contains(a.get(i))) {
                b.add(a.get(i));
            }
        }
        System.out.println(b);

        //Collections.sort(bandingList, new Comparator<Integer>() {
        //    @Override
        //    public int compare(QuickBanding o1, QuickBanding o2) {
        //        if (o1.getLastPrintTime()!=null && o2.getLastPrintTime()!=null) {
        //            return (o2.getLastPrintTime()).compareTo((o1.getLastPrintTime()));
        //        }
        //        return 1;
        //    }
        //});
    }
}
