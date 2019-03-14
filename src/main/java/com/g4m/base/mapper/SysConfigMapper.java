package com.g4m.base.mapper;

import java.util.List;
import java.util.Map;

import com.g4m.base.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * ,null数据库接口类
 * @author hsw
 *
 */
@Mapper
public interface SysConfigMapper {
	/**
	 * 根据id查询,null实体
	 * @param id
	 * @return
	 */
	public SysConfig selectSysConfigById(Long id);

	/**
	 * 查询所有,null实体
	 * @return
	 */
	public List<SysConfig> selectSysConfigAll();

	/**
	 * 插入,null实体
	 * @param sysConfigForm
	 */
	public void insertSysConfig(SysConfig sysConfigForm);

	/**
	 * 更新,null实体
	 * @param sysConfigForm
	 */
	public void updateSysConfig(SysConfig sysConfigForm);

	/**
	 * 根据条件更新,null实体
	 * @param condition
	 */
	public void updateSysConfigByCondition(Map<String, Object> condition);

	/**
	 * 删除,null实体
	 * @param ids
	 */
	public void delSysConfigByIds(@Param("ids")String ids);

	/**
	 * 根据条件查询,null实体
	 * @param map
	 * @return
	 */
	public List<SysConfig> selectSysConfigByCondition(Map<String, Object> map);

	/**
	 * 分页查询,null实体
	 * @param map
	 * @return
	 */
	public List<SysConfig> selectSysConfigByPager(Map<String, Object> map);

	/**
	 * 分页查询,null实体条数
	 * @param condition
	 * @return
	 */
	public int selectSysConfigCountByPager(Map<String, Object> condition);

	/**
	 * sql查询,null实体
	 * @param sql
	 * @return
	 */
	public List<SysConfig> selectSysConfigBySql(@Param("sql")String sql);
}
