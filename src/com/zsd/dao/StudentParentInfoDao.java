package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.StudentParentInfo;

/**
 * 学生家长信息DAO
 * @author zong
 * @date 2019-4-30
 */
public interface StudentParentInfoDao {
	/**
	 * 根据主键加载学生家长信息实体
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 学生家长主键值
	 * @return 学生家长信息PO
	 */
	StudentParentInfo get(Session sess,int id);
	
	/**
	 * 保存学生家长信息信息实体，新增一条学生家长信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param spInfo 保存的学生家长信息实例
	 */
	void save(Session sess,StudentParentInfo spInfo);
	
	/**
	 * 删除学生家长信息实体，删除一条学生家长信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param spInfo 删除的学生家长信息实体
	 */
	void delete(Session sess,StudentParentInfo spInfo);
	
	/**
	 * 根据主键删除学生家长信息实体，删除一条学生家长信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除学生家长信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条学生家长信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param spInfo 需要更新的学生家长信息
	 */
	void update(Session sess,StudentParentInfo spInfo);	

}
