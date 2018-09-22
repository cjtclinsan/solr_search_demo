package com.cableex.mall.domain.crm.primitive;

import com.cableex.core.util.page.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmallSearchKeywordsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public CmallSearchKeywordsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSearchTimeIsNull() {
            addCriterion("SEARCH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSearchTimeIsNotNull() {
            addCriterion("SEARCH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSearchTimeEqualTo(Date value) {
            addCriterion("SEARCH_TIME =", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeNotEqualTo(Date value) {
            addCriterion("SEARCH_TIME <>", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeGreaterThan(Date value) {
            addCriterion("SEARCH_TIME >", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SEARCH_TIME >=", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeLessThan(Date value) {
            addCriterion("SEARCH_TIME <", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeLessThanOrEqualTo(Date value) {
            addCriterion("SEARCH_TIME <=", value, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeIn(List<Date> values) {
            addCriterion("SEARCH_TIME in", values, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeNotIn(List<Date> values) {
            addCriterion("SEARCH_TIME not in", values, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeBetween(Date value1, Date value2) {
            addCriterion("SEARCH_TIME between", value1, value2, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchTimeNotBetween(Date value1, Date value2) {
            addCriterion("SEARCH_TIME not between", value1, value2, "searchTime");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNull() {
            addCriterion("SEARCH_KEYWORDS is null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNotNull() {
            addCriterion("SEARCH_KEYWORDS is not null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsEqualTo(String value) {
            addCriterion("SEARCH_KEYWORDS =", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotEqualTo(String value) {
            addCriterion("SEARCH_KEYWORDS <>", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThan(String value) {
            addCriterion("SEARCH_KEYWORDS >", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("SEARCH_KEYWORDS >=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThan(String value) {
            addCriterion("SEARCH_KEYWORDS <", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThanOrEqualTo(String value) {
            addCriterion("SEARCH_KEYWORDS <=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLike(String value) {
            addCriterion("SEARCH_KEYWORDS like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotLike(String value) {
            addCriterion("SEARCH_KEYWORDS not like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIn(List<String> values) {
            addCriterion("SEARCH_KEYWORDS in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotIn(List<String> values) {
            addCriterion("SEARCH_KEYWORDS not in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsBetween(String value1, String value2) {
            addCriterion("SEARCH_KEYWORDS between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotBetween(String value1, String value2) {
            addCriterion("SEARCH_KEYWORDS not between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(BigDecimal value) {
            addCriterion("MEMBER_ID =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(BigDecimal value) {
            addCriterion("MEMBER_ID <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(BigDecimal value) {
            addCriterion("MEMBER_ID >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMBER_ID >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(BigDecimal value) {
            addCriterion("MEMBER_ID <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMBER_ID <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<BigDecimal> values) {
            addCriterion("MEMBER_ID in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<BigDecimal> values) {
            addCriterion("MEMBER_ID not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMBER_ID between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMBER_ID not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIn(List<String> values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotIn(List<String> values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("IMEI is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("IMEI is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("IMEI =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("IMEI <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("IMEI >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("IMEI >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("IMEI <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("IMEI <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("IMEI like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("IMEI not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("IMEI in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("IMEI not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("IMEI between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("IMEI not between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(String value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(String value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(String value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(String value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(String value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(String value) {
            addCriterion("IS_DELETE like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(String value) {
            addCriterion("IS_DELETE not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<String> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<String> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(String value1, String value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(String value1, String value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andSearchIpIsNull() {
            addCriterion("SEARCH_IP is null");
            return (Criteria) this;
        }

        public Criteria andSearchIpIsNotNull() {
            addCriterion("SEARCH_IP is not null");
            return (Criteria) this;
        }

        public Criteria andSearchIpEqualTo(String value) {
            addCriterion("SEARCH_IP =", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpNotEqualTo(String value) {
            addCriterion("SEARCH_IP <>", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpGreaterThan(String value) {
            addCriterion("SEARCH_IP >", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpGreaterThanOrEqualTo(String value) {
            addCriterion("SEARCH_IP >=", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpLessThan(String value) {
            addCriterion("SEARCH_IP <", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpLessThanOrEqualTo(String value) {
            addCriterion("SEARCH_IP <=", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpLike(String value) {
            addCriterion("SEARCH_IP like", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpNotLike(String value) {
            addCriterion("SEARCH_IP not like", value, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpIn(List<String> values) {
            addCriterion("SEARCH_IP in", values, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpNotIn(List<String> values) {
            addCriterion("SEARCH_IP not in", values, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpBetween(String value1, String value2) {
            addCriterion("SEARCH_IP between", value1, value2, "searchIp");
            return (Criteria) this;
        }

        public Criteria andSearchIpNotBetween(String value1, String value2) {
            addCriterion("SEARCH_IP not between", value1, value2, "searchIp");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLikeInsensitive(String value) {
            addCriterion("upper(SEARCH_KEYWORDS) like", value.toUpperCase(), "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSourceLikeInsensitive(String value) {
            addCriterion("upper(SOURCE) like", value.toUpperCase(), "source");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(TYPE) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andSessionIdLikeInsensitive(String value) {
            addCriterion("upper(SESSION_ID) like", value.toUpperCase(), "sessionId");
            return (Criteria) this;
        }

        public Criteria andImeiLikeInsensitive(String value) {
            addCriterion("upper(IMEI) like", value.toUpperCase(), "imei");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLikeInsensitive(String value) {
            addCriterion("upper(IS_DELETE) like", value.toUpperCase(), "isDelete");
            return (Criteria) this;
        }

        public Criteria andSearchIpLikeInsensitive(String value) {
            addCriterion("upper(SEARCH_IP) like", value.toUpperCase(), "searchIp");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated do_not_delete_during_merge Tue Nov 07 14:24:14 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_CMALL_SEARCH_KEYWORDS
     *
     * @mbggenerated Tue Nov 07 14:24:14 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}