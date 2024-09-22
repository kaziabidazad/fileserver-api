package com.kaziabid.os.fileserverapi.dto;

import java.util.List;

import com.kaziabid.os.fileserverapi.dto.common.CommonResponseDto;

/**
 * @author Kazi
 */
public record FileUploadResponse(String message, List<FileProcessingResult> results) implements CommonResponseDto {

}
