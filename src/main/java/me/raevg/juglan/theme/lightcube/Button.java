package me.raevg.juglan.theme.lightcube;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import me.raevg.juglan.theme.lightcube.event.ActionListener;
import me.raevg.juglan.webserver.TextWebNode;
import me.raevg.juglan.webserver.event.Event;
import me.raevg.juglan.webserver.event.MouseClickEvent;

public class Button extends LightcubeInteractableWebNode {
	private String				text			= "";
	private boolean				enabled			= true;
	private Set<ActionListener>	actionListeners	= new HashSet<>();
	
	public Button() {
		super("button");
		addSClass("button");
	}
	
	public String getText() { return text; }
	
	public void setText(String text) {
		this.text = text;
		removeChildren();
		addChild(new TextWebNode(text));
	}
	
	public boolean isEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if(enabled)
			removeAttribute("disabled");
		else
			setAttribute("disabled", "");
	}
	
	public void addActionListener(ActionListener listener) {
		Objects.requireNonNull(listener, "Action listener cannot be null!");
		actionListeners.add(listener);
	}
	
	public void removeActionListener(ActionListener listener) { actionListeners.remove(listener); }
	
	@Override
	protected void event(Event event) {
		super.event(event);
		
		if(event instanceof MouseClickEvent e) {
			// the event is consumed by LightcubeInteractableWebNode, but regardless of this process the event
			
			if(isEnabled()) {
				for(ActionListener l : actionListeners)
					l.action();
			}
		}
	}
}
