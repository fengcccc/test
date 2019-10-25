package com.pilot.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.pilot.test.entity.model.RoleAuthorities;

public interface IRoleAuthoritiesService extends IService<RoleAuthorities> {

    void deleteTrash();
}

