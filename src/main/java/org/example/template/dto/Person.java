package org.example.template.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/4 11:16:36
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private Integer AGE;
    private String BIRTHDAY;
    private String BPLACE;
    private String IDNO;
    private String IDTYPE;
    private String QUERY_STRING;
    private String RNAME;
    private String SEX;

    public Integer getAGE() {
        return AGE;
    }

    public void setAGE(Integer AGE) {
        this.AGE = AGE;
    }

    public String getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public String getBPLACE() {
        return BPLACE;
    }

    public void setBPLACE(String BPLACE) {
        this.BPLACE = BPLACE;
    }

    public String getIDNO() {
        return IDNO;
    }

    public void setIDNO(String IDNO) {
        this.IDNO = IDNO;
    }

    public String getIDTYPE() {
        return IDTYPE;
    }

    public void setIDTYPE(String IDTYPE) {
        this.IDTYPE = IDTYPE;
    }

    public String getQUERY_STRING() {
        return QUERY_STRING;
    }

    public void setQUERY_STRING(String QUERY_STRING) {
        this.QUERY_STRING = QUERY_STRING;
    }

    public String getRNAME() {
        return RNAME;
    }

    public void setRNAME(String RNAME) {
        this.RNAME = RNAME;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }
}
