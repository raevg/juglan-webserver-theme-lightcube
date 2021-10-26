package me.raevg.juglan.theme.lightcube;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import me.raevg.juglan.webserver.event.Event;
import me.raevg.juglan.webserver.event.FocusEvent;
import me.raevg.juglan.webserver.event.KeyEvent;
import me.raevg.juglan.webserver.event.MouseClickEvent;
import me.raevg.juglan.webserver.event.MouseEvent;
import me.raevg.juglan.webserver.event.ValueChangeEvent;

public class PasswordField extends LightcubeInteractableWebNode {
	private InputField						field					= new InputField();
	private Reveal							reveal					= new Reveal();
	private Label							label					= new Label(field);
	private Container						container				= new Container();
	private String							value					= "";
	private boolean							enabled					= true;
	private Set<Consumer<ValueChangeEvent>>	valueChangeListeners	= new HashSet<>();
	
	public PasswordField() {
		super("div");
		addSClass("password-container");
		addChild(label);
		addChild(container);
	}
	
	public boolean isEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if(enabled)
			field.removeAttribute("disabled");
		else
			field.setAttribute("disabled", "");
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
		field.setAttribute("value", value);
		
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
	public void addMouseListener(Consumer<MouseEvent> listener) { field.addMouseListener(listener); }
	
	@Override
	public void removeMouseListener(Consumer<MouseEvent> listener) { field.removeMouseListener(listener); }
	
	@Override
	public void addKeyListener(Consumer<KeyEvent> listener) { field.addKeyListener(listener); }
	
	@Override
	public void removeKeyListener(Consumer<KeyEvent> listener) { field.removeKeyListener(listener); }
	
	@Override
	public void addFocusListener(Consumer<FocusEvent> listener) { field.addFocusListener(listener); }
	
	@Override
	public void removeFocusListener(Consumer<FocusEvent> listener) { field.removeFocusListener(listener); }
	
	public class Container extends LightcubeWebNode {
		private Container() {
			super("div");
			addSClass("container");
			addChild(field);
			addChild(reveal);
		}
	}
	
	public class InputField extends LightcubeInteractableWebNode {
		private InputField() {
			super("input");
			setAttribute("type", "password");
			addSClass("field");
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
	
	public class Reveal extends LightcubeWebNode {
		private Reveal() {
			super("button");
			addSClass("reveal");
		}
		
		@Override
		protected void event(Event event) {
			super.event(event);
			
			if(event instanceof MouseClickEvent e) {
				e.consume();
				
				if(field.getAttribute("type").equals("password"))
					field.setAttribute("type", "text");
				else
					field.setAttribute("type", "password");
			}
		}
	}
}
