package com.pilot.test.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pilot.test.entity.model.Menu;
import com.pilot.test.mapper.MenuMapper;
import com.pilot.test.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
