package org.training.facades.suggestion;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.Test;
import org.training.facades.product.CwDefaultProductFacade;

import javax.annotation.Resource;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@IntegrationTest
public class CwDefaultProductFacadeIntegrationTest extends ServicelayerTransactionalTest {

    private static final String ProductCode = "product1";

    @Resource
    private CwDefaultProductFacade cwDefaultProductFacade;

    @Before
    public void setUp() throws Exception {
        importCsv("/trainingfacades/test/testProduct.impex", "utf-8");
    }

    @Test
    public void verifyProductImportAndCatalogVersion() {
        ProductData productData = cwDefaultProductFacade.getProductForCodeAndOptions(ProductCode, Collections.singleton(ProductOption.PHYSICAL_DIMENSIONS));

        assertEquals(ProductCode, productData.getCode());
        assertEquals(Double.valueOf(1500.0), productData.getWeight());
        assertEquals(Double.valueOf(5.5), productData.getHeight());
        assertEquals(Double.valueOf(20.0), productData.getLength());
        assertEquals(Double.valueOf(10.5), productData.getWidth());
    }

    @Test
    public void shouldRetrieveProductWithoutPhysicalDimensions() {
        ProductData productData = cwDefaultProductFacade.getProductForCodeAndOptions(ProductCode, Collections.emptySet());

        assertEquals(ProductCode, productData.getCode());

        assertNull(productData.getWeight());
        assertNull(productData.getHeight());
        assertNull(productData.getLength());
        assertNull(productData.getWidth());
    }
}