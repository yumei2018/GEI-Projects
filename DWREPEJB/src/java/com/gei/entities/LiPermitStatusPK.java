/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Embeddable
public class LiPermitStatusPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERMIT_STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permitStatusDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_STATUS_ID")
    private Integer liStatusId;

    public LiPermitStatusPK() {
    }

    public LiPermitStatusPK(Integer epId, Date permitStatusDate, Integer liStatusId) {
        this.epId = epId;
        this.permitStatusDate = permitStatusDate;
        this.liStatusId = liStatusId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Date getPermitStatusDate() {
        return permitStatusDate;
    }

    public void setPermitStatusDate(Date permitStatusDate) {
        this.permitStatusDate = permitStatusDate;
    }

    public Integer getLiStatusId() {
        return liStatusId;
    }

    public void setLiStatusId(Integer liStatusId) {
        this.liStatusId = liStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epId != null ? epId.hashCode() : 0);
        hash += (permitStatusDate != null ? permitStatusDate.hashCode() : 0);
        hash += (liStatusId != null ? liStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitStatusPK)) {
            return false;
        }
        LiPermitStatusPK other = (LiPermitStatusPK) object;
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        if ((this.permitStatusDate == null && other.permitStatusDate != null) || (this.permitStatusDate != null && !this.permitStatusDate.equals(other.permitStatusDate))) {
            return false;
        }
        if ((this.liStatusId == null && other.liStatusId != null) || (this.liStatusId != null && !this.liStatusId.equals(other.liStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitStatusPK[ epId=" + epId + ", permitStatusDate=" + permitStatusDate + ", liStatusId=" + liStatusId + " ]";
    }
    
}
