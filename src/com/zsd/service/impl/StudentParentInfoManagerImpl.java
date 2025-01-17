package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudentParentInfoDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudentParentInfo;
import com.zsd.module.User;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudentParentInfoManagerImpl implements StudentParentInfoManager {
	StudentParentInfoDao spDao = null;
	Transaction tran = null;
	UserDao userDao=null;
	@Override
	public Integer addSpInfo(Integer upId, Integer uId) throws WEBException {
		try {
			spDao = (StudentParentInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PARENT_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User userByParentId = userDao.get(sess, upId);
			User userByStuId = userDao.get(sess, uId);
			StudentParentInfo spInfo = new StudentParentInfo(userByParentId, userByStuId);
			spDao.save(sess, spInfo);
			tran.commit();
			return spInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加家长学生绑定时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
