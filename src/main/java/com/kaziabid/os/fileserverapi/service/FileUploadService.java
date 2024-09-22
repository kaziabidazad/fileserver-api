package com.kaziabid.os.fileserverapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kaziabid.os.fileserverapi.component.FileUploadHandler;
import com.kaziabid.os.fileserverapi.dto.FileProcessingResult;
import com.kaziabid.os.fileserverapi.dto.FileUploadResponse;

/**
 * @author Kazi
 */
@Service
public class FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    private final FileUploadHandler fileHandler;

    public FileUploadService(FileUploadHandler fileUploadHandler) {
	super();
	this.fileHandler = fileUploadHandler;
    }

    public FileUploadResponse uploadMultipartFilesSimple(List<MultipartFile> files) {
	String result = null;
	AtomicInteger errorFileCounter = new AtomicInteger(0);
	List<FileProcessingResult> fileProcessingResults = new ArrayList<>();
	if (files != null && !files.isEmpty()) {
	    List<CompletableFuture<FileProcessingResult>> futures = new ArrayList<>();
	    files.forEach(f -> {
		futures.add(fileHandler.uploadMultipartFile(f));
	    });
	    futures.forEach(f -> {
		try {
		    FileProcessingResult fileProcessingResult = f.get();
		    if (!fileProcessingResult.success()) {
			errorFileCounter.incrementAndGet();
		    }
		    fileProcessingResults.add(f.get());
		} catch (InterruptedException | ExecutionException e) {
		    LOGGER.error("Error getting upload result from FileUploadHandler.", e);
		}
	    });
	} else {
	    result = "No files uploaded!";
	}
	if (errorFileCounter.get() > 0)
	    result = String.format("There were errors processing %d files ", errorFileCounter.get());
	else
	    result = "Files Uploaded Successfully!";
	return new FileUploadResponse(result, fileProcessingResults);
    }

}
