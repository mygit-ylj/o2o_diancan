package com.yanglijing.util;

import com.yanglijing.entity.ProductInfo;
import com.yanglijing.vo.FoodVO;

public class DataTranferTool {



    public static FoodVO transferProductInfo(ProductInfo productInfo) {
        FoodVO foodVO = new FoodVO();
        foodVO.setId(productInfo.getProductId());
        foodVO.setDescription(productInfo.getProductDescription());
        foodVO.setIcon(productInfo.getProductIcon());
        foodVO.setName(productInfo.getProductName());
        foodVO.setPrice(productInfo.getProductPrice());
        return foodVO;
    }

}
