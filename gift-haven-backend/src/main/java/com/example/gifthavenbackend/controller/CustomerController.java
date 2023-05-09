package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
@RestController()
@RequestMapping("/test")
public class CustomerController {

    @GetMapping()
    public Result<String> testComment() {
        return Result.success("comment successfully");
    }
}
