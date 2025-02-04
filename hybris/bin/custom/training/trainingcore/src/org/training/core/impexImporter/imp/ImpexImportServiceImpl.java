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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImpexImportServiceImpl implements ImpexImportService {

    private static final Logger LOG = LoggerFactory.getLogger(ImpexImportServiceImpl.class);

    @Value("${impex.import.folder}")
    private String importFolder;

    private static final String UPDATE_PRODUCT = "UPDATE Product;code[unique=true];name[lang=en]\n";

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

        List<File> impexFiles = Arrays.asList(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".impex") || name.endsWith(".csv"))));

        impexFiles.forEach(file -> {
            if (file.getName().endsWith(".impex")) {
                importImpexFile(file);
            } else if (file.getName().endsWith(".csv")) {
                importCsvFileWithHeader(file);
            }
        });

        LOG.info("Import file processing completed.");
    }

    private void importImpexFile(File file) {
        LOG.info("Processing file: {}", file.getName());
        try (FileInputStream inputStream = new FileInputStream(file)) {
            ImpExMediaModel jobMedia = createJobMedia(file.getName(), "text/csv", inputStream);
            createAndRunCronJob(file.getName(), jobMedia);
        } catch (FileNotFoundException e) {
            LOG.error("Impex/CSV File not found", e);
        } catch (IOException e) {
            LOG.error("Error closing stream", e);
        }
    }

    private void importCsvFileWithHeader(File file) {
        LOG.info("Processing CSV file: {}", file.getName());
        try {
            String content = Files.readString(file.toPath());
            String fullContent = UPDATE_PRODUCT + content;
            InputStream inputStream = new ByteArrayInputStream(fullContent.getBytes());

            ImpExMediaModel jobMedia = createJobMedia(file.getName(), "text/csv", inputStream);
            createAndRunCronJob(file.getName(), jobMedia);
        } catch (IOException e) {
            LOG.error("CSV File not found or unreadable", e);
        }
    }

    private ImpExMediaModel createJobMedia(String fileName, String mimeType, InputStream inputStream) {
        ImpExMediaModel jobMedia = modelService.create(ImpExMediaModel.class);
        jobMedia.setCode(fileName + "_Media_" + System.currentTimeMillis());
        jobMedia.setMime(mimeType);
        jobMedia.setFieldSeparator(';');
        jobMedia.setQuoteCharacter('"');
        modelService.save(jobMedia);

        mediaService.setStreamForMedia(jobMedia, inputStream);
        return jobMedia;
    }

    private void createAndRunCronJob(String fileName, ImpExMediaModel jobMedia) {
        ImpExImportCronJobModel cronJob = modelService.create(ImpExImportCronJobModel.class);
        cronJob.setCode(fileName + "_CronJob_" + System.currentTimeMillis());
        cronJob.setJobMedia(jobMedia);
        modelService.save(cronJob);

        LOG.info("Starting import CronJob for file: {}", fileName);
        cronJobService.performCronJob(cronJob, true);
    }
}
