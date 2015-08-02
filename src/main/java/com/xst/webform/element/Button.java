package com.xst.webform.element;

import org.apache.commons.lang3.StringUtils;

public class Button extends AbstractElement {

	/**
	 *
	 */
	private static final long serialVersionUID = -1928511055175819228L;

	public Button() {
		this(null, null, null, true, true);
	}

	public Button(String name, String val, String lbl, boolean vis, boolean ena) {
		super(name, val, lbl, vis, ena);
	}

	public String getHtmlTemplate() {
		return "<button" + this._renderAttributes() + ">%s</button>";
	}

	public String render() {
		if (StringUtils.isEmpty(this.getAttribute("value")) && StringUtils.isNotEmpty(this.getValue())) {
            this.setAttribute("value", this.getValue());
        }
        return String.format(this.getHtmlTemplate(), this.getLabel()) + super.afterRender();
	}

    public Button addClickEvent(String params) {
    	params = params == null ? StringUtils.EMPTY : params;
		this.setAttribute("onclick", "__doPostBack(this, '" + params + "');return false;");
    	return this;
    }

    public Button removeClickEvent() {
		this._attributes.remove("onclick");
    	return this;
    }

}
