package org.training.core.handler;


import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.ProductBundleModel;

public class ProductBundlePriceAttributeHandler implements DynamicAttributeHandler<Double, ProductBundleModel> {

    @Override
    public Double get(ProductBundleModel productBundle) {
        Double totalPrice = 0.0;
        for (ProductModel bundledProduct : productBundle.getBundledProducts()) {
            if (bundledProduct != null && !CollectionUtils.isEmpty(bundledProduct.getEurope1Prices())) {
                PriceRowModel priceRow = bundledProduct.getEurope1Prices().iterator().next();
                if (priceRow != null && priceRow.getPrice() != null) {
                    totalPrice += priceRow.getPrice();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public void set(ProductBundleModel productBundle, Double value) {
        throw new UnsupportedOperationException("Bundle price is calculated dynamically and cannot be set manually.");
    }
}