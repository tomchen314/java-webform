package com.xst.webform.element;

public class UpdatePanel extends AbstractElementContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = -2973777634812619501L;

	public UpdatePanel() {
		this(null);
	}

	public UpdatePanel(String name) {
		super(name, null, null, true, true);
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		html.append("<div id=\"");
		html.append(this.getAttribute("id"));
		html.append("\">");
		html.append(super.render());
		html.append("</div>");
		AbstractElementContainer parentContainer = this._container;
		while (!(parentContainer instanceof AbstractForm)) {
			parentContainer = parentContainer.getContainer();
		}
		AbstractForm parentForm = (AbstractForm) parentContainer;
		parentForm.addUpdatePanel(this.getAttribute("id"));
		return html.toString();
	}

}
