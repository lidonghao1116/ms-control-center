package com.jiacer.modules.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserCertEntity implements Serializable{

    //工种名称
    private String workType;
    //鉴定等级
    private String authenticateGrade;
    //理论成绩
    private Double theoryScores;
    //技能成绩
    private Double poScores;
    //综合成绩
    private Double comprehensiveScores;
    //能力成绩
    private Double abilityScores;
    //评定结果
    private String dealResult;
    //考试结果
    private String examResult;
    //证书编号
    private String certificateNo;
    //发证日期
    private Date issuingDate;
    //发证机构
    private String certAuthorityId;
    //发证机构名称
    private String certOrgName;
    //证书名称
    private String certName;

    private String gradeName;

    private String examResultText;

    public String getExamResultText() {
        return examResultText;
    }

    public void setExamResultText(String examResultText) {
        this.examResultText = examResultText;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getAuthenticateGrade() {
        return authenticateGrade;
    }

    public void setAuthenticateGrade(String authenticateGrade) {
        this.authenticateGrade = authenticateGrade;
    }

    public String getTheoryScores() {
        if(theoryScores != null){
            return theoryScores+"";
        }
        return "--";
    }

    public void setTheoryScores(Double theoryScores) {
        this.theoryScores = theoryScores;
    }

    public String getPoScores() {
        if(poScores != null){
            return poScores+"";
        }
        return "--";
    }

    public void setPoScores(Double poScores) {
        this.poScores = poScores;
    }

    public String getComprehensiveScores() {
        if(comprehensiveScores != null){
            return comprehensiveScores+"";
        }
        return "--";
    }

    public void setComprehensiveScores(Double comprehensiveScores) {
        this.comprehensiveScores = comprehensiveScores;
    }

    public String getAbilityScores() {
        if(abilityScores != null){
            return abilityScores+"";
        }
        return "--";
    }

    public void setAbilityScores(Double abilityScores) {
        this.abilityScores = abilityScores;
    }

    public String getDealResult() {
        if(dealResult != null){
            return dealResult+"";
        }
        return "--";
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }


    public String getIssuingDate() {
        if(issuingDate != null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(issuingDate);
        }
        return "--";

    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getCertAuthorityId() {
        return certAuthorityId;
    }

    public void setCertAuthorityId(String certAuthorityId) {
        this.certAuthorityId = certAuthorityId;
    }

    public String getCertOrgName() {
        return certOrgName;
    }

    public void setCertOrgName(String certOrgName) {
        this.certOrgName = certOrgName;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
