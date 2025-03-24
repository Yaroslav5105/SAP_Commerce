package org.training.facades.product;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.ConfigurablePopulator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

public class CwDefaultProductFacade implements ProductFacade{

    private static final Logger LOG = getLogger(CwDefaultProductFacade.class);

    @Resource
    private ProductService productService;
    @Resource
    private Converter<ProductModel, ProductData> productBasicConverter;
    @Resource
    private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;

    @Override
    public ProductData getProductForCodeAndOptions(String code, Collection<ProductOption> options) {
        ProductModel productModel = productService.getProductForCode(code);
        ProductData productData = productBasicConverter.convert(productModel);

        if (options != null && !options.isEmpty()) {

            if (productConfiguredPopulator != null) {
                productConfiguredPopulator.populate(productModel, productData, options);
            } else {
                LOG.warn("no product configured populator set to convert options [" + options.toArray() + "]");
            }
        }
        return productData;
    }
}
