/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

import java.util.List;

/**
 *
 * @author ileung
 */
public class FilesOffsetNode {
    private Integer ymin;
    private Integer ymax;
    private Integer xmin;
    private Integer xmax;
    private Long offset;
    private final List<XBlock> xBlock;
    private String fileName;
    
    public FilesOffsetNode(Integer ymin, Integer ymax, Integer xmin, Integer xmax, Long offset, List<XBlock> xblock) {
        this.ymin = ymin;
        this.ymax = ymax;
        this.xmin = xmin;
        this.xmax = xmax;
        this.offset = offset;
        this.xBlock = xblock;
    }
    /**
     * @return the ymin
     */
    public Integer getYmin() {
        return ymin;
    }

    /**
     * @param ymin the ymin to set
     */
    public void setYmin(Integer ymin) {
        this.ymin = ymin;
    }

    /**
     * @return the ymax
     */
    public Integer getYmax() {
        return ymax;
    }

    /**
     * @param ymax the ymax to set
     */
    public void setYmax(Integer ymax) {
        this.ymax = ymax;
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
     * @return the xBlock
     */
    public List<XBlock> getxBlock() {
        return xBlock;
    }

    /**
     * @return the xmin
     */
    public Integer getXmin() {
        return xmin;
    }

    /**
     * @return the xmax
     */
    public Integer getXmax() {
        return xmax;
    }
    
}
