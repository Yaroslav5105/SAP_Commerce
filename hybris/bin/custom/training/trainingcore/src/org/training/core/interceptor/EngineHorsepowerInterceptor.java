package org.training.core.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import org.training.model.EngineModel;

public class EngineHorsepowerInterceptor implements PrepareInterceptor<EngineModel> {

    @Override
    public void onPrepare(EngineModel engineModel, InterceptorContext interceptorContext) throws InterceptorException {
        Integer horsepower = engineModel.getHorsepower();
        if (horsepower != null && (horsepower < 1 || horsepower > 1000)) {
            throw new InterceptorException("Horsepower must be between 1 and 1000.");
        }
    }
}