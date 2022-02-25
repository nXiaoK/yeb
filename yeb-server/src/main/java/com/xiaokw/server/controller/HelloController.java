package com.xiaokw.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试Swaager")
@RestController
public class HelloController {

    @ApiOperation(value = "测试Hello")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @ApiOperation(value = "测试基本资料")
    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/hello";
    }
    @ApiOperation(value = "测试高级资料")
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/hello";
    }

}
