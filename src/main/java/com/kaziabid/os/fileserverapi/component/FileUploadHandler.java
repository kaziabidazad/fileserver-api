package com.kaziabid.os.fileserverapi.component;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kaziabid.os.fileserverapi.dto.FileProcessingResult;
import com.kaziabid.os.fileserverapi.storage.service.StorageService;
import com.kaziabid.os.fileserverapi.utils.FileUtils;
import com.kaziabid.os.fileserverapi.utils.FileUtils.FileSizeFormat;

/**
 * @author Kazi
 */
@Component
public class FileUploadHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);
    private final StorageService storageService;

    public FileUploadHandler(StorageService storageService) {
	super();
	this.storageService = storageService;
    }

    @Async
    public CompletableFuture<FileProcessingResult> uploadMultipartFile(MultipartFile file) {

	boolean success = false;
	String filename = null;
	String message = null;
	CompletableFuture<FileProcessingResult> result = new CompletableFuture<>();
	if (file != null) {
	    filename = file.getOriginalFilename();
	    storageService.store(file);
	    LOGGER.info("Saved file name {} , size: {} {}", filename,
		    FileUtils.calculateFileSize(file.getSize(), FileSizeFormat.MiB), FileSizeFormat.MiB);
	    success = true;
	} else {
	    message = "No File!";
	}
	result.complete(new FileProcessingResult(success, filename, message));
	return result;
    }
}
