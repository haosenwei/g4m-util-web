package com.g4m.base.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * ,null实体类
 * 
 * @author hsw
 *
 */
public class SysConfig implements Serializable {

	
	/** 主键 */
	private Long id ;
	
	/** 名称 */
	private String name;
	
	/** 描述 */
	private String description;
	
	/** 值 */
	private String val;
	
	/** 类型 */
	private String type;
	
	
	/** get 主键 ***/
	public Long getId() {
		return id;
	}
	
	/** set 主键 ***/
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 名称 ***/
	public String getName() {
		return name;
	}
	
	/** set 名称 ***/
	public void setName(String name) {
		this.name = name;
	}
	
	/** get 描述 ***/
	public String getDescription() {
		return description;
	}
	
	/** set 描述 ***/
	public void setDescription(String description) {
		this.description = description;
	}
	
	/** get 值 ***/
	public String getVal() {
		return val;
	}
	
	/** set 值 ***/
	public void setVal(String val) {
		this.val = val;
	}
	
	/** get 类型 ***/
	public String getType() {
		return type;
	}
	
	/** set 类型 ***/
	public void setType(String type) {
		this.type = type;
	}
	
}