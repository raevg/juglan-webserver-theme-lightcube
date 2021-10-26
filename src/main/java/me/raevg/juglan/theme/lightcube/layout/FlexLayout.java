package me.raevg.juglan.theme.lightcube.layout;

import java.util.Objects;

import me.raevg.juglan.webserver.AbstractWebNode;
import me.raevg.juglan.webserver.WebNode;

public class FlexLayout extends WebNode {
	public enum Direction {
		ROW, COLUMN, ROW_REVERSE, COLUMN_REVERSE
	}
	
	public enum Wrap {
		NOWRAP, WRAP, WRAP_REVERSE
	}
	
	public enum JustifyContent {
		FLEX_START, FLEX_END, CENTER, SPACE_BETWEEN, SPACE_AROUND, SPACE_EVENLY, START, END, LEFT, RIGHT
	}
	
	public enum AlignItems {
		NORMAL, STRETCH, FLEX_START, SELF_START, START, CENTER, BASELINE, FLEX_END, END, SELF_END
	}
	
	public enum AlignContent {
		NORMAL, FLEX_START, FLEX_END, CENTER, SPACE_BETWEEN, SPACE_AROUND, SPACE_EVENLY, STRETCH, START, END, BASELINE
	}
	
	public static FlexLayout row(AbstractWebNode... nodes) {
		FlexLayout layout = new FlexLayout();
		layout.setDirection(Direction.ROW);
		for(AbstractWebNode n : nodes)
			layout.addChild(n);
		return layout;
	}
	
	public static FlexLayout column(AbstractWebNode... nodes) {
		FlexLayout layout = new FlexLayout();
		layout.setDirection(Direction.COLUMN);
		for(AbstractWebNode n : nodes)
			layout.addChild(n);
		return layout;
	}
	
	private Direction		direction		= Direction.ROW;
	private Wrap			wrap			= Wrap.NOWRAP;
	private JustifyContent	justifyContent	= JustifyContent.FLEX_START;
	private AlignItems		alignItems		= AlignItems.NORMAL;
	private AlignContent	alignContent	= AlignContent.NORMAL;
	
	public FlexLayout() {
		super("div");
		setStyle("display", "flex");
	}
	
	public Direction getDirection() { return direction; }
	
	public void setDirection(Direction direction) {
		Objects.requireNonNull(direction, "Flexbox direction cannot be null!");
		this.direction = direction;
		setStyle("flex-direction", direction.name().toLowerCase().replace('_', '-'));
	}
	
	public Wrap getWrap() { return wrap; }
	
	public void setWrap(Wrap wrap) {
		Objects.requireNonNull(wrap, "Wrap cannot be null!");
		this.wrap = wrap;
		setStyle("flex-wrap", wrap.name().toLowerCase().replace('_', '-'));
	}
	
	public JustifyContent getJustifyContent() { return justifyContent; }
	
	public void setJustifyContent(JustifyContent justifyContent) {
		Objects.requireNonNull(justifyContent, "Justify content cannot be null!");
		this.justifyContent = justifyContent;
		setStyle("justify-content", justifyContent.name().toLowerCase().replace('_', '-'));// TODO safe
	}
	
	public AlignItems getAlignItems() { return alignItems; }
	
	public void setAlignItems(AlignItems alignItems) {
		Objects.requireNonNull(alignItems, "Align items cannot be null!");
		this.alignItems = alignItems;
		setStyle("align-items", alignItems.name().toLowerCase().replace('_', '-'));// TODO safe
	}
	
	public AlignContent getAlignContent() { return alignContent; }
	
	public void setAlignContent(AlignContent alignContent) {
		Objects.requireNonNull(alignContent, "Align content cannot be null!");
		this.alignContent = alignContent;
		setStyle("align-content", alignContent.name().toLowerCase().replace('_', '-'));// TODO safe
	}
	
	public void setRowGap(String gap) {
		Objects.requireNonNull(gap, "Gap cannot be null!");
		setStyle("row-gap", gap);
	}
	
	public void setColumnGap(String gap) {
		Objects.requireNonNull(gap, "Gap cannot be null!");
		setStyle("column-gap", gap);
	}
}
