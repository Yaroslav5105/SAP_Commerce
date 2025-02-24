package org.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionService;
import org.training.core.enums.UnitSystemType;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CwPhysicalDimensionsProductPopulator implements Populator<ProductModel, ProductData> {

    private static final double MG_TO_GRAINS = 0.0154324;
    private static final double CM_TO_INCHES = 0.393701;
    private static final String UNIT_SYSTEM = "unitSystem";

    @Resource
    private SessionService sessionService;

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        boolean useImperial = isImperialSystem();

        productData.setWeight(convertValue(productModel.getWeight(), useImperial ? MG_TO_GRAINS : 1.0));
        productData.setHeight(convertValue(productModel.getHeight(), useImperial ? CM_TO_INCHES : 1.0));
        productData.setLength(convertValue(productModel.getLength(), useImperial ? CM_TO_INCHES : 1.0));
        productData.setWidth(convertValue(productModel.getWidth(), useImperial ? CM_TO_INCHES : 1.0));
    }

    private boolean isImperialSystem() {
        UnitSystemType unitSystem = sessionService.getAttribute(UNIT_SYSTEM);
        return UnitSystemType.IMPERIAL.equals(unitSystem);
    }

    private double convertValue(Double value, double conversionFactor) {
        if (value == null) {
            return 0.0;
        }
        return Math.ceil(value * conversionFactor * 100) / 100.0;
    }
}
