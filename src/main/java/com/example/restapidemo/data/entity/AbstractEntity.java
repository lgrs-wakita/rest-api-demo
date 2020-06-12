package com.example.restapidemo.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * AbstractEntity
 *
 * @author Daisuke Wakita
 */
@SuppressWarnings("serial")
@Data
@MappedSuperclass
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(insertable = false, updatable = false)
	private Date createdAt;

	@Column(insertable = false, updatable = false)
	private Date updatedAt;

	@JsonIgnore
	@Column(insertable = false)
	private Boolean deleted;

}
