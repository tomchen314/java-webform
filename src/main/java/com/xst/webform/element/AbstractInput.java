package com.xst.webform.element;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractInput extends AbstractElement {

	/**
	 *
	 */
	private static final long serialVersionUID = -2186691755415317366L;

	public AbstractInput() {
		this(null, null, null, true, true);
	}

	public AbstractInput(String name, String val, String lbl, boolean vis,
			boolean ena) {
		super(name, val, lbl, vis, ena);
	}

	abstract public String getInputType();

    public Map<String, String> getDefaultAttributes() {
		Map<String, String> defaultAttributs = new HashMap<String, String>();
		defaultAttributs.putAll(super.getDefaultAttributes());
		defaultAttributs.put("type", this.getInputType());
		return defaultAttributs;
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		if (StringUtils.isNotEmpty(this.getLabel())) {
			html.append(String.format("<label for=\"%s\">%s:</label>", this.getAttribute("id"), this.getLabel()));
		}
		html.append(String.format(this.getHtmlTemplate(), this.getValue()));
		html.append(super.afterRender()).append(LINE_END);
		return html.toString();
	}

	public String getHtmlTemplate() {
		return "<input" + this._renderAttributes() + " value=\"%s\"/>";
	}

}
