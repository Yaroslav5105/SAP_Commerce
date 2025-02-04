package org.training.core.impexImporter.imp;

import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.impex.model.cronjob.ImpExImportCronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.training.core.impexImporter.ImpexImportService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class ImpexImportServiceImpl implements ImpexImportService {

    private static final Logger LOG = LoggerFactory.getLogger(ImpexImportServiceImpl.class);

    @Value("${impex.import.folder}")
    private String importFolder;

    @Resource
    private ModelService modelService;
    @Resource
    private MediaService mediaService;
    @Resource
    private CronJobService cronJobService;

    @Override
    public void processImpexFiles() {
        LOG.info("Starting processing of import files in folder: " + importFolder);
        File folder = new File(importFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            LOG.warn("Folder does not exist or is not a directory: " + importFolder);
            return;
        }

        List<File> impexFiles = Arrays.asList(folder.listFiles((dir, name) -> name.endsWith(".impex") || name.endsWith(".csv")));

        impexFiles.forEach(this::importCsvFile);
        LOG.info("Import file processing completed.");
    }

    private void importCsvFile(File file) {
        LOG.info("Processing file: " + file.getName());
        try {
            ImpExMediaModel jobMedia = modelService.create(ImpExMediaModel.class);
            jobMedia.setCode(file.getName() + "_Media_" + System.currentTimeMillis());
            jobMedia.setMime("text/csv");
            jobMedia.setFieldSeparator(';');
            jobMedia.setQuoteCharacter('\"');
            modelService.save(jobMedia);

            mediaService.setStreamForMedia(jobMedia, new FileInputStream(file));

            ImpExImportCronJobModel cronJob = modelService.create(ImpExImportCronJobModel.class);
            cronJob.setCode(file.getName() + "_CronJob_" + System.currentTimeMillis());
            cronJob.setJobMedia(jobMedia);
            modelService.save(cronJob);

            LOG.info("Starting import CronJob for file: " + file.getName());
            cronJobService.performCronJob(cronJob, true);
        } catch (FileNotFoundException e) {
            LOG.error("Impex/CSV File not Found", e);
        }
    }
}
