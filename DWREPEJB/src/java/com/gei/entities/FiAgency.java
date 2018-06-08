/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "FI_AGENCY")
@NamedQueries({
    @NamedQuery(name = "FiAgency.findAll", query = "SELECT f FROM FiAgency f")})
public class FiAgency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_AGENCY_ID")
    private Integer fiAgencyId;
    @Column(name = "FI_AGENCY_NAME")
    private String fiAgencyName;
    @Column(name = "FI_AGENCY_NAME_SHORT")
    private String fiAgencyNameShort;
    @Column(name = "FI_AGENCY_TYPE_ID")
    private Integer fiAgencyTypeId;

    public FiAgency() {
    }

    public FiAgency(Integer fiAgencyId) {
        this.fiAgencyId = fiAgencyId;
    }

    public Integer getFiAgencyId() {
        return fiAgencyId;
    }

    public void setFiAgencyId(Integer fiAgencyId) {
        this.fiAgencyId = fiAgencyId;
    }

    public String getFiAgencyName() {
        return fiAgencyName;
    }

    public void setFiAgencyName(String fiAgencyName) {
        this.fiAgencyName = fiAgencyName;
    }

    public String getFiAgencyNameShort() {
        return fiAgencyNameShort;
    }

    public void setFiAgencyNameShort(String fiAgencyNameShort) {
        this.fiAgencyNameShort = fiAgencyNameShort;
    }

    public Integer getFiAgencyTypeId() {
        return fiAgencyTypeId;
    }

    public void setFiAgencyTypeId(Integer fiAgencyTypeId) {
        this.fiAgencyTypeId = fiAgencyTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiAgencyId != null ? fiAgencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiAgency)) {
            return false;
        }
        FiAgency other = (FiAgency) object;
        if ((this.fiAgencyId == null && other.fiAgencyId != null) || (this.fiAgencyId != null && !this.fiAgencyId.equals(other.fiAgencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.FiAgency[ fiAgencyId=" + fiAgencyId + " ]";
    }
    
}
