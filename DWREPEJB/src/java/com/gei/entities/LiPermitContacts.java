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
import javax.persistence.Table;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "LI_PERMIT_CONTACTS")
public class LiPermitContacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LiPermitContactsPK liPermitContactsPK;
    @ManyToOne(optional = false)
    @JoinColumn(name = "FI_CONTACT_ID", referencedColumnName = "FI_CONTACT_ID", insertable = false, updatable = false)
    private FiContact fiContact;
    @ManyToOne(optional = false)
    @JoinColumn(name = "CONTACT_TYPE_ID", referencedColumnName = "FI_CONTACT_TYPE_ID", insertable = false, updatable = false)
    private FiContactTypeLookup fiContactTypeLookup;

    public LiPermitContacts() {
    }

    public LiPermitContacts(LiPermitContactsPK liPermitContactsPK) {
        this.liPermitContactsPK = liPermitContactsPK;
    }

    public LiPermitContacts(Integer epId, Integer fiContactId, Integer contactTypeId) {
        this.liPermitContactsPK = new LiPermitContactsPK(epId, fiContactId, contactTypeId);
    }

    public LiPermitContactsPK getLiPermitContactsPK() {
        return liPermitContactsPK;
    }

    public void setLiPermitContactsPK(LiPermitContactsPK liPermitContactsPK) {
        this.liPermitContactsPK = liPermitContactsPK;
    }

    public FiContact getFiContact() {
        return fiContact;
    }

    public void setFiContact(FiContact fiContact) {
        this.fiContact = fiContact;
    }

    public FiContactTypeLookup getFiContactTypeLookup() {
        return fiContactTypeLookup;
    }

    public void setFiContactTypeLookup(FiContactTypeLookup fiContactTypeLookup) {
        this.fiContactTypeLookup = fiContactTypeLookup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liPermitContactsPK != null ? liPermitContactsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitContacts)) {
            return false;
        }
        LiPermitContacts other = (LiPermitContacts) object;
        if ((this.liPermitContactsPK == null && other.liPermitContactsPK != null) || (this.liPermitContactsPK != null && !this.liPermitContactsPK.equals(other.liPermitContactsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitContacts[ liPermitContactsPK=" + liPermitContactsPK + " ]";
    }
    
}
