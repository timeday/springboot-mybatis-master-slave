package com.msql.dynamicdatasource.mapper;

import com.msql.dynamicdatasource.modal.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao {
    User select(@Param("id") long id);

    Integer update(User user);

    Integer insert(User user);

    Integer delete(long user);

    List<User> getAllUser();
}
