package me.raevg.juglan.theme.lightcube;

import me.raevg.juglan.webserver.TextWebNode;
import me.raevg.juglan.webserver.WebNode;

public class Label extends LightcubeInteractableWebNode {
	private WebNode	forNode;
	private String	text	= "";
	
	public Label() { super("label"); }
	
	public Label(WebNode forNode) {
		this();
		setForNode(forNode);
	}
	
	public WebNode getForNode() { return forNode; }
	
	public void setForNode(WebNode forNode) {
		this.forNode = forNode;
		if(forNode != null) setAttribute("for", forNode.getId());
	}
	
	public String getText() { return text; }
	
	public void setText(String text) {
		this.text = text;
		removeChildren();
		addChild(new TextWebNode(text));
	}
}
