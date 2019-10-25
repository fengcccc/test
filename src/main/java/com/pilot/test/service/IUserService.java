package com.pilot.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.pilot.test.entity.model.User;

public interface IUserService extends IService<User> {

    User getByUsername(String username);

}
