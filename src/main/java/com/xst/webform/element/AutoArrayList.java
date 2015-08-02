package com.xst.webform.element;

import java.util.ArrayList;

import com.xst.webform.element.GridData;

public class AutoArrayList extends ArrayList<GridData> {

/**
	 *
	 */
	private static final long serialVersionUID = -6294829796495891654L;

    public AutoArrayList() {
    }

    public GridData get(int index) {
        try {
            while (index >= size()) {
                add(new GridData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.get(index);
    }
}
