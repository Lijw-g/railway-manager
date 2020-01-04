package com.railway.manager.model.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Table(name = "monitor_data")
public class MonitorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "id", orderNum = "0")
    private Long id;

    @Column(name = "created_at")
    @Excel(name = "时间", orderNum = "1", exportFormat = "yyyy-MM-dd")
    private Date createdAt;

    @Column(name = "city")
    @Excel(name = "城市", orderNum = "2")
    private int city;

    @Column(name = "line")
    @Excel(name = "车次", orderNum = "3")
    private String line;

    @Column(name = "deviceId")
    @Excel(name = "车门型号", orderNum = "4")
    private String deviceId;

    @Column(name = "m_vstate")
    @Excel(name = "门控器电压", orderNum = "5")
    private String mVstate;

    @Column(name = "m_astate")
    @Excel(name = "门控器电流", orderNum = "6")
    private String mAstate;

    @Column(name = "m_tstate")
    @Excel(name = "门控器温度", orderNum = "7")
    private String mTstate;

    @Column(name = "d_vstate")
    @Excel(name = "电机电压", orderNum = "8")
    private String dVstate;

    @Column(name = "d_astate")
    @Excel(name = "电机电流", orderNum = "9")
    private String dAstate;

    @Column(name = "d_tstate")
    @Excel(name = "电机温度", orderNum = "10")
    private String dTstate;

    @Column(name = "degree")
    @Excel(name = "门开合度", orderNum = "11")
    private String degree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getmVstate() {
        return mVstate;
    }

    public void setmVstate(String mVstate) {
        this.mVstate = mVstate;
    }

    public String getmAstate() {
        return mAstate;
    }

    public void setmAstate(String mAstate) {
        this.mAstate = mAstate;
    }

    public String getmTstate() {
        return mTstate;
    }

    public void setmTstate(String mTstate) {
        this.mTstate = mTstate;
    }

    public String getdVstate() {
        return dVstate;
    }

    public void setdVstate(String dVstate) {
        this.dVstate = dVstate;
    }

    public String getdAstate() {
        return dAstate;
    }

    public void setdAstate(String dAstate) {
        this.dAstate = dAstate;
    }

    public String getdTstate() {
        return dTstate;
    }

    public void setdTstate(String dTstate) {
        this.dTstate = dTstate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
