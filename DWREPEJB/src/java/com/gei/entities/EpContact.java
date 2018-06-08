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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "EP_CONTACT")
public class EpContact implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_CONTACT_ID")
    private Integer epContactId;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_INITIAL")
    private String middleInitial;
    @Column(name = "LAST_NAME2")
    private String lastName2;
    @Column(name = "FIRST_NAME2")
    private String firstName2;
    @Column(name = "MIDDLE_INITIAL2")
    private String middleInitial2;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "TITLE2")
    private String title2;
    @Column(name = "FIRM_NAME")
    private String firmName;
    @Column(name = "FIRM_NAME2")
    private String firmName2;
    @Column(name = "ADDRESS1")
    private String address1;
    @Column(name = "ADDRESS2")
    private String address2;
    @Column(name = "PO_BOX")
    private String poBox;
    @Column(name = "CITY")
    private String city;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "SALUTATION")
    private String salutation;
    @Column(name = "SALUTATION2")
    private String salutation2;
    @Column(name = "GREETING")
    private String greeting;
    @Column(name = "COUNTY_ID")
    private String countyId;
    @Column(name = "STATE_ID")
    private String stateId;
    @Column(name = "ACTIVE_FLAG")
    private Character activeFlag;
    @Column(name = "EP_EMAIL")
    private String epEmail;
    @OneToMany
    @JoinColumn(name = "EP_CONTACT_ID", referencedColumnName = "EP_CONTACT_ID")
    private Collection<EpContactTelephone> epContactTelephone;

    public EpContact() {
    }

    public EpContact(Integer epContactId) {
        this.epContactId = epContactId;
    }

    public Integer getEpContactId() {
        return epContactId;
    }

    public void setEpContactId(Integer epContactId) {
        this.epContactId = epContactId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getMiddleInitial2() {
        return middleInitial2;
    }

    public void setMiddleInitial2(String middleInitial2) {
        this.middleInitial2 = middleInitial2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmName2() {
        return firmName2;
    }

    public void setFirmName2(String firmName2) {
        this.firmName2 = firmName2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutation2() {
        return salutation2;
    }

    public void setSalutation2(String salutation2) {
        this.salutation2 = salutation2;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Character getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Character activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getEpEmail() {
        return epEmail;
    }

    public void setEpEmail(String epEmail) {
        this.epEmail = epEmail;
    }

    public Collection<EpContactTelephone> getEpContactTelephone() {
        return epContactTelephone;
    }

    public void setEpContactTelephone(Collection<EpContactTelephone> epContactTelephone) {
        this.epContactTelephone = epContactTelephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epContactId != null ? epContactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpContact)) {
            return false;
        }
        EpContact other = (EpContact) object;
        if ((this.epContactId == null && other.epContactId != null) || (this.epContactId != null && !this.epContactId.equals(other.epContactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpContact[ epContactId=" + epContactId + " ]";
    }
    
}
