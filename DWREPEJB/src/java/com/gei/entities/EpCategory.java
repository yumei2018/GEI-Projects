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
@Table(name = "EP_CATEGORY")
public class EpCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CAT_ID")
    private Integer epCatId;
    @Column(name = "EP_CAT_DESC")
    private String epCatDesc;
    @Column(name = "EP_CAT_DEF")
    private String epCatDef;

    public EpCategory() {
    }

    public EpCategory(Integer epCatId) {
        this.epCatId = epCatId;
    }

    public Integer getEpCatId() {
        return epCatId;
    }

    public void setEpCatId(Integer epCatId) {
        this.epCatId = epCatId;
    }

    public String getEpCatDesc() {
        return epCatDesc;
    }

    public void setEpCatDesc(String epCatDesc) {
        this.epCatDesc = epCatDesc;
    }

    public String getEpCatDef() {
        return epCatDef;
    }

    public void setEpCatDef(String epCatDef) {
        this.epCatDef = epCatDef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epCatId != null ? epCatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpCategory)) {
            return false;
        }
        EpCategory other = (EpCategory) object;
        if ((this.epCatId == null && other.epCatId != null) || (this.epCatId != null && !this.epCatId.equals(other.epCatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpCategory[ epCatId=" + epCatId + " ]";
    }
    
}
