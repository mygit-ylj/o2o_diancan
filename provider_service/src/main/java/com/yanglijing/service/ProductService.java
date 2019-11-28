package com.yanglijing.service;

import com.yanglijing.entity.ProductCategory;
import com.yanglijing.entity.ProductInfo;
import com.yanglijing.vo.BuyerProductInfoVO;
import com.yanglijing.vo.ProductVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @InterfaceName ProductService
 * @Description: TODO
 * @Author :lianweibo
 * @Date 2019/11/28
 * @Version V1.0
 **/
public interface ProductService {
    public List<BuyerProductInfoVO> selectAllProducts();

    public Page<ProductInfo> selectByCondition(ProductVo productVo);

    public List<ProductCategory> selectAllCats();
}
