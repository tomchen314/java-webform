package com.xst.webform.element;

import org.apache.commons.lang3.StringUtils;


public abstract class AbstractUserContainer extends AbstractElementContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = -7106623871942235488L;

	public AbstractUserContainer() {
		this(null, null);
	}

	public AbstractUserContainer(String name, String lbl) {
		super(name, null, lbl, true, true);
	}

//	public AbstractElementContainer add(int order, AbstractElement element) {
//		super.add(order, element);
//        element.setValName(this.getValName() + (StringUtils.isEmpty(this._valName) ? "" : ".") + this.getName());
//        element.setValId(this.getValId() + (StringUtils.isEmpty(this._valName) ? "" : "_") + this.getName());
//        return this;
//	}

	protected void setValNameAndId(AbstractElement element) {
        element.setValName(this.getValName() + (StringUtils.isEmpty(this._valName) ? "" : ".") + this.getName());
        element.setValId(this.getValId() + (StringUtils.isEmpty(this._valName) ? "" : "_") + this.getName());
		return;
	}

}
