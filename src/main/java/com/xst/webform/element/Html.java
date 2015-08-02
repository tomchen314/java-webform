package com.xst.webform.element;

import org.apache.commons.lang3.StringUtils;

public class Html extends AbstractElement {

	/**
	 *
	 */
	private static final long serialVersionUID = -9077906672501740199L;

	public Html() {
		this(null, null);
	}

	public Html(String name, String lbl) {
		super(name, null, lbl, true, true);
	}

	public void setValue(String value) {
		super.setLabel(value);
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		html.append(super.getLabel());
		html.append(super.afterRender()).append(LINE_END);
		return html.toString();
	}

	public String getHtmlTemplate() {
		return StringUtils.EMPTY;
	}

}
