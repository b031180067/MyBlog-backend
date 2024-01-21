package com.bill.service;

import com.bill.model.Result;
import com.bill.vo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User findByUserId(Integer userId);

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    List<User> queryAll();

    Result<String> updatePassword(Map<String, String> map, String token);

    Result<String> login(String username, String password);

    Result<String> logout(String token);
}
