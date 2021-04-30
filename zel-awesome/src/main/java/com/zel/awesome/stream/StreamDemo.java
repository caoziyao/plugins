package com.zel.awesome.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        String ids = "1,2,3,4,5,a";

        List<Integer> ints = new ArrayList<>();

        // 使用流遍历操作
        Arrays.stream(ids.split(",")).forEach((item) -> {
            System.out.println(item);
            if (StringUtils.isNotBlank(item) && StringUtils.isNumeric(item)) {
                ints.add(Integer.parseInt(item));
            }
        });
        System.out.println(ints);
        ////int limit = countStep(list.size());
        ////方法一：使用流遍历操作
        //List<List<Integer>> mglist = new ArrayList<>();
        //Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
        //    mglist.add(list.stream().skip(i * MAX_SEND).limit(MAX_SEND).collect(Collectors.toList()));
        //});
    }
}
