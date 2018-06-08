/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Embeddable
public class EpContactTypePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_ID")
    private Integer epContactId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_TYPE_ID")
    private Integer epContactTypeId;

    public EpContactTypePK() {
    }

    public EpContactTypePK(Integer epContactId, Integer epId, Integer epContactTypeId) {
        this.epContactId = epContactId;
        this.epId = epId;
        this.epContactTypeId = epContactTypeId;
    }

    public Integer getEpContactId() {
        return epContactId;
    }

    public void setEpContactId(Integer epContactId) {
        this.epContactId = epContactId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Integer getEpContactTypeId() {
        return epContactTypeId;
    }

    public void setEpContactTypeId(Integer epContactTypeId) {
        this.epContactTypeId = epContactTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactId != null ? epContactId.hashCode() : 0);
        hash += (epId != null ? epId.hashCode() : 0);
        hash += (epContactTypeId != null ? epContactTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContactTypePK)) {
            return false;
        }
        EpContactTypePK other = (EpContactTypePK) object;
        if ((this.epContactId == null && other.epContactId != null) || (this.epContactId != null && !this.epContactId.equals(other.epContactId))) {
            return false;
        }
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        if ((this.epContactTypeId == null && other.epContactTypeId != null) || (this.epContactTypeId != null && !this.epContactTypeId.equals(other.epContactTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContactTypePK[ epContactId=" + epContactId + ", epId=" + epId + ", epContactTypeId=" + epContactTypeId + " ]";
    }
    
}
