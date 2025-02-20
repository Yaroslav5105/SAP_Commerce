package org.training.core.service;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

public interface CwProductService {

    List<ProductModel> getProductsByCategoryCodeAndCatalogVersionAndLimit(String categoryCode, String catalogVersion, int offset, int limit);
}
