/**
 * 
 */
package com.kaziabid.os.fileserverapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kaziabid.os.fileserverapi.dto.FileUploadRequest;
import com.kaziabid.os.fileserverapi.dto.FileUploadResponse;
import com.kaziabid.os.fileserverapi.service.FileUploadService;
import com.kaziabid.os.fileserverapi.utils.ApiConstants;

@RestController
@RequestMapping(value = ApiConstants.ENDPOINT_BASE_API_URL + ApiConstants.ENDPOINT_BASE_API_FILE_URL
	+ ApiConstants.ENDPOINT_BASE_API_FILE_UPLOAD_URL)
public class FileUploadController implements ApiConstants {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
	super();
	this.fileUploadService = fileUploadService;
    }

    @PostMapping(value = ENDPOINT_UPLOAD_FILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileUploadResponse uploadAnyFile(@RequestPart List<MultipartFile> files,
	    @RequestPart(required = false) FileUploadRequest uploadRequest) {
	return fileUploadService.uploadMultipartFilesSimple(files);
    }

}
