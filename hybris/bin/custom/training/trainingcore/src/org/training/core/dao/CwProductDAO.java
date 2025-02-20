package org.training.core.dao;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

public interface CwProductDAO {

    List<ProductModel> getProductsByCategoryCodeAndCatalogVersionAndLimit(String categoryCode, String catalogVersion, int offset, int limit);

}
