package com.example.demo.controller;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Merchant;
import com.example.demo.entity.Result;
import com.example.demo.entity.UploadFileResponse;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.MerchantService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 16:42
 * @Version 1.0
 */

//商家
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    FileService fileService;

    /**
     *商户登录
     * @param phone,password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String phone,String password){
        return merchantService.login(phone,password);

    }
    /**
     *商户注册
     * @param phone,password
     * @return
     */
    @RequestMapping("/register")
    public Result register(String phone,String name,String password){
        return merchantService.register(phone,name,password);
    }
    /**
     * 商户信息更新 id必传
     * @param merchantId,merchantPlace,merchantPassword,图片文件
     * @return
     */
    @PostMapping("/update")
    public Result update(Integer merchantId, String merchantPlace, String merchantPassword, MultipartFile file){
        if( merchantService.update(merchantId,merchantPlace,merchantPassword).getCode()==0){
            String fileName = fileService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            Merchant old=merchantRepository.findMerchantByMerchantId(merchantId);
            old.setMerchantPic(fileDownloadUri);
            merchantRepository.save(old);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
            return ResultUtils.success(uploadFileResponse);
        }
        return merchantService.update(merchantId,merchantPlace,merchantPassword);
    }
    /**
     * 商户删除，id必传
     * @param merchantId
     * @return
     */
    @RequestMapping("/delete" )
    public Result delete(Integer merchantId){
        return merchantService.delete(merchantId);
    }

    @RequestMapping("/findNameByMId")
    public Result findNameByMId(Integer mId)
    {
        Merchant merchant = merchantRepository.findMerchantByMerchantId(mId);
        if(merchant == null)
            return ResultUtils.error(MsgId.MERCHANT_NOT_EXIST);

        String name = merchant.getMerchantName();

        return ResultUtils.success(name);
    }
    /**
     * 查找商户/店铺头像图片地址
     * @param merchantId
     * @return
     */
    public Result findAvatar(Integer merchantId){
        return ResultUtils.success(merchantRepository.findMerchantPicByMerchantId(merchantId));
    }
    /**
     * 查找商户
     * @param merchantId
     * @return
     */
    @RequestMapping("/findMerchant")
    public Result findMerchant(Integer merchantId){

        return ResultUtils.success(merchantRepository.findMerchantByMerchantId(merchantId));
    }

}
