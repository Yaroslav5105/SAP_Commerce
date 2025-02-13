package org.training.core.handler;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.training.core.model.ProductBundleModel;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class ProductBundlePriceAttributeHandlerTest  extends ServicelayerTransactionalTest {

    private static final String UNIT_CODE = "Piece";
    private static final String UNIT_TYPE = "pieces";
    private static final String CURRENCY_ISO = "USD";
    private static final double PRICE = 120.0;
    private static final String CATALOG_ID = "testCatalog";
    private static final String CATALOG_VERSION = "Staged";
    private static final String PRODUCT_1_CODE = "product1";
    private static final String PRODUCT_2_CODE = "product2";
    private static final String BUNDLE_CODE = "bundle1";

    @Resource
    private ModelService modelService;

    private ProductBundleModel productBundle;

    @Before
    public void setUp() {
        UnitModel unitModel = createUnit();
        CurrencyModel currencyModel = createCurrency();
        PriceRowModel priceRow = createPriceRow(unitModel, currencyModel);
        CatalogVersionModel catalogVersion = createCatalogVersion();

        ProductModel product1 = createProduct(PRODUCT_1_CODE, catalogVersion, priceRow);
        ProductModel product2 = createProduct(PRODUCT_2_CODE, catalogVersion, priceRow);

        productBundle = createProductBundle(catalogVersion, product1, product2);

        modelService.saveAll();
    }

    @Test
    public void shouldCalculateCorrectBundlePrice() {
        assertNotNull("Product bundle should not be null", productBundle);

        Double calculatedPrice = productBundle.getCalculatedBundlePrice();
        assertNotNull("Calculated price should not be null", calculatedPrice);
        assertEquals("Calculated bundle price should match expected value", 240.0, calculatedPrice, 0.01);
    }

    @Test
    public void shouldContainCorrectBundledProducts() {
        assertNotNull("Product bundle should not be null", productBundle);
        assertNotNull("Bundled products should not be null", productBundle.getBundledProducts());
        assertEquals("Bundle should contain exactly 2 products", 2, productBundle.getBundledProducts().size());
    }

    private UnitModel createUnit() {
        UnitModel unit = modelService.create(UnitModel.class);
        unit.setCode(UNIT_CODE);
        unit.setUnitType(UNIT_TYPE);
        return unit;
    }

    private CurrencyModel createCurrency() {
        CurrencyModel currency = modelService.create(CurrencyModel.class);
        currency.setIsocode(CURRENCY_ISO);
        currency.setActive(Boolean.TRUE);
        return currency;
    }

    private PriceRowModel createPriceRow(UnitModel unit, CurrencyModel currency) {
        PriceRowModel priceRow = modelService.create(PriceRowModel.class);
        priceRow.setPrice(PRICE);
        priceRow.setUnit(unit);
        priceRow.setCurrency(currency);
        return priceRow;
    }

    private CatalogVersionModel createCatalogVersion() {
        CatalogModel catalog = modelService.create(CatalogModel.class);
        catalog.setId(CATALOG_ID);

        CatalogVersionModel catalogVersion = modelService.create(CatalogVersionModel.class);
        catalogVersion.setCatalog(catalog);
        catalogVersion.setVersion(CATALOG_VERSION);

        return catalogVersion;
    }

    private ProductModel createProduct(String code, CatalogVersionModel catalogVersion, PriceRowModel priceRow) {
        ProductModel product = modelService.create(ProductModel.class);
        product.setCode(code);
        product.setCatalogVersion(catalogVersion);
        product.setEurope1Prices(Collections.singleton(priceRow));
        return product;
    }

    private ProductBundleModel createProductBundle(CatalogVersionModel catalogVersion, ProductModel... products) {
        ProductBundleModel bundle = modelService.create(ProductBundleModel.class);
        bundle.setCode(BUNDLE_CODE);
        bundle.setCatalogVersion(catalogVersion);
        bundle.setBundledProducts(Set.of(products));
        return bundle;
    }
}