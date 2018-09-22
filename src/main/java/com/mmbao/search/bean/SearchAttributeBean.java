package com.mmbao.search.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/19.
 */
public class SearchAttributeBean {

    String facetName;
    String facetCName;
    List<String> facetValue;

    public String getFacetName() {
        return facetName;
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    public String getFacetCName() {
        return facetCName;
    }

    public void setFacetCName(String facetCName) {
        this.facetCName = facetCName;
    }

    public List<String> getFacetValue() {
        return facetValue;
    }

    public void setFacetValue(List<String> facetValue) {
        this.facetValue = facetValue;
    }
}
