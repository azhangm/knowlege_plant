package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.req.EbookReq;
import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.resp.EbookResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/list/")
    public ApiResponse list(EbookReq req) {
        PageResp<EbookResp> list = ebookService.list(req);
        ApiResponse<PageResp<EbookResp>> listApiResponse = new ApiResponse<>();
        listApiResponse.setData(list);
        return listApiResponse;
    }


}
