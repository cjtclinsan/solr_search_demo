package com.mmbao.search.service.impl;

import com.cableex.mall.domain.crm.primitive.CmallSearchKeywords;
import com.cableex.mall.domain.xmall.primitive.PrtCatgoryAttrFront;
import com.cableex.mall.domain.xmall.primitive.PrtCatgoryAttrFrontExample;
import com.cableex.mall.domain.xmall.primitive.VFrontCatgoryAttr4Solr;
import com.cableex.mall.domain.xmall.primitive.VFrontCatgoryAttr4SolrExample;
import com.cableex.mall.persistence.crm.primitive.CmallSearchKeywordsMapper;
import com.cableex.mall.persistence.xmall.PrtCatgoryAttrFrontExtMapper;
import com.cableex.mall.persistence.xmall.primitive.VFrontCatgoryAttr4SolrMapper;
import com.mmbao.search.bean.*;
import com.mmbao.search.exception.MmbSearchLogException;
import com.mmbao.search.exception.MmbSearchQueryException;
import com.mmbao.search.service.ProductSearchBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.AnalysisParams;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

import static com.mmbao.search.bean.SearchQueryBean.VIP_FIELD_PREFIX;
import static com.mmbao.search.bean.SearchQueryBean.VIP_FIELD_SUFFIX;

/**
 * 通用搜索实现
 * Created by QPing on 2017/11/19.
 */
@Service
public class ProductSearchBaseServiceImpl implements ProductSearchBaseService {
    private Logger logger = LoggerFactory.getLogger(ProductSearchBaseService.class);


    @Value("${mmb.solr.product.solrj.url}")
    public String solrIndexUrl;

    //中文正则匹配
    private static Pattern chineseRegex = Pattern.compile("[\u4e00-\u9fa5]");

    @Autowired
    private VFrontCatgoryAttr4SolrMapper vfrontCatgoryAttr4SolrMapper;//前台分类和属性名称关联视图

    @Autowired
    private CmallSearchKeywordsMapper cmallSearchKeywordsMapper;

    @Autowired
    private PrtCatgoryAttrFrontExtMapper prtCatgoryAttrFrontExtMapper;  //前台属性


    public void log(SearchQueryBean searchQueryBean) throws Exception {

        String keywords = searchQueryBean.getKeywords();
        String source = searchQueryBean.getSource();
        BigDecimal memberId = searchQueryBean.getMemberId();
        String imei = searchQueryBean.getImei();
        String ipAddr = searchQueryBean.getSearchIp();

        if(StringUtils.isBlank(keywords) || StringUtils.isBlank(source)) {
            return;
        }

        if(keywords.getBytes().length > 512){
            throw new MmbSearchLogException("搜索词过长");
        }

        if(!"1".equals(source) && !"2".equals(source) && !"3".equals(source) && !"4".equals(source)){
            throw new MmbSearchLogException("source搜索来源非法！");
        }

        if(StringUtils.isNotBlank(imei) && imei.getBytes().length > 128){
            throw new MmbSearchLogException("imei过长");
        }

        if(StringUtils.isNotBlank(ipAddr) && ipAddr.getBytes().length > 128){
            throw new MmbSearchLogException("ip地址过长!");
        }

        logger.info("用户搜索 keywords:" + keywords + " source:" + source + " memberId:" + memberId + " imei:" + imei + " ip:" + ipAddr);

        try{
            // 插入关键词搜索记录
            CmallSearchKeywords searchKeywords = new CmallSearchKeywords();
            searchKeywords.setSearchKeywords(keywords);
            searchKeywords.setSearchTime(new Date());
            searchKeywords.setSource(source);//1 PC 2IOS 3H5 4AndriodH5
            searchKeywords.setMemberId(memberId);
            searchKeywords.setImei(imei);
            searchKeywords.setSearchIp(ipAddr);

            String jsonString = new JSONObject(searchKeywords).toString();
            logger.info("json:" + jsonString);

            cmallSearchKeywordsMapper.insertSelective(searchKeywords);
        }catch (Exception ex){
            throw new MmbSearchLogException("保存日志错误："  + ex.getMessage());
        }
    }

