package com.xst.webform.element;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GridData implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1696550774806215901L;
	private String value;
	private String val;
	private Map<String, GridData> ds;
	public GridData() {
		this.ds = new HashMap<String, GridData>();
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public GridData getDs(String key) {try {
    	if (!this.ds.containsKey(key)) {
    		this.ds.put(key, new GridData());
    	}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return ds.get(key);
	}
	public void setDs(String key, GridData ds) {
		this.ds.put(key, ds);
	}

}
