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
@Table(name = "EP_CONTACT_TYPE_LOOKUP")
public class EpContactTypeLookup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_TYPE_ID")
    private Integer epContactTypeId;
    @Column(name = "EP_CONTACT_TYPE_DESC")
    private String epContactTypeDesc;

    public EpContactTypeLookup() {
    }

    public EpContactTypeLookup(Integer epContactTypeId) {
        this.epContactTypeId = epContactTypeId;
    }

    public Integer getEpContactTypeId() {
        return epContactTypeId;
    }

    public void setEpContactTypeId(Integer epContactTypeId) {
        this.epContactTypeId = epContactTypeId;
    }

    public String getEpContactTypeDesc() {
        return epContactTypeDesc;
    }

    public void setEpContactTypeDesc(String epContactTypeDesc) {
        this.epContactTypeDesc = epContactTypeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactTypeId != null ? epContactTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContactTypeLookup)) {
            return false;
        }
        EpContactTypeLookup other = (EpContactTypeLookup) object;
        if ((this.epContactTypeId == null && other.epContactTypeId != null) || (this.epContactTypeId != null && !this.epContactTypeId.equals(other.epContactTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContactTypeLookup[ epContactTypeId=" + epContactTypeId + " ]";
    }
    
}
