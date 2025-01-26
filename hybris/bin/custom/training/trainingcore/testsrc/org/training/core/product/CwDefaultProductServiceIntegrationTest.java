package org.training.core.product;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.Test;
import org.training.core.service.CwProductService;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@IntegrationTest
public class CwDefaultProductServiceIntegrationTest extends ServicelayerTransactionalTest {

    private static final String CATEGORY_CODE = "sea";
    private static final String CATALOG_VERSION = "Online";
    private static final int OFFSET_ZERO = 0;
    private static final int OFFSET_THREE = 3;
    private static final int LIMIT_TWO = 2;
    private static final int LIMIT_THREE = 3;
    private static final List<String> EXPECTED_PRODUCT_CODES = Arrays.asList("product6", "product7", "product8");

    @Resource
    private CwProductService cwProductService;

    @Before
    public void setUp() throws Exception {
        importCsv("/trainingcore/test/testProduct.impex", "utf-8");
    }

    @Test
    public void shouldReturnLimitedNumberOfProducts() {
        List<ProductModel> products = cwProductService.getProductsByCategoryCodeAndCatalogVersionAndLimit(CATEGORY_CODE, CATALOG_VERSION, OFFSET_ZERO, LIMIT_TWO);

        assertNotNull(products);
        assertEquals(LIMIT_TWO, products.size());
    }

    @Test
    public void offsetIsAppliedCorrectly() {
        List<ProductModel> allProducts = cwProductService.getProductsByCategoryCodeAndCatalogVersionAndLimit(CATEGORY_CODE, CATALOG_VERSION, OFFSET_ZERO, Integer.MAX_VALUE);
        List<ProductModel> offsetProducts = cwProductService.getProductsByCategoryCodeAndCatalogVersionAndLimit(CATEGORY_CODE, CATALOG_VERSION, OFFSET_THREE, LIMIT_TWO);

        assertNotNull(offsetProducts);
        assertTrue(allProducts.size() > offsetProducts.size());

        List<String> expectedCodes = allProducts.stream()
                .skip(OFFSET_THREE)
                .limit(LIMIT_TWO)
                .map(ProductModel::getCode).toList();

        List<String> actualCodes = offsetProducts.stream().map(ProductModel::getCode).toList();
        assertEquals(expectedCodes, actualCodes);
    }

    @Test
    public void shouldReturnExpectedProductCodes() {
        List<ProductModel> products = cwProductService.getProductsByCategoryCodeAndCatalogVersionAndLimit(CATEGORY_CODE, CATALOG_VERSION, OFFSET_ZERO, LIMIT_THREE);

        assertNotNull(products);
        List<String> actualCodes = products.stream().map(ProductModel::getCode).toList();
        assertTrue(actualCodes.containsAll(EXPECTED_PRODUCT_CODES));
    }

    @Test
    public void shouldRetrieveProductsFromCorrectCategoryAndCatalog() {
        List<ProductModel> products = cwProductService.getProductsByCategoryCodeAndCatalogVersionAndLimit(CATEGORY_CODE, CATALOG_VERSION, OFFSET_ZERO, LIMIT_THREE);

        assertNotNull(products);
        for (ProductModel product : products) {
            assertTrue(product.getSupercategories().stream().anyMatch(category -> CATEGORY_CODE.equals(category.getCode())));
            assertEquals(CATALOG_VERSION, product.getCatalogVersion().getVersion());
        }
    }
}
