package com.cableex.mall.persistence.xmall.primitive;

import com.cableex.mall.domain.xmall.primitive.VFrontCatgoryAttr4Solr;
import com.cableex.mall.domain.xmall.primitive.VFrontCatgoryAttr4SolrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFrontCatgoryAttr4SolrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int countByExample(VFrontCatgoryAttr4SolrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int deleteByExample(VFrontCatgoryAttr4SolrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int insert(VFrontCatgoryAttr4Solr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int insertSelective(VFrontCatgoryAttr4Solr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    List<VFrontCatgoryAttr4Solr> selectByExample(VFrontCatgoryAttr4SolrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int updateByExampleSelective(@Param("record") VFrontCatgoryAttr4Solr record, @Param("example") VFrontCatgoryAttr4SolrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table V_FRONT_CATGORY_ATTR_4SOLR
     *
     * @mbggenerated Mon May 25 13:56:26 CST 2015
     */
    int updateByExample(@Param("record") VFrontCatgoryAttr4Solr record, @Param("example") VFrontCatgoryAttr4SolrExample example);
}