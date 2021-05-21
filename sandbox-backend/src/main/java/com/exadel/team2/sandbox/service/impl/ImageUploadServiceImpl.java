package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.configuration.ImageStorageProperties;
import com.exadel.team2.sandbox.exceptions.FileNotFoundException;
import com.exadel.team2.sandbox.exceptions.FileStorageException;
import com.exadel.team2.sandbox.service.ImageUploadService;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.image.UploadImageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private Path fileStorageLocation;

    @Autowired
    public ImageUploadServiceImpl(ImageStorageProperties imageStorageProperties) {
        this.fileStorageLocation = Paths.get(imageStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create directory where the uploaded file will be stored");
        }
    }

    public static Optional<String> getExtensionByStringHandling(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }

    @Override
    public UploadImageResponseDTO uploadImage(EventResponseDTO eventResponseDTO, MultipartFile file) {

        String fileNameOriginal = StringUtils.cleanPath(file.getOriginalFilename());

        String fileExtension = null;
        String newFileName = null;

        try {

            fileExtension = ImageUploadServiceImpl.getExtensionByStringHandling(fileNameOriginal).get();

            if ("jpg".equals(fileExtension) || "gif".equals(fileExtension) || "png".equals(fileExtension)
                    || "jpeg".equals(fileExtension) || "ico".equals(fileExtension) || "svg".equals(fileExtension)) {

                newFileName = "image_for_event_id_"
                        + eventResponseDTO.getId().toString() + "_"
                        + UUID.randomUUID().hashCode() + "."
                        + fileExtension;

                StringUtils.cleanPath(file.getOriginalFilename());

                Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());

                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                Files.move(targetLocation, targetLocation.resolveSibling(newFileName));
            } else {
                throw new FileStorageException("The file " + fileNameOriginal + " doesn't have available extension. " +
                        "Please upload file with extension '.jpg', '.jpeg', '.ico', '.svg', '.gif' or '.png'");
            }

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileNameOriginal + ". Please try again!" + ex);
        }
        return UploadImageResponseDTO.builder()
                .ext(fileExtension)
                .name(newFileName)
                .size(file.getSize())
                .build();
    }

    @Override
    public Path load(String filename) {
        return fileStorageLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Image with name = " + filename + " not found!");
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("Could not read file: " + filename + "\n" + ex);
        }
    }
}
