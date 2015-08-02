package com.xst.webform.element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractElement implements ElementInterface, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1025510703257462208L;
	protected AbstractElementContainer _container;
	protected String _name;
	protected String _label;
	protected String value;
	protected boolean _visible = true;
	protected boolean _enable = true;
	protected boolean _br;
	protected Map<String, String> _attributes;
	protected String val;
	protected static String LINE_END = "";//System.getProperty("line.separator");
	protected String _valId;
	protected String _valName;
	private String _label0;
	protected boolean _grid;

	public AbstractElement() {
		this(null, null, null, true, true);
	}

	public AbstractElement(String name, String val, String lbl, boolean vis, boolean ena) {
		this._name = name;
		this._label0 = this._label = lbl == null ? "" : lbl;
		this.value = val == null ? "" : val;
		this._attributes = new HashMap<String, String>();
		this._attributes.putAll(this.getDefaultAttributes());
		this.setVisible(vis);
		this.setEnable(ena);
	}

	public void setValId(String valId) {
		this._valId = valId;
        this.setAttribute("id", this.getValId() + (StringUtils.isEmpty(this._valId) ? "" : "_") + this.getName());
	}

	public String getValId() {
		return this._valId == null ? "" : this._valId;
	}

	public void setValName(String valName) {
		this._valName = valName;
        this.setAttribute("name", this.getValName() + (StringUtils.isEmpty(this._valName) ? "" : ".")
        		+ (this._grid ? ("ds(" + this.getName() + ")") : this.getName()) + ".value");
	}

	public String getValName() {
		return this._valName == null ? "" : this._valName;
	}

	public AbstractElement setContainer(AbstractElementContainer container) {
	    this._container = container;
	    return this;
	}

	public AbstractElementContainer getContainer() {
		return this._container;
	}

	public AbstractElement setLabel(String label) {
		this._label = label;
		return this;
	}

	public String getLabel() {
		return this._label == null ? "" : this._label;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value == null ? "" : this.value;
	}

	public void setVal(String val) {
		this.val = val;
		if (StringUtils.isEmpty(val)) {
			return;
		}
		JSONObject jsonObj = JSONObject.fromObject(val);
		if (jsonObj.containsKey("l")) {
			this.setLabel(jsonObj.getString("l"));
		}
		if (jsonObj.containsKey("s")) {
			this.setVisible(jsonObj.getBoolean("s"));
		}
		if (jsonObj.containsKey("e")) {
			this.setEnable(jsonObj.getBoolean("e"));
		}
		this.setOption(jsonObj);
	}

	protected void setOption(JSONObject jsonObj) {
	}

	public Map<String, String> getAttributes() {
		return this._attributes;
	}

	public AbstractElement setAttribute(String name, String value) {
		this._attributes.put(name, value);
		return this;
	}

	public String getAttribute(String name) {
		if (!this._attributes.containsKey(name)) {
			return null;
		}
		return this._attributes.get(name) == null ? "" : this._attributes.get(name);
	}

	public String getName() {
		return this._name;
	}

	public AbstractElement setVisible(boolean visible) {
		if (this._visible != visible) {
			if (visible) {
				this.setAttribute("style", this.getAttribute("style") + "display:block;");
			}
			else {
				this.setAttribute("style", this.getAttribute("style") + "display:none;");
			}
		}
		this._visible = visible;
		return this;
	}

	public boolean isVisible() {
		return this._visible;
	}

	public AbstractElement setEnable(boolean enable) {
    	if (enable) {
    		this._attributes.remove("readonly");
    	}
    	else {
    		this.setAttribute("readonly", "readonly");
    	}
		this._enable = enable;
		return this;
	}

	public boolean isEnable() {
		return this._enable;
	}

	public AbstractElement setBr(boolean br) {
		this._br = br;
		return this;
	}

	public boolean isBr() {
		return this._br;
	}

	public void setGrid(boolean _grid) {
		this._grid = _grid;
	}

	public Map<String, String> getDefaultAttributes() {
		Map<String, String> defaultAttributs = new HashMap<String, String>();
		defaultAttributs.put("name", this.getName());
		defaultAttributs.put("id", this.getName());
		return defaultAttributs;
	}

	protected String _renderAttributes() {
		StringBuffer html = new StringBuffer();
		for (Map.Entry<String, String> entry : this._attributes.entrySet()) {
			html.append(" " + entry.getKey() + "=\"" + entry.getValue() + "\"");
		}
		return html.toString();
	}

	protected String afterRender() {
		StringBuffer html = new StringBuffer();
		JSONObject jsonObj = new JSONObject();
		if (!this._label0.equals(this._label)) {
			jsonObj.put("l", this.getLabel());
		}
		if (!this._visible) {
			jsonObj.put("s", this.isVisible());
		}
		if (!this._enable) {
			jsonObj.put("e", this.isEnable());
		}
		jsonObj = this.setValJson(jsonObj);
		if (!jsonObj.isEmpty()) {
			html.append("<input type=\"hidden\" id=\"");
			html.append(this.getAttribute("id")).append("_val");
			html.append("\" name=\"");
			html.append(this.getAttribute("name").replace(".value", ".val"));
			html.append("\" value=\"");
			html.append(jsonObj.toString().replaceAll("\"", "&quot;"));
			html.append("\"/>");
		}
		html.append(this._br ? "<br/>" : StringUtils.EMPTY);
		html.append(LINE_END);
		return html.toString();
	}

	protected JSONObject setValJson(JSONObject jsonObj) {
		return jsonObj;
	}

	public AbstractElement clone()
	{
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream objOut = null;
		ByteArrayInputStream byteIn = null;
		ObjectInputStream objIn = null;

		try {
			// 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			byteOut = new ByteArrayOutputStream();
			objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(this);

			// 将流序列化成对象
			byteIn  = new ByteArrayInputStream(byteOut.toByteArray());
			objIn = new ObjectInputStream(byteIn);
			return (AbstractElement) objIn.readObject();
		}
		catch (IOException e) {
			throw new RuntimeException("Clone Object failed in IO.",e);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found.",e);
		}
		finally {
			try {
				byteIn = null;
				byteOut = null;
				if (objOut != null) objOut.close();
				if (objIn != null) objIn.close();
			}
			catch (IOException e){
			}
		}
	}

}
