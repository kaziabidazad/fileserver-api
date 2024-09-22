package com.kaziabid.os.fileserverapi.dto;

import com.kaziabid.os.fileserverapi.dto.common.CommonResponseDto;

/**
 * @author Kazi
 */
public record FileProcessingResult(boolean success, String filename, String message) implements CommonResponseDto {

}
