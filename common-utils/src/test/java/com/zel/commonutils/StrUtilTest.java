package com.zel.commonutils;

import com.zel.commonutils.client.RequestUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrUtilTest extends TestCase {

    @Test
    public void testTestFormat() {
        List<Integer> ls = new ArrayList<>();
        ls.add(3);
        ls.add(4);
        System.out.println(isSymArray(ls));

    }

    public boolean isSymArray(List<Integer> ls) {
        if (ls.size() == 1) {
            return true;
        }

        for (int i = 0; i < ls.size() / 2; i++) {
            int index = ls.size() - i - 1;
            if (ls.get(i) != ls.get(index)) {
                return false;
            }
        }
        return true;
    }
}