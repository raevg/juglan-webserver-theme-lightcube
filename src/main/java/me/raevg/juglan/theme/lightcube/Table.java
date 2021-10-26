package me.raevg.juglan.theme.lightcube;

import me.raevg.juglan.webserver.TextWebNode;
import me.raevg.juglan.webserver.WebNode;

public class Table extends LightcubeWebNode {
	private WebNode		thead		= new WebNode("thead");
	private WebNode		tbody		= new WebNode("tbody");
	private WebNode		tfoot		= new WebNode("tfoot");
	private Component	component	= new Component();
	
	public Table() {
		super("div");
		addSClass("table-container");
		addChild(component);
	}
	
	public Row addHeader() { return addHeader(thead.getChildren().size()); }
	
	public Row addHeader(int at) {
		Row col = new Row();
		thead.addChild(at, col);
		return col;
	}
	
	public Row getHeader(int at) { return (Row) thead.getChildren().get(at); }
	
	public Row addRow() { return addRow(tbody.getChildren().size()); }
	
	public Row addRow(int at) {
		Row row = new Row();
		tbody.addChild(at, row);
		return row;
	}
	
	public Row getRow(int at) { return (Row) tbody.getChildren().get(at); }
	
	public Row addFooter() { return addFooter(tfoot.getChildren().size()); }
	
	public Row addFooter(int at) {
		Row col = new Row();
		tfoot.addChild(at, col);
		return col;
	}
	
	public Row getFooter(int at) { return (Row) tfoot.getChildren().get(at); }
	
	public class Component extends LightcubeWebNode {
		private Component() {
			super("table");
			addSClass("table");
			addChild(thead);
			addChild(tbody);
			addChild(tfoot);
		}
	}
	
	public class Row extends LightcubeInteractableWebNode {
		private Row() { super("tr"); }
		
		public RowData addData() { return addData(getChildren().size()); }
		
		public RowData addData(int at) {
			RowData data = new RowData();
			addChild(at, data);
			return data;
		}
		
		public HeaderData addHeaderData() { return addHeaderData(getChildren().size()); }
		
		public HeaderData addHeaderData(int at) {
			HeaderData data = new HeaderData();
			addChild(at, data);
			return data;
		}
	}
	
	public class HeaderData extends LightcubeInteractableWebNode {
		private HeaderData() { super("th"); }
		
		public void setText(String text) {
			removeChildren();
			addChild(new TextWebNode(text));
		}
	}
	
	public class RowData extends LightcubeInteractableWebNode {
		private RowData() { super("td"); }
		
		public void setText(String text) {
			removeChildren();
			addChild(new TextWebNode(text));
		}
	}
}