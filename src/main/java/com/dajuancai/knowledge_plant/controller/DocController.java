package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.req.DocQueryReq;
import com.dajuancai.knowledge_plant.req.DocSaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.DocResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.service.DocService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    @RequestMapping("/hello/")
    public String hello() {
        return "hello world3";
    }

    @GetMapping("/list/")
    public ApiResponse list(@Valid DocQueryReq req) {
        PageResp<DocResp> list = docService.list(req);
        ApiResponse<PageResp<DocResp>> listApiResponse = new ApiResponse<>();
        listApiResponse.setData(list);
        return listApiResponse;
    }

        @PutMapping("/update/")
    public ApiResponse update(@Valid @RequestBody DocSaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        docService.update(req);
        return apiResponse;
    }

    @PostMapping("/save/")
    public ApiResponse save  (@Valid @RequestBody DocSaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        docService.save(req);
        return apiResponse;
    }

    @DeleteMapping("/delete/{ids}")
    public ApiResponse delete(@PathVariable String ids) {
        ApiResponse apiResponse = new ApiResponse();
        docService.delet(ids);
        return apiResponse;
    }

    @GetMapping("/selectAll/")
    public ApiResponse allList( ) {
        ApiResponse objectApiResponse = new ApiResponse();
        List<DocResp> docResps = docService.allList();
        objectApiResponse.setData(docResps);
        return objectApiResponse;
    }


}
