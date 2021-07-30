package com.example.demo.controller;


import com.example.demo.entity.Merchant;
import com.example.demo.entity.Result;
import com.example.demo.entity.UploadFileResponse;

import com.example.demo.entity.User;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileService;
import com.example.demo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author 赵思阳
 * @Date 2021/7/15 11:07
 * @Version 1.0
 */

//文件上传功能
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MerchantRepository merchantRepository;
    //用户上传头像
    @PostMapping("/user/uploadAvatar")
    public Result uploadUser(@RequestParam("file") MultipartFile file, Integer uid){
        User user=userRepository.findByUid(uid);
        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        user.setAvatar(fileDownloadUri);
        userRepository.save(user);

        UploadFileResponse uploadFileResponse= new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

        return ResultUtils.success(uploadFileResponse);
    }
    //商户上传头像
    @PostMapping("/merchant/uploadAvatar")
    public Result uploadMerchant(@RequestParam("file") MultipartFile file, Integer merchantId){
        Merchant merchant=merchantRepository.findMerchantByMerchantId(merchantId);
        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        merchant.setMerchantPic(fileDownloadUri);
        merchantRepository.save(merchant);

        UploadFileResponse uploadFileResponse= new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        return ResultUtils.success(uploadFileResponse);
    }


//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.stream(files)
//                .map(this::uploadFile)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
