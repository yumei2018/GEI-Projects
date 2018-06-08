/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "LI_USERS")
public class LiUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Column(name = "USER_INITIALS")
    private String userInitials;
    @Column(name = "DBUSER")
    private String dbuser;
    @Column(name = "MGR_ID")
    private Integer mgrId;
    @Column(name = "USER_SUPER")
    private String userSuper;
    @Column(name = "LI_USER_TYPE_ID")
    private Integer liUserTypeId;
    @Column(name = "LI_USER_ACTIVE")
    private String liUserActive;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "EMAIL_SEND")
    private Character emailSend;

    public LiUsers() {
    }

    public LiUsers(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserInitials() {
        return userInitials;
    }

    public void setUserInitials(String userInitials) {
        this.userInitials = userInitials;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getUserSuper() {
        return userSuper;
    }

    public void setUserSuper(String userSuper) {
        this.userSuper = userSuper;
    }

    public Integer getLiUserTypeId() {
        return liUserTypeId;
    }

    public void setLiUserTypeId(Integer liUserTypeId) {
        this.liUserTypeId = liUserTypeId;
    }

    public String getLiUserActive() {
        return liUserActive;
    }

    public void setLiUserActive(String liUserActive) {
        this.liUserActive = liUserActive;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Character getEmailSend() {
        return emailSend;
    }

    public void setEmailSend(Character emailSend) {
        this.emailSend = emailSend;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiUsers)) {
            return false;
        }
        LiUsers other = (LiUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiUsers[ userId=" + userId + " ]";
    }
    
}
