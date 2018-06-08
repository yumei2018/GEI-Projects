/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "LI_PERMIT_STATUS")
public class LiPermitStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LiPermitStatusPK liPermitStatusPK;
    @Column(name = "USER_ID")
    private Integer userId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    private LiUsers liUsers;
    @ManyToOne(optional = false)
    @JoinColumn(name = "LI_STATUS_ID", referencedColumnName = "LI_STATUS_ID", insertable = false, updatable = false)
    private LiStatusLookup liStatusLookup;

    public LiPermitStatus() {
    }

    public LiPermitStatus(LiPermitStatusPK liPermitStatusPK) {
        this.liPermitStatusPK = liPermitStatusPK;
    }

    public LiPermitStatus(Integer epId, Date permitStatusDate, Integer liStatusId) {
        this.liPermitStatusPK = new LiPermitStatusPK(epId, permitStatusDate, liStatusId);
    }

    public LiPermitStatusPK getLiPermitStatusPK() {
        return liPermitStatusPK;
    }

    public void setLiPermitStatusPK(LiPermitStatusPK liPermitStatusPK) {
        this.liPermitStatusPK = liPermitStatusPK;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LiUsers getLiUsers() {
        return liUsers;
    }

    public void setLiUsers(LiUsers liUsers) {
        this.liUsers = liUsers;
    }

    public LiStatusLookup getLiStatusLookup() {
        return liStatusLookup;
    }

    public void setLiStatusLookup(LiStatusLookup liStatusLookup) {
        this.liStatusLookup = liStatusLookup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liPermitStatusPK != null ? liPermitStatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitStatus)) {
            return false;
        }
        LiPermitStatus other = (LiPermitStatus) object;
        if ((this.liPermitStatusPK == null && other.liPermitStatusPK != null) || (this.liPermitStatusPK != null && !this.liPermitStatusPK.equals(other.liPermitStatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitStatus[ liPermitStatusPK=" + liPermitStatusPK + " ]";
    }
    
}
