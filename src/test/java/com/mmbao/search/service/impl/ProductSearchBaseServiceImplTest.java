package com.mmbao.search.service.impl;

import com.mmbao.search.bean.ProductDebugBeanList;
import com.mmbao.search.bean.SearchQueryBean;
import junit.framework.TestCase;

/**
 * Created by Administrator on 2018/1/10.
 */
public class ProductSearchBaseServiceImplTest extends TestCase {
    public void testDebug() throws Exception {
        ProductSearchBaseServiceImpl impl = new ProductSearchBaseServiceImpl();
        impl.solrIndexUrl = "http://beta.mmbao.com:28983/solr/mmb2";

        String keywords = "5UB01061CC122";
        String analysisWords = impl.getAnalysisWords(keywords);

        System.out.println(analysisWords);

        SearchQueryBean queryBean = new SearchQueryBean();
        queryBean.setPageNum(1);
        queryBean.setRows(50);
        queryBean.setKeywords(keywords);
        queryBean.setAnalysisWords(analysisWords);

        ProductDebugBeanList res = impl.debug(queryBean);

        System.out.println(res.getPrtList().size());
    }

}