package com.dajuancai.knowledge_plant.controller;

import com.alibaba.fastjson.JSONObject;

import com.dajuancai.knowledge_plant.commen.ApiResponse;
import com.dajuancai.knowledge_plant.req.UserLoginReq;
import com.dajuancai.knowledge_plant.req.UserQueryReq;
import com.dajuancai.knowledge_plant.req.UserResetPasswordReq;
import com.dajuancai.knowledge_plant.req.UserSaveReq;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.resp.UserLoginResp;
import com.dajuancai.knowledge_plant.resp.UserQueryResp;
import com.dajuancai.knowledge_plant.service.UserService;
import com.dajuancai.knowledge_plant.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;


    @GetMapping("/list")
    public ApiResponse list(@Valid UserQueryReq req) {
        ApiResponse<PageResp<UserQueryResp>> resp = new ApiResponse<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setData(list);
        return resp;
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        ApiResponse resp = new ApiResponse<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        ApiResponse resp = new ApiResponse<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/reset-password")
    public ApiResponse resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        ApiResponse resp = new ApiResponse<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        ApiResponse<UserLoginResp> resp = new ApiResponse<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        userLoginResp.setToken(token.toString());

        resp.setData(userLoginResp);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public ApiResponse logout(@PathVariable String token) {
        ApiResponse resp = new ApiResponse<>();
        LOG.info("从redis中删除token: {}", token);
        return resp;
    }
}
