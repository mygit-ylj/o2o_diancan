package com.yanglijing.repository;

import com.yanglijing.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @InterfaceName ProductCategoryRepository
 * @Description: TODO
 * @Author :lianweibo
 * @Date 2019/11/28
 * @Version V1.0
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findAll();
}
