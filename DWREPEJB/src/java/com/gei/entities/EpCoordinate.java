/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ymei
 */
@Entity
@Table(name = "EP_COORDINATE")
public class EpCoordinate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EP_COORDINATE_ID")
    private Integer epCoordinateId;
    @Column(name = "EP_ID")
    private Integer epId;
    @Column(name = "EP_LATITUDE_START")
    private Double epLatitudeStart;
    @Column(name = "EP_LONGITUDE_START")
    private Double epLongitudeStart;
    @Column(name = "EP_LATITUDE_END")
    private Double epLatitudeEnd;
    @Column(name = "EP_LONGITUDE_END")
    private Double epLongitudeEnd;
    @Column(name = "EP_LEVEE_MILE_START")
    private Double epLeveeMileStart;
    @Column(name = "EP_LEVEE_MILE_END")
    private Double epLeveeMileEnd;
    @Column(name = "EP_RIVER_MILE_START")
    private Double epRiverMileStart;
    @Column(name = "EP_RIVER_MILE_END")
    private Double epRiverMileEnd;

    public EpCoordinate() {
    }

    public EpCoordinate(Integer epCoordinateId) {
        this.epCoordinateId = epCoordinateId;
    }

    public Integer getEpCoordinateId() {
        return epCoordinateId;
    }

    public void setEpCoordinateId(Integer epCoordinateId) {
        this.epCoordinateId = epCoordinateId;
    }

    public Integer getEpId() {
        return epId;
    }

    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    public Double getEpLatitudeStart() {
        return epLatitudeStart;
    }

    public void setEpLatitudeStart(Double epLatitudeStart) {
        this.epLatitudeStart = epLatitudeStart;
    }

    public Double getEpLongitudeStart() {
        return epLongitudeStart;
    }

    public void setEpLongitudeStart(Double epLongitudeStart) {
        this.epLongitudeStart = epLongitudeStart;
    }

    public Double getEpLatitudeEnd() {
        return epLatitudeEnd;
    }

    public void setEpLatitudeEnd(Double epLatitudeEnd) {
        this.epLatitudeEnd = epLatitudeEnd;
    }

    public Double getEpLongitudeEnd() {
        return epLongitudeEnd;
    }

    public void setEpLongitudeEnd(Double epLongitudeEnd) {
        this.epLongitudeEnd = epLongitudeEnd;
    }

    public Double getEpLeveeMileStart() {
        return epLeveeMileStart;
    }

    public void setEpLeveeMileStart(Double epLeveeMileStart) {
        this.epLeveeMileStart = epLeveeMileStart;
    }

    public Double getEpLeveeMileEnd() {
        return epLeveeMileEnd;
    }

    public void setEpLeveeMileEnd(Double epLeveeMileEnd) {
        this.epLeveeMileEnd = epLeveeMileEnd;
    }

    public Double getEpRiverMileStart() {
        return epRiverMileStart;
    }

    public void setEpRiverMileStart(Double epRiverMileStart) {
        this.epRiverMileStart = epRiverMileStart;
    }

    public Double getEpRiverMileEnd() {
        return epRiverMileEnd;
    }

    public void setEpRiverMileEnd(Double epRiverMileEnd) {
        this.epRiverMileEnd = epRiverMileEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epCoordinateId != null ? epCoordinateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpCoordinate)) {
            return false;
        }
        EpCoordinate other = (EpCoordinate) object;
        if ((this.epCoordinateId == null && other.epCoordinateId != null) || (this.epCoordinateId != null && !this.epCoordinateId.equals(other.epCoordinateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpCoordinate[ epCoordinateId=" + epCoordinateId + " ]";
    }
    
}
