package com.example.restapidemo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * AbstractRepository
 *
 * @author Daisuke Wakita
 */
@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long> {

	List<T> findByOrderByUpdatedAtDesc();

}