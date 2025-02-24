package org.training.facades.suggestion;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.training.core.enums.UnitSystemType;
import org.training.facades.product.CwDefaultProductFacade;

import javax.annotation.Resource;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@IntegrationTest
public class CwDefaultProductFacadeIntegrationTest extends ServicelayerTransactionalTest {

    private static final String ProductCode = "product1";
    private static final String UNIT_SYSTEM = "unitSystem";

    @Resource
    private CwDefaultProductFacade cwDefaultProductFacade;
    @Resource
    private SessionService sessionService;

    @Before
    public void setUp() throws Exception {
        importCsv("/trainingfacades/test/testProduct.impex", "utf-8");
        sessionService.setAttribute(UNIT_SYSTEM, UnitSystemType.METRIC);
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

    @Test
    public void shouldRetrieveProductWithConvertedPhysicalDimensionsImperial() {
        sessionService.setAttribute(UNIT_SYSTEM,UnitSystemType.IMPERIAL);

        ProductData productData = cwDefaultProductFacade.getProductForCodeAndOptions(ProductCode, Collections.singleton(ProductOption.PHYSICAL_DIMENSIONS));

        assertEquals(ProductCode, productData.getCode());

        assertEquals(23.15, productData.getWeight(), 0.01); // 1500 mg equals 23.15 grains
        assertEquals(4.13, productData.getWidth(), 0.01);   // 10.5 cm equals 4.13 inches
        assertEquals(7.87, productData.getLength(), 0.01);  // 20.0 cm equals 7.87 inches
        assertEquals(2.17, productData.getHeight(), 0.01);  // 5.5 cm equals 2.17 inches
    }
}