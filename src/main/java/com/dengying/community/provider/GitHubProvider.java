package com.dengying.community.provider;
import	java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.dengying.community.core.config.ServerPropertiesConfig;
import com.dengying.community.dto.AccessTokenDto;
import com.dengying.community.dto.GithubUserDto;
import com.dengying.community.utils.OkHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitHubProvider {

    @Autowired
    private ServerPropertiesConfig config;
    public String getAccessToken(String code,String state){
        AccessTokenDto atd = new AccessTokenDto();
        atd.setClient_id(config.getClientId());
        atd.setClient_secret(config.getClientSecret());
        atd.setCode(code);
        atd.setRedirect_uri("https://localhost:8888/login_callback");
        atd.setState(state);
        String post = OkHttpUtils.post("https://github.com/login/oauth/access_token", JSON.toJSONString(atd));
        System.out.println(post);
        return post;
    }
    public GithubUserDto getUser(String accessToken){
        String userStr = OkHttpUtils.get("https://api.github.com/user?access_token="+accessToken);
        GithubUserDto user = JSON.parseObject(userStr, GithubUserDto.class);
        return user;
    }
}
