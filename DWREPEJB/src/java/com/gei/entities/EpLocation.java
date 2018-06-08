/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.entities;

import java.io.Serializable;
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
@Table(name = "EP_LOCATION")
public class EpLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EpLocationPK epLocationPK;
    @Column(name = "USGS_QUAD")
    private String usgsQuad;
    @Column(name = "PARCEL_ID")
    private Integer parcelId;
    @Column(name = "EP_SECTION")
    private String epSection;
    @Column(name = "EP_TOWNSHIP")
    private String epTownship;
    @Column(name = "EP_RANGE")
    private String epRange;
    @Column(name = "EP_MERIDIAN")
    private String epMeridian;
    @Column(name = "EP_LOCATION_DESC")
    private String epLocationDesc;
    @Column(name = "EP_COUNTY")
    private String epCounty;
    @Column(name = "EP_MILEPOST")
    private String epMilepost;
    @Column(name = "EP_UNIT")
    private String epUnit;
    @Column(name = "EP_DISTRICT")
    private String epDistrict;
    @Column(name = "EP_STREAM")
    private String epStream;
    @Column(name = "EP_PARCELNO")
    private String epParcelno;
    @Column(name = "EP_LOCATION_FMI")
    private String epLocationFmi;
    @Column(name = "EP_LOCATION_STAFF")
    private String epLocationStaff;
    @Column(name = "STREAM_ID")
    private Integer streamId;
    @Column(name = "COUNTY_ID")
    private String countyId;
    @Column(name = "DISTRICT_ID")
    private Integer districtId;
//    @JoinColumn(name = "EP_ID", referencedColumnName = "EP_ID", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private EpApplication epApplication;
    @ManyToOne(optional = false)
    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "DISTRICT_ID", insertable = false, updatable = false)
    private DistrictLookup districtLookup;

    public EpLocation() {
    }

    public EpLocation(EpLocationPK epLocationPK) {
        this.epLocationPK = epLocationPK;
    }

    public EpLocation(Integer epLocationId, Integer epId) {
        this.epLocationPK = new EpLocationPK(epLocationId, epId);
    }

    public EpLocationPK getEpLocationPK() {
        return epLocationPK;
    }

    public void setEpLocationPK(EpLocationPK epLocationPK) {
        this.epLocationPK = epLocationPK;
    }

    public String getUsgsQuad() {
        return usgsQuad;
    }

    public void setUsgsQuad(String usgsQuad) {
        this.usgsQuad = usgsQuad;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public String getEpSection() {
        return epSection;
    }

    public void setEpSection(String epSection) {
        this.epSection = epSection;
    }

    public String getEpTownship() {
        return epTownship;
    }

    public void setEpTownship(String epTownship) {
        this.epTownship = epTownship;
    }

    public String getEpRange() {
        return epRange;
    }

    public void setEpRange(String epRange) {
        this.epRange = epRange;
    }

    public String getEpMeridian() {
        return epMeridian;
    }

    public void setEpMeridian(String epMeridian) {
        this.epMeridian = epMeridian;
    }

    public String getEpLocationDesc() {
        return epLocationDesc;
    }

    public void setEpLocationDesc(String epLocationDesc) {
        this.epLocationDesc = epLocationDesc;
    }

    public String getEpCounty() {
        return epCounty;
    }

    public void setEpCounty(String epCounty) {
        this.epCounty = epCounty;
    }

    public String getEpMilepost() {
        return epMilepost;
    }

    public void setEpMilepost(String epMilepost) {
        this.epMilepost = epMilepost;
    }

    public String getEpUnit() {
        return epUnit;
    }

    public void setEpUnit(String epUnit) {
        this.epUnit = epUnit;
    }

    public String getEpDistrict() {
        return epDistrict;
    }

    public void setEpDistrict(String epDistrict) {
        this.epDistrict = epDistrict;
    }

    public String getEpStream() {
        return epStream;
    }

    public void setEpStream(String epStream) {
        this.epStream = epStream;
    }

    public String getEpParcelno() {
        return epParcelno;
    }

    public void setEpParcelno(String epParcelno) {
        this.epParcelno = epParcelno;
    }

    public String getEpLocationFmi() {
        return epLocationFmi;
    }

    public void setEpLocationFmi(String epLocationFmi) {
        this.epLocationFmi = epLocationFmi;
    }

    public String getEpLocationStaff() {
        return epLocationStaff;
    }

    public void setEpLocationStaff(String epLocationStaff) {
        this.epLocationStaff = epLocationStaff;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public DistrictLookup getDistrictLookup() {
        return districtLookup;
    }

//    public EpApplication getEpApplication() {
//        return epApplication;
//    }
//
//    public void setEpApplication(EpApplication epApplication) {
//        this.epApplication = epApplication;
//    }
    public void setDistrictLookup(DistrictLookup districtLookup) {
        this.districtLookup = districtLookup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epLocationPK != null ? epLocationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpLocation)) {
            return false;
        }
        EpLocation other = (EpLocation) object;
        if ((this.epLocationPK == null && other.epLocationPK != null) || (this.epLocationPK != null && !this.epLocationPK.equals(other.epLocationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gei.entities.EpLocation[ epLocationPK=" + epLocationPK + " ]";
    }

}
