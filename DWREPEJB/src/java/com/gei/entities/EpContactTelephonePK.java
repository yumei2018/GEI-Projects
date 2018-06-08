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
public class EpContactTelephonePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_PHONE_ID")
    private Integer epContactPhoneId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_ID")
    private Integer epContactId;

    public EpContactTelephonePK() {
    }

    public EpContactTelephonePK(Integer epContactPhoneId, Integer epContactId) {
        this.epContactPhoneId = epContactPhoneId;
        this.epContactId = epContactId;
    }

    public Integer getEpContactPhoneId() {
        return epContactPhoneId;
    }

    public void setEpContactPhoneId(Integer epContactPhoneId) {
        this.epContactPhoneId = epContactPhoneId;
    }

    public Integer getEpContactId() {
        return epContactId;
    }

    public void setEpContactId(Integer epContactId) {
        this.epContactId = epContactId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactPhoneId != null ? epContactPhoneId.hashCode() : 0);
        hash += (epContactId != null ? epContactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContactTelephonePK)) {
            return false;
        }
        EpContactTelephonePK other = (EpContactTelephonePK) object;
        if ((this.epContactPhoneId == null && other.epContactPhoneId != null) || (this.epContactPhoneId != null && !this.epContactPhoneId.equals(other.epContactPhoneId))) {
            return false;
        }
        if ((this.epContactId == null && other.epContactId != null) || (this.epContactId != null && !this.epContactId.equals(other.epContactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContactTelephonePK[ epContactPhoneId=" + epContactPhoneId + ", epContactId=" + epContactId + " ]";
    }
    
}
