package com.yanglijing.controller;

import com.yanglijing.entity.ProductCategory;
import com.yanglijing.entity.ProductInfo;
import com.yanglijing.service.ProductService;
import com.yanglijing.vo.BuyerProductInfoVO;
import com.yanglijing.vo.CodeMsg;
import com.yanglijing.vo.ProductVo;
import com.yanglijing.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BuyerProductController
 * @Description: TODO
 * @Author :yanglijing
 * @Date 2019/11/28
 * @Version V1.0
 **/
@RestController
@RequestMapping("/buyer/product")
@CrossOrigin
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/order_list")
    public ResponseVO<List<ProductInfo>> seledtProductList(ProductVo productVo){
        Page<ProductInfo> page = productService.selectByCondition(productVo);
        return ResponseVO.success(page);
    }

    @GetMapping("/cat_list")
    public ResponseVO<List<ProductCategory>> selectAllProductCategory(){
        return ResponseVO.success(productService.selectAllCats());
    }

    @GetMapping("/list")
    public ResponseVO<List<BuyerProductInfoVO>> selectBuyerFoods(){
        List<BuyerProductInfoVO> buyerProductInfoVOList = productService.selectAllProducts();
        if (buyerProductInfoVOList != null && buyerProductInfoVOList.size()>0){
            return ResponseVO.success(buyerProductInfoVOList);
        }
        return ResponseVO.error(CodeMsg.PRODUCT_NOT_EXIST);
    }
}
