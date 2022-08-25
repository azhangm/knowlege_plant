package com.dajuancai.knowledge_plant.controller;

import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.req.CategoryQueryReq;
import com.dajuancai.knowledge_plant.req.CategorySaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.CategoryResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;
    @RequestMapping("/hello/")
    public String hello() {
        return "hello world3";
    }

    @GetMapping("/list/")
    public ApiResponse list(@Valid CategoryQueryReq req) {
        PageResp<CategoryResp> list = categoryService.list(req);
        ApiResponse<PageResp<CategoryResp>> listApiResponse = new ApiResponse<>();
        listApiResponse.setData(list);
        return listApiResponse;
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody CategorySaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        categoryService.update(req);
        return apiResponse;
    }

    @PostMapping("/save")
    public ApiResponse save  (@Valid @RequestBody CategorySaveOrUpdateReq req) {
        ApiResponse apiResponse = new ApiResponse();
        categoryService.save(req);
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        categoryService.delet(id);
        return apiResponse;
    }


}
