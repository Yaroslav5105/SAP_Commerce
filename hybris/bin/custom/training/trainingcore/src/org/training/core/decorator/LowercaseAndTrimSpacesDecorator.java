package org.training.core.decorator;

import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

public class LowercaseAndTrimSpacesDecorator implements CSVCellDecorator {

    @Override
    public String decorate(int position, Map<Integer, String> srcLine) {
        System.out.println("111111111111111111111111111111111111111111");
        String parsedValue = srcLine.get(position);

        if (parsedValue != null) {
            return parsedValue.replaceAll("\\s", "").toLowerCase();
        }
        return null;
    }
}