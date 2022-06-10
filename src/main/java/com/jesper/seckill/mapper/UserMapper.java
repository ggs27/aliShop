package com.jesper.seckill.mapper;

import com.jesper.seckill.bean.User;
import com.jesper.seckill.vo.RigisterVo;
import org.apache.ibatis.annotations.*;

/**
 * Created by jiangyunxiong on 2018/5/21.
 */
@Mapper
public interface UserMapper {

    @Select("select * from sk_user where id = #{id}")
    public User getById(@Param("id")long id);

    @Select("select * from sk_user where nickname = #{nickname}")
    public User getByName(@Param("nickname")String nickname);

    @Update("update sk_user set password = #{password} where id = #{id}")
    public void update(User toBeUpdate);

    @Insert("insert sk_user (id, nickname, password, salt) values (#{mobile},#{name}, #{password}, #{salt})")
    public void insert(RigisterVo rigisterVo);
}
