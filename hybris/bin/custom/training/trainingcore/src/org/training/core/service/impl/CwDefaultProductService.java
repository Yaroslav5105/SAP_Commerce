package org.training.core.service.impl;

import de.hybris.platform.core.model.product.ProductModel;
import org.training.core.dao.CwProductDAO;
import org.training.core.service.CwProductService;

import javax.annotation.Resource;
import java.util.List;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class CwDefaultProductService implements CwProductService {

    @Resource
    private CwProductDAO cwProductDAO;

    @Override
    public List<ProductModel> getProductsByCategoryCodeAndCatalogVersionAndLimit(String categoryCode, String catalogVersion, int offset, int limit) {
        validateParameterNotNull(categoryCode, "categoryCode cannot be null");
        return cwProductDAO.getProductsByCategoryCodeAndCatalogVersionAndLimit(categoryCode, catalogVersion, offset, limit);
    }
}
