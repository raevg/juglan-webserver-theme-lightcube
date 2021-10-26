package me.raevg.juglan.theme.lightcube;

import me.raevg.juglan.webserver.WebPage;

public class LightcubeWebPage extends WebPage {
	@Override
	protected void init() {
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/style.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/button.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/checkbox.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/radio-button.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/textbox.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/textarea.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/password-field.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/progressbar.css");
		loadStyle(LightcubeWebPage.class.getClassLoader(), "juglan-theme-lightcube/table.css");
	}
}
