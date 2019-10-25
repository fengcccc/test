package com.pilot.test.service;

import com.baomidou.mybatisplus.service.IService;
import com.pilot.test.entity.model.Authorities;

import java.util.List;

public interface IAuthoritiesService extends IService<Authorities> {

    List<String> listByUserId(Integer userId);

    List<String> listByRoleIds(List<Integer> roleId);

    List<String> listByRoleId(Integer roleId);

}
