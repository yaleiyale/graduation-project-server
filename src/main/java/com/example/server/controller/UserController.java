package com.example.server.controller;

import com.example.server.ApiResult;
import com.example.server.entity.User;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getuserlist")
    public boolean login(String account, String password) {
        String res = userRepository.findByUserId(Integer.parseInt(account)).get(0).getUserPassword();
        System.out.println(account + "登录");
        return res.equals(password);
    }

    public ApiResult getUserList() {
        int code = 0;
        String status = "unknown error";
        User data = new User();
        try {
            data = userRepository.findByUserId(123).get(0);
            code = 300;
        } catch (Exception e) {
            status = String.valueOf(e);
        }
        //返回结果
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setStatus(status);
        apiResult.setData(data.getUserPassword());//需要返回的信息

        return apiResult;
    }


}