/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.data.objects;

import java.util.Date;

/**
 *
 * @author ileung
 */
public class TimeSeriesWSE {
    
    private Double wse;
    private Date date;

    /**
     * @return the wse
     */
    public Double getWse() {
        return wse;
    }

    /**
     * @param wse the wse to set
     */
    public void setWse(Double wse) {
        this.wse = wse;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
