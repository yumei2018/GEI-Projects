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
@Table(name = "FI_CONTACT_TYPE_LOOKUP")
@NamedQueries({
    @NamedQuery(name = "FiContactTypeLookup.findAll", query = "SELECT f FROM FiContactTypeLookup f")})
public class FiContactTypeLookup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_CONTACT_TYPE_ID")
    private Integer fiContactTypeId;
    @Column(name = "FI_CONTACT_TYPE_DESC")
    private String fiContactTypeDesc;

    public FiContactTypeLookup() {
    }

    public FiContactTypeLookup(Integer fiContactTypeId) {
        this.fiContactTypeId = fiContactTypeId;
    }

    public Integer getFiContactTypeId() {
        return fiContactTypeId;
    }

    public void setFiContactTypeId(Integer fiContactTypeId) {
        this.fiContactTypeId = fiContactTypeId;
    }

    public String getFiContactTypeDesc() {
        return fiContactTypeDesc;
    }

    public void setFiContactTypeDesc(String fiContactTypeDesc) {
        this.fiContactTypeDesc = fiContactTypeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiContactTypeId != null ? fiContactTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiContactTypeLookup)) {
            return false;
        }
        FiContactTypeLookup other = (FiContactTypeLookup) object;
        if ((this.fiContactTypeId == null && other.fiContactTypeId != null) || (this.fiContactTypeId != null && !this.fiContactTypeId.equals(other.fiContactTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.FiContactTypeLookup[ fiContactTypeId=" + fiContactTypeId + " ]";
    }
    
}
