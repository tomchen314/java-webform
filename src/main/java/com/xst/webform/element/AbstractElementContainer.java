package com.xst.webform.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractElementContainer extends AbstractElement {

    /**
	 *
	 */
	private static final long serialVersionUID = 4204535967657020548L;
	protected List<AbstractElement> _elements;
    protected Map<String, Integer> _orders;

    public AbstractElementContainer() {
    	this(null, null, null, true, true);
    }

	public AbstractElementContainer(String name, String val, String lbl,
			boolean vis, boolean ena) {
		super(name, val, lbl, vis, ena);
		this._elements = new ArrayList<AbstractElement>();
		this._orders = new HashMap<String, Integer>();
	}

	public AbstractElementContainer add(AbstractElement element) {
		return this.add(-1, element);
	}

	public AbstractElementContainer add(int order, AbstractElement element) {
		if (order >= 0) {
	        this._elements.set(order, element);
        }
		else {
			this._elements.add(element);
		}
		this._orders.put(element.getName(), order);
        element.setContainer(this);
        return this;
	}

	public AbstractElement get(String name) {
		if (this.has(name)) {
			this._elements.get(this._orders.get(name));
		}
		return this;
	}

	public boolean has(String name) {
        return this._orders.containsKey(name);
	}

	public AbstractElementContainer remove(String name) {
		if (this.has(name)) {
			this._elements.remove(this._orders.get(name));
			this._orders.remove(name);
		}
		return this;
	}

	public List<AbstractElement> getElements() {
		return this._elements;
	}

	public AbstractElementContainer clearElements() {
		this._elements.clear();
		this._orders.clear();
		return this;
	}

	protected void setValNameAndId(AbstractElement element) {
        element.setValName(this.getValName());
        element.setValId(this.getValId());
		return;
	}

	public void setGrid(boolean _grid) {
		for (AbstractElement element : this._elements) {
			element.setGrid(this._grid);
		}
	}

	public String render() {
		StringBuffer html = new StringBuffer();
		for (AbstractElement element : this._elements) {
			this.setValNameAndId(element);
			html.append(element.render());
		}
		return html.toString();
	}

	public String getHtmlTemplate() {
		return StringUtils.EMPTY;
	}

	public String renderForUpdate(String targetId) {
		StringBuffer html = new StringBuffer();
		if (targetId.equals(this.getAttribute("id"))) {
			if (this instanceof UpdatePanel) {
				StringBuffer innerHtml = new StringBuffer();
				for (AbstractElement element : this._elements) {
					this.setValNameAndId(element);
					innerHtml.append(((AbstractElement)element).render().replaceAll("\"", "\\\\\\\""));
				}
				html.append("{\"updatePanelId\" : \"");
				html.append(this.getAttribute("id"));
				html.append("\", \"updateContent\" : \"");
				html.append(innerHtml.toString());
				html.append("\"}");
				return html.toString();
			}
		}
		for (AbstractElement element : this._elements) {
			this.setValNameAndId(element);
			if (element instanceof AbstractElementContainer) {
				html.append(((AbstractElementContainer)element).renderForUpdate(targetId));
				if (html.length() > 0) {
					return html.toString();
				}
			}
		}
		return html.toString();
	}

}
