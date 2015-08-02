package com.xst.webform.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.xst.webform.forms.LogonForm;
import com.xst.webform.models.UserVO;
import com.xst.webform.services.LogonService;

/**
 * ログオン処理を行うアクション
 *
 */
@Controller("logonAction")
public class LogonAction extends Action {

    /**
     * ログオンのビジネスロジッククラス
     */
    @Autowired(required=true)
    @Qualifier("logonService")
    private LogonService logonService;

    /**
     * ログオン処理を行うメソッド。
     *
     * @param param LogonParam
     * @return BLogicResult
     * @throws Exception
     */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogonForm logonForm = form == null ? new LogonForm() : (LogonForm)form;
		request.setAttribute("cmmForm", logonForm.cmmForm);
		if (request.getMethod().equalsIgnoreCase("get")) {
			return mapping.findForward("logon");
		}

		// 検索条件作成
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		conditionMap.put("userId", logonForm.getUserID().getValue());
		conditionMap.put("password", logonForm.getPassword().getValue());

		UserVO uvo = logonService.logon(conditionMap);

		if (uvo == null) {
			return mapping.findForward("failure");
		}

		HttpSession session = request.getSession();
		session.setAttribute("USER_VALUE_OBJECT", uvo);

		return mapping.findForward("success");
	}
}