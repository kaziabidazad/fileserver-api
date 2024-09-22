package com.kaziabid.os.fileserverapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Kazi
 */
@ConfigurationProperties(prefix = "storage.config", ignoreUnknownFields = true)
public record StorageConfigurationProiperties(String uploadBasePath) {

}
