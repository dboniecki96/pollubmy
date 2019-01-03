package pl.pollubmy.server.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollubmy.server.entity.DatabaseFile;
import pl.pollubmy.server.service.DownloadFileService;

@RestController
@RequestMapping("/download")
public class DownloadFileController {

    private final DownloadFileService downloadFileService;

    public DownloadFileController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }


    @GetMapping("/{fileInformationId}")
    public ResponseEntity<?> downloadFile(@PathVariable final String fileInformationId) {

        DatabaseFile fileToDownload = this.downloadFileService.getFile(fileInformationId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileToDownload.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileToDownload.getFileName() + "\"")
                .body(new ByteArrayResource(fileToDownload.getData()));
    }
}
