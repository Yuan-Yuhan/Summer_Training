package com.example.demo.controller;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.InventoryLogRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author 赵思阳
 * @since 2021-07-23
 * @version 1.0
 */

//商品的规格
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    FileService fileService;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsItemRepository goodsItemRepository;
    @Autowired
    InventoryLogRepository inventoryLogRepository;

    /**
     * 添加商品规格
     * @param goods
     *
     * @return
     */
    @PostMapping("/addOne")
    public Result addGoods(Goods goods,@RequestParam("file") MultipartFile file){
        if(goodsService.addOne(goods).getCode()!=0) {
            //规格文字信息正确之后,上传规格照片
            String fileName = fileService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            goods.setGpic(fileDownloadUri);
            goodsRepository.save(goods);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());

            return ResultUtils.success(uploadFileResponse);
        }
        return goodsService.addOne(goods);
    }
    /**
     * 更新规格
     * @param goods
     * @return
     */
    @PostMapping("/update")
    public Result update(Goods goods, MultipartFile file){

        //文件相关
        if ( goodsService.update(goods).getCode()==0 ){
            String fileName = fileService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();

            Goods old=goodsRepository.findGoodsByGid(goods.getGid());
            old.setGpic(fileDownloadUri);
            goodsRepository.save(old);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
            return ResultUtils.success(uploadFileResponse);
        }
        return goodsService.update(goods);
    }

    //修改规格的原价和折扣
    //能改,只能改一点点
    @RequestMapping("/changePrice")
    public Result changePrice(Integer gid, Double originalPrice, Double discount)
    {
        Goods goods = goodsRepository.findGoodsByGid(gid);
        if(goods == null)
            return ResultUtils.error(MsgId.GUIGE_NOT_EXIST);
        goods.setOriginalPrice(originalPrice);
        goods.setDiscount(discount);
        goods.setPrice(discount*originalPrice);

        goodsRepository.save(goods);

        return ResultUtils.success();
    }
    /**
     * 根据gid查找规格
     * @param gid
     * @return
     */

    @RequestMapping("/findByGid")
    public Result findByGid(Integer gid)
    {
        Goods goods = goodsRepository.findGoodsByGid(gid);

        if ( goods == null )
            return ResultUtils.error(MsgId.GUIGE_NOT_EXIST);
        //GUIGE_NOT_EXIST(88,"规格不存在")

        return ResultUtils.success(goods);
    }
    /**
     *查找一个商品的所有规格
     * @param sid
     * @return
     */
    @RequestMapping("/findAllBySid")
    public Result findAllBySid(Integer sid){
        return goodsService.findAllBySid(sid);
    }


    //商家改变规格的库存
    //必传: 规格ID, 新的库存量
    @RequestMapping("/changeInventory")
    public Result changeInventory(Integer gid, Integer inventory)
    {
        Goods goods = goodsRepository.findGoodsByGid(gid);
        if(goods == null)
        {
            return ResultUtils.error(MsgId.GUIGE_NOT_EXIST);
        }

        //设置出入库日志
        InventoryLog inventoryLog = new InventoryLog();

        inventoryLog.setCount(inventory - goods.getInventory());
        inventoryLog.setGname(goods.getSpecification());
        inventoryLog.setSname(goodsItemRepository.findBySid(goods.getSid()).getGname());
        if((inventory - goods.getInventory()) > 0)
            inventoryLog.setInOrOut("入库");
        else
            inventoryLog.setInOrOut("出库");

        inventoryLog.setMerchantId(goodsItemRepository.findBySid(goods.getSid()).getMerchantId());
        //保存出入库日志
        inventoryLogRepository.save(inventoryLog);

        goods.setInventory(inventory);

        goodsRepository.save(goods);

        return ResultUtils.success();
    }

    //找到价格最低

}
