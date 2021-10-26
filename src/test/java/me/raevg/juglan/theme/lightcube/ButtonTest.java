package me.raevg.juglan.theme.lightcube;

import java.util.concurrent.CountDownLatch;

import me.raevg.juglan.theme.lightcube.Table.HeaderData;
import me.raevg.juglan.theme.lightcube.Table.Row;
import me.raevg.juglan.theme.lightcube.Table.RowData;
import me.raevg.juglan.theme.lightcube.layout.FlexLayout;
import me.raevg.juglan.theme.lightcube.layout.FlexLayout.AlignItems;
import me.raevg.juglan.theme.lightcube.layout.FlexLayout.Direction;
import me.raevg.juglan.webserver.JuglanWebServer;
import me.raevg.juglan.webserver.JuglanWebServer.Configuration;

public class ButtonTest {
	public static void main(String[] args) throws Exception {
		Configuration c = new Configuration();
		c.setHTTPPort(8000);
		c.setRequestPage("/", TestPage.class);
		JuglanWebServer.start(c);
		new CountDownLatch(1).await();
	}
	
	public static class TestPage extends LightcubeWebPage {
		@Override
		protected void init() {
			super.init();
			
			FlexLayout layout = new FlexLayout();
			layout.setDirection(Direction.COLUMN);
			layout.setRowGap("20px");
			layout.setAlignItems(AlignItems.FLEX_START);
			getBody().addChild(layout);
			
			Button b1 = new Button();
			Checkbox c1 = new Checkbox();
			RadioButton r1 = new RadioButton();
			TextField f1 = new TextField();
			ProgressBar p1 = new ProgressBar();
			PasswordField pf1 = new PasswordField();
			TextArea a1 = new TextArea();
			Table t1 = new Table();
			
			b1.setText("my text");
			b1.addActionListener(() -> {
				c1.setSelected(!c1.isSelected());
				f1.setEnabled(!f1.isEnabled());
				r1.setSelected(false);
				p1.setValue(p1.getValue() + 0.1f <= 1 ? p1.getValue() + 0.1f : 1);
			});
			layout.addChild(b1);
			
			c1.setLabel("check me");
			c1.addValueChangeListener(e -> {
				f1.setValue(e.getValue());
			});
			layout.addChild(c1);
			
			r1.setLabel("i am radio");
			c1.addValueChangeListener(e -> {
				System.out.println(e.getValue());
			});
			layout.addChild(r1);
			
			f1.setLabel("type something:");
			f1.addValueChangeListener(e -> {
				System.out.println(e.getValue());
			});
			layout.addChild(f1);
			
			p1.setValue(0.5f);
			layout.addChild(p1);
			
			pf1.setLabel("type a password:");
			pf1.addValueChangeListener(e -> {
				p1.setValue(e.getValue().length() <= 10 ? e.getValue().length() / 10.0f : 1);
			});
			pf1.setValue("pass");
			layout.addChild(pf1);
			
			a1.setLabel("type some big text:");
			a1.setValue("some lorem\nipsum with a newline");
			layout.addChild(a1);
			
			{
				Row c = t1.addHeader();
				
				HeaderData d1 = c.addHeaderData();
				d1.setText("header 1");
				
				HeaderData d2 = c.addHeaderData();
				d2.setText("header 2");
			}
			for(int i = 0; i < 10; i++) {
				Row r = t1.addRow();
				
				RowData d1 = r.addData();
				d1.setText("data 1");
				
				RowData d2 = r.addData();
				d2.setText("data 2");
			}
			{
				Row c = t1.addFooter();
				
				HeaderData d1 = c.addHeaderData();
				d1.setText("ftr 1");
				
				HeaderData d2 = c.addHeaderData();
				d2.setText("ftr 2");
			}
			layout.addChild(t1);
		}
	}
}
