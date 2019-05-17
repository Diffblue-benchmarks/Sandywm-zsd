package com.zsd.service;

import java.sql.Timestamp;
import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetQueInfo;

public interface BuffetQueInfoManager {

	/**
	 * 增加自助餐题库
	 * @author wm
	 * @date 2019-5-17 下午07:06:42
	 * @param btId 巴菲特基础类型
	 * @param loreId 知识点编号
	 * @param num 题号
	 * @param title 标题
	 * @param subject 题干
	 * @param answer 答案
	 * @param lexId 关联词库编号
	 * @param tipsId 提示编号
	 * @param resolution 解析
	 * @param queType 题干类型
	 * @param order 排序号
	 * @param answerA
	 * @param answerB
	 * @param answerC
	 * @param answerD
	 * @param answerE
	 * @param answerF
	 * @param operateUserName
	 * @param operateDate
	 * @return
	 * @throws WEBException
	 */
	Integer addBQ(Integer btId,Integer loreId,Integer num,String title,String subject,String answer,
			Integer lexId,Integer tipsId,String resolution,String queType,Integer order,
			String answerA,String answerB,String answerC,String answerD,String answerE,String answerF,
			String operateUserName,String operateDate) throws WEBException;
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-17 下午07:08:06
	 * @param buffetId 自助餐主键编号
	 * @return
	 * @throws WEBException
	 */
	BuffetQueInfo getEntityById(Integer buffetId) throws WEBException;
	
	/**
	 * 根据知识点名称、自助餐基础类型、开启状态获取自助餐题库列表
	 * @author wm
	 * @date 2019-5-17 下午07:08:26
	 * @param loreId 知识点编号
	 * @param buffetType 自助餐基础类型（0表示全部）
	 * @param inUse 开启状态（-1:表示全部，0：有效，1：无效）
	 * @return
	 * @throws WEBException
	 */
	List<BuffetQueInfo> listInfoByOpt(Integer loreId,Integer buffetType,Integer inUse)throws WEBException;
	
	/**
	 * 分页获取指定知识点目录下的自助餐题库列表
	 * @author wm
	 * @date 2019-5-17 下午07:10:15
	 * @param loreId 知识点目录编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<BuffetQueInfo> listPageInfoByLoreId(Integer loreId,int pageNo, int pageSize)throws WEBException;
	
	/**
	 * 获取指定知识点目录下的自助餐题库记录条数
	 * @author wm
	 * @date 2019-5-17 下午07:10:41
	 * @param loreId 知识点目录编号
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByLoreId(Integer loreId)throws WEBException;
	
	/**
	 * 修改自助餐题库有/无效状态
	 * @author wm
	 * @date 2019-5-17 下午07:11:09
	 * @param buffetId 自助餐编号
	 * @param inUse 开启状态（0：有效，1：无效）
	 * @param operateUserName
	 * @param operateDate
	 * @return
	 * @throws WEBException
	 */
	boolean updateInUseStatusById(Integer buffetId,Integer inUse,String operateUserName,Timestamp operateDate)throws WEBException;
	
	/**
	 * 获取指定知识点编号和指定基础类型下的最后一个巴菲特题的num值
	 * @author wm
	 * @date 2019-5-17 下午07:06:14
	 * @param loreId 知识点编号
	 * @param basicBuffetTypeId 基础类型编号
	 * @return 最后一个巴菲特题的num值
	 * @throws WEBException
	 */
	Integer getLastCountByOpt(Integer loreId,Integer basicBuffetTypeId)throws WEBException;
}
