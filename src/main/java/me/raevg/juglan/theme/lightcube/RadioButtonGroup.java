package me.raevg.juglan.theme.lightcube;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import me.raevg.juglan.webserver.event.ValueChangeEvent;

public class RadioButtonGroup {
	private Set<RadioButton>								radios		= new HashSet<>();
	private Map<RadioButton, Consumer<ValueChangeEvent>>	listeners	= new HashMap<>();
	
	public RadioButtonGroup(RadioButton... selectables) {
		Objects.requireNonNull(selectables, "Radios cannot be null!");
		
		for(RadioButton s : selectables)
			add(s);
	}
	
	public void add(RadioButton s) {
		Objects.requireNonNull(s, "Radio cannot be null!");
		radios.add(s);
		Consumer<ValueChangeEvent> l = e -> {
			if(s.isSelected()) {
				for(RadioButton b : radios)
					if(b.isSelected())
						b.setSelected(false);
			}
		};
		listeners.put(s, l);
		s.addValueChangeListener(l);
	}
	
	public void remove(RadioButton s) {
		if(s != null) {
			s.removeValueChangeListener(listeners.get(s));
			radios.remove(s);
			listeners.remove(s);
		}
	}
	
	public void clear() {
		for(RadioButton r : new HashSet<>(radios))
			remove(r);
	}
}
