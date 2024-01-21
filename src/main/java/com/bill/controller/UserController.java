package com.bill.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.model.Result;
import com.bill.service.UserService;
import com.bill.util.ThreadLocalUtil;
import com.bill.vo.User;

import jakarta.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 註冊
     */
    @PostMapping("register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                                   @Pattern(regexp = "^\\S{5,16}$") String password) {
        User findUser = userService.findByUsername(username);
        if (findUser == null) {
            userService.register(username, password);
            return Result.success("創建成功");
        }

        return Result.error("已有此帳號");
    }

    /**
     * 登入
     */
    @PostMapping("login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.login(username, password);
    }
    
    /**
     * 登出
     */
    @PostMapping("logout")
    public Result<String> logout(@RequestHeader("Authorization") String token) {
    	return userService.logout(token);
    }

    /**
     * 當前登入用戶資訊
     */
    @GetMapping("userInfo")
    public Result<User> userInfo() {
        Integer userId = ThreadLocalUtil.getUserId();
        User user = userService.findByUserId(userId);
        return Result.success(user);
    }

    /**
     * 更新用戶資訊
     */
    @PutMapping("update")
    public Result<String> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * 更新頭像URL地址
     */
    @PatchMapping("updateAvatar")
    public Result<String> updateAvatar(@URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 更新密碼
     */
    @PatchMapping("updatePassword")
    public Result<String> updatePassword(@RequestBody Map<String, String> map,
                                         @RequestHeader("Authorization") String token) {
        return userService.updatePassword(map, token);
    }

    @GetMapping("query")
    public List<User> query() {
        return userService.queryAll();
    }

}


