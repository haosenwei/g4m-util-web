package com.g4m.auth.serivce;

import java.util.List;
import java.util.Map;

import com.g4m.auth.entity.SysUser;
import com.g4m.auth.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * ,null服务实现类
 * @author hsw
 *
 */
@Service
@Transactional(transactionManager = "authTransactionManager")
public class SysUserServiceImpl  {

	/**,null实体操作类**/
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 根据id查询,null实体
	 * @param id
	 * @return
	 */
	@Cacheable(value="sysUser",key = "#id")
	public SysUser findSysUserById(Long id) {
		return sysUserMapper.selectSysUserById(id);
	}
	
	/**
	 * 查询所有,null实体
	 * @return
	 */
	@Cacheable(value="sysUserAll")
	public List<SysUser> findEntityAll() {
		return sysUserMapper.selectSysUserAll();
	}
	
	/**
	 * 新增,null实体
	 * @param sysUserForm
	 */
	public void addSysUser(SysUser sysUserForm) {
		sysUserMapper.insertSysUser(sysUserForm);
	}

	/**
	 * 更新,null实体
	 * @param sysUserForm
	 */
	public void modifySysUser(SysUser sysUserForm) {
		sysUserMapper.updateSysUser(sysUserForm);
	}

	/**
	 * 根据条件更新,null实体
	 * @param condition
	 */
	public void modifySysUserByCondition(Map<String, Object> condition) {
		sysUserMapper.updateSysUserByCondition(condition);
	}

	/**
	 * 根据ids删除,null实体
	 * @param ids
	 */
	public void removeSysUserByIds(String ids) {
		sysUserMapper.delSysUserByIds(ids);
	}
	
	/**
	 * 根据条件查询,null实体
	 * @param condition
	 * @return
	 */
	public List<SysUser> findSysUserByCondition(Map<String, Object> condition) {
		return sysUserMapper.selectSysUserByCondition(condition);
	}

}