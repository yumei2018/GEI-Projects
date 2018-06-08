/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "FI_CONTACT")
public class FiContact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_CONTACT_ID")
    private Integer fiContactId;
    @Column(name = "FI_FIRST_NAME")
    private String fiFirstName;
    @Column(name = "FI_LAST_NAME")
    private String fiLastName;
    @Column(name = "FI_FIRM_NAME")
    private String fiFirmName;
    @Column(name = "FI_PHONE1_TYPE_NUM")
    private Integer fiPhone1TypeNum;
    @Column(name = "FI_PHONE1")
    private String fiPhone1;
    @Column(name = "FI_PHONE2_TYPE_NUM")
    private Integer fiPhone2TypeNum;
    @Column(name = "FI_PHONE2")
    private String fiPhone2;
    @Column(name = "FI_ADDRESS1")
    private String fiAddress1;
    @Column(name = "FI_ADDRESS2")
    private String fiAddress2;
    @Column(name = "FI_CITY")
    private String fiCity;
    @Column(name = "FI_ZIP")
    private String fiZip;
    @Column(name = "STATE_ID")
    private String stateId;
    @Column(name = "FI_EMAIL")
    private String fiEmail;
    @Column(name = "FI_CONTACT_TYPE_ID")
    private Integer fiContactTypeId;
    @Column(name = "FI_AGENCY_ID")
    private Integer fiAgencyId;
    @Column(name = "PERSONNEL_TYPE_ID")
    private Integer personnelTypeId;
    @Column(name = "FI_CONTACT_ACTIVE")
    private String fiContactActive;
    @Column(name = "FI_CONTACT_TYPE_ID_2")
    private Integer fiContactTypeId2;
    @Column(name = "FI_INITIAL")
    private String fiInitial;
    @Column(name = "FI_CALL_SEQ_ID")
    private Integer fiCallSeqId;
    @Column(name = "FI_NAME_SUFFIX_NUM")
    private Integer fiNameSuffixNum;
    @OneToMany
    @JoinColumn(name = "FI_CONTACT_ID", referencedColumnName = "FI_CONTACT_ID")
    private Collection<FiContactPhone> fiContactPhoneCollection;
    @ManyToOne(optional = false)
    @JoinColumn(name = "FI_AGENCY_ID", referencedColumnName = "FI_AGENCY_ID", insertable = false, updatable = false)
    private FiAgency fiAgency;

    public FiContact() {
    }

    public FiContact(Integer fiContactId) {
        this.fiContactId = fiContactId;
    }

    public Integer getFiContactId() {
        return fiContactId;
    }

    public void setFiContactId(Integer fiContactId) {
        this.fiContactId = fiContactId;
    }

    public String getFiFirstName() {
        return fiFirstName;
    }

    public void setFiFirstName(String fiFirstName) {
        this.fiFirstName = fiFirstName;
    }

    public String getFiLastName() {
        return fiLastName;
    }

    public void setFiLastName(String fiLastName) {
        this.fiLastName = fiLastName;
    }

    public String getFiFirmName() {
        return fiFirmName;
    }

    public void setFiFirmName(String fiFirmName) {
        this.fiFirmName = fiFirmName;
    }

    public Integer getFiPhone1TypeNum() {
        return fiPhone1TypeNum;
    }

    public void setFiPhone1TypeNum(Integer fiPhone1TypeNum) {
        this.fiPhone1TypeNum = fiPhone1TypeNum;
    }

    public String getFiPhone1() {
        return fiPhone1;
    }

    public void setFiPhone1(String fiPhone1) {
        this.fiPhone1 = fiPhone1;
    }

    public Integer getFiPhone2TypeNum() {
        return fiPhone2TypeNum;
    }

    public void setFiPhone2TypeNum(Integer fiPhone2TypeNum) {
        this.fiPhone2TypeNum = fiPhone2TypeNum;
    }

    public String getFiPhone2() {
        return fiPhone2;
    }

    public void setFiPhone2(String fiPhone2) {
        this.fiPhone2 = fiPhone2;
    }

    public String getFiAddress1() {
        return fiAddress1;
    }

    public void setFiAddress1(String fiAddress1) {
        this.fiAddress1 = fiAddress1;
    }

    public String getFiAddress2() {
        return fiAddress2;
    }

    public void setFiAddress2(String fiAddress2) {
        this.fiAddress2 = fiAddress2;
    }

    public String getFiCity() {
        return fiCity;
    }

    public void setFiCity(String fiCity) {
        this.fiCity = fiCity;
    }

    public String getFiZip() {
        return fiZip;
    }

    public void setFiZip(String fiZip) {
        this.fiZip = fiZip;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getFiEmail() {
        return fiEmail;
    }

    public void setFiEmail(String fiEmail) {
        this.fiEmail = fiEmail;
    }

    public Integer getFiContactTypeId() {
        return fiContactTypeId;
    }

    public void setFiContactTypeId(Integer fiContactTypeId) {
        this.fiContactTypeId = fiContactTypeId;
    }

    public Integer getFiAgencyId() {
        return fiAgencyId;
    }

    public void setFiAgencyId(Integer fiAgencyId) {
        this.fiAgencyId = fiAgencyId;
    }

    public Integer getPersonnelTypeId() {
        return personnelTypeId;
    }

    public void setPersonnelTypeId(Integer personnelTypeId) {
        this.personnelTypeId = personnelTypeId;
    }

    public String getFiContactActive() {
        return fiContactActive;
    }

    public void setFiContactActive(String fiContactActive) {
        this.fiContactActive = fiContactActive;
    }

    public Integer getFiContactTypeId2() {
        return fiContactTypeId2;
    }

    public void setFiContactTypeId2(Integer fiContactTypeId2) {
        this.fiContactTypeId2 = fiContactTypeId2;
    }

    public String getFiInitial() {
        return fiInitial;
    }

    public void setFiInitial(String fiInitial) {
        this.fiInitial = fiInitial;
    }

    public Integer getFiCallSeqId() {
        return fiCallSeqId;
    }

    public void setFiCallSeqId(Integer fiCallSeqId) {
        this.fiCallSeqId = fiCallSeqId;
    }

    public Integer getFiNameSuffixNum() {
        return fiNameSuffixNum;
    }

    public void setFiNameSuffixNum(Integer fiNameSuffixNum) {
        this.fiNameSuffixNum = fiNameSuffixNum;
    }

    public Collection<FiContactPhone> getFiContactPhoneCollection() {
        return fiContactPhoneCollection;
    }

    public void setFiContactPhoneCollection(Collection<FiContactPhone> fiContactPhoneCollection) {
        this.fiContactPhoneCollection = fiContactPhoneCollection;
    }

    public FiAgency getFiAgency() {
        return fiAgency;
    }

    public void setFiAgency(FiAgency fiAgency) {
        this.fiAgency = fiAgency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiContactId != null ? fiContactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiContact)) {
            return false;
        }
        FiContact other = (FiContact) object;
        if ((this.fiContactId == null && other.fiContactId != null) || (this.fiContactId != null && !this.fiContactId.equals(other.fiContactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.FiContact[ fiContactId=" + fiContactId + " ]";
    }
    
}