    public List<String> getBrandNames() {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setRows(0);
        solrQuery.setStart(0);
        solrQuery.setFacet(true);
        solrQuery.setFacetMinCount(1);
        solrQuery.addFacetField("bname_a");

        List<String> brands = new ArrayList<>();
        SolrServer server = null;
        try {
            showLog("获取品牌", solrIndexUrl ,solrQuery);
            server = new HttpSolrServer(solrIndexUrl);
            QueryResponse response = server.query(solrQuery);
            FacetField facetField = response.getFacetField("bname_a");
            for(FacetField.Count count : facetField.getValues()){
                brands.add(count.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }

        return brands;

    }

    public String getAnalysisWords(String keywords) {

        if(StringUtils.isBlank(keywords)){
            return "";
        }

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/analysis/field");
        solrQuery.set(AnalysisParams.FIELD_TYPE, "text_ik");
        solrQuery.set(AnalysisParams.QUERY, keywords);
        solrQuery.set(CommonParams.WT, "json");


        StringBuffer analysisBuffer = new StringBuffer();
        SolrServer server = null;
        try {
            System.out.println("solrIndexUrl:"+solrIndexUrl);
            server = new HttpSolrServer(solrIndexUrl);
            showLog("获取分词", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            NamedList<Object> r_analysis = (NamedList<Object>) response.getResponse().get("analysis");
            NamedList<Object> r_field_types = (NamedList<Object>) r_analysis.get("field_types");
            NamedList<Object> r_text_ik = (NamedList<Object>) r_field_types.get("text_ik");
            NamedList<Object> r_query = (NamedList<Object>) r_text_ik.get("query");
            List<SimpleOrderedMap<String>> list = (ArrayList<SimpleOrderedMap<String>>) r_query.get("org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter");

            for (Iterator<SimpleOrderedMap<String>> iter = list.iterator(); iter.hasNext(); ) {
                analysisBuffer.append(iter.next().get("text")).append(" ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
        return analysisBuffer.toString().trim();
    }

    public ProductSearchBeanList getProductList(SearchQueryBean queryBean) throws MmbSearchQueryException {


        String keywords = queryBean.getKeywords();
        String analysisWords = queryBean.getAnalysisWords();
        String vipVal = StringUtils.isBlank(queryBean.getVipVal()) ? SearchQueryBean.VIP_VAL_DEFAULT : queryBean.getVipVal();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/browse");
        solrQuery.setQuery(createQ(keywords, analysisWords));
        solrQuery.setRows(queryBean.getRows());
        solrQuery.setStart((Integer.valueOf(queryBean.getPageNum()) - 1) * Integer.valueOf(queryBean.getRows()));

        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("prtTitle");
        solrQuery.setHighlightSimplePre("<span style='color:red;'>");
        solrQuery.setHighlightSimplePost("</span>");

        // 分组统计
        solrQuery.setFacet(true);
        solrQuery.addFacetPivotField("CN2,CN3");

        // 属性过滤
        if(null != queryBean.getFilterMap()){
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }


        // 排序
        if (StringUtils.isNotEmpty(queryBean.getOrder()) && StringUtils.isNotEmpty(queryBean.getCur())){

            SolrQuery.ORDER order = "asc".equals(queryBean.getCur()) ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
            String field = queryBean.getOrder();
            if(queryBean.getOrder().equals("prtPrice")){
                field = VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX;
            }
            solrQuery.addSort(field, order);

            if(!queryBean.getOrder().equals("saleDate")){
                solrQuery.addSort("saleDate", SolrQuery.ORDER.desc);
            }
        }

        // 解析结果
        SolrServer server = null;
        ProductSearchBeanList productList = new ProductSearchBeanList();
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("精确搜索", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            SolrDocumentList documents = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            NamedList<List<PivotField>> facetPivotNameList = response.getFacetPivot();

            List<ProductSearchBean> prtList = new ArrayList<>();
            for(SolrDocument document : documents){
                System.out.println("document---"+document);
                ProductSearchBean bean = getProductByDocument(vipVal, document, highlighting);
                prtList.add(bean);
            }

            List<SearchParentCategoryBean> parentCategoryBeenList = new ArrayList<>();
            if (facetPivotNameList != null && facetPivotNameList.get("CN2,CN3") != null) {
                List<PivotField> facetPivot = facetPivotNameList.get("CN2,CN3");
                for (int i = 0; i < facetPivot.size(); i++) {
                    PivotField cn2Field = facetPivot.get(i);


                    Set<SearchCatgoryBean> catgoryBeanList = new TreeSet<>();

                    for (PivotField cn3Field: cn2Field.getPivot()){
                        SearchCatgoryBean catgory = new SearchCatgoryBean();
                        catgory.setValue(cn3Field.getValue().toString());
                        catgory.setCount(cn3Field.getCount());
                        catgoryBeanList.add(catgory);
                    }

                    SearchParentCategoryBean parentCategory = new SearchParentCategoryBean();
                    parentCategory.setValue(cn2Field.getValue().toString());
                    parentCategory.setCount(cn2Field.getCount());
                    parentCategory.setCatgorysBeans(catgoryBeanList);


                    parentCategoryBeenList.add(parentCategory);

                }
            }
            productList.setCatList(parentCategoryBeenList);
            productList.setTotalCount(documents.getNumFound());
            productList.setPrtList(prtList);

            return productList;

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }

    }

    public ProductSearchBeanList getProductFuzzyList(SearchQueryBean queryBean) throws MmbSearchQueryException {

        String keywords = queryBean.getKeywords();
        String analysisWords = queryBean.getAnalysisWords();
        String vipVal = StringUtils.isBlank(queryBean.getVipVal()) ? SearchQueryBean.VIP_VAL_DEFAULT : queryBean.getVipVal();

        if(queryBean.getBrandNames() == null){
            queryBean.setBrandNames(getBrandNames());
        }

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(createFuzzyQ(keywords, analysisWords, queryBean.getBrandNames()));
        solrQuery.setRows(queryBean.getRows());
        solrQuery.setStart((Integer.valueOf(queryBean.getPageNum()) - 1) * Integer.valueOf(queryBean.getRows()));

        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("prtTitle");
        solrQuery.setHighlightSimplePre("<span style=\"color:red;\">");
        solrQuery.setHighlightSimplePost("</span>");

        // 属性过滤
        if(null != queryBean.getFilterMap()) {
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }

        // 分组统计
        solrQuery.setFacet(true);
        solrQuery.addFacetPivotField("CN2,CN3");

        // 排序
        if (StringUtils.isNotEmpty(queryBean.getOrder()) && StringUtils.isNotEmpty(queryBean.getCur())){

            SolrQuery.ORDER order = "asc".equals(queryBean.getCur()) ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
            String field = queryBean.getOrder();
            if(queryBean.getOrder().equals("prtPrice")){
                field = VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX;
            }
            solrQuery.addSort(field, order);

            if(!queryBean.getOrder().equals("saleDate")){
                solrQuery.addSort("saleDate", SolrQuery.ORDER.desc);
            }
        }


        // 解析结果
        SolrServer server = null;
        ProductSearchBeanList productList = new ProductSearchBeanList();
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("模糊搜索", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            SolrDocumentList documents = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            NamedList<List<PivotField>> facetPivotNameList = response.getFacetPivot();

            List<ProductSearchBean> prtList = new ArrayList<>();
            for(SolrDocument document : documents){
                ProductSearchBean bean = getProductByDocument(vipVal, document, highlighting);
                prtList.add(bean);
            }

            List<SearchParentCategoryBean> parentCategoryBeenList = new ArrayList<>();
            if (facetPivotNameList != null && facetPivotNameList.get("CN2,CN3") != null) {
                List<PivotField> facetPivot = facetPivotNameList.get("CN2,CN3");
                for (int i = 0; i < facetPivot.size(); i++) {
                    PivotField cn2Field = facetPivot.get(i);

                    SearchParentCategoryBean parentCategory = new SearchParentCategoryBean();
                    parentCategory.setValue(cn2Field.getValue().toString());
                    parentCategory.setCount(cn2Field.getCount());

                    Set<SearchCatgoryBean> catgoryBeanList = new TreeSet<>();
                    for (PivotField cn3Field: cn2Field.getPivot()){
                        SearchCatgoryBean catgory = new SearchCatgoryBean();
                        catgory.setValue(cn3Field.getValue().toString());
                        catgory.setCount(cn3Field.getCount());
                        catgoryBeanList.add(catgory);
                    }
                    parentCategory.setCatgorysBeans(catgoryBeanList);

                    parentCategoryBeenList.add(parentCategory);

                }
            }
            productList.setCatList(parentCategoryBeenList);

            productList.setTotalCount(documents.getNumFound());
            productList.setPrtList(prtList);

            // 标识已进行过模糊查询
            if(documents.getNumFound() > 0){
                queryBean.setFuzzy(true);
            }

            return productList;

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }

    }

    public List<String> getAttributeFields(SearchQueryBean queryBean) throws MmbSearchQueryException{


        if(queryBean.getFilterMap().get("CN3") == null && (queryBean.getCategoryBeanList() == null)){
            throw new MmbSearchQueryException("CN3或者CategoryBeanList必须有一个不为空");
        }

        String CN2 = queryBean.getFilterMap().get("CN2");
        String CN3 = queryBean.getFilterMap().get("CN3");
        List<SearchParentCategoryBean> categoryList = queryBean.getCategoryBeanList();

        List<String> fields = new ArrayList<String>();
        try {

            List<String> fieldSet = new ArrayList<String>();

            // 如果三级分类不为空，则查询其所有的属性
            if (StringUtils.isNotBlank(CN3)) {
                fieldSet = findFrontAttrSetByFrontCatName(CN3);
            }
            // 如果二级分类不为空，且无三级分类，则查询二级分类下所有三级分类的属性
            else if (StringUtils.isNotBlank(CN2) &&  queryBean.getCategoryBeanList().size() > 0) {

                List<String> CN3Strings = new ArrayList<String>();
                for(SearchParentCategoryBean catBean : categoryList){
                    if(!CN2.equals(catBean.getValue())) continue;
                    for (SearchCatgoryBean child : catBean.getCatgorysBeans()){
                        if(child == null) continue;
                        CN3Strings.add(child.getValue());
                    }
                }
                fieldSet = findFrontAttrSetByFrontCatNameCN2(CN3Strings);
            }
            // 如果二级分类三级分类都为空，则取第一个二级分类的第一个三级分类，查询其所有的属性
            else if(StringUtils.isBlank(CN2) && StringUtils.isBlank(CN3) &&  queryBean.getCategoryBeanList().size() > 0){
                Iterator<SearchCatgoryBean> iterator = categoryList.get(0).getCatgorysBeans().iterator();
                if(iterator.hasNext()){
                    fieldSet = findFrontAttrSetByFrontCatName(iterator.next().getValue());
                }
            }

            if (null != fieldSet && fieldSet.size() > 0) {
                for (String field : fieldSet) {
                    if(StringUtils.isNotBlank(field)){
                        fields.add(field);
                    }
                }
            }

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        }

        return fields;
    }

    /**
     * 获取商品属性列表
     * 移动端和PC区别：
     *          1 移动端需要已经选中的属性，而PC端需要
     *          2 移动端需要第一次只查询CN3和品牌，有CN3时根据CN3查询属性。而PC没有CN3时，取CN2下所有CN3的属性，如果CN2也不存在则去第一个CN2的第一个CN3查询属性。
     * @param queryBean
     * @return
     * @throws MmbSearchQueryException
     */
    public List<SearchAttributeBean> getProductAttributeList(SearchQueryBean queryBean, List<String> fields) throws MmbSearchQueryException {

        String keywords = queryBean.getKeywords();
        String analysisWords = queryBean.getAnalysisWords();
        boolean isFuzzy = queryBean.isFuzzy();
        if(queryBean.getBrandNames() == null){
            queryBean.setBrandNames(getBrandNames());
        }
        if(queryBean.getAttrNamesMap() == null){
            Map<String, String> attrNamesMap = findAttrNamesMap();
            queryBean.setAttrNamesMap(attrNamesMap);
        }


        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(isFuzzy ? createFuzzyQ(keywords, analysisWords, queryBean.getBrandNames()) : createQ(keywords, analysisWords));
        solrQuery.setRows(0);
        solrQuery.setStart(0);
        solrQuery.setFacet(true);
        solrQuery.setFacetMinCount(1);

        if(null == fields){
            throw new MmbSearchQueryException("属性名列表不可传空");
        }

        for (String field : fields) {
            solrQuery.addFacetField(field);
        }

        // 属性过滤
        if(null != queryBean.getFilterMap()){
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }

        // 解析结果
        SolrServer server = null;
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("获取属性", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            List<FacetField> facetFieldList = response.getFacetFields();
            List<SearchAttributeBean> attributeBeanList = new ArrayList<>();


//            if(null != fields){
//                for (String field : fields) {
//                    FacetField factField = response.getFacetField(field);
//                    if(factField == null ){
//                        continue;
//                    }
//                    solrQuery.addFacetField(field);
//                }
//            }
            for(FacetField facetField : facetFieldList){

                String factName = facetField.getName();
                String factCName = queryBean.getAttrNamesMap().get(facetField.getName());

                List<String> values = new ArrayList<>();
                for(FacetField.Count factValue :facetField.getValues()){
                    String value = factValue.getName();
                    if(StringUtils.isBlank(value)){
                        continue;
                    }
                    values.add(value);
                }

                // 排除没有属性值的属性
                if(values.size() == 0){
                    continue;
                }

                // 对属性值进行排序（分类和品牌不用排序）
                if(!"CN2".equals(factName) && !"CN3".equals(factName) && !"bname_a".equals(factName) ){
                    Collections.sort(values, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            String s1 = o1.replaceAll("[^0-9]*", "");
                            String s2 = o2.replaceAll("[^0-9]*", "");
                            s1 = "".equals(s1) ? "0" : s1;
                            s2 = "".equals(s2) ? "0" : s2;
                            return new BigDecimal(s1).compareTo(new BigDecimal(s2));
                        }
                    });
                }

                SearchAttributeBean bean = new SearchAttributeBean();
                bean.setFacetName(factName);// 设置属性code
                bean.setFacetCName(factCName == null ? "" : factCName);// 设置属性中文名
                bean.setFacetValue(values);
                attributeBeanList.add(bean);
            }
            return attributeBeanList;
        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }

    }

    private void showLog(String title, String solrIndexUrl, SolrQuery solrQuery) {
        String   handler = solrQuery.getRequestHandler() == null ? "/select" : solrQuery.getRequestHandler();
        logger.info(title + "  " + solrIndexUrl + handler + "?" + solrQuery.toString());
    }

    @Override
    public List<SearchParentCategoryBean> getProductCategoryList(SearchQueryBean queryBean) throws MmbSearchQueryException {
        String keywords = queryBean.getKeywords();
        String analysisWords = queryBean.getAnalysisWords();
        boolean isFuzzy = queryBean.isFuzzy();
        if(queryBean.getBrandNames() == null){
            queryBean.setBrandNames(getBrandNames());
        }
        if(queryBean.getAttrNamesMap() == null){
            Map<String, String> attrNamesMap = findAttrNamesMap();
            queryBean.setAttrNamesMap(attrNamesMap);
        }
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(createQ(keywords, analysisWords));
        solrQuery.setRows(0);
        solrQuery.setStart(0);
        solrQuery.setFacet(true);
        solrQuery.addFacetPivotField("CN2,CN3");

        // 属性过滤
        if(null != queryBean.getFilterMap()){
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }

        // 解析结果
        SolrServer server = null;
        try {
            server = new HttpSolrServer(solrIndexUrl);
            QueryResponse response = server.query(solrQuery);
            NamedList<List<PivotField>> facetPivotNameList = response.getFacetPivot();
            List<SearchParentCategoryBean> parentCategoryBeenList = new ArrayList<>();
            if (facetPivotNameList != null && facetPivotNameList.get("CN2,CN3") != null) {
                List<PivotField> facetPivot = facetPivotNameList.get("CN2,CN3");
                for (int i = 0; i < facetPivot.size(); i++) {
                    PivotField cn2Field = facetPivot.get(i);


                    Set<SearchCatgoryBean> catgoryBeanList = new TreeSet<>();

                    for (PivotField cn3Field: cn2Field.getPivot()){
                        SearchCatgoryBean catgory = new SearchCatgoryBean();
                        catgory.setValue(cn3Field.getValue().toString());
                        catgory.setCount(cn3Field.getCount());
                        catgoryBeanList.add(catgory);
                    }

                    SearchParentCategoryBean parentCategory = new SearchParentCategoryBean();
                    parentCategory.setValue(cn2Field.getValue().toString());
                    parentCategory.setCount(cn2Field.getCount());
                    parentCategory.setCatgorysBeans(catgoryBeanList);


                    parentCategoryBeenList.add(parentCategory);

                }
            }

            return parentCategoryBeenList;
        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }

    @Override
    public ProductSearchBeanList getRecommedProductList(SearchQueryBean queryBean) throws Exception {

        String vipVal = StringUtils.isBlank(queryBean.getVipVal()) ? SearchQueryBean.VIP_VAL_DEFAULT : queryBean.getVipVal();
        //随机数
        int pageRandom = RandomUtils.nextInt(2000);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/browse");
        solrQuery.setQuery(createQ(null, null));
        solrQuery.setRows(10);
        solrQuery.setStart(pageRandom);

        // 排序
        if (StringUtils.isNotEmpty(queryBean.getOrder()) && StringUtils.isNotEmpty(queryBean.getCur())){

            SolrQuery.ORDER order = "asc".equals(queryBean.getCur()) ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
            String field = queryBean.getOrder();
            if(queryBean.getOrder().equals("prtPrice")){
                field = VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX;
            }
            solrQuery.addSort(field, order);

            if(!queryBean.getOrder().equals("saleDate")){
                solrQuery.addSort("saleDate", SolrQuery.ORDER.desc);
            }
        }


        // 解析结果
        SolrServer server = null;
        ProductSearchBeanList productList = new ProductSearchBeanList();
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("推荐商品", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            SolrDocumentList documents = response.getResults();

            List<ProductSearchBean> prtList = new ArrayList<>();
            for(SolrDocument document : documents){
                ProductSearchBean bean = getProductByDocument(vipVal, document, null);
                prtList.add(bean);
            }

            productList.setTotalCount(documents.getNumFound());
            productList.setPrtList(prtList);

            return productList;

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }

    }

    @Override
    public ProductSearchBeanList getProductListByFilter(SearchQueryBean queryBean) throws Exception {
        String vipVal = StringUtils.isBlank(queryBean.getVipVal()) ? SearchQueryBean.VIP_VAL_DEFAULT : queryBean.getVipVal();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/browse");
        solrQuery.setQuery(createQ(null, null));
        solrQuery.setRows(queryBean.getRows());
        solrQuery.setStart((queryBean.getPageNum() - 1) * queryBean.getRows());

        // 属性过滤
        if(null != queryBean.getFilterMap()){
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }


        // 排序
        if (StringUtils.isNotEmpty(queryBean.getOrder()) && StringUtils.isNotEmpty(queryBean.getCur())){

            SolrQuery.ORDER order = "asc".equals(queryBean.getCur()) ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
            String field = queryBean.getOrder();
            if(queryBean.getOrder().equals("prtPrice")){
                field = VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX;
            }
            solrQuery.addSort(field, order);

            if(!queryBean.getOrder().equals("saleDate")){
                solrQuery.addSort("saleDate", SolrQuery.ORDER.desc);
            }
        }


        // 解析结果
        SolrServer server = null;
        ProductSearchBeanList productList = new ProductSearchBeanList();
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("推荐商品", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            SolrDocumentList documents = response.getResults();

            List<ProductSearchBean> prtList = new ArrayList<>();
            for(SolrDocument document : documents){
                ProductSearchBean bean = getProductByDocument(vipVal, document, null);
                prtList.add(bean);
            }

            productList.setTotalCount(documents.getNumFound());
            productList.setPrtList(prtList);

            return productList;

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }

    @Override
    public ProductDebugBeanList debug(SearchQueryBean queryBean) throws Exception {
        String keywords = queryBean.getKeywords();
        String analysisWords = queryBean.getAnalysisWords();
        String vipVal = StringUtils.isBlank(queryBean.getVipVal()) ? SearchQueryBean.VIP_VAL_DEFAULT : queryBean.getVipVal();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/browse");
        solrQuery.setQuery(createQ(keywords, analysisWords));
        solrQuery.setRows(queryBean.getRows());
        solrQuery.setStart((Integer.valueOf(queryBean.getPageNum()) - 1) * Integer.valueOf(queryBean.getRows()));
        solrQuery.setShowDebugInfo(true);
        solrQuery.setParam(CommonParams.EXPLAIN_STRUCT, true);

        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("prtTitle");
        solrQuery.setHighlightSimplePre("<span style='color:red;'>");
        solrQuery.setHighlightSimplePost("</span>");

        // 分组统计
        solrQuery.setFacet(true);
        solrQuery.addFacetPivotField("CN2,CN3");

        // 属性过滤
        if(null != queryBean.getFilterMap()){
            Iterator<String> iter = queryBean.getFilterMap().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = queryBean.getFilterMap().get(key);
                if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
                    continue;
                }

                solrQuery.addFilterQuery(key + ":" + value);
            }
        }


        // 排序
        if (StringUtils.isNotEmpty(queryBean.getOrder()) && StringUtils.isNotEmpty(queryBean.getCur())){

            SolrQuery.ORDER order = "asc".equals(queryBean.getCur()) ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
            String field = queryBean.getOrder();
            if(queryBean.getOrder().equals("prtPrice")){
                field = VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX;
            }
            solrQuery.addSort(field, order);

            if(!queryBean.getOrder().equals("saleDate")){
                solrQuery.addSort("saleDate", SolrQuery.ORDER.desc);
            }
        }

        // 解析结果
        SolrServer server = null;
        ProductDebugBeanList productList = new ProductDebugBeanList();
        try {
            server = new HttpSolrServer(solrIndexUrl);
            showLog("debug", solrIndexUrl ,solrQuery);

            QueryResponse response = server.query(solrQuery);
            SolrDocumentList documents = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            Map<String, Object> debugMap = response.getDebugMap();

            List<ProductDebugBean> prtList = new ArrayList<>();
            for(SolrDocument document : documents){
                ProductSearchBean vo = getProductByDocument(vipVal, document, highlighting);
                ProductDebugBean debugBean = getDebugBean(vo, debugMap);

                prtList.add(debugBean);
            }

            productList.setTotalCount(documents.getNumFound());
            productList.setPrtList(prtList);

            return productList;

        } catch (Exception e) {
            throw new MmbSearchQueryException(e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }

    private ProductDebugBean getDebugBean(ProductSearchBean vo, Map<String, Object> debugMap) {


        NamedList explainNList = (NamedList) debugMap.get("explain");
        NamedList voNList = (NamedList) explainNList.get(vo.getId());
        Float totalScore = (Float) voNList.get("value");

        List<NamedList> details = (List<NamedList>) voNList.get("details");

        // 相关度得分
        NamedList relNList = details.get(0);
        Float relScore = (Float) relNList.get("value");

        // 加权得分
        NamedList funcNList = details.get(1);
        Float funcScore = (Float) funcNList.get("value");
        List<NamedList> funcDetails = (List<NamedList>) funcNList.get("details");
        NamedList queryNormNList = funcDetails.get(2);
        Float queryNorm = (Float) queryNormNList.get("value");

        ProductDebugBean debugBean = ProductDebugBeanConverter.convertToProductSearchBean(vo);

        debugBean.setScore(new BigDecimal(totalScore));
        debugBean.setRelationScore(new BigDecimal(relScore));
        debugBean.setFunctionScore(new BigDecimal(funcScore));
        debugBean.setQueryNorm(new BigDecimal(queryNorm));

        return debugBean;
    }

    private ProductSearchBean getProductByDocument(String vipVal, SolrDocument document, Map<String, Map<String, List<String>>> highlighting) {

        String id = document.get("id").toString();
        String prtId = document.get("prtId").toString();
        String skuId = document.get("skuId").toString();

        String prtCode = dealNull(document, "prtCode", "");
        String prtTitle = dealNull(document, "prtTitle", "");

        String prtPrice = dealNull(document, "prtPrice", "0");
        String vipPrice = dealNull(document, VIP_FIELD_PREFIX + vipVal + VIP_FIELD_SUFFIX, prtPrice);

        String saleCount = dealNull(document, "saleCount", "0");
        String shopId = dealNull(document, "shopId", "");
        String fpath1 = dealNull(document, "fpath1", "");
        String fpath1_310 = "";
        int lastIndex = fpath1.lastIndexOf(".");
        if(lastIndex != -1){
            fpath1_310 = fpath1.substring(0, lastIndex) + "_310" + fpath1.substring(lastIndex, fpath1.length());
        }

        String brandId = dealNull(document, "brandId", "");
        String prtSubtitle = dealNull(document, "prtSubtitle", ""); // document.get("prtSubtitle").getAsString();
        String bname_a = dealNull(document, "bname_a", "");
        String boTYPE = dealNull(document, "boTYPE", "");
        String cNAME = dealNull(document, "cNAME", "");
        String cTYPE = dealNull(document, "cTYPE", "");
        String commissionMode = dealNull(document, "commissionMode", "");
        String bTYPE = dealNull(document, "bTYPE", "");
        String isActivityPrice = dealNull(document, "isActivityPrice", "");
        String oldPrice = dealNull(document, "oldPrice", "0");
        String CN1 = dealNull(document, "CN1", "");
        String CN2 = dealNull(document, "CN2", "");
        String CN3 = dealNull(document, "CN3", "");
        String freightType = dealNull(document, "freightType", "");
        String bLogoPath = dealNull(document, "bLogoPath", "");
        String poNumber = dealNull(document, "poNumber", "");
        String model_a = dealNull(document, "model_a", "");
        String prtInfo = dealNull(document, "prtInfo", "");
        String prtCInfo = dealNull(document, "prtCInfo", "");
        String salesVol = dealNull(document, "salesVol", "");

        String province = dealNull(document, "province", "");
        String city = dealNull(document, "city", "");

        String prtAttention = dealNull(document, "prtAttention", "0");
        String density = dealNull(document, "density", "0");

        ProductSearchBean bean = new ProductSearchBean();
        bean.setId(id);
        bean.setPrtId(prtId);
        bean.setSkuId(skuId);
        bean.setPrtCode(prtCode);
        bean.setPrtOriginTitle(prtTitle);
        if (null != highlighting && highlighting.containsKey(id) && highlighting.get(id).containsKey("prtTitle")) {
            bean.setPrtTitle(highlighting.get(id).get("prtTitle").get(0));
        } else {
            bean.setPrtTitle(prtTitle);
        }
        bean.setPrtPrice(new BigDecimal(vipPrice));
        bean.setSaleCount(new BigDecimal(saleCount));
        bean.setShopId(new BigDecimal(shopId));
        bean.setFpath1(fpath1_310);
        bean.setBrandId(brandId);
        bean.setPrtSubtitle(prtSubtitle);
        bean.setbNAME(bname_a);
        bean.setbTYPE(bTYPE);
        bean.setBoTYPE(boTYPE);
        bean.setcNAME(cNAME);
        bean.setcTYPE(cTYPE);
        bean.setIsActivityPrice(isActivityPrice);
        bean.setOldPrice(new BigDecimal(oldPrice));
        bean.setCN3(CN3);
        bean.setCommissionMode(commissionMode);
        bean.setCN1(CN1);
        bean.setCN2(CN2);
        bean.setCN3(CN3);
        bean.setFreightType(freightType);
        bean.setbLogoPath(bLogoPath);
        bean.setPoNumber(poNumber);
        bean.setModel(model_a);
        bean.setPrtInfo(prtInfo);
        bean.setPrtCInfo(prtCInfo);
        bean.setSalesVol(new BigDecimal(salesVol));
        bean.setProvince(province);
        bean.setCity(city);
        bean.setPrtAttention(new BigDecimal(prtAttention));
        bean.setDensity(new BigDecimal(density));

        return bean;
    }

    private String dealNull(SolrDocument document, String field, String defaultValue){
        if(document == null) return defaultValue;

        Object fieldObj = document.getFirstValue(field);

        if(fieldObj != null && StringUtils.isNotBlank(fieldObj.toString())){
            return fieldObj.toString();
        }
        return defaultValue;
    }

    /**
     * 处理用户输入的关键词，按空格分开后处理特殊字符
     */
    private String createQ(String keywords, String analysisWords) throws MmbSearchQueryException {

        if (StringUtils.isBlank(keywords)) {
            return "*:*";
        }

        if (StringUtils.isBlank(analysisWords)) {
            throw new MmbSearchQueryException("createQ:analysisWords must be initialize first!");
        }

        String[] analysisSplit = analysisWords.split(" ");
        List<String> keywordList = new ArrayList();
        // 去除多余的空格
        for(int i = 0; i < analysisSplit.length; i++){
            String keyword = analysisSplit[i];
            if(StringUtils.isBlank(keyword)){
                continue;
            }
            keywordList.add(keyword);
        }

        StringBuffer keywordsAfterDeal = new StringBuffer();

        for (int i = 0; i < keywordList.size(); i++) {
            if (i != 0) {
                keywordsAfterDeal.append(" AND ");
            }
            keywordsAfterDeal.append("text_ik:").append(ClientUtils.escapeQueryChars(keywordList.get(i)));
        }

        return keywordsAfterDeal.toString();
    }

    private String createFuzzyQ(String keywords, String analysisWords, List<String> brandNames) throws MmbSearchQueryException {
        if(StringUtils.isBlank(keywords)){
            return "*:*";
        }

        if (StringUtils.isBlank(analysisWords)) {
            throw new MmbSearchQueryException("createFuzzyQ:analysisWords must be initialize first!");
        }

        List<String> keywordList = new ArrayList<>();
        String[] keywordsArr = keywords.split(" ");
        // 去除多余的空格
        for(int i = 0; i < keywordsArr.length; i++){
            String keyword = keywordsArr[i];
            if(StringUtils.isBlank(keyword)){
                continue;
            }
            keywordList.add(keyword);
        }


        List<String> queryParseList = new ArrayList<>();
        // 特殊处理  add by qping 20170918 增加对于输入单个词情况下，其中可能活包含品牌、型号、订货号的处理
        // 用例：施耐德A9F18232，iC65N，ABBS355，施耐德开关面板
        // 方案：提取品牌（品牌只能出现在头部）后对于剩下的部分，模糊匹配型号和订货号
        if(keywordList.size() == 1){
            //op = "OR";
            String keyword = keywordList.get(0);
            String brandMatch = matchBrand(keyword, brandNames);
            String leftWords = brandMatch == null ? keyword : keyword.substring(brandMatch.length(), keyword.length());

            if(brandMatch != null && StringUtils.isNotBlank(leftWords)){
                queryParseList.add("bname_a:" + brandMatch);
            }

            // 如果剩下的字中没有中文，则模糊匹配
            if(StringUtils.isNotBlank(leftWords)){
                if(!isContainsChinese(leftWords)){
                    queryParseList.add("poNumber:*" + ClientUtils.escapeQueryChars(leftWords) + "*");
                    queryParseList.add("model_a:*" + ClientUtils.escapeQueryChars(leftWords) + "*");
                }
            }

        }

        // 特殊处理 add by qping 20170918 增加对于输入两个词情况下  一词为品牌 另一词为订货号和型号的处理
        // 用例：施耐德 9F18
        // 方案：按空格分词后判断两个词是否有一个是品牌，如果含有品牌，则将另一个词模糊匹配型号和订货号
        if(keywordList.size() == 2){
            String brandMatch = null;
            boolean isMatch = false;
            for(int i = 0; i < keywordList.size(); i++){
                brandMatch = matchBrand(keywordList.get(i), brandNames);
                if(brandMatch != null){
                    isMatch = true;
                }
            }

            if(isMatch){
                for(int i = 0; i < keywordList.size(); i++){
                    brandMatch = matchBrand(keywordList.get(i), brandNames);
                    if(brandMatch != null){
                        queryParseList.add("bname_a:" + brandMatch);
                        continue;
                    }

                    if(!isContainsChinese(keywordList.get(i))){
                        queryParseList.add("poNumber:*" + ClientUtils.escapeQueryChars(keywordList.get(i)) + "*");
                        queryParseList.add("model_a:*" + ClientUtils.escapeQueryChars(keywordList.get(i)) + "*");
                        continue;
                    }

                }
            }
        }


        if(keywordList.size() > 2){
            String brandMatch = null;
            boolean isMatch = false;
            for(int i = 0; i < keywordList.size(); i++){
                brandMatch = matchBrand(keywordList.get(i), brandNames);
                if(brandMatch != null){
                    isMatch = true;
                }
            }

            if(isMatch){
                for(int i = 0; i < keywordList.size(); i++){
                    brandMatch = matchBrand(keywordList.get(i), brandNames);
                    if(brandMatch != null){
                        queryParseList.add("bname_a:" + brandMatch);
                        continue;
                    }
                }
            }
        }

        StringBuffer keywordsAfterDeal = new StringBuffer();
        String[] analysisArr = analysisWords.split(" ");
        // text_ik处理
        for(int i = 0; i < analysisArr.length; i++){
            if(i != 0){
                keywordsAfterDeal.append(" OR ");
            }
            keywordsAfterDeal.append("text_ik:").append(ClientUtils.escapeQueryChars(analysisArr[i]));
        }

        // 特殊处理 add by qping 20170920 订单号、型号模糊搜索的处理
        for(int i = 0; i < queryParseList.size(); i++){
            keywordsAfterDeal.append(" OR ").append(queryParseList.get(i));
        }


        return keywordsAfterDeal.toString();
    }

    private String matchBrand(String s, List<String> brandNames) {
        if(StringUtils.isBlank(s)){
            return null;
        }

        for(String brand : brandNames){
            if(StringUtils.isBlank(brand)){
                continue;
            }
            if(s.startsWith(brand)){
                return brand;
            }
        }
        return null;
    }

    private static boolean isContainsChinese(String str) {
        return chineseRegex.matcher(str).find();
    }

    private List<String> findFrontAttrSetByFrontCatName(String catgoryName){
        List<String>  attrNames = new ArrayList<String>();
        VFrontCatgoryAttr4SolrExample example = new VFrontCatgoryAttr4SolrExample();
        VFrontCatgoryAttr4SolrExample.Criteria criteria = example.createCriteria();
        criteria.andLev3CatNameEqualTo(catgoryName);
        criteria.andIsShowEqualTo("1");
        example.setDistinct(true);
        example.setOrderByClause(" ATTR_SEQUENCE ASC ");
        List<VFrontCatgoryAttr4Solr> resultList = vfrontCatgoryAttr4SolrMapper.selectByExample(example);
        for (VFrontCatgoryAttr4Solr attr:resultList){
            if(StringUtils.isNotEmpty(attr.getAttrCode()))
                attrNames.add(attr.getAttrCode());
        }
        return attrNames;
    }

    private List<String> findFrontAttrSetByFrontCatNameCN2(List<String> cN2){
        List<String>  attrNames = new ArrayList<String>();
        VFrontCatgoryAttr4SolrExample  example = new VFrontCatgoryAttr4SolrExample();
        VFrontCatgoryAttr4SolrExample.Criteria criteria = example.createCriteria();
        criteria.andLev3CatNameIn(cN2);
        criteria.andIsShowEqualTo("1");
        example.setDistinct(true);
        example.setOrderByClause(" ATTR_SEQUENCE ASC ");
        List<VFrontCatgoryAttr4Solr> resultList = vfrontCatgoryAttr4SolrMapper.selectByExample(example);
        if(resultList.size()>=1){
            for (VFrontCatgoryAttr4Solr attr:resultList){
                if(!attrNames.contains(attr.getAttrCode()))
                    attrNames.add(attr.getAttrCode());
            }
            return attrNames;
        }else{
            return null;
        }
    }


    public Map<String, String> findAttrNamesMap() {

        Map<String,String> attriNames = new HashMap<String,String>();
        attriNames.put("bname_a", "品牌");
        List<PrtCatgoryAttrFront> attributes = new ArrayList<PrtCatgoryAttrFront>();
        PrtCatgoryAttrFrontExample example = new PrtCatgoryAttrFrontExample();
        PrtCatgoryAttrFrontExample.Criteria criteria = example.createCriteria();
        criteria.andIsShowEqualTo("1");
        criteria.andAttrCodeIsNotNull();
        example.isDistinct();
        attributes = prtCatgoryAttrFrontExtMapper.selectByExample(example);
        for(PrtCatgoryAttrFront attribute : attributes){
            attriNames.put(attribute.getAttrCode(), attribute.getAttrName());
        }

        return attriNames;
    }

}
