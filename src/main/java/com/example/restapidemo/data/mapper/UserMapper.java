package com.example.restapidemo.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.restapidemo.data.entity.User;

/**
 * UserMapper
 *
 * @author Daisuke Wakita
 */
@Mapper
public interface UserMapper {

	List<User> search(UserSearchCondition condition);

}
