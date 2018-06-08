/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

/**
 *
 * @author ileung
 */
public class XBlock {
    private Integer xmin;
    private Integer xmax;
    private Long offset;
    private final String fileName;
    
    public XBlock(Integer xmin, Integer xmax, Long offset, String fileName){
        this.xmin = xmin;
        this.xmax = xmax;
        this.offset = offset;
        this.fileName = fileName;
    }
    /**
     * @return the xmin
     */
    public Integer getXmin() {
        return xmin;
    }

    /**
     * @param xmin the xmin to set
     */
    public void setXmin(Integer xmin) {
        this.xmin = xmin;
    }

    /**
     * @return the xmax
     */
    public Integer getXmax() {
        return xmax;
    }

    /**
     * @param xmax the xmax to set
     */
    public void setXmax(Integer xmax) {
        this.xmax = xmax;
    }

    /**
     * @return the offset
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
}
