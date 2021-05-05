package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.configuration.FileStorageProperties;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.UploadFileResponseDTO;
import com.exadel.team2.sandbox.exceptions.FileNotFoundException;
import com.exadel.team2.sandbox.exceptions.FileStorageException;
import com.exadel.team2.sandbox.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.FilterChain;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@Qualifier("fileUploadServiceImpl")
public class FileUploadServiceImpl implements FileUploadService {

    private Path fileStorageLocation;

    @Autowired
    public FileUploadServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
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
    public UploadFileResponseDTO uploadFile(CandidateResponseDTO candidateResponseDTO, MultipartFile file) {

        String fileNameOriginal = StringUtils.cleanPath(file.getOriginalFilename());

        String fileExtension = null;
        String newFileName = null;

        try {

            fileExtension = FileUploadServiceImpl.getExtensionByStringHandling(fileNameOriginal).get();

            if ("txt".equals(fileExtension) || "docx".equals(fileExtension) || "pdf".equals(fileExtension)) {

                newFileName = candidateResponseDTO.getId().toString() + "_"
                        + UUID.randomUUID().hashCode() + "_"
                        + candidateResponseDTO.getMainSkill() + "."
                        + fileExtension;

                StringUtils.cleanPath(file.getOriginalFilename());

                Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());

                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                Files.move(targetLocation, targetLocation.resolveSibling(newFileName));
            } else {
                throw new FileStorageException("The file " + fileNameOriginal + " doesn't have available extension. Please upload file with extension '.txt', '.docx' or '.pdf'");
            }

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileNameOriginal + ". Please try again!" + ex);
        }
        return UploadFileResponseDTO.builder()
                .fileExtension(fileExtension)
                .fileName(newFileName)
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
                throw new FileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("Could not read file: " + filename + "\n" + ex);
        }
    }
}
