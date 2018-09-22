package com.mmbao.search.bean;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SearchParentCategoryBean {

    private String value;            // 中文名
    private int count;            // 数量
    private Set<SearchCatgoryBean> catgorysBeans;
    private Set<String> childValues;

    public void setChildValues(Set<String> childValues) {
        this.childValues = childValues;
    }

    public Set<String> getChildValues() {
        Set<String> hs = new TreeSet<>();
        for (SearchCatgoryBean sc : catgorysBeans) {
            hs.add(sc.getValue());
        }
        return hs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getValue().equals(((SearchParentCategoryBean) obj).getValue());
    }

    public Set<SearchCatgoryBean> getCatgorysBeans() {
        return catgorysBeans;
    }

    public void setCatgorysBeans(Set<SearchCatgoryBean> catgorysBeans) {
        this.catgorysBeans = catgorysBeans;
    }

}
