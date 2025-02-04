package org.training.core.import_impex;

import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.impex.model.cronjob.ImpExImportCronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.training.core.impexImporter.imp.ImpexImportServiceImpl;

import java.io.FileInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImpexImportServiceUnitTest {

    @Mock
    private ModelService modelService;
    @Mock
    private MediaService mediaService;
    @Mock
    private CronJobService cronJobService;
    @Mock
    private ImpExMediaModel impExMediaModel;
    @Mock
    private ImpExImportCronJobModel impExImportCronJobModel;
    @InjectMocks
    private ImpexImportServiceImpl impexImportServiceImpl;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(impexImportServiceImpl, "importFolder", "resources/test/import_impex");
        when(modelService.create(ImpExMediaModel.class)).thenReturn(impExMediaModel);
        when(modelService.create(ImpExImportCronJobModel.class)).thenReturn(impExImportCronJobModel);
    }

    @Test
    public void importImpexFile() {
        impexImportServiceImpl.processImpexFiles();

        verify(modelService).save(impExMediaModel);
        verify(mediaService).setStreamForMedia(any(ImpExMediaModel.class), any(FileInputStream.class));
        verify(modelService).save(impExImportCronJobModel);
        verify(cronJobService).performCronJob(impExImportCronJobModel, true);
    }
}
