package com.kaziabid.os.fileserverapi.storage.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kaziabid.os.fileserverapi.config.StorageConfigurationProiperties;
import com.kaziabid.os.fileserverapi.exception.StorageException;
import com.kaziabid.os.fileserverapi.exception.StorageFileNotFoundException;

import jakarta.annotation.PostConstruct;

/**
 * @author Kazi
 */
@Service
public class FileSystemStorageService implements StorageService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER =
	        LoggerFactory.getLogger(FileSystemStorageService.class);
	private final Path          basePath;

	public FileSystemStorageService(
	        StorageConfigurationProiperties storageConfigurationProiperties)
	        throws URISyntaxException {
		super();
		if (storageConfigurationProiperties.uploadBasePath().trim().isEmpty()) {
			throw new StorageException(
			        "Storage base path cannot be empty! Add the base path in the application properties");
		} else {
			this.basePath = Path.of(storageConfigurationProiperties.uploadBasePath());
			// init();
		}
	}

	@Override
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(basePath);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void store(MultipartFile file) {
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file.");
		}
		Path destinationPath = this.basePath
		        .resolve(Paths.get(file.getOriginalFilename())).normalize()
		        .toAbsolutePath();
		if (!destinationPath.getParent().equals(this.basePath.toAbsolutePath())) {
			// This is a security check
			throw new StorageException("Cannot store file outside current directory.");
		}
		try {
			try (InputStream inputStream = file.getInputStream()) {
				Files
				        .copy(inputStream, destinationPath,
				                StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files
			        .walk(this.basePath, 1).filter(path -> !path.equals(this.basePath))
			        .map(this.basePath::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String filename) {
		return basePath.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException(
				        "Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(basePath);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}
