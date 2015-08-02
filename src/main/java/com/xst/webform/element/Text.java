package com.xst.webform.element;

public class Text extends AbstractInput {

	/**
	 *
	 */
	private static final long serialVersionUID = -7346168942077757006L;

	public Text() {
		this(null, null, null, true, true);
	}

	public Text(String name, String val, String lbl, boolean vis, boolean ena) {
		super(name, val, lbl, vis, ena);
	}

	@Override
	public String getInputType() {
		return "text";
	}

}
