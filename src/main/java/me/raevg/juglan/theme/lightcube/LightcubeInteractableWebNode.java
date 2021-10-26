package me.raevg.juglan.theme.lightcube;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import me.raevg.juglan.webserver.event.Event;
import me.raevg.juglan.webserver.event.FocusEvent;
import me.raevg.juglan.webserver.event.KeyEvent;
import me.raevg.juglan.webserver.event.MouseEvent;

public class LightcubeInteractableWebNode extends LightcubeWebNode {
	protected Set<Consumer<MouseEvent>>	mouseListeners	= new HashSet<>();
	protected Set<Consumer<KeyEvent>>	keyListeners	= new HashSet<>();
	protected Set<Consumer<FocusEvent>>	focusListeners	= new HashSet<>();
	
	LightcubeInteractableWebNode(String tag) { super(tag); }
	
	LightcubeInteractableWebNode(String tag, boolean tagOnly) { super(tag, tagOnly); }
	
	LightcubeInteractableWebNode(String tag, String id) { super(tag, id); }
	
	LightcubeInteractableWebNode(String tag, String id, boolean tagOnly) { super(tag, id, tagOnly); }
	
	public void addMouseListener(Consumer<MouseEvent> listener) {
		Objects.requireNonNull(listener, "Mouse listener cannot be null!");
		mouseListeners.add(listener);
	}
	
	public void removeMouseListener(Consumer<MouseEvent> listener) { mouseListeners.remove(listener); }
	
	public void addKeyListener(Consumer<KeyEvent> listener) {
		Objects.requireNonNull(listener, "Key listener cannot be null!");
		keyListeners.add(listener);
	}
	
	public void removeKeyListener(Consumer<KeyEvent> listener) { keyListeners.remove(listener); }
	
	public void addFocusListener(Consumer<FocusEvent> listener) {
		Objects.requireNonNull(listener, "Focus listener cannot be null!");
		focusListeners.add(listener);
	}
	
	public void removeFocusListener(Consumer<FocusEvent> listener) { focusListeners.remove(listener); }
	
	@Override
	protected void event(Event event) {
		super.event(event);
		
		if(event instanceof MouseEvent e) {
			e.consume();
			for(Consumer<MouseEvent> l : mouseListeners)
				l.accept(e);
		} else if(event instanceof KeyEvent e) {
			e.consume();
			for(Consumer<KeyEvent> l : keyListeners)
				l.accept(e);
		} else if(event instanceof FocusEvent e) {
			for(Consumer<FocusEvent> l : focusListeners)
				l.accept(e);
		}
	}
}
