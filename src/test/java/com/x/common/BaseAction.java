package com.x.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by x on 2017/12/24.
 */

public class BaseAction extends BaseOps {
    protected String caseId;
    protected String caseDescription;
    protected List<CheckPoint> checkPoints = new ArrayList<CheckPoint>();

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public List<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(List<CheckPoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

    public void setCheckPoint(Object actual,Object expectation,String comment){
        CheckPoint cp = new CheckPoint(caseId,actual,expectation,comment);
        checkPoints.add(cp);
    }

    public void check() {
        for (CheckPoint checkPoint : checkPoints) {
            checkPoint.check();   //验证
        }
        checkPoints = new ArrayList<CheckPoint>();//检查完毕后清空
    }
}
