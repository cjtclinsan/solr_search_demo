package com.mmbao.search.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/18.
 */
public class ProductSearchBeanList {


    List<ProductSearchBean> prtList;

    List<SearchParentCategoryBean> catList;

    long totalCount;

    public List<ProductSearchBean> getPrtList() {
        return prtList;
    }

    public void setPrtList(List<ProductSearchBean> prtList) {
        this.prtList = prtList;
    }

    public List<SearchParentCategoryBean> getCatList() {
        return catList;
    }

    public void setCatList(List<SearchParentCategoryBean> catList) {
        this.catList = catList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
