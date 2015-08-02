package com.xst.webform.element.user;

import com.xst.webform.element.AbstractUserContainer;
import com.xst.webform.element.Text;

public class SelectUserCode extends AbstractUserContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = -1666110822415781569L;
	private Text userCode;

	public SelectUserCode() {
		this(null, null);
	}

	public SelectUserCode(String name, String lbl) {
		super(name, lbl);
		this.userCode = new Text("userCode", null, "ユーザーコード", true, true);
		this.add(this.userCode.setBr(true));
	}

	public Text getUserCode() {
		return userCode;
	}

	public void setUserCode(Text userCode) {
		this.userCode = userCode;
	}

}
