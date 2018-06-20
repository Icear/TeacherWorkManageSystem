package com.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Token相关数据的储存结构
 */
public class TokenInfo implements Serializable {
    private TeacherEntity identity;
    private Calendar lastActiveTime;


    public TeacherEntity getIdentity() {
        return identity;
    }

    public void setIdentity(TeacherEntity identity) {
        this.identity = identity;
    }

    public Calendar getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Calendar lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "token='" +
                "identity=" + identity +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }
}
