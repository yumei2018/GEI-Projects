/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "EP_USERS")
public class EpUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Column(name = "USER_INITIALS")
    private String userInitials;
    @Column(name = "DBUSER")
    private String dbuser;
    @Column(name = "USER_ACTIVE")
    private String userActive;
    @Column(name = "EP_PROCESS_CONTACT_ID")
    private Integer epProcessContactId;
    @Column(name = "EP_USER_ADMIN")
    private String epUserAdmin;
    @Column(name = "EP_LETTER_FOLDER")
    private String epLetterFolder;

    public EpUsers() {
    }

    public EpUsers(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getUserActive() {
        return userActive;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }

    public Integer getEpProcessContactId() {
        return epProcessContactId;
    }

    public void setEpProcessContactId(Integer epProcessContactId) {
        this.epProcessContactId = epProcessContactId;
    }

    public String getEpUserAdmin() {
        return epUserAdmin;
    }

    public void setEpUserAdmin(String epUserAdmin) {
        this.epUserAdmin = epUserAdmin;
    }

    public String getEpLetterFolder() {
        return epLetterFolder;
    }

    public void setEpLetterFolder(String epLetterFolder) {
        this.epLetterFolder = epLetterFolder;
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
        if (!(object instanceof EpUsers)) {
            return false;
        }
        EpUsers other = (EpUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpUsers[ userId=" + userId + " ]";
    }
    
}
