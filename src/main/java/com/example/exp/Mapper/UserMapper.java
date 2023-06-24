package com.example.exp.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exp.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
