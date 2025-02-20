package org.training.core.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.impl.DefaultFlexibleSearchService;
import org.apache.commons.lang3.StringUtils;
import org.training.core.dao.CwProductDAO;

import javax.annotation.Resource;
import java.util.List;

public class CwDefaultProductDAO implements CwProductDAO {

    private static final String FIND_PRODUCTS_BY_CATEGORY_AND_CATALOG = StringUtils.join(
            "SELECT {", ProductModel.PK, "} FROM {", ProductModel._TYPECODE, " AS p ",
            "JOIN CategoryProductRelation AS cpr ON {cpr.target} = {p.", ProductModel.PK, "} ",
            "JOIN ", CategoryModel._TYPECODE, " AS c ON {c.", CategoryModel.PK, "} = {cpr.source} ",
            "JOIN ", CatalogVersionModel._TYPECODE, " AS cv ON {cv.", CatalogVersionModel.PK, "} = {c.", CategoryModel.CATALOGVERSION, "}} ",
            "WHERE {c.", CategoryModel.CODE, "} = ?categoryCode AND {cv.", CatalogVersionModel.VERSION, "} = ?catalogVersion"
    );

    @Resource
    private DefaultFlexibleSearchService flexibleSearchService;

    @Override
    public List<ProductModel> getProductsByCategoryCodeAndCatalogVersionAndLimit(String categoryCode, String catalogVersion, int offset, int limit) {
        FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(FIND_PRODUCTS_BY_CATEGORY_AND_CATALOG);

        searchQuery.addQueryParameter("categoryCode", categoryCode);
        searchQuery.addQueryParameter("catalogVersion", catalogVersion);
        searchQuery.setStart(offset);
        searchQuery.setCount(limit);

        final SearchResult<ProductModel> processes = flexibleSearchService.search(searchQuery);
        return processes.getResult();
    }
}