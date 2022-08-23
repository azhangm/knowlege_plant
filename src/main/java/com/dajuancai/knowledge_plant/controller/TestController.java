package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.pojo.Test;
import com.dajuancai.knowledge_plant.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;
    @RequestMapping("/hello/")
    public String hello() {
        return "hello world3";
    }

    @RequestMapping("/list/")
    public List<Test> list() {
        return testService.list() ;
    }
}
