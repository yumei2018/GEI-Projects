/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "EP_APPLICATION")
public class EpApplication extends AbstractEntity<EpApplication> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;
    @Column(name = "EP_NO")
    private String epNo;
    @Column(name = "EP_PURPOSE")
    private String epPurpose;
    @Column(name = "EP_CAT_ID")
    private Integer epCatId;
    @Column(name = "EP_COVENANT")
    private String epCovenant;
    @Column(name = "EP_BOARD")
    private String epBoard;
    @Column(name = "EP_PREACCEPT")
    private String epPreaccept;
    @Column(name = "EP_INSPECTOR_ID")
    private Integer epInspectorId;
    @Column(name = "EP_PROJECT_TYPE")
    private String epProjectType;
    @Column(name = "EP_CLASS_ID")
    private Integer epClassId;
    @Column(name = "ENF_LEVEE_TYPE_ID")
    private Integer enfLeveeTypeId;
    @Column(name = "ENF_LEVEE_TYPE_COMMENT")
    private String enfLeveeTypeComment;
    @Column(name = "EP_PROJECT_ID")
    private Integer epProjectId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "EP_CAT_ID", referencedColumnName = "EP_CAT_ID", insertable = false, updatable = false)
    private EpCategory epCategory;
        
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "epApplication")
//    private Collection<EpLocation> epLocationCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<EpLocation> epLocationCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<EpCoordinate> epCoordinateCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<EpComments> epCommentsCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<EpContactType> epContactTypeCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<LiPermitContacts> liPermitContactsCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<LiPermitDocs> liPermitDocsCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<LiPermitComments> liPermitCommentsCollection;
    @OneToMany
    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID")
    private Collection<LiPermitStatus> liPermitStatusCollection;

    public EpApplication() {
    }

    public EpApplication(Integer epId) {
        this.epId = epId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public String getEpNo() {
        return epNo;
    }

    public void setEpNo(String epNo) {
        this.epNo = epNo;
    }

    public String getEpPurpose() {
        return epPurpose;
    }

    public void setEpPurpose(String epPurpose) {
        this.epPurpose = epPurpose;
    }

    public Integer getEpCatId() {
        return epCatId;
    }

    public void setEpCatId(Integer epCatId) {
        this.epCatId = epCatId;
    }

    public String getEpCovenant() {
        return epCovenant;
    }

    public void setEpCovenant(String epCovenant) {
        this.epCovenant = epCovenant;
    }

    public String getEpBoard() {
        return epBoard;
    }

    public void setEpBoard(String epBoard) {
        this.epBoard = epBoard;
    }

    public String getEpPreaccept() {
        return epPreaccept;
    }

    public void setEpPreaccept(String epPreaccept) {
        this.epPreaccept = epPreaccept;
    }

    public Integer getEpInspectorId() {
        return epInspectorId;
    }

    public void setEpInspectorId(Integer epInspectorId) {
        this.epInspectorId = epInspectorId;
    }

    public String getEpProjectType() {
        return epProjectType;
    }

    public void setEpProjectType(String epProjectType) {
        this.epProjectType = epProjectType;
    }

    public Integer getEpClassId() {
        return epClassId;
    }

    public void setEpClassId(Integer epClassId) {
        this.epClassId = epClassId;
    }

    public Integer getEnfLeveeTypeId() {
        return enfLeveeTypeId;
    }

    public void setEnfLeveeTypeId(Integer enfLeveeTypeId) {
        this.enfLeveeTypeId = enfLeveeTypeId;
    }

    public String getEnfLeveeTypeComment() {
        return enfLeveeTypeComment;
    }

    public void setEnfLeveeTypeComment(String enfLeveeTypeComment) {
        this.enfLeveeTypeComment = enfLeveeTypeComment;
    }

    public Integer getEpProjectId() {
        return epProjectId;
    }

    public void setEpProjectId(Integer epProjectId) {
        this.epProjectId = epProjectId;
    }

    public EpCategory getEpCategory() {
        return epCategory;
    }

    public void setEpCategory(EpCategory epCategory) {
        this.epCategory = epCategory;
    }

    public Collection<EpLocation> getEpLocationCollection() {
        return epLocationCollection;
    }

    public void setEpLocationCollection(Collection<EpLocation> epLocationCollection) {
        this.epLocationCollection = epLocationCollection;
    }

    public Collection<EpCoordinate> getEpCoordinateCollection() {
        return epCoordinateCollection;
    }

    public void setEpCoordinateCollection(Collection<EpCoordinate> epCoordinateCollection) {
        this.epCoordinateCollection = epCoordinateCollection;
    }

    public Collection<EpComments> getEpCommentsCollection() {
        return epCommentsCollection;
    }

    public void setEpCommentsCollection(Collection<EpComments> epCommentsCollection) {
        this.epCommentsCollection = epCommentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epId != null ? epId.hashCode() : 0);
        return hash;
    }

    public Collection<EpContactType> getEpContactTypeCollection() {
        return epContactTypeCollection;
    }

    public void setEpContactTypeCollection(Collection<EpContactType> epContactTypeCollection) {
        this.epContactTypeCollection = epContactTypeCollection;
    }

    public Collection<LiPermitContacts> getLiPermitContactsCollection() {
        return liPermitContactsCollection;
    }

    public void setLiPermitContactsCollection(Collection<LiPermitContacts> liPermitContactsCollection) {
        this.liPermitContactsCollection = liPermitContactsCollection;
    }

    public Collection<LiPermitDocs> getLiPermitDocsCollection() {
        return liPermitDocsCollection;
    }

    public void setLiPermitDocsCollection(Collection<LiPermitDocs> liPermitDocsCollection) {
        this.liPermitDocsCollection = liPermitDocsCollection;
    }

    public Collection<LiPermitComments> getLiPermitCommentsCollection() {
        return liPermitCommentsCollection;
    }

    public void setLiPermitCommentsCollection(Collection<LiPermitComments> liPermitCommentsCollection) {
        this.liPermitCommentsCollection = liPermitCommentsCollection;
    }

    public Collection<LiPermitStatus> getLiPermitStatusCollection() {
        return liPermitStatusCollection;
    }

    public void setLiPermitStatusCollection(Collection<LiPermitStatus> liPermitStatusCollection) {
        this.liPermitStatusCollection = liPermitStatusCollection;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpApplication)) {
            return false;
        }
        EpApplication other = (EpApplication) object;
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpApplication[ epId=" + epId + " ]";
    }
    
    @Override
    public String getErrorLogs(){
        String errors = null;
        return errors;
    }
    
    @Override
    public Object getId(){
        Object o = null;
        return o;
    }
}
