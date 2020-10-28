package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("blog_user")
@Data
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private int id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("status")
    private int status;
}
