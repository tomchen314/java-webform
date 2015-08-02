package com.xst.webform.forms;

import com.xst.webform.element.CommonForm;
import com.xst.webform.element.Hidden;

import org.apache.struts.validator.ValidatorActionForm;

public class BaseForm extends ValidatorActionForm {
	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = 987111609245387238L;

	public CommonForm cmmForm = null;

	private Hidden EVENTTARGET = null;

	private Hidden EVENTARGUMENT = null;

	private String forUpdate = null;

	public BaseForm() {
		this.cmmForm = new CommonForm(null);
		this.EVENTTARGET = new Hidden("EVENTTARGET", null);
		this.EVENTARGUMENT = new Hidden("EVENTARGUMENT", null);
		this.cmmForm.add(EVENTTARGET);
		this.cmmForm.add(EVENTARGUMENT);
	}
	
	public Hidden getEVENTTARGET() {
		return this.EVENTTARGET;
	}

	public void setEVENTTARGET(Hidden eVENTTARGET) {
		this.EVENTTARGET = eVENTTARGET;
	}

	public Hidden getEVENTARGUMENT() {
		return this.EVENTARGUMENT;
	}

	public void setEVENTARGUMENT(Hidden eVENTARGUMENT) {
		this.EVENTARGUMENT = eVENTARGUMENT;
	}

	public String getForUpdate() {
		return forUpdate;
	}

	public void setForUpdate(String forUpdate) {
		this.forUpdate = forUpdate;
	}
}
