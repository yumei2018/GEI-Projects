/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "EP_CONTACT_TELEPHONE")
public class EpContactTelephone implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EpContactTelephonePK epContactTelephonePK;
    @Column(name = "EP_CONTACT_PHONE_TYPE")
    private String epContactPhoneType;
    @Column(name = "EP_CONTACT_PHONE_NUMBER")
    private String epContactPhoneNumber;
    @Column(name = "EP_CONTACT_FAX_NUMBER")
    private String epContactFaxNumber;

    public EpContactTelephone() {
    }

    public EpContactTelephone(EpContactTelephonePK epContactTelephonePK) {
        this.epContactTelephonePK = epContactTelephonePK;
    }

    public EpContactTelephone(Integer epContactPhoneId, Integer epContactId) {
        this.epContactTelephonePK = new EpContactTelephonePK(epContactPhoneId, epContactId);
    }

    public EpContactTelephonePK getEpContactTelephonePK() {
        return epContactTelephonePK;
    }

    public void setEpContactTelephonePK(EpContactTelephonePK epContactTelephonePK) {
        this.epContactTelephonePK = epContactTelephonePK;
    }

    public String getEpContactPhoneType() {
        return epContactPhoneType;
    }

    public void setEpContactPhoneType(String epContactPhoneType) {
        this.epContactPhoneType = epContactPhoneType;
    }

    public String getEpContactPhoneNumber() {
        return epContactPhoneNumber;
    }

    public void setEpContactPhoneNumber(String epContactPhoneNumber) {
        this.epContactPhoneNumber = epContactPhoneNumber;
    }

    public String getEpContactFaxNumber() {
        return epContactFaxNumber;
    }

    public void setEpContactFaxNumber(String epContactFaxNumber) {
        this.epContactFaxNumber = epContactFaxNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactTelephonePK != null ? epContactTelephonePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContactTelephone)) {
            return false;
        }
        EpContactTelephone other = (EpContactTelephone) object;
        if ((this.epContactTelephonePK == null && other.epContactTelephonePK != null) || (this.epContactTelephonePK != null && !this.epContactTelephonePK.equals(other.epContactTelephonePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContactTelephone[ epContactTelephonePK=" + epContactTelephonePK + " ]";
    }
    
}
