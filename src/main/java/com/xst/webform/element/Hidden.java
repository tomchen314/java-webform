package com.xst.webform.element;

public class Hidden extends AbstractInput {

	/**
	 *
	 */
	private static final long serialVersionUID = -6724501683356591971L;

	public Hidden() {
		this(null, null);
	}

	public Hidden(String name, String val) {
		super(name, val, null, true, true);
	}

	public String getInputType() {
		return "hidden";
	}

}
