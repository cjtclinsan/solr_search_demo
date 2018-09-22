package com.mmbao.search.bean;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 * @version $Id: ProductDebugBeanConverter.java, v 0.1 2018-01-19 10:23:17 Administrator Exp $$
 */
public class ProductDebugBeanConverter {

    /**
     * Convert ProductDebugBean to ProductSearchBean
     * @param productSearchBean
     * @return
     */
    public static ProductDebugBean convertToProductSearchBean(ProductSearchBean productSearchBean) {
        if (productSearchBean == null) {
            return null;
        }
        ProductDebugBean bean = new ProductDebugBean();

        bean.setId(productSearchBean.getId());
        bean.setPrtCode(productSearchBean.getPrtCode());
        bean.setPrtTitle(productSearchBean.getPrtTitle());
        bean.setPrtOriginTitle(productSearchBean.getPrtOriginTitle());
        bean.setPrtSubtitle(productSearchBean.getPrtSubtitle());
        bean.setPrtPrice(productSearchBean.getPrtPrice());
        bean.setSaleCount(productSearchBean.getSaleCount());
        bean.setPrtAttention(productSearchBean.getPrtAttention());
        bean.setPrtKeywords(productSearchBean.getPrtKeywords());
        bean.setCatgoryId(productSearchBean.getCatgoryId());
        bean.setBrandId(productSearchBean.getBrandId());
        bean.setShopId(productSearchBean.getShopId());
        bean.setIsUse(productSearchBean.getIsUse());
        bean.setIsSale(productSearchBean.getIsSale());
        bean.setIsDelete(productSearchBean.getIsDelete());
        bean.setFrtBoardId(productSearchBean.getFrtBoardId());
        bean.setFpath1(productSearchBean.getFpath1());
        bean.setFpath2(productSearchBean.getFpath2());
        bean.setFpath3(productSearchBean.getFpath3());
        bean.setFpath4(productSearchBean.getFpath4());
        bean.setFpath5(productSearchBean.getFpath5());
        bean.setFpath6(productSearchBean.getFpath6());
        bean.setFpath7(productSearchBean.getFpath7());
        bean.setFpath8(productSearchBean.getFpath8());
        bean.setIsShowPhone(productSearchBean.getIsShowPhone());
        bean.setIsShowPad(productSearchBean.getIsShowPad());
        bean.setIsInvoice(productSearchBean.getIsInvoice());
        bean.setLogisticsType(productSearchBean.getLogisticsType());
        bean.setFreightType(productSearchBean.getFreightType());
        bean.setEmsFreightPrice(productSearchBean.getEmsFreightPrice());
        bean.setSurfaceFreightPrice(productSearchBean.getSurfaceFreightPrice());
        bean.setExpressFreightPrice(productSearchBean.getExpressFreightPrice());
        bean.setPriceType(productSearchBean.getPriceType());
        bean.setIsEngineerComment(productSearchBean.getIsEngineerComment());
        bean.setCommissionMode(productSearchBean.getCommissionMode());
        bean.setSalesVol(productSearchBean.getSalesVol());
        bean.setStoreAddrId(productSearchBean.getStoreAddrId());
        bean.setModifyDate(productSearchBean.getModifyDate());
        bean.setIsRecommend(productSearchBean.getIsRecommend());
        bean.setDensity(productSearchBean.getDensity());
        bean.setIsActivityPrice(productSearchBean.getIsActivityPrice());
        bean.setOldPrice(productSearchBean.getOldPrice());
        bean.setActType(productSearchBean.getActType());
        bean.setActId(productSearchBean.getActId());
        bean.setPoNumber(productSearchBean.getPoNumber());
        bean.setSeries(productSearchBean.getSeries());
        bean.setDqsPrtId(productSearchBean.getDqsPrtId());
        bean.setCreateDate(productSearchBean.getCreateDate());
        bean.setSaleDate(productSearchBean.getSaleDate());
        bean.setPrtId(productSearchBean.getPrtId());
        bean.setSkuId(productSearchBean.getSkuId());
        bean.setBoTYPE(productSearchBean.getBoTYPE());
        bean.setProvince(productSearchBean.getProvince());
        bean.setCity(productSearchBean.getCity());
        bean.setCN1(productSearchBean.getCN1());
        bean.setCN2(productSearchBean.getCN2());
        bean.setCN3(productSearchBean.getCN3());
        bean.setModel(productSearchBean.getModel());
        bean.setPrtInfo(productSearchBean.getPrtInfo());
        bean.setPrtCInfo(productSearchBean.getPrtCInfo());

        return bean;
    }
}
