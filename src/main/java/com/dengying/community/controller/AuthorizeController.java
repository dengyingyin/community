package com.dengying.community.controller;
import	java.util.jar.Attributes.Name;

import com.dengying.community.dto.GithubUserDto;
import com.dengying.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider hubProvider;
    @RequestMapping("/login_callback")
    @ResponseBody
    public String callback(String code,String state){
        String atRep = hubProvider.getAccessToken(code,state);
        String accessToken = atRep.split("&")[0].split("=")[1];
        GithubUserDto user = hubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return null;
    }
}
