package com.bill.mapper;

import com.bill.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> queryAll();

    User findByUserId(Integer userId);

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl, Integer userId);

    void updatePassword(String password, Integer userId);

}
