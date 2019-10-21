package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @RequestMapping("/doRegister")
    public String registerUser(User user){
        user.setState("0000");
        userMapper.insertForUser(user);
        return "success";
    }

    @RequestMapping(value = "/checkUserName",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> checkUserName(@RequestParam(name = "userName",required = false) String userName){
        User user = userMapper.findByUsername(userName);
        Map<String,Object> map = new HashMap<String,Object>();
        if(user==null)
            map.put("result","success");
        else
            map.put("result","fail");
        return map;
    }
}
