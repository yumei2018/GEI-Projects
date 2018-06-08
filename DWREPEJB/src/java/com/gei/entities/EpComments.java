/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "EP_COMMENTS")
public class EpComments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EpCommentsPK epCommentsPK;
    @Column(name = "COMMENTS_TEXT")
    private String commentsText;
    @Column(name = "COMMENT_TYPE")
    private Integer commentType;
    @Column(name = "DBUSER")
    private String dbuser;
    @ManyToOne(optional = false)
    @JoinColumn(name = "DBUSER", referencedColumnName = "DBUSER", insertable = false, updatable = false)
    private EpUsers epUsers;

    public EpComments() {
    }

    public EpComments(EpCommentsPK epCommentsPK) {
        this.epCommentsPK = epCommentsPK;
    }

    public EpComments(Integer epId, Date commentDate) {
        this.epCommentsPK = new EpCommentsPK(epId, commentDate);
    }

    public EpCommentsPK getEpCommentsPK() {
        return epCommentsPK;
    }

    public void setEpCommentsPK(EpCommentsPK epCommentsPK) {
        this.epCommentsPK = epCommentsPK;
    }

    public String getCommentsText() {
        return commentsText;
    }

    public void setCommentsText(String commentsText) {
        this.commentsText = commentsText;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public EpUsers getEpUsers() {
        return epUsers;
    }

    public void setEpUsers(EpUsers epUsers) {
        this.epUsers = epUsers;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epCommentsPK != null ? epCommentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpComments)) {
            return false;
        }
        EpComments other = (EpComments) object;
        if ((this.epCommentsPK == null && other.epCommentsPK != null) || (this.epCommentsPK != null && !this.epCommentsPK.equals(other.epCommentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpComments[ epCommentsPK=" + epCommentsPK + " ]";
    }
    
}
