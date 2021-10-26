package me.raevg.juglan.theme.lightcube;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import me.raevg.juglan.webserver.event.Event;
import me.raevg.juglan.webserver.event.FocusEvent;
import me.raevg.juglan.webserver.event.KeyEvent;
import me.raevg.juglan.webserver.event.MouseEvent;
import me.raevg.juglan.webserver.event.ValueChangeEvent;

public class Checkbox extends LightcubeInteractableWebNode {
	private Box		box		= new Box();
	private Label	label	= new Label(box);
	
	private boolean	enabled		= true;
	private boolean	selected	= false;
	
	private Set<Consumer<ValueChangeEvent>> valueChangeListeners = new HashSet<>();
	
	public Checkbox() {
		super("div");
		addChild(box);
		addChild(label);
		addSClass("checkbox-container");
	}
	
	public String getLabel() { return label.getText(); }
	
	public void setLabel(String text) { label.setText(text); }
	
	public boolean isEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if(enabled)
			box.removeAttribute("disabled");
		else
			box.setAttribute("disabled", "");
	}
	
	public boolean isSelected() { return selected; }
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		
		if(selected)
			box.setAttribute("checked", "true");
		else
			box.removeAttribute("checked");
		
		ValueChangeEvent e = new ValueChangeEvent(String.valueOf(selected));
		for(Consumer<ValueChangeEvent> l : valueChangeListeners)
			l.accept(e);
	}
	
	public void addValueChangeListener(Consumer<ValueChangeEvent> listener) {
		Objects.requireNonNull(listener, "Value change listener cannot be null!");
		valueChangeListeners.add(listener);
	}
	
	public void removeValueChangeListener(Consumer<ValueChangeEvent> listener) { valueChangeListeners.remove(listener); }
	
	@Override
	public void addMouseListener(Consumer<MouseEvent> listener) { box.addMouseListener(listener); }
	
	@Override
	public void removeMouseListener(Consumer<MouseEvent> listener) { box.removeMouseListener(listener); }
	
	@Override
	public void addKeyListener(Consumer<KeyEvent> listener) { box.addKeyListener(listener); }
	
	@Override
	public void removeKeyListener(Consumer<KeyEvent> listener) { box.removeKeyListener(listener); }
	
	@Override
	public void addFocusListener(Consumer<FocusEvent> listener) { box.addFocusListener(listener); }
	
	@Override
	public void removeFocusListener(Consumer<FocusEvent> listener) { box.removeFocusListener(listener); }
	
	public class Box extends LightcubeInteractableWebNode {
		private Box() {
			super("input");
			setAttribute("type", "checkbox");
			addSClass("checkbox");
		}
		
		@Override
		protected void event(Event event) {
			super.event(event);
			
			if(event instanceof ValueChangeEvent e) {
				if(isEnabled()) {
					boolean c2 = e.getValue().equals("true") || e.getValue().equals("on") || e.getValue().equals("checked");
					if(c2 != selected) {
						selected = c2;
						
						for(Consumer<ValueChangeEvent> l : valueChangeListeners)
							l.accept(e);
					}
				}
			}
		}
	}
}
