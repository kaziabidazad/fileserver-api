/**
 * 
 */
package com.kaziabid.os.fileserverapi.dto;

import com.kaziabid.os.fileserverapi.dto.common.CommonRequestDto;

public record FileUploadRequest(String fileName) implements CommonRequestDto {

}
