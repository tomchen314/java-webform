package com.xst.webform.element;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

public class Checkbox extends AbstractElement {

	/**
	 *
	 */
	private static final long serialVersionUID = 703251130589230024L;
	private String _cValue;
	private boolean _check;

	public Checkbox() {
		this(null, null, null, true, true, false);
	}

	public Checkbox(String name, String val, String lbl, boolean vis,
			boolean ena, boolean chk) {
		super(name, null, lbl, vis, ena);
		this._cValue = val == null ? "" : val;
		this.setCheck(chk);
		this.setAttribute("type", "checkbox");
	}

	public void setValue(String value) {
		this.setCheck(this._cValue.equals(value));
	}

	public AbstractElement setCValue(String cValue) {
		this._cValue = cValue;
		return this;
	}

	public String getCValue() {
		return this._cValue == null ? "" : this._cValue;
	}

	public AbstractElement setCheck(boolean check) {
    	if (check) {
    		super.value = this._cValue;
    		this.setAttribute("checked", "checked");
    	}
    	else {
    		super.value = StringUtils.EMPTY;
    		this._attributes.remove("checked");
    	}
		this._check = check;
		return this;
	}

	public boolean isCheck() {
		return this._check;
	}

	protected void setOption(JSONObject jsonObj) {
		if (jsonObj.containsKey("c")) {
			this.setCheck(jsonObj.getBoolean("c"));
		}
		if (jsonObj.containsKey("v")) {
			this.setCValue(jsonObj.getString("v"));
		}
	}

	protected JSONObject setValJson(JSONObject jsonObj) {
		if (!this._enable && this._check) {
			jsonObj.put("c", this.isCheck());
		}
		jsonObj.put("v", this.getValue());
		return jsonObj;
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		html.append(String.format(this.getHtmlTemplate(), this.getCValue(), this.getLabel()));
		html.append(this.afterRender());
		return html.toString();
	}

	public String getHtmlTemplate() {
		return "<label><input " + this._renderAttributes() + " value=\"%s\"/>%s</label>";
	}

}
