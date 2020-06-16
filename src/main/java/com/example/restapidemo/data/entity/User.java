package com.example.restapidemo.data.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * User
 *
 * @author Daisuke Wakita
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Where(clause = "deleted <> 1")
public class User extends AbstractEntity {

	private static final long serialVersionUID = -4273823028347170145L;

	private String email;

	@JsonIgnore
	private String password;

}
