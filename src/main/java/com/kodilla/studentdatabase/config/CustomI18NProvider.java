package com.kodilla.studentdatabase.config;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class CustomI18NProvider implements I18NProvider {

    @Override
    public List<Locale> getProvidedLocales() {
        // Return the locales supported by your application
        return Arrays.asList(Locale.ENGLISH, Locale.FRENCH);
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        // Load translations from resource bundles or any other source
        ResourceBundle bundle = ResourceBundle.getBundle("translations", locale);
        return bundle.getString(key);
    }
}
