package com.x.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Created by x on 2017/12/24.
 */

public class CheckPoint {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String caseId;
    private Object actual;
    private Object expectation;
    private String comment;

    public CheckPoint() {
    }

    public CheckPoint(Object actual, Object expectation) {
        this.actual = actual;
        this.expectation = expectation;
    }

    public CheckPoint(Object actual, Object expectation, String comment) {
        this.actual = actual;
        this.expectation = expectation;
        this.comment = comment;
    }

    public CheckPoint(String caseId, Object actual, Object expectation, String comment) {
        this.caseId = caseId;
        this.actual = actual;
        this.expectation = expectation;
        this.comment = comment;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Object getActual() {
        return actual;
    }

    public void setActual(Object actual) {
        this.actual = actual;
    }

    public Object getExpectation() {
        return expectation;
    }

    public void setExpectation(Object expectation) {
        this.expectation = expectation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void check() {
        if(comment == null){
            comment = "checkpoint";
        }
        if(caseId == null){
            caseId = "caseId";
        }
        if(actual.equals(expectation)){
            logger.info("caseId:{},comment:{},预期值：{}>>>>>>>>>>实际值：{},PASS",caseId,comment,expectation,actual);
        }else{
            logger.info("caseId:{},comment:{},预期值：{}>>>>>>>>>>实际值：{},PASS",caseId,comment,expectation,actual);
        }
        Assert.assertEquals(actual,expectation);
    }


}
