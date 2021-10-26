package me.raevg.juglan.theme.lightcube;

import java.util.function.Function;

import me.raevg.juglan.webserver.TextWebNode;

public class ProgressBar extends LightcubeInteractableWebNode {
	/**
	 * Accepts a float number and outputs the int percent representation as a string.
	 * 
	 * @param value the float value of progress
	 */
	public static final Function<Float, String> DEFAULT_TEXT_SUPPLIER = value -> String.valueOf((int) (value * 100)) + "%"));
	
	private InnerBar				bar				= new InnerBar();
	private LightcubeWebNode		text			= new LightcubeWebNode("div");
	private float					value			= 0;
	private Function<Float, String>	textSupplier	= null;
	
	public ProgressBar() {
		super("div");
		addSClass("progressbar");
		addChild(bar);
		addChild(text);
		setStyle("--progressbar-value", "0");
		
		text.addSClass("text");
	}
	
	public float getValue() { return value; }
	
	public void setValue(float value) {
		if(value < 0 || value > 1) throw new IllegalArgumentException("Value must be between 0 and 1!");
		this.value = value;
		setStyle("--progressbar-value", String.valueOf(value));
		text.removeChildren();
		if(textSupplier != null) {
			String str = textSupplier.apply(value);
			if(str != null)
				text.addChild(new TextWebNode(str));
		}
	}
	
	/**
	 * Applies the given function as the calculator for the text representation of the progressbar value
	 * and refreshes the text. If {@code null} is passed as {@code func} or the function returns
	 * {@code null}, no text is displayed. By default no text supplier is defined, however there is a
	 * constant {@link #DEFAULT_TEXT_SUPPLIER} for easy use.
	 */
	public void setValueTextSupplier(Function<Float, String> func) {
		textSupplier = func;
		setValue(getValue());
	}
	
	public class InnerBar extends LightcubeInteractableWebNode {
		private InnerBar() {
			super("div");
			addSClass("bar");
		}
	}
}
