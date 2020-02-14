package org.woehlke.computer.kurzweil.tabs.cca.config;

import static java.io.File.separator;

public interface ConfigProperties {

    String TITLE = "cyclic cellular automaton";
    String SUBTITLE = "(c) 2019 Thomas Woehlke";
    String WIDTH = "640";
    String HEIGHT = "468";
    String NEIGHBORHOOD="Neighborhood";
    String BUTTON_VON_NEUMANN="Von Neumann";
    String BUTTON_MOORE="Moore";
    String BUTTON_WOEHLKE="Woehlke";

    String KEY = "org.woehlke.computer.kurzweil.apps.cca.config.";

    String KEY_TITLE = KEY + "title";
    String KEY_SUBTITLE = KEY + "subtitle";
    String KEY_WIDTH = KEY + "width";
    String KEY_HEIGHT = KEY + "height";
    String KEY_NEIGHBORHOOD = KEY + "neighborhood";
    String KEY_BUTTON_VON_NEUMANN = KEY + "buttonVonNeumann";
    String KEY_BUTTON_MOORE = KEY + "buttonMoore";
    String KEY_BUTTON_WOEHLKE = KEY + "buttonWoehlke";

    String APP_PROPERTIES_FILENAME ="src" + separator
        +"main" + separator + "resources" + separator  + "application.properties";
}