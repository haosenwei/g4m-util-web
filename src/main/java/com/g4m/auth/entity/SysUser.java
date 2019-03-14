package com.g4m.auth.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * ,null实体类
 * 
 * @author hsw
 *
 */
public class SysUser implements Serializable {

	
	/** id */
	private Long id ;
	
	/** 姓名 */
	private String name;
	
	/**  */
	private String code;
	
	/**  */
	private String username;
	
	/**  */
	private String password;
	
	/**  */
	private String email;
	
	/**  */
	private String tel;
	
	/**  */
	private int age ;
	
	/**  */
	private Long sysOrgId ;
	
	/**  */
	private Long ownerId ;
	
	/**  */
	private Date createTime = new Date();
	
	/**  */
	private Date modifyTime = new Date();
	
	/**  */
	private Long modifyUserId ;
	
	
	/** get id ***/
	public Long getId() {
		return id;
	}
	
	/** set id ***/
	public void setId(Long id) {
		this.id = id;
	}
	
	/** get 姓名 ***/
	public String getName() {
		return name;
	}
	
	/** set 姓名 ***/
	public void setName(String name) {
		this.name = name;
	}
	
	/** get  ***/
	public String getCode() {
		return code;
	}
	
	/** set  ***/
	public void setCode(String code) {
		this.code = code;
	}
	
	/** get  ***/
	public String getUsername() {
		return username;
	}
	
	/** set  ***/
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** get  ***/
	public String getPassword() {
		return password;
	}
	
	/** set  ***/
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** get  ***/
	public String getEmail() {
		return email;
	}
	
	/** set  ***/
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** get  ***/
	public String getTel() {
		return tel;
	}
	
	/** set  ***/
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/** get  ***/
	public int getAge() {
		return age;
	}
	
	/** set  ***/
	public void setAge(int age) {
		this.age = age;
	}
	
	/** get  ***/
	public Long getSysOrgId() {
		return sysOrgId;
	}
	
	/** set  ***/
	public void setSysOrgId(Long sysOrgId) {
		this.sysOrgId = sysOrgId;
	}
	
	/** get  ***/
	public Long getOwnerId() {
		return ownerId;
	}
	
	/** set  ***/
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	/** get  ***/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	
	/** set  ***/
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/** get  ***/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}
	
	/** set  ***/
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/** get  ***/
	public Long getModifyUserId() {
		return modifyUserId;
	}
	
	/** set  ***/
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
}