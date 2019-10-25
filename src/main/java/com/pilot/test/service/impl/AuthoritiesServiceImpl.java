package com.pilot.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pilot.test.entity.model.Authorities;
import com.pilot.test.mapper.AuthoritiesMapper;
import com.pilot.test.service.IAuthoritiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {


    @Override
    public List<String> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public List<String> listByRoleIds(List<Integer> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleId(roleIds);
    }

    @Override
    public List<String> listByRoleId(Integer roleId) {
        List<Integer> roleIds = new ArrayList<>();
        if (roleIds != null) {
            roleIds.add(roleId);
        }
        return listByRoleIds(roleIds);
    }
}
