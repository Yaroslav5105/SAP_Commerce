package org.training.core.interceptor;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.training.model.EngineModel;

import javax.annotation.Resource;

@IntegrationTest
public class EngineHorsepowerValidateInterceptorTest extends ServicelayerTransactionalTest {

    @Resource
    private ModelService modelService;

    private EngineModel validEngine;
    private EngineModel invalidEngineLow;
    private EngineModel invalidEngineHigh;

    @Before
    public void setUp() {
        validEngine = modelService.create(EngineModel.class);
        validEngine.setHorsepower(150);

        invalidEngineLow = modelService.create(EngineModel.class);
        invalidEngineLow.setHorsepower(0);

        invalidEngineHigh = modelService.create(EngineModel.class);
        invalidEngineHigh.setHorsepower(2000);
    }

    @Test
    public void testValidHorsepower() {
        modelService.save(validEngine);
    }

    @Test(expected = ModelSavingException.class)
    public void testInvalidHorsepowerLow() {
        modelService.save(invalidEngineLow);
    }

    @Test(expected = ModelSavingException.class)
    public void testInvalidHorsepowerHigh() {
        modelService.save(invalidEngineHigh);
    }
}