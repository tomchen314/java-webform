package com.xst.webform.forms;

import java.util.ArrayList;
import java.util.List;

import com.xst.webform.element.Button;
import com.xst.webform.element.Checkbox;
import com.xst.webform.element.DataView;
import com.xst.webform.element.Html;
import com.xst.webform.element.Text;
import com.xst.webform.element.UpdatePanel;
import com.xst.webform.element.user.SelectUserCode;
import com.xst.webform.models.UserVO;


public class UserListForm extends BaseForm {
	/**
	 *
	 */
	private static final long serialVersionUID = -945698854260277125L;
	private UpdatePanel uppnl1;
	private SelectUserCode userCode;
	private Text userName;
	private Text userDivision;
	private Button search;
	private DataView userList;
	private List<UserVO> rows = new ArrayList<UserVO>();

	public List<UserVO> getRows() {
		return rows;
	}

	public void setRows(List<UserVO> rows) {
		this.rows = rows;
	}

	public UserListForm() {
		super();
		//this.rows = new ArrayList<String>();
		this.userName =new Text();
		this.userCode = new SelectUserCode("userCode", null);
		this.userName = new Text("userName", null, "ユーザー名", true, true);
		this.userDivision = new Text("userDivision", null, "ユーザー区分", true, true);
		this.search = new Button("search", null, "検索", true, true);
		this.search.addClickEvent(null);
		this.uppnl1 = new UpdatePanel("uppnl1");
		this.userList = new DataView("userList");
		super.cmmForm.add(this.uppnl1);
		this.uppnl1.add(this.userCode.setBr(true));
		this.uppnl1.add(this.userName.setBr(true));
		this.uppnl1.add(this.userDivision.setBr(true));
		this.uppnl1.add(this.search);
		this.uppnl1.add(this.userList);
		this.userList.addToHeaderTemplate(new Checkbox("chkAll", null, null, true, true, false));
		this.userList.addToHeaderTemplate(new Html("hdrUserCode", "ユーザーコード"));
		this.userList.addToHeaderTemplate(new Html("hdrUserName", "ユーザー名"));
		this.userList.addToHeaderTemplate(new Html("hdrUserDivision", "ユーザー区分"));
		this.userList.addToItemTemplate("userCodes", new Checkbox("chkSeled", null, null, true, true, false));
		this.userList.addToItemTemplate("userCodes", new Html("userCodes", null));
		this.userList.addToItemTemplate("userNames", new Html("userNames", null));
		this.userList.addToItemTemplate("userDivisions", new Html("userDivisions", null));
	}

	public UpdatePanel getUppnl1() {
		return uppnl1;
	}

	public void setUppnl1(UpdatePanel uppnl1) {
		this.uppnl1 = uppnl1;
	}

	public SelectUserCode getUserCode() {
		return userCode;
	}

	public void setUserCode(SelectUserCode userCode) {
		this.userCode = userCode;
	}

	public Text getUserName() {
		return userName;
	}

	public void setUserName(Text userName) {
		this.userName = userName;
	}

	public Text getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(Text userDivision) {
		this.userDivision = userDivision;
	}

	public Button getSearch() {
		return search;
	}

	public void setSearch(Button search) {
		this.search = search;
	}

	public DataView getUserList() {
		return userList;
	}

	public void setUserList(DataView userList) {
		this.userList = userList;
	}
}
