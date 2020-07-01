package com.ideacome.logistics.entity;

import java.util.Date;

public class ShellLogistics {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.id
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.create_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.modify_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Date modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.version
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Long version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.tracking_no
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private String trackingNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.delivery_corp_code
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private String deliveryCorpCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.dest_nu
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private String destNu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.dest_com
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private String destCom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shell_logistics.dest_state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    private Integer destState;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.id
     *
     * @return the value of shell_logistics.id
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.id
     *
     * @param id the value for shell_logistics.id
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.create_date
     *
     * @return the value of shell_logistics.create_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.create_date
     *
     * @param createDate the value for shell_logistics.create_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.modify_date
     *
     * @return the value of shell_logistics.modify_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.modify_date
     *
     * @param modifyDate the value for shell_logistics.modify_date
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.version
     *
     * @return the value of shell_logistics.version
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.version
     *
     * @param version the value for shell_logistics.version
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.tracking_no
     *
     * @return the value of shell_logistics.tracking_no
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.tracking_no
     *
     * @param trackingNo the value for shell_logistics.tracking_no
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo == null ? null : trackingNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.delivery_corp_code
     *
     * @return the value of shell_logistics.delivery_corp_code
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public String getDeliveryCorpCode() {
        return deliveryCorpCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.delivery_corp_code
     *
     * @param deliveryCorpCode the value for shell_logistics.delivery_corp_code
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setDeliveryCorpCode(String deliveryCorpCode) {
        this.deliveryCorpCode = deliveryCorpCode == null ? null : deliveryCorpCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.state
     *
     * @return the value of shell_logistics.state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.state
     *
     * @param state the value for shell_logistics.state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.dest_nu
     *
     * @return the value of shell_logistics.dest_nu
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public String getDestNu() {
        return destNu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.dest_nu
     *
     * @param destNu the value for shell_logistics.dest_nu
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setDestNu(String destNu) {
        this.destNu = destNu == null ? null : destNu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.dest_com
     *
     * @return the value of shell_logistics.dest_com
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public String getDestCom() {
        return destCom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.dest_com
     *
     * @param destCom the value for shell_logistics.dest_com
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setDestCom(String destCom) {
        this.destCom = destCom == null ? null : destCom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shell_logistics.dest_state
     *
     * @return the value of shell_logistics.dest_state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public Integer getDestState() {
        return destState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shell_logistics.dest_state
     *
     * @param destState the value for shell_logistics.dest_state
     *
     * @mbggenerated Mon Mar 27 14:39:12 CST 2017
     */
    public void setDestState(Integer destState) {
        this.destState = destState;
    }
}