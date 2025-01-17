/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.factory.AppFactory;
import com.zsd.module.Edition;
import com.zsd.module.Education;
import com.zsd.module.GradeSubject;
import com.zsd.module.Subject;
import com.zsd.page.PageConst;
import com.zsd.service.EditionManager;
import com.zsd.service.EducationManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.SubjectManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 04-29-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CommonAction extends DispatchAction {
	
	/**
	 * 导向出版社页面
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-1 下午04:32:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goEditionPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("ediPage");
	}
	
	/**
	 * 根据条件获取出版社列表
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:52:00
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getEditionData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EditionManager em = (EditionManager) AppFactory.instance(null).getApp(Constants.WEB_EDITION_INFO);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);//-1表示全部,0：显示，1：隐藏
		List<Edition> ediList = em.listInfoByShowStatus(0, showStatus);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(ediList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Edition> it = ediList.iterator() ; it.hasNext();){
				Edition edi = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", edi.getId());
				map_d.put("ediName", edi.getEdiName());
				map_d.put("ediOrder", edi.getEdiOrder());
				if(edi.getShowStatus().equals(0)){
					map_d.put("showStatusChi", "显示");
				}else{
					map_d.put("showStatusChi", "隐藏");
				}
				map_d.put("showStatus", edi.getShowStatus());
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", ediList.size());
			map.put("code", 0);
		}else{
			msg = "暂无记录";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定出版社详情
	 * @author wm
	 * @date 2019-5-4 下午10:11:36 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEditionDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EditionManager em = (EditionManager) AppFactory.instance(null).getApp(Constants.WEB_EDITION_INFO);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		List<Edition> ediList = em.listInfoByShowStatus(ediId, -1);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(ediList.size() > 0){
			msg = "success";
			Edition edi = ediList.get(0);
			map.put("id", edi.getId());
			map.put("ediName", edi.getEdiName());
			map.put("ediOrder", edi.getEdiOrder());
			map.put("showStatus", edi.getShowStatus());
		}else{
			msg = "noInfo";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向学科页面
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-1 下午04:33:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goSubjectPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("subjectPage");
	}
	
	/**
	 * 获取学科列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubjectData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SubjectManager sm = (SubjectManager) AppFactory.instance(null).getApp(Constants.WEB_SUBJECT_INFO);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);//-1表示全部,0：显示，1：隐藏
		List<Subject> sList = sm.listInfoByDisplayStatus(showStatus);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(sList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Subject> it = sList.iterator() ; it.hasNext();){
				Subject sub = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", sub.getId());
				map_d.put("subName", sub.getSubName());
				map_d.put("subOrder", sub.getSubOrder());
				if(sub.getDisplayStatus().equals(0)){
					map_d.put("showStatusChi", "显示");
				}else{
					map_d.put("showStatusChi", "隐藏");
				}
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", sList.size());
			map.put("code", 0);
		}else{
			msg = "暂无记录";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向年级学科页面
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-1 下午04:34:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goGSubjectPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("gsPage");
	}
	
	/**
	 * 分页获取年级学科列表
	 * @author wm
	 * @date 2019-4-28 下午10:15:54 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGSubjectData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		String gName = Transcode.unescape_new("gName", request);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);//-1表示全部,0：显示，1：隐藏
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer count = gsm.getCountByOpt(gName, subId, 0, showStatus);
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<GradeSubject> gsList = gsm.listPageInfoByOpt(gName, subId, 0, showStatus, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				GradeSubject gs = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", gs.getId());
				map_d.put("gName", gs.getGradeName());
				map_d.put("subName", gs.getSubject().getSubName());
				Integer schoolType = gs.getSchoolType();
				String schoolTypeChi = "";
				if(schoolType.equals(1)){
					schoolTypeChi = "小学";
				}else if(schoolType.equals(2)){
					schoolTypeChi = "初中";
				}else if(schoolType.equals(3)){
					schoolTypeChi = "高中";
				}
				map_d.put("schoolTypeChi", schoolTypeChi);
				if(gs.getDisplayStatus().equals(0)){
					map_d.put("showStatusChi", "可见");
				}else{
					map_d.put("showStatusChi", "隐藏");
				}
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
		}else{
			msg = "暂无记录";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据年级名称获取学科列表
	 * @author wm
	 * @date 2019-5-7 上午09:57:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubjectDataByGname(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		String gName = Transcode.unescape_new("gName", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<GradeSubject> gsList = gsm.listSpecInfoByGname(gName);
		if(gsList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				GradeSubject gs = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("subId", gs.getSubject().getId());
				map_d.put("subName", gs.getSubject().getSubName());
				list_d.add(map_d);
			}
			map.put("subList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据学科编号获取年级列表
	 * @author wm
	 * @date 2019-5-7 下午05:22:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGradeDataBySubId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<GradeSubject> gsList = gsm.listSpecInfoBySubId(subId);
		if(gsList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				GradeSubject gs = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("gsId", gs.getId());
				map_d.put("gName", gs.getGradeName());
				list_d.add(map_d);
			}
			map.put("gList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定年级学科编号详情
	 * @author wm
	 * @date 2019-4-28 下午10:34:39 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGSubjectDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		SubjectManager sm = (SubjectManager) AppFactory.instance(null).getApp(Constants.WEB_SUBJECT_INFO);
		Integer gsId = CommonTools.getFinalInteger("gsId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		List<GradeSubject> gsList = gsm.listSpecInfoById(gsId);
		if(gsList.size() > 0){
			msg = "success";
			GradeSubject gs = gsList.get(0);
			map.put("id", gs.getId());
			Integer subId = gs.getSubject().getId();
			map.put("subId", subId);
			//获取所有学科列表
			List<Subject> sList = sm.listInfoByDisplayStatus(0);
			List<Object> list_sub_d = new ArrayList<Object>();
			for(Iterator<Subject> it = sList.iterator() ; it.hasNext();){
				Subject sub = it.next();
				Map<String,Object> map_sub_d = new HashMap<String,Object>();
				map_sub_d.put("subId", sub.getId());
				map_sub_d.put("subName", sub.getSubName());
				if(subId.equals(sub.getId())){
					map_sub_d.put("selStatus", true);
				}else{
					map_sub_d.put("selStatus", false);
				}
				list_sub_d.add(map_sub_d);
			}
			map.put("subList", list_sub_d);
			Integer schoolType = gs.getSchoolType();
			map.put("schoolType", schoolType);
			map.put("gName", gs.getGradeName());
			map.put("showStatus", gs.getDisplayStatus());
		}else{
			msg = "noInfo";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定学科下的学科年级列表
	 * @author wm
	 * @date 2019-4-28 下午11:16:06 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGSubjectBySubId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		List<GradeSubject> gsList = gsm.listSpecInfoBySubId(subId);
		if(gsList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				Map<String,Object> map_d = new HashMap<String,Object>();
				GradeSubject gs = it.next();
				map_d.put("gId", gs.getId());
				Integer schoolType = gs.getSchoolType();
				String schoolTypeChi = "";
				if(schoolType.equals(1)){
					schoolTypeChi = "小学:";
				}else if(schoolType.equals(2)){
					schoolTypeChi = "初中:";
				}else if(schoolType.equals(3)){
					schoolTypeChi = "高中:";
				}
				map_d.put("gName", schoolTypeChi+gs.getGradeName());
				list_d.add(map_d);
			}
			map.put("gsList", list_d);
		}else{
			msg = "noInfo";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定的年级学科信息
	 * @author wm
	 * @date 2019-4-28 下午10:31:31 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateGSubjectInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer gsId = CommonTools.getFinalInteger("gsId", request);
		String gName = Transcode.unescape_new("gName", request);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);//0：显示，1：隐藏
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);
		List<GradeSubject> gsList_1 = gsm.listSpecInfoById(gsId);
		boolean existFlag = false;
		Map<String,String> map = new HashMap<String,String>();
		if(gsList_1.size() > 0){
			GradeSubject gs = gsList_1.get(0);
			if(gName.equals(gs.getGradeName()) && subId.equals(gs.getSubject().getId()) && schoolType.equals(gs.getSchoolType())){
				//都没发生变化，不用检查
			}else{
				//有变化，需要检查
				List<GradeSubject> gsList = gsm.listSpecInfoByOpt(gName, subId, schoolType);
				if(gsList.size() > 0){
					existFlag = true;
				}
			}
		}
		if(!existFlag){
			gsm.updateGSub(gsId, gName, subId, schoolType, showStatus);
			map.put("result", "success");
		}else{
			map.put("result", "existInfo");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加年级学科信息
	 * @author Administrator
	 * @date 2019-5-5 上午09:45:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addGSubjectInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		String gName = Transcode.unescape_new("gName", request);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);
		List<GradeSubject> gsList = gsm.listSpecInfoByOpt(gName, subId, schoolType);
		Map<String,String> map = new HashMap<String,String>();
		if(gsList.size() > 0){
			map.put("result", "existInfo");
		}else{
			gsm.addGSub(gName, subId, schoolType);
			map.put("result", "success");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向教材页面
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-1 下午04:35:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goEducationPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("eduPage");
	}
	
	/**
	 * 分页获取指定条件的教材信息列表
	 * @author Administrator
	 * @date 2019-4-29 上午11:01:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEducationData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager em = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		Integer gsId = CommonTools.getFinalInteger("gsId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer count = em.getCountByOpt(ediId, gsId);
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<Education> eList = em.listPageInfoByOpt(ediId, gsId, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Education> it = eList.iterator() ; it.hasNext();){
				Education edu = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("eduId", edu.getId());
				map_d.put("ediName", edu.getEdition().getEdiName());
				GradeSubject gs = edu.getGradeSubject();
				Subject sub = gs.getSubject();
				map_d.put("subName", sub.getSubName());
				Integer schoolType = gs.getSchoolType();
				String schoolTypeChi = "";
				if(schoolType.equals(1)){
					schoolTypeChi = "小学";
				}else if(schoolType.equals(2)){
					schoolTypeChi = "初中";
				}else if(schoolType.equals(3)){
					schoolTypeChi = "高中";
				}
				map_d.put("schoolType", schoolTypeChi);
				map_d.put("gradeName", gs.getGradeName());
				Integer showStatus = edu.getDisplayStatus();
				if(showStatus.equals(0)){
					map_d.put("showStatusChi", "可见");
				}else{
					map_d.put("showStatusChi", "隐藏");
				}
				map_d.put("eduColume", edu.getEduVolume());
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
		}else{
			msg = "暂无记录";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取教材详情
	 * @author Administrator
	 * @date 2019-4-29 上午11:15:08
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEducationDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager em = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		SubjectManager sm = (SubjectManager) AppFactory.instance(null).getApp(Constants.WEB_SUBJECT_INFO);
		EditionManager edim = (EditionManager) AppFactory.instance(null).getApp(Constants.WEB_EDITION_INFO);
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		List<Education> eList = em.listSpecInfoById(eduId);
		if(eList.size() > 0){
			msg = "success";
			Education edu = eList.get(0);
			map.put("eduId", edu.getId());
			Integer subId = edu.getGradeSubject().getSubject().getId();
			map.put("subId", subId);
			//获取所有学科列表
			List<Subject> sList = sm.listInfoByDisplayStatus(0);
			List<Object> list_sub_d = new ArrayList<Object>();
			for(Iterator<Subject> it = sList.iterator() ; it.hasNext();){
				Subject sub = it.next();
				Map<String,Object> map_sub_d = new HashMap<String,Object>();
				map_sub_d.put("subId", sub.getId());
				map_sub_d.put("subName", sub.getSubName());
				if(subId.equals(sub.getId())){
					map_sub_d.put("selStatus", true);
				}else{
					map_sub_d.put("selStatus", false);
				}
				list_sub_d.add(map_sub_d);
			}
			map.put("subList", list_sub_d);
			Integer gradeId = edu.getGradeSubject().getId();
			map.put("gradeId", gradeId);
			//获取指定学科的年级学科列表
			List<GradeSubject> gsList = gsm.listSpecInfoBySubId(subId);
			if(gsList.size() > 0){
				List<Object> list_g_d = new ArrayList<Object>();
				for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
					Map<String,Object> map_g_d = new HashMap<String,Object>();
					GradeSubject gs = it.next();
					map_g_d.put("gId", gs.getId());
					Integer schoolType = gs.getSchoolType();
					String schoolTypeChi = "";
					if(schoolType.equals(1)){
						schoolTypeChi = "小学:";
					}else if(schoolType.equals(2)){
						schoolTypeChi = "初中:";
					}else if(schoolType.equals(3)){
						schoolTypeChi = "高中:";
					}
					map_g_d.put("gName", schoolTypeChi+gs.getGradeName());
					if(gradeId.equals(gs.getId())){
						map_g_d.put("selStatus", true);
					}else{
						map_g_d.put("selStatus", false);
					}
					list_g_d.add(map_g_d);
				}
				map.put("gList", list_g_d);
			}
			//获取所有出版社列表
			Integer ediId = edu.getEdition().getId();
			map.put("ediId", ediId);
			List<Edition> ediList = edim.listInfoByShowStatus(0, 0);
			if(ediList.size() > 0){
				List<Object> list_edi_d = new ArrayList<Object>();
				for(Iterator<Edition> it = ediList.iterator() ; it.hasNext();){
					Edition edi = it.next();
					Map<String,Object> map_edi_d = new HashMap<String,Object>();
					map_edi_d.put("ediId", edi.getId());
					map_edi_d.put("ediName", edi.getEdiName());
					if(ediId.equals(edi.getId())){
						map_edi_d.put("selStatus", true);
					}else{
						map_edi_d.put("selStatus", false);
					}
					list_edi_d.add(map_edi_d);
				}
				map.put("ediList", list_edi_d);
			}
			map.put("showStatus", edu.getDisplayStatus());
			map.put("eduColume", edu.getEduVolume());
			map.put("eduImg", edu.getEduImg());
		}else{
			msg = "noInfo";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定教材的信息
	 * @author Administrator
	 * @date 2019-4-29 上午11:26:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateEducationInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager em = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		Integer gradeId = CommonTools.getFinalInteger("gradeId", request);
		String eduVolume = Transcode.unescape_new("eduVolume", request);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);//0：显示，1：隐藏
		List<Education> eduList = em.listSpecInfoById(eduId);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(eduList.size() > 0){
			Education edu = eduList.get(0);
			if(edu.getEdition().getId().equals(ediId) && edu.getGradeSubject().getId().equals(gradeId) && edu.getEduVolume().equals(eduVolume)){
				//没发生变化-不检查
				msg = "success";
			}else{
				if(em.listInfoByOpt(ediId, gradeId, eduVolume).size() > 0){
					msg = "existInfo";
				}else{
					msg = "success";
				}
			}
			if(msg.equals("success")){
				em.updateEduById(eduId, gradeId, ediId, 0, showStatus, eduVolume, "");
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加教材信息
	 * @author Administrator
	 * @date 2019-5-5 上午10:44:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addEducationInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager em = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		Integer gradeId = CommonTools.getFinalInteger("gradeId", request);
		String eduVolume = Transcode.unescape_new("eduVolume", request);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		Integer eduOrder = 1;
		if(em.listInfoByOpt(ediId, gradeId, eduVolume).size() > 0){
			msg = "existInfo";
		}else{
			msg = "success";
			if(eduVolume.equals("下册")){
				eduOrder = 2;
			}
			em.addEdu(gradeId, ediId, eduOrder, eduVolume, "");
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据年级学科编号、出版社编号获取教材信息列表
	 * @author wm
	 * @date 2019-5-7 下午05:44:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEduData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager em = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		Integer gsId = CommonTools.getFinalInteger("gsId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<Education>  eList = em.listInfoByOpt(ediId, gsId);
		if(eList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Education> it = eList.iterator() ; it.hasNext();){
				Education edu = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("eduId", edu.getId());
				map_d.put("eduColume", edu.getEduVolume());
				list_d.add(map_d);
			}
			map.put("eduList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}