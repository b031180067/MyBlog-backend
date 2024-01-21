package com.bill.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bill.mapper.UserMapper;
import com.bill.model.Result;
import com.bill.service.UserService;
import com.bill.util.JwtUtil;
import com.bill.util.ThreadLocalUtil;
import com.bill.vo.User;

import cn.hutool.crypto.SecureUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用ID查詢
     */
    @Override
    public User findByUserId(Integer userId) {
        return userMapper.findByUserId(userId);
    }

    /**
     * 用帳號查詢
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 登入
     */
    @Override
    public Result<String> login(String username,String password) {
        User userByDatabase = findByUsername(username);
        if (userByDatabase == null) {
            return Result.error("帳號或密碼有誤");
        }

        // 登入成功
        if(SecureUtil.md5(password).equals(userByDatabase.getPassword())) {
            // 產生token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", userByDatabase.getUserId());
            claims.put("username", userByDatabase.getUsername());
            String token = JwtUtil.genToken(claims);
            // 將token存入redis
            ValueOperations<String, String> stringOperations = stringRedisTemplate.opsForValue();
            stringOperations.set(token, token, 365, TimeUnit.DAYS);
            return Result.success(token);
        }
        return Result.error("帳號或密碼有誤");
    }
    
    /**
     * 登出
     */
    @Override
	public Result<String> logout(String token) {
    	stringRedisTemplate.delete(token);
		return Result.success();
	}

    /**
     * 註冊
     */
    @Override
    public void register(String username, String password) {
        String md5 = SecureUtil.md5(password);
        userMapper.register(username, md5);
    }

    /**
     * 修改
     */
    @Override
    public void update(User user) {
        Integer userId = ThreadLocalUtil.getUserId();
        user.setUserId(userId);
        userMapper.update(user);
    }

    /**
     * 修改頭像
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Integer userId = ThreadLocalUtil.getUserId();
        userMapper.updateAvatar(avatarUrl, userId);
    }

    /**
     * 查全部
     */
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    /**
     * 修改密碼
     */
    @Override
    public Result<String> updatePassword(Map<String, String> map, String token) {
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        String rePassword = map.get("rePassword");

        // 檢查是否都有值
        if (!StringUtils.hasText(oldPassword) ||
            !StringUtils.hasText(newPassword) ||
            !StringUtils.hasText(rePassword)) {
            return Result.error("請確認是否都有填寫");
        }

        // 檢查新密碼第二次是否填寫一樣
        if (!newPassword.equals(rePassword)) {
            return Result.error("新密碼請填寫一致");
        }

        // 檢查舊密碼是否填寫正確
        Integer userId = ThreadLocalUtil.getUserId();
        User user = findByUserId(userId);
        if (!SecureUtil.md5(oldPassword).equals(user.getPassword())) {
            return Result.error("舊密碼填寫不正確");
        }

        // 更新
        String newPasswordMd5 = SecureUtil.md5(newPassword);
        userMapper.updatePassword(newPasswordMd5, userId);

        // 刪除redis裡的token
        stringRedisTemplate.delete(token);

        return Result.success();
    }

}
