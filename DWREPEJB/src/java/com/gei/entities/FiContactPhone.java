/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "FI_CONTACT_PHONE")
public class FiContactPhone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_PHONE_ID")
    private Integer fiPhoneId;
    @Column(name = "FI_CONTACT_ID")
    private Integer fiContactId;
    @Column(name = "PHONE_TYPE_NUM")
    private Integer phoneTypeNum;
    @Column(name = "FI_PHONE_NUM")
    private String fiPhoneNum;
    @Column(name = "FI_PHONE_EXT")
    private String fiPhoneExt;
    @Column(name = "FI_PHONE_PUBLISH")
    private String fiPhonePublish;
    @Column(name = "FI_PHONE_AVAILABLE_NUM")
    private Integer fiPhoneAvailableNum;

    public FiContactPhone() {
    }

    public FiContactPhone(Integer fiPhoneId) {
        this.fiPhoneId = fiPhoneId;
    }

    public Integer getFiPhoneId() {
        return fiPhoneId;
    }

    public void setFiPhoneId(Integer fiPhoneId) {
        this.fiPhoneId = fiPhoneId;
    }

    public Integer getFiContactId() {
        return fiContactId;
    }

    public void setFiContactId(Integer fiContactId) {
        this.fiContactId = fiContactId;
    }

    public Integer getPhoneTypeNum() {
        return phoneTypeNum;
    }

    public void setPhoneTypeNum(Integer phoneTypeNum) {
        this.phoneTypeNum = phoneTypeNum;
    }

    public String getFiPhoneNum() {
        return fiPhoneNum;
    }

    public void setFiPhoneNum(String fiPhoneNum) {
        this.fiPhoneNum = fiPhoneNum;
    }

    public String getFiPhoneExt() {
        return fiPhoneExt;
    }

    public void setFiPhoneExt(String fiPhoneExt) {
        this.fiPhoneExt = fiPhoneExt;
    }

    public String getFiPhonePublish() {
        return fiPhonePublish;
    }

    public void setFiPhonePublish(String fiPhonePublish) {
        this.fiPhonePublish = fiPhonePublish;
    }

    public Integer getFiPhoneAvailableNum() {
        return fiPhoneAvailableNum;
    }

    public void setFiPhoneAvailableNum(Integer fiPhoneAvailableNum) {
        this.fiPhoneAvailableNum = fiPhoneAvailableNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiPhoneId != null ? fiPhoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiContactPhone)) {
            return false;
        }
        FiContactPhone other = (FiContactPhone) object;
        if ((this.fiPhoneId == null && other.fiPhoneId != null) || (this.fiPhoneId != null && !this.fiPhoneId.equals(other.fiPhoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.FiContactPhone[ fiPhoneId=" + fiPhoneId + " ]";
    }
    
}
