package com.pilot.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.pilot.test.entity.model.RoleAuthorities;

public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int deleteTrash();
}
