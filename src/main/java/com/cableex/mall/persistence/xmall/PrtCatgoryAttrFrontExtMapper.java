package com.cableex.mall.persistence.xmall;


import com.cableex.mall.domain.xmall.primitive.PrtCatgoryAttrFront;
import com.cableex.mall.domain.xmall.primitive.PrtCatgoryAttrFrontExample;

import java.util.List;

public interface PrtCatgoryAttrFrontExtMapper {
   
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRT_CATGORY_ATTR_FRONT
     *
     * @mbggenerated Sun May 17 15:17:22 CST 2015
     */
    List<PrtCatgoryAttrFront> selectByExample(PrtCatgoryAttrFrontExample example);

  
}