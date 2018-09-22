package com.mmbao.search.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19.
 */
public class SearchQueryBean {

    public static final String VIP_VAL_DEFAULT = "1";
    public static final String VIP_FIELD_PREFIX = "price_";
    public static final String VIP_FIELD_SUFFIX = "_d";


    String keywords;                                    // 用户搜索词
    String analysisWords;                               // 用户搜索词分词
    Map<String, String> filterMap;                      // solr的fq参数内容
    int pageNum;                                        // 当前第几页，从1开始
    int rows;                                           // 每页记录数
    String order;                                       // 结果集排序，prtAttention or saleCount or prtPrice
    String cur;                                         // 结果集正序或倒序，desc or asc
    List<String> brandNames;                            // 现有品牌名列表
    Map<String, String> attrNamesMap;                   // 属性名中文对照
    String vipVal;                                      // 用户VIP等级
    // -------------- 记录用户搜索记录 ------------------- //
    String source;                                      // 1 PC 2 IOS 3 H5 4 ANDROID, source 为空时不记录此次搜索历史
    BigDecimal memberId;                                // 账号ID
    String sessionId;                                   // 预留
    String imei;                                        // IMEI
    String searchIp;                                    // 搜索IP地址
    // -------------- 属性过滤列表查询时使用 ------------------- //
    boolean isAttrSort = true;                          // 返回属性过滤列表是否需要排序
    boolean isFuzzy = false;                            // 返回属性过滤列表是否需要模糊搜索，调用 getProductFuzzyList 方法后自动变为true
    List<SearchParentCategoryBean> categoryBeanList;    // 当前商品集合所有分类
    // --------------------------------- //

    public String getVipVal() {
        return vipVal;
    }

    public void setVipVal(String vipVal) {
        this.vipVal = vipVal;
    }

    public Map<String, String> getAttrNamesMap() {
        return attrNamesMap;
    }

    public void setAttrNamesMap(Map<String, String> attrNamesMap) {
        this.attrNamesMap = attrNamesMap;
    }

    public List<SearchParentCategoryBean> getCategoryBeanList() {
        return categoryBeanList;
    }

    public void setCategoryBeanList(List<SearchParentCategoryBean> categoryBeanList) {
        this.categoryBeanList = categoryBeanList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAnalysisWords() {
        return analysisWords;
    }

    public void setAnalysisWords(String analysisWords) {
        this.analysisWords = analysisWords;
    }

    public Map<String, String> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, String> filterMap) {
        this.filterMap = filterMap;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }

    public boolean isAttrSort() {
        return isAttrSort;
    }

    public void setAttrSort(boolean attrSort) {
        isAttrSort = attrSort;
    }

    public boolean isFuzzy() {
        return isFuzzy;
    }

    public void setFuzzy(boolean fuzzy) {
        isFuzzy = fuzzy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getMemberId() {
        return memberId;
    }

    public void setMemberId(BigDecimal memberId) {
        this.memberId = memberId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSearchIp() {
        return searchIp;
    }

    public void setSearchIp(String searchIp) {
        this.searchIp = searchIp;
    }


}
