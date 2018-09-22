package com.mmbao.search.service;

import com.mmbao.search.bean.GetFilterBean;
import com.mmbao.search.bean.ProductSearchBean;
import com.mmbao.search.bean.ProductSearchBeanList;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2.
 */
public interface ProductGetBaseService {



    /**
     * 通过SKUID获取商品
     * @param skuId
     * @return
     */
    public ProductSearchBean getProduct(String vipVal, String skuId) throws Exception;

    /**
     * 通过SKUID列表获取商品
     * @param skuIds
     * @return
     */
    public List<ProductSearchBean> getProductList(String vipVal, List<String> skuIds) throws Exception;

    /**
     * 通过SkuId列表获取商品Map
     * @param skuIds
     * @return
     */
    public Map<String,ProductSearchBean> getProductMap(String vipVal, List<String> skuIds) throws Exception;

    /**
     * 通过指定条件获取商品List
     * @param filterBean
     * @return
     */
    public ProductSearchBeanList getProductListByType(GetFilterBean filterBean) throws Exception;


}
