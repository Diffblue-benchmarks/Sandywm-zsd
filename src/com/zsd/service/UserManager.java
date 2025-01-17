package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.User;

public interface UserManager {
	/**
	 * 添加用户信息实体
	 * 
	 * @author zong
	 * @date 2019-4-29 上午10:21:35
	 * @param userAccount
	 *            用户账户
	 * @param realName
	 *            真实姓名
	 * @param password
	 *            密码
	 * @param mobile
	 *            电话号码
	 * @param lastLoginDate
	 *            最后登录时间
	 * @param lastLoginIp
	 *            最后登录Ip
	 * @param signDate
	 *            注册时间
	 * @param schoolId
	 *            学校编号
	 * @param endDate
	 *            账户到期时间
	 * @param yearSystem
	 *            学校学年制
	 * @param prov
	 *            省
	 * @param city
	 *            市
	 * @return
	 * @throws WEBException
	 */
	Integer addUser(String userAccount, String realName, String password,
			String mobile, String lastLoginDate, String lastLoginIp,
			String signDate, Integer schoolId, String endDate,
			Integer yearSystem, String prov, String city) throws WEBException;

	/**
	 * 更新用户信息实体
	 * 
	 * @author zong
	 * @date 2019-4-29 上午11:57:25
	 * @param id
	 *            用户编号
	 * @param nickName
	 *            网络昵称
	 * @param realName
	 *            真实姓名
	 * @param sex
	 *            性别
	 * @param password
	 *            密码
	 * @param email
	 *            电子邮箱
	 * @param mobile
	 *            电话号码
	 * @param portrait
	 *            头像
	 * @param birthday
	 *            出生日期
	 * @param qq
	 *            QQ
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id, String nickName, String realName,
			String sex, String password, String email, String mobile,
			String portrait, String birthday, String qq) throws WEBException;

	/**
	 * 每次登录账户是修改用户信息
	 * 
	 * @author zong
	 * @date 2019-4-29 上午11:53:23
	 * @param id
	 *            用户编号
	 * @param lastLoginDate
	 *            最后登录时间
	 * @param lastLoginIp
	 *            最后登录IP
	 * @param loginTimes
	 *            累计登录次数
	 * @param loginStatus
	 *            登录次数（50次一个周期）
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id, String lastLoginDate, String lastLoginIp,
			Integer loginTimes, Integer loginStatus) throws WEBException;
	/**
	 * 修改指定用户的账户状态,截止时间
	 * @author zong
	 * 2019-5-10下午05:15:01
	 * @param id 用户编号
	 * @param accStatus 账户状态
	 * @param endDate 截止时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id,  Integer accStatus, String endDate) throws WEBException;

	/**
	 * 根据用户账户和密码获取用户信息
	 * @author zong
	 * @date  2019-5-4 上午10:06:52
	 * @param account 用户账户
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	List<User> listInfoByAccount(String account, String password)
			throws WEBException;
	/**
	 * 根据用户账户获取用户信息
	 * @author zong
	 * @date 2019-4-28 下午05:01:48
	 * @param account   用户账户
	 * @return
	 * @throws WEBException
	 */
	List<User> listInfoByAccount(String account) throws WEBException;
	/**
	 * 根据用户名和密码判断登录信息
	 * @param account 用户名
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	boolean userLogin(String account,String password) throws WEBException;
	/**
	 * 修改指定用户的金币数,经验,知识典币,账号余额
	 * @author zong
	 * 2019-5-11下午04:21:42
	 * @param id 用户编号
	 * @param coin 金币
	 * @param exp 经验
	 * @param zsdCoin 知识典币
	 * @param accMoney 账号余额
	 * @return 
	 * @throws WEBException
	 */
	boolean updateUser(Integer id,  Integer coin, Integer exp,Integer zsdCoin,Integer accMoney) throws WEBException;
}
