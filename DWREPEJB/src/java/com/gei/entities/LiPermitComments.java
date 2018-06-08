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
import javax.validation.constraints.Size;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "LI_PERMIT_COMMENTS")
public class LiPermitComments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LiPermitCommentsPK liPermitCommentsPK;
    @Column(name = "COMMENTS_TEXT")
    private String commentsText;
    @ManyToOne(optional = false)
    @JoinColumn(name = "DBUSER", referencedColumnName = "DBUSER", insertable = false, updatable = false)
    private LiUsers liUsers;

    public LiPermitComments() {
    }

    public LiPermitComments(LiPermitCommentsPK liPermitCommentsPK) {
        this.liPermitCommentsPK = liPermitCommentsPK;
    }

    public LiPermitComments(Integer epId, Date commentDate, String dbuser) {
        this.liPermitCommentsPK = new LiPermitCommentsPK(epId, commentDate, dbuser);
    }

    public LiPermitCommentsPK getLiPermitCommentsPK() {
        return liPermitCommentsPK;
    }

    public void setLiPermitCommentsPK(LiPermitCommentsPK liPermitCommentsPK) {
        this.liPermitCommentsPK = liPermitCommentsPK;
    }

    public String getCommentsText() {
        return commentsText;
    }

    public void setCommentsText(String commentsText) {
        this.commentsText = commentsText;
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
        hash += (liPermitCommentsPK != null ? liPermitCommentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiPermitComments)) {
            return false;
        }
        LiPermitComments other = (LiPermitComments) object;
        if ((this.liPermitCommentsPK == null && other.liPermitCommentsPK != null) || (this.liPermitCommentsPK != null && !this.liPermitCommentsPK.equals(other.liPermitCommentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.LiPermitComments[ liPermitCommentsPK=" + liPermitCommentsPK + " ]";
    }
    
}
