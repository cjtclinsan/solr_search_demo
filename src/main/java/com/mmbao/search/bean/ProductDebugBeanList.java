package com.mmbao.search.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
public class ProductDebugBeanList {

    List<ProductDebugBean> prtList;

    long totalCount;

    public List<ProductDebugBean> getPrtList() {
        return prtList;
    }

    public void setPrtList(List<ProductDebugBean> prtList) {
        this.prtList = prtList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

}
