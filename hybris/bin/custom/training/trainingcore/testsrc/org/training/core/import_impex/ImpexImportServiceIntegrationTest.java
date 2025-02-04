package org.training.core.import_impex;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;
import org.training.core.impexImporter.ImpexImportService;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class ImpexImportServiceIntegrationTest extends ServicelayerTransactionalTest {

    private static final String PRODUCT_CATALOG = "productCatalog";
    private static final String ProductCode = "product1";
    @Resource
    private ImpexImportService impexImportService;
    @Resource
    private ProductService productService;
    @Resource
    private ModelService modelService;

    @Test
    public void verifyProductImportAndCatalogVersion() {
        final CatalogModel catalog = createCatalog();
        final CatalogVersionModel catalogVersion = createCatalogVersion(catalog);

        impexImportService.processImpexFiles();
        ProductModel productModel = productService.getProductForCode(catalogVersion, ProductCode);

        assertNotNull(productModel);
        assertEquals(ProductCode, productModel.getCode());
        assertEquals(CatalogManager.ONLINE_VERSION, productModel.getCatalogVersion().getVersion());
        assertEquals(PRODUCT_CATALOG, productModel.getCatalogVersion().getCatalog().getId());
    }
    private CatalogModel createCatalog()
    {
        final CatalogModel catalog = new CatalogModel();
        catalog.setId(PRODUCT_CATALOG);
        modelService.save(catalog);
        return catalog;
    }

    private CatalogVersionModel createCatalogVersion(final CatalogModel catalog)
    {
        final CatalogVersionModel catalogVersion = new CatalogVersionModel();
        catalogVersion.setCatalog(catalog);
        catalogVersion.setVersion(CatalogManager.ONLINE_VERSION);
        catalogVersion.setActive(Boolean.TRUE);
        modelService.save(catalogVersion);
        return catalogVersion;
    }
}
