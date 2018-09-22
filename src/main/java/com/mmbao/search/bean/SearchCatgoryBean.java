package com.mmbao.search.bean;

public class SearchCatgoryBean implements Comparable {
    //中文名
    private String value;
    //数量
    private int count;

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

    public boolean equals(Object obj) {
        return this.getValue().equals(((SearchCatgoryBean) obj).getValue());
    }

    public int compareTo(Object o) {
        return this.getValue().equals(((SearchCatgoryBean) o).getValue()) == true ? 0 : 1;

    }

}
