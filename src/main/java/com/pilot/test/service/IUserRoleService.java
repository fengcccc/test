package com.pilot.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.pilot.test.entity.model.UserRole;

public interface IUserRoleService extends IService<UserRole> {

    Integer[] getRoleIds(Integer userId);
}
