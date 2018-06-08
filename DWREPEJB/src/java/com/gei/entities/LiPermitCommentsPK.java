/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Embeddable
public class LiPermitCommentsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_ID")
    private Integer epId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DBUSER")
    private String dbuser;

    public LiPermitCommentsPK() {
    }

    public LiPermitCommentsPK(Integer epId, Date commentDate, String dbuser) {
        this.epId = epId;
        this.commentDate = commentDate;
        this.dbuser = dbuser;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epId != null ? epId.hashCode() : 0);
        hash += (commentDate != null ? commentDate.hashCode() : 0);
        hash += (dbuser != null ? dbuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitCommentsPK)) {
            return false;
        }
        LiPermitCommentsPK other = (LiPermitCommentsPK) object;
        if ((this.epId == null && other.epId != null) || (this.epId != null && !this.epId.equals(other.epId))) {
            return false;
        }
        if ((this.commentDate == null && other.commentDate != null) || (this.commentDate != null && !this.commentDate.equals(other.commentDate))) {
            return false;
        }
        if ((this.dbuser == null && other.dbuser != null) || (this.dbuser != null && !this.dbuser.equals(other.dbuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitCommentsPK[ epId=" + epId + ", commentDate=" + commentDate + ", dbuser=" + dbuser + " ]";
    }
    
}
