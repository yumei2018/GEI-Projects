/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "EP_CONTACT_TYPE")
public class EpContactType implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EpContactTypePK epContactTypePK;
    @ManyToOne(optional = false)
    @JoinColumn(name = "EP_CONTACT_ID", referencedColumnName = "EP_CONTACT_ID", insertable = false, updatable = false)
    private EpContact epContact;
    @ManyToOne(optional = false)
    @JoinColumn(name = "EP_CONTACT_TYPE_ID", referencedColumnName = "EP_CONTACT_TYPE_ID", insertable = false, updatable = false)
    private EpContactTypeLookup epContactTypeLookup;

    public EpContactType() {
    }

    public EpContactType(EpContactTypePK epContactTypePK) {
        this.epContactTypePK = epContactTypePK;
    }

    public EpContactType(Integer epContactId, Integer epId, Integer epContactTypeId) {
        this.epContactTypePK = new EpContactTypePK(epContactId, epId, epContactTypeId);
    }

    public EpContactTypePK getEpContactTypePK() {
        return epContactTypePK;
    }

    public void setEpContactTypePK(EpContactTypePK epContactTypePK) {
        this.epContactTypePK = epContactTypePK;
    }

    public EpContact getEpContact() {
        return epContact;
    }

    public void setEpContact(EpContact epContact) {
        this.epContact = epContact;
    }

    public EpContactTypeLookup getEpContactTypeLookup() {
        return epContactTypeLookup;
    }

    public void setEpContactTypeLookup(EpContactTypeLookup epContactTypeLookup) {
        this.epContactTypeLookup = epContactTypeLookup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactTypePK != null ? epContactTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContactType)) {
            return false;
        }
        EpContactType other = (EpContactType) object;
        if ((this.epContactTypePK == null && other.epContactTypePK != null) || (this.epContactTypePK != null && !this.epContactTypePK.equals(other.epContactTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContactType[ epContactTypePK=" + epContactTypePK + " ]";
    }
    
}
