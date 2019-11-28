package com.yanglijing.service.Impl;

import com.yanglijing.entity.ProductCategory;
import com.yanglijing.entity.ProductInfo;
import com.yanglijing.repository.ProductCategoryRepository;
import com.yanglijing.repository.ProductInfoRepository;
import com.yanglijing.service.ProductService;
import com.yanglijing.util.DataTranferTool;
import com.yanglijing.vo.BuyerProductInfoVO;
import com.yanglijing.vo.FoodVO;
import com.yanglijing.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description: TODO
 * @Author :yanglijing
 * @Date 2019/11/28
 * @Version V1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<BuyerProductInfoVO> selectAllProducts() {
        List<BuyerProductInfoVO> productVoList = new ArrayList<>();
        List<ProductCategory> catList = productCategoryRepository.findAll();

        if (catList != null && catList.size()>0){
            for (int i = 0;i < catList.size();i++){
                BuyerProductInfoVO buyerProductInfoVO = new BuyerProductInfoVO();

                ProductCategory cat = catList.get(i);

                buyerProductInfoVO.setName(cat.getCategoryName());
                buyerProductInfoVO.setType(cat.getCategoryType());

                List<FoodVO> foodVOS = new ArrayList<>();
                List<ProductInfo> productInfoList = productInfoRepository.findAllByProductStatusAndCategoryType(0, cat.getCategoryType());
                if (productInfoList != null && productInfoList.size()>0){
                    for (ProductInfo productInfo : productInfoList){
                        FoodVO foodVO = DataTranferTool.transferProductInfo(productInfo);
                        foodVOS.add(foodVO);
                    }
                }

                buyerProductInfoVO.setFoods(foodVOS);
                productVoList.add(buyerProductInfoVO);

            }
        }
        return productVoList;
    }

    @Override
    public Page<ProductInfo> selectByCondition(ProductVo productVo) {
        Specification<ProductInfo> spec = new Specification<ProductInfo>() {
            @Override
            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                if (productVo.getProductName() != null && !productVo.getProductName().equals("")){

                    Predicate p1 = cb.like(root.get("productName"), "%" + productVo.getProductName() + "%");
                    list.add(p1);
                }


                if (productVo.getMinPrice() != null){

                    Predicate p2 = cb.ge(root.get("productPrice"),  productVo.getMinPrice());
                    list.add(p2);
                }


                if (productVo.getMaxPrice() != null){

                    Predicate p3 = cb.le(root.get("productPrice"),  productVo.getMaxPrice());
                    list.add(p3);
                }

                if (productVo.getProductType() != null){

                    Predicate p4 = cb.equal(root.get("productType"),  productVo.getProductType());
                    list.add(p4);
                }

                if (productVo.getPropName() != null){
                    if (productVo.getStartDate() != null){
                        Predicate p5 = cb.greaterThanOrEqualTo(root.get(productVo.getPropName()),  productVo.getStartDate());
                        list.add(p5);
                    }
                    if (productVo.getEndDate() != null){
                        Predicate p6 = cb.lessThanOrEqualTo(root.get(productVo.getPropName()),  productVo.getStartDate());
                        list.add(p6);
                    }
                }


                Predicate[] newArr = list.toArray(new Predicate[list.size()]);

                return cb.and(newArr);

            }
        };
        Page<ProductInfo> page = productInfoRepository.findAll(spec, PageRequest.of(productVo.getPageNum() - 1, productVo.getPageSize()));
        return page;
    }

    @Override
    public List<ProductCategory> selectAllCats() {
        return productCategoryRepository.findAll();
    }
}
