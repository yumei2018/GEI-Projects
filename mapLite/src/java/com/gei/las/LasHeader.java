/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Item                        Format           Size          Required 
 * File Signature (“LASF”)      char[4]          4 bytes         * 
 * File Source ID              unsigned short   2 bytes         * 
 * Global Encoding             unsigned short   2 bytes         * 
 * Project ID - GUID data 1    unsigned long    4 bytes 
 * Project ID - GUID data 2    unsigned short   2 byte 
 * Project ID - GUID data 3    unsigned short   2 byte 
 * Project ID - GUID data 4    unsigned char[8] 8 bytes 
 * Version Major               unsigned char    1 byte          * 
 * Version Minor               unsigned char    1 byte          * 
 * System Identifier           char[32]         32 bytes        * 
 * Generating Software         char[32]         32 bytes        * 
 * File Creation Day of Year   unsigned short   2 bytes         * 
 * File Creation Year          unsigned short   2 bytes         * 
 * Header Size                 unsigned short   2 bytes         * 
 * Offset to point data        unsigned long    4 bytes         * 
 * Number of Variable Length Records unsigned long 4 bytes      * 
 * Point Data Format ID (0-99 for spec) unsigned char 1 byte    * 
 * Point Data Record Length    unsigned short   2 bytes         * 
 * Number of point records unsigned long 4 bytes * 
 * Number of points by return  unsigned long[7] 28 bytes        * 
 * X scale factor              Double           8 bytes         * 
 * Y scale factor              Double           8 bytes         * 
 * Z scale factor              Double           8 bytes         * 
 * X offset                    Double           8 bytes         * 
 * Y offset                    Double           8 bytes         * 
 * Z offset                    Double           8 bytes         * 
 * Max X                       Double           8 bytes         * 
 * Min X                       Double           8 bytes         * 
 * Max Y                       Double           8 bytes         * 
 * Min Y                       Double           8 bytes         * 
 * Max Z                       Double           8 bytes         * 
 * Min Z                       Double           8 bytes         * 
 * Start of Waveform Data Packet Record Unsigned long long 8 bytes *
 */

/**
 *
 * @author hdunsford
 */
public class LasHeader {

    //<editor-fold defaultstate="collapsed" desc="Fields">

    // LASF                   // 4 bytes
    private int fileSourceId; // 2 bytes ushort
    private int globalEncoding; // 2 bytes ushort
    private long projectId_GUID_1; // 4 bytes uint
    private int projectId_GUID_2; // 2 bytes ushort
    private int projectId_GUID_3; // 2 bytes ushort
    private String projectId_GUID_4; // 8 bytes uchar[8]
    private char versionMajor; // 1 byte
    private char versionMinor; // 1 byte
    private String systemIdentifier; // 32 bytes
    private String generatingSoftware; // 32 byte
    private int fileCreationDayOfYear; // 2 bytes
    private int fileCreationYear; // 2 bytes
    private int headerSize; // 2 bytes
    private long offsetToPointData; // 4 bytes
    private long variableLengthRecordCount; // 4 bytes
    private byte pointDataFormatID; // 1 byte (0-99)
    private int pointDataRecordLength; // 2 bytes
    private long pointRecordCount; // 2 bytes;
    private long[] pointsByReturn; // 28 bytes;
    private double scaleFactorX; // 8 bytes;
    private double scaleFactorY; // 8 bytes;
    private double scaleFactorZ; // 8 bytes;
    private double offsetX; // 8 bytes;
    private double offsetY; // 8 bytes;
    private double offsetZ; // 8 bytes;
    private double maxX; // 8 bytes;
    private double minX; // 8 bytes;
    private double maxY; // 8 bytes;
    private double minY; // 8 bytes;
    private double maxZ; // 8 bytes;
    private double minZ; // 8 bytes;
    private BigInteger startOfWaveformDataPacketRecord; // 8 bytes;
            
    //</editor-fold>

