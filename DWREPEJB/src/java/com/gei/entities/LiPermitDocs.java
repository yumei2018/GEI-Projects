/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "LI_PERMIT_DOCS")
public class LiPermitDocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LI_DOC_ID")
    private Integer liDocId;
    @Column(name = "EP_ID")
    private Integer epId;
    @Column(name = "DOC_FILENAME")
    private String docFilename;
    @Column(name = "DOC_PATH")
    private String docPath;
    @Column(name = "DOC_FORMAT_NUM")
    private Integer docFormatNum;
    @Column(name = "DOC_NOTES")
    private String docNotes;
    @Column(name = "DATE_ENTERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntered;
    @Column(name = "DBUSER")
    private String dbuser;
    @Column(name = "DOC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "DBUSER", referencedColumnName = "DBUSER", insertable = false, updatable = false)
    private LiUsers liUsers;

    public LiPermitDocs() {
    }

    public LiPermitDocs(Integer liDocId) {
        this.liDocId = liDocId;
    }

    public Integer getLiDocId() {
        return liDocId;
    }

    public void setLiDocId(Integer liDocId) {
        this.liDocId = liDocId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public String getDocFilename() {
        return docFilename;
    }

    public void setDocFilename(String docFilename) {
        this.docFilename = docFilename;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Integer getDocFormatNum() {
        return docFormatNum;
    }

    public void setDocFormatNum(Integer docFormatNum) {
        this.docFormatNum = docFormatNum;
    }

    public String getDocNotes() {
        return docNotes;
    }

    public void setDocNotes(String docNotes) {
        this.docNotes = docNotes;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public LiUsers getLiUsers() {
        return liUsers;
    }

    public void setLiUsers(LiUsers liUsers) {
        this.liUsers = liUsers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liDocId != null ? liDocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitDocs)) {
            return false;
        }
        LiPermitDocs other = (LiPermitDocs) object;
        if ((this.liDocId == null && other.liDocId != null) || (this.liDocId != null && !this.liDocId.equals(other.liDocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitDocs[ liDocId=" + liDocId + " ]";
    }
    
}
