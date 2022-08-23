package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.service.EbookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/eBook")
public class EbookController {

    @Resource
    private EbookService ebookService;
    @RequestMapping("/hello/")
    public String hello() {
        return "hello world3";
    }

    @RequestMapping("/list/")
    public ApiResponse list() {
        List<Ebook> list = ebookService.list();
        ApiResponse<List<Ebook>> listApiResponse = new ApiResponse<>();
        listApiResponse.setData(list);
        return listApiResponse;
    }
}
