package me.raevg.juglan.theme.lightcube;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import me.raevg.juglan.theme.lightcube.Table.HeaderData;
import me.raevg.juglan.theme.lightcube.Table.Row;
import me.raevg.juglan.theme.lightcube.Table.RowData;
import me.raevg.juglan.webserver.JuglanWebServer;
import me.raevg.juglan.webserver.JuglanWebServer.Configuration;
import me.raevg.juglan.webserver.event.FocusEvent;
import me.raevg.juglan.webserver.event.KeyEvent;
import me.raevg.juglan.webserver.event.KeyReleaseEvent;
import me.raevg.juglan.webserver.event.MouseClickEvent;

public class EditableTableTest {
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
			
			Table table = new Table();
			
			{
				Row row = table.addHeader();
				
				HeaderData name = row.addHeaderData();
				name.setText("Name");
				
				HeaderData surname = row.addHeaderData();
				surname.setText("Surame");
			}
			
			Map<String, String> names = new HashMap<>();
			names.put("John", "Atanasoff");
			names.put("Peter", "Marx");
			for(Entry<String, String> e : names.entrySet()) {
				Row row = table.addRow();
				
				RowData name = row.addData();
				name.addMouseListener(evt -> {
					if(evt instanceof MouseClickEvent evt2) {
						name.removeChildren();
						TextField field = new TextField();
						field.addKeyListener(evt3 -> {
							if(evt3 instanceof KeyReleaseEvent evt4) {
								if(evt4.decodeSpecialKey() == KeyEvent.KEY_ENTER) {
									names.remove(e.getKey());
									names.put(field.getValue(), e.getValue());
									name.setText(field.getValue());
								}
							}
						});
						field.addFocusListener(evt3 -> {
							if(evt3.getState() == FocusEvent.FOCUS_LOST)
								name.setText(e.getKey());
						});
						field.setValue(e.getKey());
						name.addChild(field);
					}
				});
				name.setText(e.getKey());
				
				RowData surname = row.addData();
				surname.setText(e.getValue());
			}
			
			getBody().addChild(table);
		}
	}
}
