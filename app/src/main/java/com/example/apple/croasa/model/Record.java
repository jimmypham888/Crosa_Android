package com.example.apple.croasa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("call_id")
    @Expose
    private String callId;
    @SerializedName("mobile_phone")
    @Expose
    private String mobilePhone;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("ring_time")
    @Expose
    private Integer ringTime;
    @SerializedName("link_down_record")
    @Expose
    private String linkDownRecord;

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRingTime() {
        return ringTime;
    }

    public void setRingTime(Integer ringTime) {
        this.ringTime = ringTime;
    }

    public String getLinkDownRecord() {
        return linkDownRecord;
    }

    public void setLinkDownRecord(String linkDownRecord) {
        this.linkDownRecord = linkDownRecord;
    }
}
