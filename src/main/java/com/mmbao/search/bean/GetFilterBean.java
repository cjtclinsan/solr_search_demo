package com.mmbao.search.bean;

/**
 * 过滤商品条件
 * 可用网站部位：
 *      看了又看
 *      推荐商品
 *      热销商品
 * Created by QPing on 2018/1/3.
 */
public class GetFilterBean {

    // ----------------------------- 预留设计，用于后期扩展 --------------------------------//
    /** 频道英文简写
          大首页:ids
          找电缆：dhs
          电工电气：dqs
          商品详情页：pts
          金具附件：jjs
          资讯频道：news
          百科频道：baike
          搜索频道：pss
     */
    String channel;
    // 页面名称，例如：详情页为detail，首页为index
    String page;
    // 页面模块，例如：热销商品为hot
    String module;
    // ----------------------------- 预留设计，用于后期扩展--------------------------------//

    public static final int TYPE_MOST_SALE = 1;         // 热销商品
    public static final int TYPE_RANDOM_RECOMMEND = 2;  //随机推荐

    // 用户VIP等级
    String vipVal;
    // 需要查询的类型
    int type;
    // 一次返回多少条
    int rows;
    // 第几页
    int pageNo = 1;
    // 二级分类
    String CN2;
    // 三级分类
    String CN3;

    public String getVipVal() {
        return vipVal;
    }

    public void setVipVal(String vipVal) {
        this.vipVal = vipVal;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public static int getTypeMostSale() {
        return TYPE_MOST_SALE;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getCN2() {
        return CN2;
    }

    public void setCN2(String CN2) {
        this.CN2 = CN2;
    }

    public String getCN3() {
        return CN3;
    }

    public void setCN3(String CN3) {
        this.CN3 = CN3;
    }

    public static GetFilterBean getBean(){
        GetFilterBean bean = new GetFilterBean();

        bean.setChannel("default");
        bean.setPage("default");
        bean.setModule("default");

        bean.setRows(5);
        bean.setPageNo(1);
        bean.setVipVal(null);
        return bean;
    }
}
