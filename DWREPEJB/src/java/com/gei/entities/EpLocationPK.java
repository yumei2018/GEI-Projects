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
public class EpLocationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_LOCATION_ID")
    private Integer epLocationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;

    public EpLocationPK() {
    }

    public EpLocationPK(Integer epLocationId, Integer epId) {
        this.epLocationId = epLocationId;
        this.epId = epId;
    }

    public Integer getEpLocationId() {
        return epLocationId;
    }

    public void setEpLocationId(Integer epLocationId) {
        this.epLocationId = epLocationId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epLocationId != null ? epLocationId.hashCode() : 0);
        hash += (epId != null ? epId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpLocationPK)) {
            return false;
        }
        EpLocationPK other = (EpLocationPK) object;
        if ((this.epLocationId == null && other.epLocationId != null) || (this.epLocationId != null && !this.epLocationId.equals(other.epLocationId))) {
            return false;
        }
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpLocationPK[ epLocationId=" + epLocationId + ", epId=" + epId + " ]";
    }
    
}
