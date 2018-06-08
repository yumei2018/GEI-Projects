package entity.core.resource;

import entity.core.delegate.QueryDelegate;
import entity.core.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.lang3.StringUtils;

public class EntityClassBuilder {

  public static void mainTest(String[] args) {
    try {
      String response = prompt("Generate base class only? [y]: ");
      Boolean baseOnly = StringUtils.isEmpty(response) 
        || response.equalsIgnoreCase("y") 
        || response.equalsIgnoreCase("yes") 
        || (!response.equalsIgnoreCase("n") 
        && !response.equalsIgnoreCase("no")); // default true;
      System.out.println(baseOnly);
      System.exit(0);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  
  private static String prompt(String question) throws Exception {
    String result = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(question);
    result = br.readLine();
    return result;
  }
  
  public static void main(String[] args) {

    String[] tables = {
//      "EWM_MEASUREMENT_ISSUE_TYPE"
      "SGMA_PRECIP_ET_TS_CHG_FACTORS"
    };

    String dest = "C:\\tmp"
          ,packagename = "gov.ca.water.entity"
          ,user = "SGMA_CLIMATE"
          ,pass = "Gei95670"
          ,host = "sac1v-vtieudb"
          ,sid = "orcl";

//    host = "sac1v-qaqcdb";
    String[] prefixSuffixes = {""};

    dest = "C:\\SVN\\trunk\\SGMAMapServices\\SGMAMapServicesWeb";
    
    Boolean baseOnly = null;
    
    try {
      String response = prompt("Generate base class only? [y]: ");
      baseOnly = StringUtils.isEmpty(response) 
        || response.equalsIgnoreCase("y") 
        || response.equalsIgnoreCase("yes") 
        || (!response.equalsIgnoreCase("n") 
        && !response.equalsIgnoreCase("no")); // default true;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

    for (String tablename : tables) {
      Logger.getLogger(EntityClassBuilder.class.getName())
              .log(Level.INFO, "Bulding " + tablename + " entity classes...");
      if (buildEntityClasses(dest,packagename,tablename,prefixSuffixes,user,pass,host,sid,baseOnly)) {
        Logger.getLogger(EntityClassBuilder.class.getName())
              .log(Level.INFO, "done!");
      }
      else {
        Logger.getLogger(EntityClassBuilder.class.getName())
              .log(Level.INFO, "failed!");
      }
    }
    
    System.exit(0);
  }

  //<editor-fold defaultstate="collapsed" desc="Build Entity Class Methods">
  /**
   *
   * @param destpath
   * @param table
   * @param url
   * @param user
   * @param password
   * @return
   */
  public static boolean buildEntityClasses(String destpath, String packagename
          , String table, String[] prefixSuffixReplacements, String user, String password, String host, String sid, boolean baseonly){
    boolean result = false;
    try {
      if (StringUtil.isEmpty(destpath)
              || StringUtil.isEmpty(table)
              || StringUtil.isEmpty(user)
              || StringUtil.isEmpty(password)
              || StringUtil.isEmpty(host)){
        throw new Exception("All parameters are required!");
      }

      File file = new File(destpath);
      if (!file.exists() || !file.isDirectory() || !file.canWrite()){
        throw new Exception("The destination path is not valid! It must exist, is a directory, and writable!");
      }
      File businessDir = new File(destpath + "/src/java/" + packagename.replace(".", File.separator));
      if (!businessDir.exists()){
        businessDir.mkdirs();
      }
      File baseDir = new File(businessDir.getAbsolutePath() + "/base");
      if (!baseDir.exists()){
        baseDir.mkdir();
      }
      File collectionDir = new File(businessDir.getAbsolutePath() + "/collection");
      if (!collectionDir.exists()){
        collectionDir.mkdir();
      }

      File dataSourceDir = new File(destpath + "/web/WEB-INF");
      if (!dataSourceDir.exists()){
        dataSourceDir.mkdir();
      }

      File projectPath = new File("")
              ,resourcesPath = new File(projectPath.getAbsoluteFile()+ "/src/entity/core/resource")
              ,baseTplFile = new File(resourcesPath.getAbsolutePath() + "/BaseTpl")
              ,businessTplFile = new File(resourcesPath.getAbsolutePath() + "/BusinessTpl")
              ,collectionTplFile = new File(resourcesPath.getAbsolutePath() + "/CollectionTpl")
              ,setterTplFile = new File(resourcesPath.getAbsolutePath() + "/SetterTpl")
              ,getterTplFile = new File(resourcesPath.getAbsolutePath() + "/GetterTpl")
              ,propertyTplFile = new File(resourcesPath.getAbsolutePath() + "/PropertyTpl")
              ,dataSourceTplFile = new File(resourcesPath.getAbsolutePath() + "/DataSourceTpl");

      String refinedTableName = table;

      for (String ps : prefixSuffixReplacements) {
        refinedTableName = refinedTableName.replace(ps, "");
      }

      final String baseTpl = FileUtil.readFrom(baseTplFile)
              ,businessTpl = FileUtil.readFrom(businessTplFile)
              ,setterTpl = FileUtil.readFrom(setterTplFile)
              ,getterTpl = FileUtil.readFrom(getterTplFile)
              ,propertyTpl = FileUtil.readFrom(propertyTplFile)
              ,dataSourceTpl = FileUtil.readFrom(dataSourceTplFile)
              ,collectionTpl = FileUtil.readFrom(collectionTplFile)
              ,businessClassName = toCamelCase(refinedTableName.toUpperCase(),false)
              ,baseClassName =  businessClassName + "Base"
              ,businessFile = businessDir.getAbsolutePath() + "/" + businessClassName + ".java"
              ,baseFile = baseDir.getAbsolutePath() + "/" + baseClassName + ".java"
              ,dataSourceFile = dataSourceDir.getAbsolutePath() + "/" + toCamelCase(user,false) + "DataSource.xml"
              ,collectionFile = collectionDir.getAbsolutePath() + "/" + businessClassName + "Collection.java";

      if (StringUtil.isEmpty(baseTpl) || StringUtil.isEmpty(businessTpl)){
        throw new Exception("Cannot locate the template files!");
      }
      String url = String.format("jdbc:oracle:thin:@%s:1521:%s",host,sid);
      OracleDataSource ods = new OracleDataSource();
      ods.setURL(url);
      ods.setUser(user);
      ods.setPassword(password);
      try (Connection conn = ods.getConnection()) {
        String query = "SELECT cols.table_name, cols.column_name, cols.position, cons.status, cons.owner\n" +
                "FROM all_constraints cons, all_cons_columns cols\n" +
                "WHERE UPPER(cols.table_name) = UPPER(?)\n" +
                "AND cons.constraint_type = 'P'\n" +
                "AND cons.constraint_name = cols.constraint_name\n" +
                "AND cons.owner = cols.owner\n" +
                "ORDER BY cols.table_name, cols.position";
        final List<String> primaryColumns = new ArrayList<>();
        String listener = "";
        execute(conn,query,new QueryDelegate(listener) {
          @Override
          public void handle(ResultSet rs) throws Exception {
            boolean found = false;
            while(rs.next()){
              primaryColumns.add(rs.getString("COLUMN_NAME"));
              found = true;
            }

            if (!found) {
              throw new Exception("Table not found!");
            }
          }
        },table);

        final StringBuilder privateProperties = new StringBuilder()
                ,publicSetters = new StringBuilder()
                ,publicGetters = new StringBuilder()
                ,finalizeProperties = new StringBuilder();

        query = "SELECT * FROM USER_TAB_COLS WHERE UPPER(TABLE_NAME) = UPPER(?)";
        final StringBuilder imports = new StringBuilder();
        execute(conn,query,new QueryDelegate(listener) {
          @Override
          public void handle(ResultSet rs) throws Exception {
            String columnName,columnType,propName,methodName,typeName,idAnnot;
            Integer precision,datalength,scale;
            String str;
            while(rs.next()){
              columnName = rs.getString("COLUMN_NAME");
              columnType = rs.getString("DATA_TYPE");
              precision = rs.getInt("DATA_PRECISION");
              datalength = rs.getInt("DATA_LENGTH");
              idAnnot="";
              if (primaryColumns.contains(columnName)){
                idAnnot=",primary=true";//,autoId=true
              }
              propName = toCamelCase(columnName,true);
              methodName = toCamelCase(columnName,false);
              typeName = Object.class.getSimpleName();
              switch(columnType){
                case "NUMBER":
                  scale = rs.getInt("DATA_SCALE");
                  if (scale == null || scale.intValue() == 0){
                    if (precision > 9){
                      typeName = Long.class.getSimpleName();
                    }
                    else {
                      typeName = Integer.class.getSimpleName();
                    }
                  }
                  else {
                    typeName = Double.class.getSimpleName();
                  }
                  break;
                case "TIMESTAMP(6)":
                  typeName = java.sql.Timestamp.class.getSimpleName();
                  str = "import " + java.sql.Timestamp.class.getName() + ";\n";
                  if (imports.indexOf(str) == -1) {
                    imports.append(str);
                  }
                  break;
                case "DATE":
                  typeName = java.sql.Date.class.getSimpleName();
                  str = "import " + java.sql.Date.class.getName() + ";\n";
                  if (imports.indexOf(str) == -1) {
                    imports.append(str);
                  }
                  break;
                case "BINARY_DOUBLE":
                  typeName = Double.class.getSimpleName();
                  break;
                case "VARCHAR2":
                case "CLOB":
                  typeName = String.class.getSimpleName();
                  break;
                case "BLOB":
                  typeName = byte[].class.getSimpleName();
                  break;
              }
              publicSetters
                      .append(setterTpl
                              .replace("[PROPNAME]", propName)
                              .replace("[TYPENAME]", typeName)
                              .replace("[METHODNAME]", methodName));
              publicGetters
                      .append(getterTpl
                              .replace("[PROPNAME]", propName)
                              .replace("[TYPENAME]", typeName)
                              .replace("[METHODNAME]", methodName));

              privateProperties
                      .append(propertyTpl
                              .replace("[IDANNOT]", idAnnot)
                              .replace("[PROPNAME]", propName)
                              .replace("[TYPENAME]", typeName)
                              .replace("[COLUMNNAME]", columnName));
              finalizeProperties.append(
                String.format("\n\t\tthis.%s = null;"
                  , propName
                )
              );
            }
          }
        },table);

        String baseClassStr = baseTpl
                .replace("[IMPORTS]",imports.toString())
                .replace("[PACKAGENAME]", packagename)
                .replace("[TABLE]", table)
                .replace("[DATASOURCE]", toCamelCase(user,false) + "DataSource")
                .replace("[DBUSER]", user)
                .replace("[DBPASSWORD]", password)
                .replace("[DBURL]", String.format("jdbc:oracle:thin:@%s:1521:%s","localhost",sid))
                .replace("[BASECLASS]", baseClassName)
                .replace("[PRIVATEPROPERTIES]", privateProperties.toString())
                .replace("[PUBLICSETTERS]",publicSetters.toString())
                .replace("[PUBLICGETTERS]",publicGetters.toString())
                .replace("[FINALIZEPROPERTIES]",finalizeProperties.toString())
                .replace("\t","  ")
                ,businessClassStr = businessTpl
                        .replace("[PACKAGENAME]", packagename)
                        .replace("[BUSINESSCLASS]",businessClassName)
                ,dataSourceStr = dataSourceTpl
                        .replace("[DATASOURCE]",toCamelCase(user,false) + "DataSource")
                        .replace("[DBUSER]",user.toLowerCase())
                ,collectionClassStr = collectionTpl
                        .replace("[PACKAGENAME]", packagename)
                        .replace("[ENTITYCLASS]",businessClassName);

        FileUtil.writeTo(baseFile, baseClassStr);
        if (!baseonly) {
          FileUtil.writeTo(businessFile, businessClassStr);
          FileUtil.writeTo(dataSourceFile, dataSourceStr);
          FileUtil.writeTo(collectionFile, collectionClassStr);
        }
        result = true;
      }
    } catch (Exception ex) {
      Logger
              .getLogger(EntityUtil.class.getName())
              .log(Level.WARNING, "{0}.buildEntityClasses Error:\n{1}"
                      ,new Object[]{EntityUtil.class.getName(), ex.getMessage()});
    } finally {
      return result;
    }
  }

  private static void execute(Connection conn, String query,QueryDelegate rsh,Object... values) throws Exception{
    try(PreparedStatement pstmt = conn.prepareStatement(query)){
      if (values != null && values.length > 0){
        int i=1;
        for (Object o : values){
          if (o == null){
            pstmt.setNull(i, pstmt.getParameterMetaData().getParameterType(i++));
          }
          else {
            pstmt.setObject(i, o);
          }
        }
      }
      try(ResultSet rs = pstmt.executeQuery()){
        rsh.handle(rs);
      }
      catch(Exception ex){
        throw ex;
      }
    }
    catch(Exception ex){
      throw ex;
    }
  }

  /**
   *
   * @param column
   * @param isProperty
   * @return
   */
  private static String toCamelCase(String rawname,boolean isProperty){
    String result="";
    if (!StringUtil.isEmpty(rawname)){
      String[] pieces = rawname.split("_");
      for (String p : pieces){
        if (isProperty && StringUtil.isEmpty(result)){
          result += p.toLowerCase();
        }
        else {
          result += p.substring(0,1).toUpperCase() + p.substring(1).toLowerCase();
        }
      }
    }
    return result;
  }
//</editor-fold>
}
