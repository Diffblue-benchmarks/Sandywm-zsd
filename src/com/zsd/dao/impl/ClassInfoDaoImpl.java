package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ClassInfoDao;
import com.zsd.module.ClassInfo;

public class ClassInfoDaoImpl implements ClassInfoDao {

	@Override
	public ClassInfo get(Session sess, int id) {
		return (ClassInfo) sess.load(ClassInfo.class, id);
	}

	@Override
	public void save(Session sess, ClassInfo classInfo) {
		sess.save(classInfo);
	}

	@Override
	public void delete(Session sess, ClassInfo classInfo) {
		sess.delete(classInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, ClassInfo classInfo) {
		sess.update(classInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassInfo> findClassInfo(Session sess) {
		String hql = " from ClassInfo as ci";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassInfo> findClassInfoByOption(Session sess, Integer gradeId,
			String currentTime, Integer schoolId, String className) {
		String hql = "from ClassInfo as ci where ci.school.id = "+schoolId;
		hql += " and truncate(period_diff(date_format('"+ currentTime +"','%Y%m'),date_format(c.buildClassDate,'%Y%m'))/12,0)+1 ="+gradeId;
		hql += " and c.className = '"+ className +"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassInfo> findClassInfoById(Session sess, Integer cId) {
		String hql = "from ClassInfo as ci where ci.id = "+cId;
		return sess.createQuery(hql).list();
	}

}
