package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.req.EbookQueryReq;
import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.req.EbookSaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.EbookResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.service.EbookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
@Validated
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
    public ApiResponse list(@Valid  EbookQueryReq req) {
        PageResp<EbookResp> list = ebookService.list(req);
        ApiResponse<PageResp<EbookResp>> listApiResponse = new ApiResponse<>();
        listApiResponse.setData(list);
        return listApiResponse;
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody EbookSaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        ebookService.update(req);
        return apiResponse;
    }

    @PostMapping("/save")
    public ApiResponse save  (@RequestBody EbookSaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        ebookService.save(req);
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        ebookService.delet(id);
        return apiResponse;
    }
}
