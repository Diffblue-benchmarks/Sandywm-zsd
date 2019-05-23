/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.loreQuestionError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.factory.AppFactory;
import com.zsd.service.LoreQuestionErrorManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-23-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoreQuestionErrorAtion extends DispatchAction {
	
	/**
	 * 导向知识点错题修改页面
	 * @author wm
	 * @date 2019-5-23 下午05:54:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goLqePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("lqePage");
	}
	
	/**
	 * 按照条件分页获取错题记录列表
	 * @author wm
	 * @date 2019-5-23 下午05:55:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getPageLqeData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreQuestionErrorManager lqem = (LoreQuestionErrorManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_ERROR_INFO);
		String errorType = CommonTools.getFinalStr("errorType", request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);
		Integer updateStatus = CommonTools.getFinalInteger("updateStatus", request);
		String opt = CommonTools.getFinalStr("opt", request);//类型(stu,admin)
		Integer addUserId = 0;
		if(opt.equals("stu")){
			addUserId = CommonTools.getLoginUserId(request);
		}
//		Integer count = lqem.getCountByOptions(addUserId, loreQuestionId, sDate, eDate, updateStatus, errorType);
		return null;
	}
}