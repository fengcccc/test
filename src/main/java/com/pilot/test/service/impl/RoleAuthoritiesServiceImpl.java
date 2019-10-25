package com.pilot.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pilot.test.entity.model.RoleAuthorities;
import com.pilot.test.mapper.RoleAuthoritiesMapper;
import com.pilot.test.service.IRoleAuthoritiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleAuthoritiesServiceImpl extends ServiceImpl<RoleAuthoritiesMapper, RoleAuthorities> implements IRoleAuthoritiesService {

    @Override
    public void deleteTrash() {
        baseMapper.deleteTrash();
    }

}
