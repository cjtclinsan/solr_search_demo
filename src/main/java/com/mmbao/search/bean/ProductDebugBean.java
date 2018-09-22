package com.mmbao.search.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/10.
 */
public class ProductDebugBean extends ProductSearchBean {
    private BigDecimal score;
    private BigDecimal queryNorm;
    private BigDecimal relationScore;
    private BigDecimal functionScore;



    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getQueryNorm() {
        return queryNorm;
    }

    public void setQueryNorm(BigDecimal queryNorm) {
        this.queryNorm = queryNorm;
    }

    public BigDecimal getRelationScore() {
        return relationScore;
    }

    public void setRelationScore(BigDecimal relationScore) {
        this.relationScore = relationScore;
    }

    public BigDecimal getFunctionScore() {
        return functionScore;
    }

    public void setFunctionScore(BigDecimal functionScore) {
        this.functionScore = functionScore;
    }
}
