package com.mmbao.search.service;

import com.mmbao.search.bean.*;
import com.mmbao.search.exception.MmbSearchQueryException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 抽象出搜索使用的方法，整合PC、移动端和H5的搜索代码
 * Created by QPing on 2017/11/18.
 */
@Service
public interface ProductSearchBaseService {

    /**
     * 获取用户输入关键词的分词结果
     * @param keywords 用户搜索词
     * @return
     */
    public String getAnalysisWords(String keywords);


    public Map<String, String> findAttrNamesMap();

    /**
     * 记录此次用户搜索
     * @param searchQueryBean
     */
    public void log(SearchQueryBean searchQueryBean) throws Exception;

    /**
     * 获取所有品牌
     * @return
     */
    public List<String> getBrandNames();

    /**
     * 精确搜索商品列表
     * @param searchQueryBean 查询条件
     * @return
     */
    public ProductSearchBeanList getProductList(SearchQueryBean searchQueryBean) throws Exception;

    /**
     * 模糊搜索商品列表
     * @param searchQueryBean 查询条件
     * @return
     */
    public ProductSearchBeanList getProductFuzzyList(SearchQueryBean searchQueryBean) throws Exception;


    /**
     * 根据分类获取属性名列表
     * @param queryBean
     * @return
     * @throws Exception
     */
    public List<String> getAttributeFields(SearchQueryBean queryBean) throws Exception;

    /**
     * 获取用户搜索结果过滤属性列表
     * @param searchQueryBean 查询条件
     * @return
     */
    public List<SearchAttributeBean> getProductAttributeList(SearchQueryBean searchQueryBean, List<String> fields) throws Exception;

    /**
     * 模糊搜索商品分类
     * @param searchQueryBean 查询条件
     * @return
     */
    public List<SearchParentCategoryBean> getProductCategoryList(SearchQueryBean searchQueryBean) throws MmbSearchQueryException;

    /**
     * 随机推荐一些商品
     * @param searchQueryBean
     * @return
     * @throws Exception
     */
    public ProductSearchBeanList getRecommedProductList(SearchQueryBean searchQueryBean) throws  Exception;



    /**
     * 获取商品接口
     * @param searchQueryBean
     * @return
     * @throws Exception
     */
    public ProductSearchBeanList getProductListByFilter(SearchQueryBean searchQueryBean) throws  Exception;

    public ProductDebugBeanList debug(SearchQueryBean searchQueryBean) throws Exception;


}
