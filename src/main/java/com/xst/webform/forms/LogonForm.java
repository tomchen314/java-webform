package com.xst.webform.forms;

import com.xst.webform.element.Button;
import com.xst.webform.element.Text;

/**
 * ログオン機能で使われるActionForm
 *
 */
public class LogonForm extends BaseForm {

    /**
     * シリアルバージョンID
     */
    private static final long serialVersionUID = 5000593762515534317L;

    /**
     * ユーザID
     */
    private Text userID;

    /**
     * パスワード
     */
    private Text password;

    public Button logon;

    public LogonForm() {
    	super();
    	this.userID = new Text("userID", null, "ユーザーＩＤ", true, true);
    	this.password = new Text("password", null, "パスワード", true, true);
		this.logon = new Button("logon", null, "ログオン", true, true);
		super.cmmForm.add(this.userID.setBr(true));
		super.cmmForm.add(this.password.setBr(true));
		super.cmmForm.add(this.logon);
    }

    public Text getUserID() {
		return userID;
	}

	public void setUserID(Text userID) {
		this.userID = userID;
	}

	public Text getPassword() {
		return password;
	}

	public void setPassword(Text password) {
		this.password = password;
	}

	public Button getLogon() {
		return this.logon;
	}

	public void setLogon(Button logon) {
		this.logon = logon;
	}
}