package com.btp_iitj.cnfmanag.Domain_Classes;

import android.text.format.Time;

import java.util.Date;

public class Registration {
    String  name, phone, email, secemail, secmob, salutation, regPackage, paymentMode, TransId,BankName, IfscCode, modeOfTrans;
    Boolean accomodation;
    Date  TransDate, dob, arrDate;
    Time arrTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecemail() {
        return secemail;
    }

    public void setSecemail(String secemail) {
        this.secemail = secemail;
    }

    public String getSecmob() {
        return secmob;
    }

    public void setSecmob(String secmob) {
        this.secmob = secmob;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getRegPackage() {
        return regPackage;
    }

    public void setRegPackage(String regPackage) {
        this.regPackage = regPackage;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransId() {
        return TransId;
    }

    public void setTransId(String transId) {
        TransId = transId;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getIfscCode() {
        return IfscCode;
    }

    public void setIfscCode(String ifscCode) {
        IfscCode = ifscCode;
    }

    public String getModeOfTrans() {
        return modeOfTrans;
    }

    public void setModeOfTrans(String modeOfTrans) {
        this.modeOfTrans = modeOfTrans;
    }

    public Boolean getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(Boolean accomodation) {
        this.accomodation = accomodation;
    }

    public Date getTransDate() {
        return TransDate;
    }

    public void setTransDate(Date transDate) {
        TransDate = transDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getArrDate() {
        return arrDate;
    }

    public void setArrDate(Date arrDate) {
        this.arrDate = arrDate;
    }

    public Time getArrTime() {
        return arrTime;
    }

    public void setArrTime(Time arrTime) {
        this.arrTime = arrTime;
    }
}
