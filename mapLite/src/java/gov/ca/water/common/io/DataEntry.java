package gov.ca.water.common.io;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Utility Class with static Methods to convert & compare Strings, and compare numbers.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class DataEntry {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  private static final Logger logger = Logger.getLogger(DataEntry.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Field">
  /**
   * Private Static Constant
   */
  public static final double TOLERANCE = 10E-6;
  /**
   * Max iters for improvement = twice # of significant digits
   */
  public static final int MAX_ITER;

  static {
    int i = 0;
    double t = TOLERANCE;
    while (t < 1) {
      ++i;
      t *= 10;
    }
    MAX_ITER = 2 * i;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Comparisons">
  /**
   * Check if curObj matches newObj.
   * @param curObj the current object
   * @param newObj the new input object 
   * @return true is both are null or curObj.equals(newObj)
   */
  public static boolean isEq(Object curObj, Object newObj) {
    return ((curObj == null) ? (newObj == null): curObj.equals(newObj));
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="String Conversions/Test">
  /**
   * Text Trim Enums
   */
  public static final int TEXT_TRIMLEFT = 1;
  public static final int TEXT_TRIMRIGHT = 2;
  public static final int TEXT_TRIMALL = 3;
  
  /**
   * Check if sInsTr string is an empty string (""|null).
   * @param sInStr the string to check
   * @return true if cleanString(sInstr) == null
   */
  public static boolean isEmpty(String sInStr) {
    return (DataEntry.cleanString(sInStr) == null);
  }
  
  /**
   * OVERLOAD 1: Returns a trimmed string or null if the string is empty.
   * @param sInStr
   * @return String
   */
  public static String cleanString(String sInStr) {
    return ((sInStr == null) || (sInStr.trim().equals(""))) ? null
            : sInStr.trim();
  }
  
  /**
   * OVERLOAD 2: Returns a trimmed string or null if the string is empty.
   * If the length of the trimmed exceeds iMaxLength (for iMaxLength > 0), it will
   * return the first iMaxLength of the trimmed string.  Use this call for preparing
   * limited length string for assigning to the database records.
   * @param sInStr String
   * @param iMaxLength int
   * @return String
   */
  public static String cleanString(String sInStr, int iMaxLength) {
    String sResult = ((sInStr == null) || (sInStr.trim().equals(""))) ? null
            : sInStr.trim();
    if ((sResult != null) && (iMaxLength > 0) && (sResult.length() > iMaxLength)) {
      sResult = sResult.substring(0, iMaxLength);
    }
    return sResult;
  }
  /**
   * OVERLOAD 3: Clean the string by trimming it according to eTextTrim(TEXT_TRIMLEFT | 
   * TEXT_TRIMRIGHT | TEXT_TRIMALL) and truncate the string to iMaxLength if 
   * (iMaxLength>0) and the string's length exceeds iMaxLength.  Use this call for 
   * preparing limited length string for assigning to the database records and 
   * preserving either leading or trailing blanks.
   * @param sInStr the input String
   * @param iMaxLength the maximum sting length
   * @return the cleaned string null if the string is empty.
   */
  public static String cleanString(String sInStr, int iMaxLength, int eTextTrim) {
    String sResult = null;
    if (sInStr != null) {
      switch (eTextTrim) {
        case DataEntry.TEXT_TRIMLEFT:
          sResult = sInStr + "_x";
          sResult = sResult.trim();
          if (sResult.equals("_x")) {
            sResult = null;
          } else {
            sResult = sResult.substring(0, (sResult.length()-2));
          }          
          break;
        case DataEntry.TEXT_TRIMRIGHT:
          sResult = "x_" + sInStr;
          sResult = sResult.trim();
          if (sResult.equals("x_")) {
            sResult = null;
          } else {
            sResult = sResult.substring(2);
          }      
          break;
        default:
          sResult = sInStr.trim();
          break;
      }
    }
    
    if ((sResult != null) && (iMaxLength > 0) && (sResult.length() > iMaxLength)) {
      sResult = sResult.substring(0, iMaxLength);
    }
    return sResult;
  }
  
  /**
   * OVERLOAD 4: Called to "clean" sValue (i.e., removing trailing blanks and delimiters
   * as defined by sDelimiters.
   * @param sValue String
   * @param sDelimiters String
   * @return String
   */
  public static String cleanString(String sValue, String sDelimiters) {
    String sResult = DataEntry.cleanString(sValue);
    sDelimiters = DataEntry.cleanString(sDelimiters);
    if (sDelimiters != null) {
      while (sResult != null) {
        String sLast = sResult.substring(sResult.length()-1);
        if (sLast.matches(sDelimiters)) {
          sResult = DataEntry.cleanString(sResult.substring(0, sResult.length()-1));
        } else {
          break;
        }
      }
    }
    return sResult;
  }
  
  /**
   * Split the input string into a list of substring using the specified Delimiter to 
   * split the string.
   * Return empty values as "" and return an empty string id sStringList = null|""
   * @param inputStr the input string to parse into sub-strings
   * @param delimiters the delimiters to use in splitting the string into a string list 
   * (default=" ") - multiple delimiters can specified (e.g., ", /;".
   * @return the list if sub-string or an empty list if the input string is empty.
   */
  public static List<String> splitString(String inputStr, String delimiters) {
    List<String> result = new ArrayList<>();
    inputStr = DataEntry.cleanString(inputStr);
    if (inputStr != null) {
      Scanner strScanner = new Scanner(inputStr);
      strScanner.useDelimiter(delimiters);
      while (strScanner.hasNext()) {
        String nextStr = DataEntry.cleanString(strScanner.next());
        if (nextStr != null) {            
          result.add(nextStr);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the Proper Case of the String.
   * @param sInStr String
   * @return String
   */
  public static String toProper(String sInStr) {
    String sResult = null;
    sInStr = (sInStr == null)? null: sInStr.trim().toLowerCase();
    if ((sInStr != null) && (sInStr.length() > 0)) {
      List<String> pWords = DataEntry.splitString(sInStr, " ");
      if (pWords.size() > 0) {
        for (String sSubStr: pWords) {
          sSubStr = sSubStr.trim();
          if (sSubStr.equals("")) {
            continue;
          }
          
          String sCh1 = sSubStr.substring(0, 1);
          String sCh2 = sCh1.toUpperCase();
          sSubStr = sSubStr.replaceFirst(sCh1, sCh2);
          if (sResult == null) {
            sResult = sSubStr;
          } else {
            sResult += (" " + sSubStr);
          }
        }
      }
    }
    return sResult;
  }
  
  /**
   * Called to left Pad the sInstr with sPadStr until the sInt.Length is >= iLength.
   * if (sInstr = null), set sInstr = "" before padding. if sPadStr = empty or null,
   * set sPadStr = " ".
   * @param sInStr String
   * @param sPadStr String
   * @param iLength int
   * @return String
   */
  public static String leftPadString(String sInStr, String sPadStr, int iLength) {
    String sResult = (sInStr == null) ? "" : sInStr;
    sPadStr = ((sPadStr == null) || (sPadStr.equals(""))) ? " " : sPadStr;
    while (sResult.length() < iLength) {
      sResult = sPadStr + sResult;
    }
    return sResult;
  }

  /**
   * Trim all leading and ending spaces and if the input string is not empty strip out 
   * any non-numeric characters
   * @param sInStr
   * @param bIsDecimal
   * @return String
   */
  public static String cleanNumericString(String sInStr) {
    String sResult = null;
    sInStr = DataEntry.cleanString(sInStr);
    if (sInStr != null) {
      String sRegExp = "^[\\D]*[ ]*g$";
      sResult = sInStr.replaceAll(sRegExp, "");
    }
    return sResult;
  }

  /**
   * Format a numeric string by adding a delimiter (e.g., sDelimiter="-" - not an empty 
   * string, but it could be a space or spaces) at the character spacing defined in 
   * pSpacing after sInStr is trimmed from any leading or ending spaces. For example
   * formating sInstr="0000000000" using pSpacing={3,3,4} using sDelimiter="-" will 
   * return "000-000-0000". If sInstr.length exceeds the sum of pSpacing, the trailing
   * characters will be ignored.  if sInstr.length less than the sum of pSpacing, the
   * String will be right padded with "0". The trimmed sInstr will be returned if 
   * (pSpacing=null|empty) or (sDelimiter=null|"").
   * @param sInStr String
   * @param bIsDecimal
   * @return String
   */
  public static String formatNumericString(String sInStr, String sDelimiter, 
            int[] pSpacings) {
    String sResult = null;
    sInStr = DataEntry.cleanString(sInStr);
    if ((sDelimiter == null) || (sDelimiter.equals("")) || 
            (pSpacings == null) || (pSpacings.length == 0)) {
      sResult = sInStr;
    } else if (sInStr != null) {
      int iSumChars = 0;
      for (int iChars : pSpacings) {
        iSumChars += iChars;
      }
      
      if (sInStr.length() < iSumChars) {
        sInStr = DataEntry.rightPadString(sInStr, "0", iSumChars);
      }
      
      int iLast = 0;
      List<String> pSubStrings = new ArrayList<>();
      for (int iChars : pSpacings) {
        if (iChars <= 0) {
          continue;
        }
        int iNext = iLast + iChars;
        if (iNext >= (sInStr.length()-1)) {
          pSubStrings.add(sInStr.substring(iLast));
          iLast = sInStr.length();
          break;
        } else {
          pSubStrings.add(sInStr.substring(iLast, iNext));
          iLast = iNext;
        }
      }
      
      if (pSubStrings.size() <= 1) {
        sResult = sInStr;
      } else {
        for (String sSubStr : pSubStrings) {
          if (sResult == null) {
            sResult = sSubStr;                    
          } else {
            sResult += sDelimiter;
            sResult += sSubStr;
          }
        }
      }
    }
    return sResult;
  }
  
  /**
   * Called to Pad right the sInstr with sPadStr until the sInt.Length is >= iLength.
   * if (sInstr = null), set sInstr = "" before padding. if sPadStr = empty or null,
   * set sPadStr = " ".
   * @param sInStr String
   * @param sPadStr String
   * @param iLength int
   * @return String
   */
  public static String rightPadString(String sInStr, String sPadStr, int iLength) {
    String sResult = (sInStr == null) ? "" : sInStr;
    sPadStr = ((sPadStr == null) || (sPadStr.equals(""))) ? " " : sPadStr;
    while (sResult.length() < iLength) {
      sResult += sPadStr;
    }
    return sResult;
  } 
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="String Comparisions">
  /**
   * Check if sNewValue matches sCurValue. First clean sNewValue by calling the
   * cleanString method and then compare them - ignoring case is bIngnoreCase.
   * Return true if both strings are empty or match.
   * Note: sCurvalue is not "cleaned"
   * @param sCurValue the current value
   * @param sNewValue the new input value
   * @param bIgnoreCase if true, do a case insensitive comparison
   * @return boolean
   */
  public static boolean isEq(String sCurValue, String sNewValue,
          boolean bIgnoreCase) {
    sNewValue = DataEntry.cleanString(sNewValue);
    return ((sCurValue == null) ? (sNewValue == null)
            : ((bIgnoreCase)? sCurValue.equalsIgnoreCase(sNewValue):
            sCurValue.equals(sNewValue)));
  }
  
  /**
   * Check if sNewValue matches sCurValue. If (!bSkipTrim), first clean sNewValue by
   * calling the cleanString method and then compare them - ignoring case if
   * bIngnoreCase=true. Return true if both strings are empty or match.
   * Note: sCurvalue is not "cleaned"
   * @param sCurValue the current value
   * @param sNewValue the new input value
   * @param bIgnoreCase if true, do a case insensitive comparison
   * @param bSkipTrim if true, sNewValue is not trimmed to remove leading and/or
   * trailing spaces.
   * @return boolean
   */
  public static boolean isEq(String sCurValue, String sNewValue,
          boolean bIgnoreCase, boolean bSkipTrim) {
    sNewValue = (bSkipTrim)? sNewValue: DataEntry.cleanString(sNewValue);
    return ((sCurValue == null) ? (sNewValue == null)
            : ((bIgnoreCase)? sCurValue.equalsIgnoreCase(sNewValue):
            sCurValue.equals(sNewValue)));
  }
  
  /**
   * Check if sNewValue matches sCurValue. First clean sNewValue by calling the
   * cleanString with iMaxLength set method and then compare them -
   * ignoring case is bIngnorCase.Return true if both strings are empty or match.
   * Note: sCurvalue is not "cleaned"
   * @param sCurValue String
   * @param sNewValue String
   * @param bIgnoreCase boolean
   * @param iMaxLength int
   * @return boolean
   */
  public static boolean isEq(String sCurValue, String sNewValue,
          boolean bIgnoreCase, int iMaxLength) {
    sNewValue = DataEntry.cleanString(sNewValue, iMaxLength);
    return ((sCurValue == null) ? (sNewValue == null)
            : ((bIgnoreCase)? sCurValue.equalsIgnoreCase(sNewValue):
            sCurValue.equals(sNewValue)));
  }
  
  /**
   * Check if a value (sValue) is equal to a value in the array pValue of possible
   * values. Return false if sValue=""|null or if pValue=null|empty. Matchings is not
   * case sensitive.
   * @param sValue string to check
   * @param pValues array of string to match
   * @return true if a match is found.
   */
  public static boolean inList(String sValue, String[] pValues) {
    boolean bResult = false;
    try {
      sValue = DataEntry.cleanString(sValue);
      if ((sValue != null) || (pValues != null) && (pValues.length > 0)) {
        for (String sItem : pValues) {
          if (sValue.equalsIgnoreCase(sItem)) {
            bResult = true;
            break;
          }
        }
      }
    } catch (Exception pExp) {
      logger.log(Level.WARNING, "{0}.inList Error:\n {1}",
              new Object[]{"DataComparer", pExp.getMessage()});
    }
    return bResult;
  }
  //</editor-fold>
   
  //<editor-fold defaultstate="collapsed" desc="Min/Max Integer">
  /**
   * Get the Maximum of an array of integer values
   * @param values array of values
   * @return the maximum value (or Integer.MIN_VALUE if values = null|empty)
   */
  public static int Max(int...values) {
    int result = Integer.MIN_VALUE;
    if ((values != null) && (values.length > 0)) {
      for (int intVal : values) {
        result = Math.max(intVal, result);
      }
    }
    return result;
  }
  
  /**
   * Get the Minimum of an array of integer values
   * @param values array of values
   * @return the minimum value (or Integer.MAX_VALUE if values = null|empty)
   */
  public static int Min(int...values) {
    int result = Integer.MAX_VALUE;
    if ((values != null) && (values.length > 0)) {
      for (int intVal : values) {
        result = Math.min(intVal, result);
      }
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Double Comparisions">
  /**
   * Compare two double values for equality
   * @param val1 first value
   * @param val2 second value
   * @return true if ((val1=NaN) & (val2=NaN) or (abs(val1-val2) <= TOLERANCE)
   */
  public static boolean isEq(double val1, double val2) {
    boolean result = ((Double.isNaN(val1)) && (Double.isNaN(val2)));
    if (!result) {
      result = (Math.abs(val1-val2) <= DataEntry.TOLERANCE);
    }
    return result;
  }
  
  /**
   * Check is val1 &le; val2
   * @param val1 first value
   * @param val2 second value
   * @return true if ((val1=NaN) & (val2=NaN) or (val1+TOLERANCE) &le; val21)
   */
  public static boolean isLE(double val1, double val2) {
    boolean result = ((Double.isNaN(val1)) && (Double.isNaN(val2)));
    if (!result) {
      result = ((val1 + DataEntry.TOLERANCE) <= val2);
    }
    return result;
  }
    
  /**
   * Check is val1 &lt; val2
   * @param val1 first value
   * @param val2 second value
   * @return true if ((val1!=NaN) & (val2!=NaN) and (val1+TOLERANCE) &lt; Val2)
   */
  public static boolean isLT(double val1, double val2) {
    boolean result = (!(Double.isNaN(val1)) && (!Double.isNaN(val2)));
    if (result) {
      result = ((val1 + DataEntry.TOLERANCE) < val2);
    }
    return result;
  }
  
  /**
   * Check is val1 &ge; val2
   * @param val1 first value
   * @param val2 second value
   * @return true if ((val1=NaN) & (val2=NaN) or (val1-TOLERANCE) &gt Val1)
   */
  public static boolean isGE(double val1, double val2) {
    boolean result = ((Double.isNaN(val1)) && (Double.isNaN(val2)));
    if (!result) {
      result = ((val1 + DataEntry.TOLERANCE) >= val2);
    }
    return result;
  }
      
  /**
   * Check is val1 &gt; val2
   * @param val1 first value
   * @param val2 second value
   * @return true if ((val1!=NaN) & (val2!=NaN) and (val1-TOLERANCE) &gt; Val2)
   */
  public static boolean isGT(double val1, double val2) {
    boolean result = (!(Double.isNaN(val1)) && (!Double.isNaN(val2)));
    if (result) {
      result = ((val1 - DataEntry.TOLERANCE) > val2);
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Double Conversion/Test ">
  /**
   * Get the Maximum of an array of integer values
   * @param values array of values
   * @return the maximum value (or Double.NaN if values = null|empty)
   */
  public static double Max(double...values) {
    double result = Double.NaN;
    if ((values != null) && (values.length > 0)) {
      result = values[0];
      if (values.length > 1) {
        for (int i = 1; i < values.length; i++) {
          if ((Double.compare(result, values[i])) < 0) {
            result = values[i];
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Get the Minimum of an array of integer values
   * @param values array of values
   * @return the minimum value (or Integer.MAX_VALUE if values = null|empty)
   */
  public static double Min(double...values) {
    double result = Double.NaN;
    if ((values != null) && (values.length > 0)) {
      result = values[0];
      if (values.length > 1) {
        for (int i = 1; i < values.length; i++) {
          if ((Double.compare(result, values[i])) > 0) {
            result = values[i];
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Round the specified value to the precision set by {@linkplain DataEntry#TOLERANCE}
   * @param value the specified value
   * @return the rounded up value or NaN if value=NaN, and 0.0 if {@linkplain 
   * #isEq(double, double) isEq(value, 0.0)}.
   */
  public static double roundToTolerance(double value) {
    double result = value;
    if (!Double.isNaN(value)) {
      if (DataEntry.isEq(value, 0.0d)) {
        result = 0.0d;
      } else {
        Double log = Math.log10(DataEntry.TOLERANCE);
        int tolPrec = -1*log.intValue();
        int valSigNum = DataEntry.significantDigit(value, 1);
        int precision = tolPrec + valSigNum;
        result = DataEntry.roundSignificantDigit(value, precision);
      }
    }
    return result;
  }
  /**
   * Round the specified <tt>value</tt> down to the specified precision.
   * @param value the specified value
   * @param precision the number of non-zero digits in the number (e.g., for precision=3;
   * 1251.999 = 1260.00; 0.00125199 = 0.00126). Valid range is precision &ge; 1)
   * @return the rounded up value or NaN if value=NaN, and 0.0 if {@linkplain 
   * #isEq(double, double) isEq(value, 0.0)}.
   */
  public static double roundSignificantDigit(double value, int precision) {
    double result = Double.NaN;
    precision = (precision < 1)? 1: precision;
    if (!Double.isNaN(value)) {
      if (DataEntry.isEq(value, 0.0d)) {
        result = 0.0d;
      } else {
        BigDecimal bigDec = BigDecimal.valueOf(value);
        MathContext mathCtx = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal bigResult = bigDec.round(mathCtx);
        result = bigResult.doubleValue();
      }
    }
    return result;
  } 
  
  /**
   * Calculates the significant Digits of the specified <tt>value</tt> and precision = 2.
   * @param value the specified value 
   * @return {@linkplain DataEntry#significantDigit(double, int)}
   * DataEntry.significantDigit(value, 2).
   */
  public static Integer significantDigit(double value) {    
    return DataEntry.significantDigit(value, 2);
  }
  
  /**
   * Calculates the significant Digits of the specified <tt>value</tt>. 
   * @param value the specified value 
   * @param precision the number of non-zero digits in the number (e.g., for precision=3;
   * 1251.999 = 1250.00; 0.00125199 = 0.00125). Valid range is precision &ge; 1)
   * @return the significant digit or null if value = 0|NaN
   */
  public static Integer significantDigit(double value, int precision) {
    Integer result = null;
    precision = (precision < 1)? 1: precision;
    if ((!Double.isNaN(value)) && (!DataEntry.isEq(value, 0.0d))) {
      double absVal = Math.abs(value);
      Double log = Math.log10(absVal);
      result = log.intValue();
      if (absVal >= 1.0d) {
        result -= (precision - 0);
      } else {
        result -= precision;
      }
    }
    return result;
  }
  
  /**
   * Round the specified <tt>value</tt> up to precision = 2.
   * @param value the specified value
   * @return {@linkplain DataEntry#roundUpSignificantDigit(double, int) 
   * DataEntry.roundUpSignificantDigit(value, 2)}
   */
  public static double roundUpSignificantDigit(double value) {    
    return DataEntry.roundUpSignificantDigit(value, 2);
  }
  
  /**
   * Round the specified <tt>value</tt> up to the specified precision.
   * @param value the specified value
   * @param precision the number of non-zero digits in the number (e.g., for precision=3;
   * 1251.999 = 1260.00; 0.00125199 = 0.00126). Valid range is precision &ge; 1)
   * @return the rounded up value or NaN if value=NaN, and 0.0 if {@linkplain 
   * #isEq(double, double) isEq(value, 0.0)}.
   */
  public static double roundUpSignificantDigit(double value, int precision) {
    double result = Double.NaN;
    precision = (precision < 1)? 1: precision;
    if (!Double.isNaN(value)) {
      if ((DataEntry.isEq(value, 0.0d)) || 
              (DataEntry.significantDigit(value) == null)) {
        result = 0.0d;
      } else {
        BigDecimal bigDec = BigDecimal.valueOf(value);
        MathContext mathCtx = new MathContext(precision, RoundingMode.CEILING);
        BigDecimal bigResult = bigDec.round(mathCtx);
        result = bigResult.doubleValue();
      }
    }
    return result;
  }
  
  /**
   * Round the specified <tt>value</tt> down to precision = 2.
   * @param value the specified value
   * @return {@linkplain DataEntry#roundUpSignificantDigit(double, int) 
   * DataEntry.roundUpSignificantDigit(value, 2)}
   */
  public static double roundDnSignificantDigit(double value) {
    return DataEntry.roundDnSignificantDigit(value, 2);
  }  
  
  /**
   * Round the specified <tt>value</tt> down to the specified precision.
   * @param value the specified value
   * @param precision the number of non-zero digits in the number (e.g., for precision=3;
   * 1251.999 = 1260.00; 0.00125199 = 0.00126). Valid range is precision &ge; 1)
   * @return the rounded up value or NaN if value=NaN, and 0.0 if {@linkplain 
   * #isEq(double, double) isEq(value, 0.0)}.
   */
  public static double roundDnSignificantDigit(double value, int precision) {
    double result = Double.NaN;
    precision = (precision < 1)? 1: precision;
    if (!Double.isNaN(value)) {
      if ((DataEntry.isEq(value, 0.0d)) || 
              (DataEntry.significantDigit(value) == null)) {
        result = 0.0d;
      } else {
        BigDecimal bigDec = BigDecimal.valueOf(value);
        MathContext mathCtx = new MathContext(precision, RoundingMode.FLOOR);
        BigDecimal bigResult = bigDec.round(mathCtx);
        result = bigResult.doubleValue();
      }
    }
    return result;
  }  
  
  /**
   * Get a Double value formatted to the specified format
   * @param value the double value (can be null)
   * @param decimals the number of decimal places Range >= 0
   * @param showPlus true to show the plus sign in font the the number
   * @param prefix the prefix to add to a non-null value
   * @param suffix the suffix to add to a non-null value
   * @param nullValStr the string to return is value=null.
   * @return the formatted string.
   */
  public static String toString(Double value, int decimals, boolean showPlus,
          String prefix, String suffix, String nullValStr) {
    String result = nullValStr;
    if ((value != null) && (!value.equals(Double.NaN))) {
      String formatStr = "%1$";
      if (showPlus) {
        formatStr += "+";
      }
      decimals = (decimals >= 0)? decimals: 0;
      formatStr += "." + decimals + "f";
      result = String.format(formatStr, value);
      if (prefix != null) {
        result = prefix + result;
      }
      if (suffix != null) {
        result += suffix;
      }
    }
    return result;
  }
  //</editor-fold>
}
