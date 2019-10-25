package com.pilot.test.entity.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_authorities")
public class Authorities implements Serializable {
    private static final long serialVersionUID = -6058060376656180793L;

    @TableId(type = IdType.INPUT)
    private String authority;

    private String authorityName;

    private String parentName;

    private Integer sort;

    private Date createTime;

}
