/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.loreRelate;

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
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreRelateManager;
import com.zsd.util.Constants;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.tools.CommonTools;

/** 
 * MyEclipse Struts
 * Creation date: 05-13-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoreRelateAction extends DispatchAction {
	
	/**
	 * 导向关联知识点页面
	 * @author wm
	 * @date 2019-5-13 下午05:12:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goLoreRelatePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("lrPage");
	}
	
	/**
	 * 根据教材获取简单知识树
	 * @author wm
	 * @date 2019-5-13 上午10:28:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward showLoreSimpleTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
		List<MyTreeNode> result = ltmj.showLoreSimpleTree(eduId,ediId);
		CommonTools.getJsonPkg(result, response);
		return null;
	}
	
	/**
	 * 显示指定知识点的关联知识点(通用版)
	 * @author wm
	 * @date 2019-5-13 上午11:18:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showRelationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer loreId = Integer.parseInt(request.getParameter("loreId"));
		List<LoreRelateInfo> lrList = lrm.listRelateInfoByOpt(loreId, 0, -1);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		LoreInfo lore = lm.getEntityById(loreId);
		if(lore != null){
			msg = "success";
			if(lrList.size() > 0){
				List<Object> list_d = new ArrayList<Object>();
				for(Iterator<LoreRelateInfo> it = lrList.iterator() ; it.hasNext();){
					LoreRelateInfo lr = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("lrId", lr.getId());
					map_d.put("rootLoreName", lr.getRootLoreInfo().getLoreName());
					list_d.add(map_d);
				}
				map.put("lrList", list_d);
			}
		}
		map.put("loreId", lore.getId());
		map.put("loreName", lore.getLoreName());
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
	    return null;
	}
	
	/**
	 * 删除一条关联记录
	 * @author wm
	 * @date 2019-5-13 上午11:56:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		Integer lrId = CommonTools.getFinalInteger("lrId", request);
		String msg = "noInfo";
		Map<String,String> map = new HashMap<String,String>();
		if(lrId > 0){
			boolean flag = lrm.deleteLoreRelate(lrId);
			if(flag){
				msg = "success";
			}else{
				msg = "error";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return  null;
	}
	
	/**
	 * 增加一条关联信息
	 * @author wm
	 * @date 2019-5-13 下午04:06:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer rootLoreId = CommonTools.getFinalInteger("rootLoreId", request);
		String msg = "error";
		Map<String,String> map = new HashMap<String,String>();
		if(loreId > 0 && rootLoreId > 0){
			if(lrm.listRelateInfoByOpt(loreId, rootLoreId, -1).size() == 0){
				lrm.addLoreRelate(loreId, rootLoreId);
				msg = "success";
			}else{
				msg = "existInfo";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return  null;
	}
}