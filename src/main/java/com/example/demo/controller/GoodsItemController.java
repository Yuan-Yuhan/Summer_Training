package com.example.demo.controller;

/**
 * @author 赵思阳,袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
import com.example.demo.enm.MsgId;
import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Result;
import com.example.demo.entity.UploadFileResponse;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.GoodsItemService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

//
@RestController
@RequestMapping ("/goodsItem")
public class GoodsItemController {

    @Autowired
    GoodsItemService goodsItemService;
    @Autowired
    FileService fileService;
    @Autowired
    GoodsItemRepository goodsItemRepository;

    /**
     * 添加商品
     * 必传项gname,transportfee,merchantId,details,type和商品图片文件
     * @return
     */
    @PostMapping("/addOne")
    public Result addGoodsItem(String gname, Double transportfee, Integer merchantId,
                               String details, String type)
    {
        goodsItemService.addOne(gname,transportfee,merchantId,details,type);
        return ResultUtils.success();

    }
    /**
     * 更新商品
     * @param goodsItem
     * 必传sid,选传gname,transportfee,merchantId,details,type,商品图片文件
     *
     */
    @PostMapping("/update")
    public Result update(GoodsItem goodsItem,MultipartFile file){
        if ( goodsItemService.update(goodsItem).getCode()==0 ){
            String fileName = fileService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            GoodsItem old=goodsItemRepository.findBySid(goodsItem.getSid());
            old.setSpic(fileDownloadUri);
            goodsItemRepository.save(old);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
            return ResultUtils.success(uploadFileResponse);
        }
        return goodsItemService.update(goodsItem);
    }

    @RequestMapping("/delete")
    public Result delete(Integer sid)
    {
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);
        if(goodsItem == null)
            return ResultUtils.error(MsgId.SHANGPIN_NOT_EXIST);

        goodsItemRepository.delete(goodsItem);
        return ResultUtils.success();
    }

    /**
     * 模糊查询，根据name搜索商品
     * @param name
     * @return
     */
    @RequestMapping("/findAllByGnameLike")
    public Result search(String name){
        return goodsItemService.findAllByGnameLike((name));
    }

    @RequestMapping("/findAllOfThisMerchant")
    public Result findAllOfThisMerchant(String name, Integer merchantId)
    {
        return goodsItemService.findAllByGnameOfThisMerchant(name, merchantId);
    }

    //按照销量
    @RequestMapping("/findSortBySales")
    public Result sortBySales(String name)
    {
        return goodsItemService.find_And_SortBySales(name);
    }

    //按照好评率
    @RequestMapping("/findSortByFavorRate")
    public Result sortByFavorRate(String name)
    {
        return goodsItemService.find_And_SortByFavorRate(name);
    }

    //默认搜索,并限制为这个商家的商品

    /**
     * 根据sid搜索商品
     * @param sid
     * @return
     */
    @RequestMapping("/findBySid")
    public Result findBySid(Integer sid)
    {
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);

        if(goodsItem == null)
            return ResultUtils.error(MsgId.SHANGPIN_NOT_EXIST);
        //SHANGPIN_NOT_EXIST(99,"商品不存在"),

        return ResultUtils.success(goodsItem);
    }


    @RequestMapping ("/findTopSix")
    public Result findTop6BySales(Integer token)
    {
        List<GoodsItem> goodsItemList = goodsItemRepository.findTop6ByToken(token);
        if(goodsItemList == null)
            return ResultUtils.error(MsgId.NO_RESULT);

        return ResultUtils.success(goodsItemList);
    }

    //得到每个商品的销量,salesList中的元素: gname商品名, sales 销量
    @RequestMapping("/getSalesOfEachProduct")
    public Result getSalesOfEachProduct(Integer merchantId)
    {
        return goodsItemService.getSalesOfGoodsItem(merchantId);
    }

    //得到这个商家所有的商品列表
    @RequestMapping("/findAllGoodsItem")
    public Result findAllGoodsItem(Integer merchantId)
    {
        return ResultUtils.success(goodsItemRepository.findAllByMerchantId(merchantId));
    }

    //通过规格ID获取商品名----LinkWith
    @RequestMapping("/getNameBySid")
    public String getNameBySid(Integer sid)
    {
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);
        if(goodsItem == null)
            return "错误!";

        return goodsItem.getGname();
    }

    //通过MerchantID获取GoodsList
    //使用在:
    //⑩商品折扣,得到所有商品折扣信息
    //⑧得到库存量
    @RequestMapping("/getGoodsList")
    public Result getInventory(Integer merchantId)
    {
        return goodsItemService.getGoodListByMerchantId(merchantId);
    }
}
