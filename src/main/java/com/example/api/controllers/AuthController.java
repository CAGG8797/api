package com.example.api.controllers;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("security")
public class AuthController {

    @GetMapping("token/info")
    public Map<String, Object> get(OAuth2Authentication authentication){
        Map<String, Object> result = new HashMap<>();
        result.put("user", authentication.getPrincipal());
        return result;
    }

}
