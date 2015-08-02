package com.xst.webform.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.xst.webform.forms.UserListForm;
import com.xst.webform.models.UserVO;
import com.xst.webform.services.LogonService;

@Controller("userListAction")
public class UserListAction extends Action {
	/**
     * ログオンのビジネスロジッククラス
     */
    @Autowired(required=true)
    @Qualifier("logonService")
    private LogonService logonService;

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserListForm userListForm = form == null ? new UserListForm() : (UserListForm)form;
		if (StringUtils.isNotEmpty(userListForm.getForUpdate())){
			UserVO searchParam = new UserVO();
			searchParam.setUserCode(userListForm.getUserCode().getUserCode().getValue());
			searchParam.setUserName(userListForm.getUserName().getValue());
			searchParam.setUserDivision(userListForm.getUserDivision().getValue());
			List<UserVO> uvos = logonService.getUserList(searchParam);
			List<Map<String, String>> dataSource = new ArrayList<Map<String, String>>();
			for (int i = 0; i < uvos.size(); i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("userCodes", uvos.get(i).getUserCode());
				item.put("userNames", uvos.get(i).getUserName());
				item.put("userDivisions", uvos.get(i).getUserDivision());
				dataSource.add(item);
			}
			userListForm.getUserList().setDataSource(dataSource);

			//设置生成文件的类型和编码方式
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			// 出力
			PrintWriter returnValue=response.getWriter();
			String output = userListForm.cmmForm.renderForUpdate(userListForm.getForUpdate());
			returnValue.print(output);
			returnValue.flush();
			returnValue.close();
			return null;
		}
		else {
			request.setAttribute("cmmForm", userListForm.cmmForm);
			return mapping.findForward("success");
		}
	}

}
