package com.zel.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "生产者进程API接口")
@RestController
public class IndexController {

    @ApiOperation(value="查询单词计数", notes="查询单词计数", produces="application/json")
    @RequestMapping(value = "/index")
    public String index() {
        return "response";
    }
}
