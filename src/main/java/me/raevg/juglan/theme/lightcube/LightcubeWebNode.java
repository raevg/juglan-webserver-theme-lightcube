package me.raevg.juglan.theme.lightcube;

import java.util.LinkedList;
import java.util.List;

import me.raevg.juglan.webserver.WebNode;

public class LightcubeWebNode extends WebNode {
	protected List<String>	sclass	= new LinkedList<>();
	protected boolean		visible	= true;
	protected boolean		shown	= true;
	
	LightcubeWebNode(String tag) { super(tag); }
	
	LightcubeWebNode(String tag, boolean tagOnly) { super(tag, tagOnly); }
	
	LightcubeWebNode(String tag, String id) { super(tag, id); }
	
	LightcubeWebNode(String tag, String id, boolean tagOnly) { super(tag, id, tagOnly); }
	
	public void addSClass(String sclass) {
		String[] s0 = sclass.split(" ");
		for(String s : s0) {
			if(!s.isBlank())
				this.sclass.add(s);
		}
		setAttribute("sclass", String.join(" ", this.sclass));
	}
	
	public void removeSClass(String sclass) {
		String[] s0 = sclass.split(" ");
		for(String s : s0) {
			if(!s.isBlank())
				this.sclass.remove(s);
		}
		setAttribute("sclass", String.join(" ", this.sclass));
	}
	
	/**
	 * Sets the visibility of this node. If a node is attached to the DOM but not visible, space is
	 * still reserved for the node, it is just not visible.
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
		
		if(visible)
			removeSClass("invisible");
		else
			addSClass("invisible");
	}
	
	/**
	 * Sets whether or not this item is processed in the DOM. If a node is attached to the DOM, but not
	 * shown, it is not rendered at all.
	 * 
	 * @param shown
	 */
	public void setShown(boolean shown) {
		this.shown = shown;
		
		if(shown)
			removeSClass("hidden");
		else
			addSClass("hidden");
	}
}