    /**
     * Creates a new instance of the LasHeader class.
     */
    public LasHeader(){
  
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    public void readHeader(DataInputStream stream) throws IOException, LasReadException{
        ReadHelper rh = new ReadHelper(stream);
        String signature = rh.readChars(4);
        if(!"LASF".equals(signature)){
            throw new LasReadException("The file signature was incorrect.");
        }
        fileSourceId = rh.readUShort();
        globalEncoding = rh.readUShort();
        projectId_GUID_1 = rh.readUInt();
        projectId_GUID_2 = rh.readUShort();
        projectId_GUID_3 = rh.readUShort();
        projectId_GUID_4 = rh.readChars(8);
        versionMajor = rh.readChar();
        versionMinor = rh.readChar();
        systemIdentifier = rh.readChars(32);
        generatingSoftware = rh.readChars(32);
        fileCreationDayOfYear = rh.readUShort();
        fileCreationYear = rh.readUShort(); // byte 92
        headerSize = rh.readUShort();
        offsetToPointData = rh.readUInt();
        variableLengthRecordCount = rh.readUInt();
        pointDataFormatID = stream.readByte();
        pointDataRecordLength = rh.readUShort();
        pointRecordCount = rh.readUInt();
        pointsByReturn = rh.readUInt(5);
        scaleFactorX = rh.readDouble();
        scaleFactorY = rh.readDouble();
        scaleFactorZ = rh.readDouble();
        offsetX = rh.readDouble();
        offsetY = rh.readDouble(); 
        offsetZ = rh.readDouble();
        maxX = rh.readDouble();
        minX = rh.readDouble();
        maxY = rh.readDouble();
        minY = rh.readDouble();
        maxZ = rh.readDouble();
        minZ = rh.readDouble();
        startOfWaveformDataPacketRecord = rh.readULong();
        boolean stop = true;
    }
    
    public void writeHeader(DataOutputStream stream) throws IOException{
        stream.writeBytes("LASF");
        stream.writeChar(fileSourceId);
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">

    /**
     * @return the fileSourceId
     */
    public int getFileSourceId() {
        return fileSourceId;
    }

    /**
     * @param fileSourceId the fileSourceId to set
     */
    public void setFileSourceId(int fileSourceId) {
        this.fileSourceId = fileSourceId;
    }

    /**
     * @return the globalEncoding
     */
    public int getGlobalEncoding() {
        return globalEncoding;
    }

    /**
     * @param globalEncoding the globalEncoding to set
     */
    public void setGlobalEncoding(int globalEncoding) {
        this.globalEncoding = globalEncoding;
    }

    /**
     * @return the projectId_GUID_1
     */
    public long getProjectId_GUID_1() {
        return projectId_GUID_1;
    }

    /**
     * @param projectId_GUID_1 the projectId_GUID_1 to set
     */
    public void setProjectId_GUID_1(long projectId_GUID_1) {
        this.projectId_GUID_1 = projectId_GUID_1;
    }

    /**
     * @return the projectId_GUID_2
     */
    public int getProjectId_GUID_2() {
        return projectId_GUID_2;
    }

    /**
     * @param projectId_GUID_2 the projectId_GUID_2 to set
     */
    public void setProjectId_GUID_2(int projectId_GUID_2) {
        this.projectId_GUID_2 = projectId_GUID_2;
    }

    /**
     * @return the projectId_GUID_3
     */
    public int getProjectId_GUID_3() {
        return projectId_GUID_3;
    }

    /**
     * @param projectId_GUID_3 the projectId_GUID_3 to set
     */
    public void setProjectId_GUID_3(int projectId_GUID_3) {
        this.projectId_GUID_3 = projectId_GUID_3;
    }

    /**
     * @return the projectId_GUID_4
     */
    public String getProjectId_GUID_4() {
        return projectId_GUID_4;
    }

    /**
     * @param projectId_GUID_4 the projectId_GUID_4 to set
     */
    public void setProjectId_GUID_4(String projectId_GUID_4) {
        this.projectId_GUID_4 = projectId_GUID_4;
    }

    /**
     * @return the versionMajor
     */
    public char getVersionMajor() {
        return versionMajor;
    }

    /**
     * @param versionMajor the versionMajor to set
     */
    public void setVersionMajor(char versionMajor) {
        this.versionMajor = versionMajor;
    }

    /**
     * @return the versionMinor
     */
    public char getVersionMinor() {
        return versionMinor;
    }

    /**
     * @param versionMinor the versionMinor to set
     */
    public void setVersionMinor(char versionMinor) {
        this.versionMinor = versionMinor;
    }

    /**
     * @return the systemIdentifier
     */
    public String getSystemIdentifier() {
        return systemIdentifier;
    }

    /**
     * @param systemIdentifier the systemIdentifier to set
     */
    public void setSystemIdentifier(String systemIdentifier) {
        this.systemIdentifier = systemIdentifier;
    }

    /**
     * @return the generatingSoftware
     */
    public String getGeneratingSoftware() {
        return generatingSoftware;
    }

    /**
     * @param generatingSoftware the generatingSoftware to set
     */
    public void setGeneratingSoftware(String generatingSoftware) {
        this.generatingSoftware = generatingSoftware;
    }

    /**
     * @return the fileCreationDayOfYear
     */
    public int getFileCreationDayOfYear() {
        return fileCreationDayOfYear;
    }

    /**
     * @param fileCreationDayOfYear the fileCreationDayOfYear to set
     */
    public void setFileCreationDayOfYear(int fileCreationDayOfYear) {
        this.fileCreationDayOfYear = fileCreationDayOfYear;
    }

    /**
     * @return the fileCreationYear
     */
    public int getFileCreationYear() {
        return fileCreationYear;
    }

    /**
     * @param fileCreationYear the fileCreationYear to set
     */
    public void setFileCreationYear(int fileCreationYear) {
        this.fileCreationYear = fileCreationYear;
    }

    /**
     * @return the headerSize
     */
    public int getHeaderSize() {
        return headerSize;
    }

    /**
     * @param headerSize the headerSize to set
     */
    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    /**
     * @return the offsetToPointData
     */
    public long getOffsetToPointData() {
        return offsetToPointData;
    }

    /**
     * @param offsetToPointData the offsetToPointData to set
     */
    public void setOffsetToPointData(long offsetToPointData) {
        this.offsetToPointData = offsetToPointData;
    }

    /**
     * @return the variableLengthRecordCount
     */
    public long getVariableLengthRecordCount() {
        return variableLengthRecordCount;
    }

    /**
     * @param variableLengthRecordCount the variableLengthRecordCount to set
     */
    public void setVariableLengthRecordCount(long variableLengthRecordCount) {
        this.variableLengthRecordCount = variableLengthRecordCount;
    }

    /**
     * @return the pointDataFormatID
     */
    public byte getPointDataFormatID() {
        return pointDataFormatID;
    }

    /**
     * @param pointDataFormatID the pointDataFormatID to set
     */
    public void setPointDataFormatID(byte pointDataFormatID) {
        this.pointDataFormatID = pointDataFormatID;
    }

    /**
     * @return the pointDataRecordLength
     */
    public int getPointDataRecordLength() {
        return pointDataRecordLength;
    }

    /**
     * @param pointDataRecordLength the pointDataRecordLength to set
     */
    public void setPointDataRecordLength(int pointDataRecordLength) {
        this.pointDataRecordLength = pointDataRecordLength;
    }

    /**
     * @return the pointRecordCount
     */
    public long getPointRecordCount() {
        return pointRecordCount;
    }

    /**
     * @param pointRecordCount the pointRecordCount to set
     */
    public void setPointRecordCount(long pointRecordCount) {
        this.pointRecordCount = pointRecordCount;
    }

    /**
     * @return the pointsByReturn
     */
    public long[] getPointsByReturn() {
        return pointsByReturn;
    }

    /**
     * @param pointsByReturn the pointsByReturn to set
     */
    public void setPointsByReturn(long[] pointsByReturn) {
        this.pointsByReturn = pointsByReturn;
    }

    /**
     * @return the scaleFactorX
     */
    public double getScaleFactorX() {
        return scaleFactorX;
    }

    /**
     * @param scaleFactorX the scaleFactorX to set
     */
    public void setScaleFactorX(double scaleFactorX) {
        this.scaleFactorX = scaleFactorX;
    }

    /**
     * @return the scaleFactorY
     */
    public double getScaleFactorY() {
        return scaleFactorY;
    }

    /**
     * @param scaleFactorY the scaleFactorY to set
     */
    public void setScaleFactorY(double scaleFactorY) {
        this.scaleFactorY = scaleFactorY;
    }

    /**
     * @return the scaleFactorZ
     */
    public double getScaleFactorZ() {
        return scaleFactorZ;
    }

    /**
     * @param scaleFactorZ the scaleFactorZ to set
     */
    public void setScaleFactorZ(double scaleFactorZ) {
        this.scaleFactorZ = scaleFactorZ;
    }

    /**
     * @return the offsetX
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * @param offsetX the offsetX to set
     */
    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * @return the offsetY
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * @param offsetY the offsetY to set
     */
    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * @return the offsetZ
     */
    public double getOffsetZ() {
        return offsetZ;
    }

    /**
     * @param offsetZ the offsetZ to set
     */
    public void setOffsetZ(double offsetZ) {
        this.offsetZ = offsetZ;
    }

    /**
     * @return the maxX
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    /**
     * @return the minX
     */
    public double getMinX() {
        return minX;
    }

    /**
     * @param minX the minX to set
     */
    public void setMinX(double minX) {
        this.minX = minX;
    }

    /**
     * @return the maxY
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    /**
     * @return the minY
     */
    public double getMinY() {
        return minY;
    }

    /**
     * @param minY the minY to set
     */
    public void setMinY(double minY) {
        this.minY = minY;
    }

    /**
     * @return the maxZ
     */
    public double getMaxZ() {
        return maxZ;
    }

    /**
     * @param maxZ the maxZ to set
     */
    public void setMaxZ(double maxZ) {
        this.maxZ = maxZ;
    }

    /**
     * @return the minZ
     */
    public double getMinZ() {
        return minZ;
    }

    /**
     * @param minZ the minZ to set
     */
    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    /**
     * @return the startOfWaveformDataPacketRecord
     */
    public BigInteger getStartOfWaveformDataPacketRecord() {
        return startOfWaveformDataPacketRecord;
    }

    /**
     * @param startOfWaveformDataPacketRecord the startOfWaveformDataPacketRecord to set
     */
    public void setStartOfWaveformDataPacketRecord(BigInteger startOfWaveformDataPacketRecord) {
        this.startOfWaveformDataPacketRecord = startOfWaveformDataPacketRecord;
    }
  

    //</editor-fold>

}
