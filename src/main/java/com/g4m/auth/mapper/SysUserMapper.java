package com.g4m.auth.mapper;

import java.util.List;
import java.util.Map;

import com.g4m.auth.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * ,null数据库接口类
 * @author hsw
 *
 */
@Mapper
public interface SysUserMapper {
	/**
	 * 根据id查询,null实体
	 * @param id
	 * @return
	 */
	public SysUser selectSysUserById(Long id);

	/**
	 * 查询所有,null实体
	 * @return
	 */
	public List<SysUser> selectSysUserAll();

	/**
	 * 插入,null实体
	 * @param sysUserForm
	 */
	public void insertSysUser(SysUser sysUserForm);

	/**
	 * 更新,null实体
	 * @param sysUserForm
	 */
	public void updateSysUser(SysUser sysUserForm);

	/**
	 * 根据条件更新,null实体
	 * @param condition
	 */
	public void updateSysUserByCondition(Map<String, Object> condition);

	/**
	 * 删除,null实体
	 * @param ids
	 */
	public void delSysUserByIds(@Param("ids") String ids);

	/**
	 * 根据条件查询,null实体
	 * @param map
	 * @return
	 */
	public List<SysUser> selectSysUserByCondition(Map<String, Object> map);

	/**
	 * 分页查询,null实体
	 * @param map
	 * @return
	 */
	public List<SysUser> selectSysUserByPager(Map<String, Object> map);

	/**
	 * 分页查询,null实体条数
	 * @param condition
	 * @return
	 */
	public int selectSysUserCountByPager(Map<String, Object> condition);

	/**
	 * sql查询,null实体
	 * @param sql
	 * @return
	 */
	public List<SysUser> selectSysUserBySql(@Param("sql") String sql);
}
