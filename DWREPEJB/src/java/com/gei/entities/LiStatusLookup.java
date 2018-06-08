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
@Table(name = "LI_STATUS_LOOKUP")
@NamedQueries({
    @NamedQuery(name = "LiStatusLookup.findAll", query = "SELECT l FROM LiStatusLookup l")})
public class LiStatusLookup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_STATUS_ID")
    private Integer liStatusId;
    @Column(name = "FI_CAT_NUM")
    private Integer fiCatNum;
    @Column(name = "LI_STATUS_DESC")
    private String liStatusDesc;
    @Column(name = "LI_STATUS_ORDER")
    private Integer liStatusOrder;
    @Column(name = "LI_STATUS_DEF")
    private String liStatusDef;

    public LiStatusLookup() {
    }

    public LiStatusLookup(Integer liStatusId) {
        this.liStatusId = liStatusId;
    }

    public Integer getLiStatusId() {
        return liStatusId;
    }

    public void setLiStatusId(Integer liStatusId) {
        this.liStatusId = liStatusId;
    }

    public Integer getFiCatNum() {
        return fiCatNum;
    }

    public void setFiCatNum(Integer fiCatNum) {
        this.fiCatNum = fiCatNum;
    }

    public String getLiStatusDesc() {
        return liStatusDesc;
    }

    public void setLiStatusDesc(String liStatusDesc) {
        this.liStatusDesc = liStatusDesc;
    }

    public Integer getLiStatusOrder() {
        return liStatusOrder;
    }

    public void setLiStatusOrder(Integer liStatusOrder) {
        this.liStatusOrder = liStatusOrder;
    }

    public String getLiStatusDef() {
        return liStatusDef;
    }

    public void setLiStatusDef(String liStatusDef) {
        this.liStatusDef = liStatusDef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liStatusId != null ? liStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiStatusLookup)) {
            return false;
        }
        LiStatusLookup other = (LiStatusLookup) object;
        if ((this.liStatusId == null && other.liStatusId != null) || (this.liStatusId != null && !this.liStatusId.equals(other.liStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiStatusLookup[ liStatusId=" + liStatusId + " ]";
    }
    
}
