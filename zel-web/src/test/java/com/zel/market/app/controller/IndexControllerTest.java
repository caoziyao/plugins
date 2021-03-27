package com.zel.market.app.controller;

import com.zel.market.BaseTest;
import org.junit.After;
import org.junit.Test;

/**
 * MockMvc实现了对Http请求的模拟，能够直接使用网络的形式，转换到Controller的调用
 *
 */

public class IndexControllerTest extends BaseTest {


    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void index() throws Exception {
        // String json="{\"author\":\"HAHAHAA\",\"title\":\"Spring\",\"url\":\"http://tengj.top/\"}";
//        mockMvc.perform(MockMvcRequestBuilders.get("/")
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content("json.getBytes()") //传json参数
////                .session(session)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
    }
}