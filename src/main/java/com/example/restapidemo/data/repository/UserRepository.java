package com.example.restapidemo.data.repository;

import com.example.restapidemo.data.entity.User;

/**
 * UserRepository
 *
 * @author Daisuke Wakita
 */
public interface UserRepository extends AbstractRepository<User> {

	User findByEmail(String email);

}
