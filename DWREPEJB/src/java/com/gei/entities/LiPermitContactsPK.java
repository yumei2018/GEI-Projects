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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Embeddable
public class LiPermitContactsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_CONTACT_ID")
    private Integer fiContactId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTACT_TYPE_ID")
    private Integer contactTypeId;

    public LiPermitContactsPK() {
    }

    public LiPermitContactsPK(Integer epId, Integer fiContactId, Integer contactTypeId) {
        this.epId = epId;
        this.fiContactId = fiContactId;
        this.contactTypeId = contactTypeId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Integer getFiContactId() {
        return fiContactId;
    }

    public void setFiContactId(Integer fiContactId) {
        this.fiContactId = fiContactId;
    }

    public Integer getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Integer contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epId != null ? epId.hashCode() : 0);
        hash += (fiContactId != null ? fiContactId.hashCode() : 0);
        hash += (contactTypeId != null ? contactTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitContactsPK)) {
            return false;
        }
        LiPermitContactsPK other = (LiPermitContactsPK) object;
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        if ((this.fiContactId == null && other.fiContactId != null) || (this.fiContactId != null && !this.fiContactId.equals(other.fiContactId))) {
            return false;
        }
        if ((this.contactTypeId == null && other.contactTypeId != null) || (this.contactTypeId != null && !this.contactTypeId.equals(other.contactTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitContactsPK[ epId=" + epId + ", fiContactId=" + fiContactId + ", contactTypeId=" + contactTypeId + " ]";
    }
    
}
