package com.sy.pojo;

import java.util.Date;

public class EquipmentData {
    private Integer id;

    private Integer userId;

    private Integer heartrate;

    private String highpressure;

    private String bottompressure;

    private String bloodpressure;

    private String mocrocirculation;

    private String breathe;

    private String sleeping;

    private Integer stepWhen;

    private String carrieroad;

    private String sedentary;

    private String movementstate;

    private String bodytemp;

    private String humidity;

    private String crash;

    private Date createtime;

    private String qxygen;

    private String positioningS;

    private String positioningData;

    private Integer stepTime;

    private Integer stepEach;

    private String hrv;

    private String mood;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(Integer heartrate) {
        this.heartrate = heartrate;
    }

    public String getHighpressure() {
        return highpressure;
    }

    public void setHighpressure(String highpressure) {
        this.highpressure = highpressure == null ? null : highpressure.trim();
    }

    public String getBottompressure() {
        return bottompressure;
    }

    public void setBottompressure(String bottompressure) {
        this.bottompressure = bottompressure == null ? null : bottompressure.trim();
    }

    public String getBloodpressure() {
        return bloodpressure;
    }

    public void setBloodpressure(String bloodpressure) {
        this.bloodpressure = bloodpressure == null ? null : bloodpressure.trim();
    }

    public String getMocrocirculation() {
        return mocrocirculation;
    }

    public void setMocrocirculation(String mocrocirculation) {
        this.mocrocirculation = mocrocirculation == null ? null : mocrocirculation.trim();
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe == null ? null : breathe.trim();
    }

    public String getSleeping() {
        return sleeping;
    }

    public void setSleeping(String sleeping) {
        this.sleeping = sleeping == null ? null : sleeping.trim();
    }

    public Integer getStepWhen() {
        return stepWhen;
    }

    public void setStepWhen(Integer stepWhen) {
        this.stepWhen = stepWhen;
    }

    public String getCarrieroad() {
        return carrieroad;
    }

    public void setCarrieroad(String carrieroad) {
        this.carrieroad = carrieroad == null ? null : carrieroad.trim();
    }

    public String getSedentary() {
        return sedentary;
    }

    public void setSedentary(String sedentary) {
        this.sedentary = sedentary == null ? null : sedentary.trim();
    }

    public String getMovementstate() {
        return movementstate;
    }

    public void setMovementstate(String movementstate) {
        this.movementstate = movementstate == null ? null : movementstate.trim();
    }

    public String getBodytemp() {
        return bodytemp;
    }

    public void setBodytemp(String bodytemp) {
        this.bodytemp = bodytemp == null ? null : bodytemp.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public String getCrash() {
        return crash;
    }

    public void setCrash(String crash) {
        this.crash = crash == null ? null : crash.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getQxygen() {
        return qxygen;
    }

    public void setQxygen(String qxygen) {
        this.qxygen = qxygen == null ? null : qxygen.trim();
    }

    public String getPositioningS() {
        return positioningS;
    }

    public void setPositioningS(String positioningS) {
        this.positioningS = positioningS == null ? null : positioningS.trim();
    }

    public String getPositioningData() {
        return positioningData;
    }

    public void setPositioningData(String positioningData) {
        this.positioningData = positioningData == null ? null : positioningData.trim();
    }

    public Integer getStepTime() {
        return stepTime;
    }

    public void setStepTime(Integer stepTime) {
        this.stepTime = stepTime;
    }

    public Integer getStepEach() {
        return stepEach;
    }

    public void setStepEach(Integer stepEach) {
        this.stepEach = stepEach;
    }

    public String getHrv() {
        return hrv;
    }

    public void setHrv(String hrv) {
        this.hrv = hrv == null ? null : hrv.trim();
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood == null ? null : mood.trim();
    }
}