package entity.core.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soi
 */
public class FileUtil {

  /**
   *
   * @param pathandfilename
   * @return
   */
  public static String readFrom(String pathandfilename) {
    return readFrom(pathandfilename, StandardCharsets.UTF_8);
  }

  /**
   *
   * @param f
   * @return
   */
  public static String readFrom(File f) {
    return readFrom(f.getAbsolutePath(), StandardCharsets.UTF_8);
  }

  /**
   *
   * @param pathandfilename
   * @param encode
   * @return
   */
  public static String readFrom(String pathandfilename, Charset encode) {
    String result = "";
    try {
      Path path = Paths.get(pathandfilename);
      Scanner scanner = new Scanner(path, encode.name());
      while (scanner.hasNextLine()) {
        result += scanner.nextLine() + "\n";
      }
      scanner.close();
    } catch (Exception ex) {
      Logger
              .getLogger(FileUtil.class.getName())
              .log(Level.WARNING, ex.toString());
    }

    if (result.length() > 0) {
      result = result.substring(0, result.length() - 1);
    }

    return result;
  }

  public static void writeTo(String pathandfilename, String data) {
    String filename = parseFilename(pathandfilename), path = pathandfilename.replace(filename, "");
    writeTo(path, filename, data, false);
  }

  public static void writeTo(String path, String filename, String data, boolean append) {
    File dir = new File(path);

    if (!dir.exists()) {
      dir.mkdirs();
    }

    if (dir.exists()) {
      FileWriter fw = null;
      BufferedWriter out = null;
      try {
        File file = new File(path + File.separator + filename);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        fw = new FileWriter(file, append);
        out = new BufferedWriter(fw);
        out.write(data);
        out.close();
      } catch (IOException ex) {
        Logger.getLogger(FileUtil.class.getName()).log(Level.WARNING, ex.toString());
      } finally {
        try {
          fw.close();
        } catch (Exception ex) {
        }
      }
    }
  }

  private static String parsePath(String pathandfilename) {
    String path = "";

    if (pathandfilename.contains("/")) {
      path = pathandfilename.substring(0, pathandfilename.lastIndexOf("/"));
    } else if (pathandfilename.contains("\\")) {
      path = pathandfilename.substring(0, pathandfilename.lastIndexOf("\\"));
    }

    return path;
  }

  private static String parseFilename(String pathandfilename) {
    if (pathandfilename.lastIndexOf("/") != -1) {
      pathandfilename = parseFilename(pathandfilename.substring(pathandfilename.lastIndexOf("/") + 1));
    } else if (pathandfilename.lastIndexOf("\\") != -1) {
      pathandfilename = parseFilename(pathandfilename.substring(pathandfilename.lastIndexOf("\\") + 1));
    }

    return pathandfilename;
  }

  public static void appendTo(String pathandfilename, String data) {
    String filename = parseFilename(pathandfilename), path = pathandfilename.replace(filename, "");
    writeTo(path, filename, data, true);
  }

  public static boolean delete(String pathandfilename) {
    boolean success = false;
    File f = new File(pathandfilename);
    if (f.exists()) {
      success = f.delete();
    }
    return success;
  }

  public static String getTempDir() {
    String tempFilePath = "";
    try {
      //create a temp file
      File temp = File.createTempFile("temp-file-name", ".tmp");

      //Get tempropary file path
      String absolutePath = temp.getAbsolutePath();
      tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
      temp.delete();
    } catch (IOException ex) {
      Logger.getLogger(FileUtil.class.getName()).log(Level.WARNING, ex.toString());
    } finally {
      return tempFilePath;
    }
  }
  
  //<editor-fold defaultstate="collapsed" desc="Write To">
  public static void writeTo(String filenameandpath, byte[] data) {
    writeTo(filenameandpath,data,false);
  }
  public static void writeTo(String filenameandpath, byte[] data, boolean append) {
    try {
      File file = new File(filenameandpath);
      if (file.exists()) {
        if (append) {
          Files.write(file.toPath(), data, StandardOpenOption.APPEND);
          return; // return to prevent double writing
        }
      }
      Files.write(file.toPath(), data);
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
  }
  //</editor-fold>
}
