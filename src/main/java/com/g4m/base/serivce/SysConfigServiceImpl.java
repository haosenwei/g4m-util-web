package com.g4m.base.serivce;

import java.util.List;
import java.util.Map;

import com.g4m.base.entity.SysConfig;
import com.g4m.base.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * ,null服务实现类
 * @author hsw
 *
 */
@Service
@Transactional(transactionManager = "baseTransactionManager")
public class SysConfigServiceImpl {

	/**,null实体操作类**/
	@Autowired
	private SysConfigMapper sysConfigMapper;

	/**
	 * 根据id查询,null实体
	 * @param id
	 * @return
	 */
	@Cacheable(value="sysConfig", key="#p0")
	public SysConfig findSysConfigById(Long id) {
		return sysConfigMapper.selectSysConfigById(id);
	}
	
	/**
	 * 查询所有,null实体
	 * @return
	 */
	@Cacheable(value="sysConfigAll")
	public List<SysConfig> findEntityAll() {
		return sysConfigMapper.selectSysConfigAll();
	}
	
	/**
	 * 新增,null实体
	 * @param sysConfigForm
	 */
	public void addSysConfig(SysConfig sysConfigForm) {
		sysConfigMapper.insertSysConfig(sysConfigForm);
	}

	/**
	 * 更新,null实体
	 * @param sysConfigForm
	 */
	public void modifySysConfig(SysConfig sysConfigForm) {
		sysConfigMapper.updateSysConfig(sysConfigForm);
	}

	/**
	 * 根据条件更新,null实体
	 * @param condition
	 */
	public void modifySysConfigByCondition(Map<String, Object> condition) {
		sysConfigMapper.updateSysConfigByCondition(condition);
	}

	/**
	 * 根据ids删除,null实体
	 * @param ids
	 */
	public void removeSysConfigByIds(String ids) {
		sysConfigMapper.delSysConfigByIds(ids);
	}
	
	/**
	 * 根据条件查询,null实体
	 * @param condition
	 * @return
	 */
	public List<SysConfig> findSysConfigByCondition(Map<String, Object> condition) {
		return sysConfigMapper.selectSysConfigByCondition(condition);
	}

}