package com.mmbao.search.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.mmbao.search.bean.*;
import com.mmbao.search.exception.MmbSearchQueryException;
import com.mmbao.search.service.ProductGetBaseService;
import com.mmbao.search.service.ProductSearchBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mmbao.search.bean.SearchQueryBean.VIP_FIELD_PREFIX;
import static com.mmbao.search.bean.SearchQueryBean.VIP_FIELD_SUFFIX;

/**
 * Created by Administrator on 2018/1/3.
 */
@Service
public class ProductGetBaseServiceImpl implements ProductGetBaseService {

    @Autowired
    ProductSearchBaseService searchBaseService;

    ProductGetBaseService productGetBaseService;

    @Override
    public ProductSearchBean getProduct(String vipVal, String skuId) throws Exception {

        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("skuId", skuId);


        SearchQueryBean queryBean = new SearchQueryBean();
        queryBean.setFilterMap(filterMap);
        queryBean.setVipVal(vipVal);
        queryBean.setRows(1);
        queryBean.setPageNum(1);

        ProductSearchBeanList result = searchBaseService.getProductListByFilter(queryBean);

        if(result.getPrtList() == null || result.getPrtList().size() == 0) return null;
        return result.getPrtList().get(0);
    }

    @Override
    public List<ProductSearchBean> getProductList(String vipVal, List<String> skuIds) throws Exception {

        if(skuIds == null  || skuIds.size() == 0){
            return null;
        }

        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("skuId", "(" + StringUtils.join(skuIds.toArray(), " ") + ")");

        SearchQueryBean queryBean = new SearchQueryBean();
        queryBean.setFilterMap(filterMap);
        queryBean.setVipVal(vipVal);
        queryBean.setRows(skuIds.size());
        queryBean.setPageNum(1);

        ProductSearchBeanList result = searchBaseService.getProductListByFilter(queryBean);

        return result.getPrtList();
    }

    @Override
    public Map<String, ProductSearchBean> getProductMap(String vipVal, List<String> skuIds) throws Exception {
        List<ProductSearchBean> list = this.getProductList(vipVal, skuIds);
        if(list == null || list.size() == 0){
            return null;
        }

        Map<String, ProductSearchBean> map = new HashMap<>();
        for(ProductSearchBean bean : list){
            map.put(bean.getId(), bean);
        }
        return map;
    }

    @Override
    public ProductSearchBeanList getProductListByType(GetFilterBean filterBean) throws Exception {


        Map<String,String> filterMap = new HashMap<>();
        if(StringUtils.isNotBlank(filterBean.getCN2())){
            filterMap.put("CN2", filterBean.getCN2());
        }
        if(StringUtils.isNotBlank(filterBean.getCN3())){
            filterMap.put("CN3", filterBean.getCN3());
        }

        SearchQueryBean queryBean = new SearchQueryBean();
        queryBean.setFilterMap(filterMap);
        queryBean.setVipVal(filterBean.getVipVal());

        if(filterBean.getType() == GetFilterBean.TYPE_MOST_SALE){
            queryBean.setRows(filterBean.getRows());
            queryBean.setPageNum(filterBean.getPageNo());
        }
        else if(filterBean.getType() == GetFilterBean.TYPE_RANDOM_RECOMMEND){
            //随机数
            int pageRandom = RandomUtils.nextInt(50);
            queryBean.setRows(filterBean.getRows());
            queryBean.setPageNum(pageRandom);
        }else{
            queryBean.setRows(filterBean.getRows());
            queryBean.setPageNum(filterBean.getPageNo());
        }

        ProductSearchBeanList result = searchBaseService.getProductListByFilter(queryBean);
        return result;

    }
}
