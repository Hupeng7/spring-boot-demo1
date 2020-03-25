package com.demo.mybatis.generator.entity;

import java.util.Date;

public class BindCardRecord {
    private Integer id;

    private String requestId;

    private String appId;

    private String userId;

    private String fundId;

    private String accountName;

    private String cardNo;

    private String idType;

    private String idNo;

    private String mobile;

    private String customerId;

    private String externalRefNumber;

    private String expiredDate;

    private String cvv2;

    private Byte status;

    private Byte valid;

    private String storablePan;

    private String token;

    private String tr1ResponseCode;

    private String tr1ResponseMsg;

    private String validCode;

    private String payToken;

    private String tr2ResponseCode;

    private String tr2ResponseMsg;

    private Date submitDate;

    private Date tr2BindDate;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    public BindCardRecord(Integer id, String requestId, String appId, String userId, String fundId, String accountName, String cardNo, String idType, String idNo, String mobile, String customerId, String externalRefNumber, String expiredDate, String cvv2, Byte status, Byte valid, String storablePan, String token, String tr1ResponseCode, String tr1ResponseMsg, String validCode, String payToken, String tr2ResponseCode, String tr2ResponseMsg, Date submitDate, Date tr2BindDate, Date createTime, Date updateTime, String updateBy) {
        this.id = id;
        this.requestId = requestId;
        this.appId = appId;
        this.userId = userId;
        this.fundId = fundId;
        this.accountName = accountName;
        this.cardNo = cardNo;
        this.idType = idType;
        this.idNo = idNo;
        this.mobile = mobile;
        this.customerId = customerId;
        this.externalRefNumber = externalRefNumber;
        this.expiredDate = expiredDate;
        this.cvv2 = cvv2;
        this.status = status;
        this.valid = valid;
        this.storablePan = storablePan;
        this.token = token;
        this.tr1ResponseCode = tr1ResponseCode;
        this.tr1ResponseMsg = tr1ResponseMsg;
        this.validCode = validCode;
        this.payToken = payToken;
        this.tr2ResponseCode = tr2ResponseCode;
        this.tr2ResponseMsg = tr2ResponseMsg;
        this.submitDate = submitDate;
        this.tr2BindDate = tr2BindDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public BindCardRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber == null ? null : externalRefNumber.trim();
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate == null ? null : expiredDate.trim();
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2 == null ? null : cvv2.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    public String getStorablePan() {
        return storablePan;
    }

    public void setStorablePan(String storablePan) {
        this.storablePan = storablePan == null ? null : storablePan.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getTr1ResponseCode() {
        return tr1ResponseCode;
    }

    public void setTr1ResponseCode(String tr1ResponseCode) {
        this.tr1ResponseCode = tr1ResponseCode == null ? null : tr1ResponseCode.trim();
    }

    public String getTr1ResponseMsg() {
        return tr1ResponseMsg;
    }

    public void setTr1ResponseMsg(String tr1ResponseMsg) {
        this.tr1ResponseMsg = tr1ResponseMsg == null ? null : tr1ResponseMsg.trim();
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode == null ? null : validCode.trim();
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken == null ? null : payToken.trim();
    }

    public String getTr2ResponseCode() {
        return tr2ResponseCode;
    }

    public void setTr2ResponseCode(String tr2ResponseCode) {
        this.tr2ResponseCode = tr2ResponseCode == null ? null : tr2ResponseCode.trim();
    }

    public String getTr2ResponseMsg() {
        return tr2ResponseMsg;
    }

    public void setTr2ResponseMsg(String tr2ResponseMsg) {
        this.tr2ResponseMsg = tr2ResponseMsg == null ? null : tr2ResponseMsg.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getTr2BindDate() {
        return tr2BindDate;
    }

    public void setTr2BindDate(Date tr2BindDate) {
        this.tr2BindDate = tr2BindDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}