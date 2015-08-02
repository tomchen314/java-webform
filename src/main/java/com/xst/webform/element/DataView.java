package com.xst.webform.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DataView extends AbstractElementContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = -3577812150877468825L;

	private List<Map<String, String>> dataSource;

	private List<Panel> headerTemplate;

	private List<Panel> footerTemplate;

	private List<Panel> itemTemplate;

	private List<GridData> rows;

	private List<List<Panel>> datas;

	private Map<String, String> itemNames;

	private int itemsSize;

	public DataView(String name) {
		super(name, null, null, true, true);
		this.headerTemplate = new ArrayList<Panel>();
		this.footerTemplate = new ArrayList<Panel>();
		this.itemTemplate = new ArrayList<Panel>();
		this.rows = new AutoArrayList();
		this.itemNames = new HashMap<String, String>();
	}

	protected void setValNameAndId(AbstractElement element, int idx) {
        element.setValName(this.getValName() + (StringUtils.isEmpty(this._valName) ? "" : ".") + this.getName() + ".rows[" + idx + "]");
        element.setValId(this.getValId() + (StringUtils.isEmpty(this._valName) ? "" : "_") + this.getName() + "_rows[" + idx + "]");
		return;
	}

	public List<Map<String, String>> getDataSource() {
		return dataSource;
	}

	public void setDataSource(List<Map<String, String>> dataSource) {
		this.dataSource = dataSource;
		if (this.dataSource != null) {
			this.itemsSize = this.dataSource.size();
			this.datas = new ArrayList<List<Panel>>();
			for (int i = 0; i < this.itemsSize; i++) {
				Map<String, String> data = this.dataSource.get(i);
				List<Panel> pnls = new ArrayList<Panel>();
				this.datas.add(pnls);
				for (int j = 0; j < this.itemTemplate.size(); j++) {
					Panel pnl = (Panel) this.itemTemplate.get(j).clone();
					pnls.add(pnl);
					for (AbstractElement itemElement : pnl.getElements()) {
						itemElement.setValue(data.get(this.itemNames.get(itemElement.getName())));
					}
				}
			}
		}
	}

	public List<GridData> getRows() {
		return rows;
	}

	public void setRows(List<GridData> rows) {
		this.rows = rows;
		if (this.rows != null) {
			this.itemsSize = this.rows.size();
			this.datas = new ArrayList<List<Panel>>();
			for (int i = 0; i < this.itemsSize; i++) {
				List<Panel> pnls = new ArrayList<Panel>();
				this.datas.add(pnls);
				for (int j = 0; j < this.itemTemplate.size(); j++) {
					GridData data = this.rows.get(i);
					Panel pnl = (Panel) this.itemTemplate.get(j).clone();
					pnls.add(pnl);
					for (AbstractElement itemElement : pnl.getElements()) {
						itemElement.setValue(data.getDs(itemElement.getName()).getValue());
						itemElement.setVal(data.getDs(itemElement.getName()).getVal());
					}
				}
			}
		}
	}

	private DataView addTemplateItem(List<Panel> template, AbstractElement templateElement) {
		Panel pnl = new Panel();
		this.add(pnl);
		pnl.add(templateElement);
		template.add(pnl);
		return this;
	}
	public DataView addToHeaderTemplate(AbstractElement templateElement) {
		return this.addTemplateItem(this.headerTemplate, templateElement);
	}

	public DataView addToFooterTemplate(AbstractElement templateElement) {
		return this.addTemplateItem(this.footerTemplate, templateElement);
	}

	public DataView addToItemTemplate(String keyName, AbstractElement templateElement) {
		this.itemNames.put(templateElement.getName(), keyName);
		templateElement.setGrid(true);
		return this.addTemplateItem(this.itemTemplate, templateElement);
	}

	public int getItemsSize() {
		return this.itemsSize;
	}

	public String render() {
		return this.getHtmlTemplate();
	}

	public String getHtmlTemplate() {
		StringBuffer html = new StringBuffer();
		html.append("<table>").append(LINE_END);
		if (this.headerTemplate != null && this.headerTemplate.size() > 0) {
			html.append("<tr>").append(LINE_END);
			for (Panel item : this.headerTemplate) {
				html.append("<th>").append(LINE_END);
				html.append(item.render()).append(LINE_END);
				html.append("</th>").append(LINE_END);
			}
			html.append("</tr>").append(LINE_END);
		}
		for (int i = 0; i < this.itemsSize; i++) {
			html.append("<tr>").append(LINE_END);
			List<Panel> pnls = this.datas.get(i);
			for (Panel item : pnls) {
				this.setValNameAndId(item, i);
				html.append("<td>").append(LINE_END);
				html.append(item.render()).append(LINE_END);
				html.append("</td>").append(LINE_END);
			}
			html.append("</tr>").append(LINE_END);
		}
		if (this.footerTemplate != null && this.footerTemplate.size() > 0) {
			html.append("<tr>").append(LINE_END);
			for (Panel item : this.footerTemplate) {
				html.append("<td>").append(LINE_END);
				html.append(item.render()).append(LINE_END);
				html.append("</td>").append(LINE_END);
			}
			html.append("</tr>").append(LINE_END);
		}
		html.append("</table>").append(LINE_END);

		return html.toString();
	}

}
