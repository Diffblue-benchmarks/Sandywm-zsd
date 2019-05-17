package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LoreRelateInfo;

public interface LoreRelateManager {

	/**
	 * 增加知识点关联信息
	 * @author wm
	 * @date 2019-5-7 下午11:01:19 
	 * @param loreId 主知识点
	 * @param rootLoreId 关联知识点
	 * @return
	 * @throws WEBException
	 */
	Integer addLoreRelate(Integer loreId,Integer rootLoreId) throws WEBException;
	
	/**
	 * 删除关联知识点信息
	 * @author wm
	 * @date 2019-5-7 下午11:01:46 
	 * @param id 主键
	 * @return
	 * @throws WEBException
	 */
	boolean deleteLoreRelate(Integer id)throws WEBException;
	
	/**
	 * 修改指定关联知识点表信息
	 * @author wm
	 * @date 2019-5-7 下午11:02:00 
	 * @param id 主键
	 * @param loreId 主知识点
	 * @param rootLoreId 关联知识点
	 * @return
	 * @throws WEBException
	 */
	boolean updateLoreRelate(Integer id,Integer loreId,Integer rootLoreId)throws WEBException;
	
	/**
	 * 根据主知识点和关联知识点编号获取关联知识点信息列表（学生学习的时候需要loreInUse为开启状态）
	 * @author wm
	 * @date 2019-5-7 下午11:03:01 
	 * @param loreId 知识点编号(0表示全部)
	 * @param rootLoreId 子知识点编号(0表示全部)
	 * @param loreInUse 开启状态（-1为全部）
	 * @param  orderOpt 子知识点编码排序状态（asc,desc）""表示无
	 * @return
	 * @throws WEBException
	 */
	List<LoreRelateInfo> listRelateInfoByOpt(Integer loreId,Integer rootLoreId,Integer loreInUse,String orderOpt)throws WEBException;
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-13 上午11:58:14
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	LoreRelateInfo getEntityById(Integer id)throws WEBException;
}
