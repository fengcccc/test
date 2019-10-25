package com.pilot.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pilot.test.entity.model.User;
import com.pilot.test.mapper.UserMapper;
import com.pilot.test.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

}
