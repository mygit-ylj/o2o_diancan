package com.yanglijing.repository;

import com.yanglijing.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @InterfaceName ProductInfoRepository
 * @Description: TODO
 * @Author :lianweibo
 * @Date 2019/11/28
 * @Version V1.0
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>, JpaSpecificationExecutor<ProductInfo> {
    List<ProductInfo> findAllByProductStatusAndCategoryType(Integer status,Integer type);
}
