package com.pilot.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pilot.test.entity.model.Role;
import com.pilot.test.mapper.RoleMapper;
import com.pilot.test.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
