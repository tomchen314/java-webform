package com.xst.webform.element;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractForm extends AbstractElementContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = -6228551913010688541L;
	private String _action;
	private List<String> _updatePanels;

	public AbstractForm(String action) {
		super(null, null, null, true, true);
		this._action = action == null ? StringUtils.EMPTY : action + ".do";
		this._updatePanels = new ArrayList<String>();
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		html.append("<form id=\"cmmForm\" name=\"cmmForm\" action=\"");
		html.append(this._action);
		html.append("\" method=\"post\">");
		html.append(super.render());
		html.append("</form>").append(LINE_END);
		html.append("<script>Array.prototype.push.apply(updatePanelIds, [\"\"");
		for (String updatePanelNm : this._updatePanels) {
			html.append(",\"").append(updatePanelNm).append("\"");
		}
		html.append("]);</script>").append(LINE_END);
		return html.toString();
	}

	public AbstractForm addUpdatePanel(String panelName) {
		this._updatePanels.add(panelName);
		return this;
	}

}
