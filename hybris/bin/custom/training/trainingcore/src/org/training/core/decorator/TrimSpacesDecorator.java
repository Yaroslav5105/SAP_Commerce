package org.training.core.decorator;

import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

public class TrimSpacesDecorator extends AbstractValueTranslator {

    @Override
    public Object importValue(String value, Item item) throws JaloInvalidParameterException {
        if (value != null) {
            return value.replaceAll("\\s", "").toLowerCase();
        }
        return null;
    }

    @Override
    public String exportValue(Object value) throws JaloInvalidParameterException {
        return value != null ? value.toString() : null;
    }
}