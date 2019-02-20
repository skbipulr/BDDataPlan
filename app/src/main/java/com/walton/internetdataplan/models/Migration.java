package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 12/20/2016.
 */

public class Migration {
    String operator, opPacakge, processType, dialCode, smsBody, smsCode, detailsGpToGp,detailsGpToOthers,detailsFnF,detailsPulse,detailsSMS,operatorType;

    public Migration(String operator,String opPacakge, String processType, String dialCode, String smsBody, String smsCode,String detailsGpToGp,String detailsGpToOthers,String detailsFnF,String detailsPulse,String detailsSMS,String operatorType) {
        this.operator = operator;
        this.opPacakge = opPacakge;
        this.processType = processType;
        this.dialCode = dialCode;
        this.smsBody = smsBody;
        this.smsCode = smsCode;
        this.detailsGpToGp = detailsGpToGp;
        this.detailsGpToOthers = detailsGpToOthers;
        this.detailsFnF = detailsFnF;
        this.detailsPulse = detailsPulse;
        this.detailsSMS = detailsSMS;
        this.operatorType = operatorType;

    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOpPacakge() {
        return opPacakge;
    }

    public void setOpPacakge(String opPacakge) {
        this.opPacakge = opPacakge;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getDetailsGpToGp() {
        return detailsGpToGp;
    }

    public void setDetailsGpToGp(String detailsGpToGp) {
        this.detailsGpToGp = detailsGpToGp;
    }

    public String getDetailsGpToOthers() {
        return detailsGpToOthers;
    }

    public void setDetailsGpToOthers(String detailsGpToOthers) {
        this.detailsGpToOthers = detailsGpToOthers;
    }

    public String getDetailsFnF() {
        return detailsFnF;
    }

    public void setDetailsFnF(String detailsFnF) {
        this.detailsFnF = detailsFnF;
    }

    public String getDetailsPulse() {
        return detailsPulse;
    }

    public void setDetailsPulse(String detailsPulse) {
        this.detailsPulse = detailsPulse;
    }

    public String getDetailsSMS() {
        return detailsSMS;
    }

    public void setDetailsSMS(String detailsSMS) {
        this.detailsSMS = detailsSMS;
    }
}
