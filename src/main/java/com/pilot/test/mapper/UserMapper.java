package com.pilot.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pilot.test.entity.model.User;

public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
}
