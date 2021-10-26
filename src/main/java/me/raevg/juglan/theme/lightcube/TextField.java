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

public class TextField extends LightcubeInteractableWebNode {
	public enum Type {
		TEXT, PASSWORD, NUMBER, EMAIL
	}
	
	private Type							type					= Type.TEXT;
	private Box								box						= new Box();
	private Label							label					= new Label(box);
	private String							value					= "";
	private boolean							enabled					= true;
	private Set<Consumer<ValueChangeEvent>>	valueChangeListeners	= new HashSet<>();
	
	public TextField() {
		super("div");
		addSClass("textbox-container");
		addChild(box);
	}
	
	public Type getType() { return type; }
	
	public void setType(Type type) {
		Objects.requireNonNull(type, "Textbox type cannot be null!");
		this.type = type;
		box.setAttribute("type", type.name().toLowerCase());
	}
	
	public boolean isEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if(enabled)
			box.removeAttribute("disabled");
		else
			box.setAttribute("disabled", "");
	}
	
	public String getLabel() { return label.getText(); }
	
	public void setLabel(String text) {
		label.setText(text);
		
		if(text != null && !text.isBlank()) {
			if(label.getParent() == null)
				addChild(0, label);
		} else
			label.remove();
	}
	
	public String getValue() { return value; }
	
	public void setValue(String value) {
		Objects.requireNonNull(value, "Value cannot be null!");
		this.value = value;
		box.setAttribute("value", value);
		
		ValueChangeEvent e = new ValueChangeEvent(value);
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
			setAttribute("type", "text");
			addSClass("textbox");
		}
		
		@Override
		protected void event(Event event) {
			super.event(event);
			
			if(event instanceof ValueChangeEvent e) {
				if(isEnabled()) {
					value = e.getValue();
					
					for(Consumer<ValueChangeEvent> l : valueChangeListeners)
						l.accept(e);
				}
			}
		}
	}
}
