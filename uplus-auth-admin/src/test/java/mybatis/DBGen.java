package mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBGen {
    public static void main(
            String[] args) {
        // System.out.println("aaaaaa".substring(0,"aaaaaa".length()-1));
        getcolumn("sys_user");
    }

    public static void getcolumn(
            String tableName) {

        Connection conn = null;
        // Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://192.168.2.149:3306/uplus_wei", "root", "-1q+2W+3e");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 1、获取数据库所有表
        StringBuffer sbTables = new StringBuffer();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        // 2、遍历数据库表，获取各表的字段等信息
        StringBuffer sbCloumns = new StringBuffer();
        String sql = "select * from " + tableName;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columeCount = meta.getColumnCount();
            sbCloumns.append("表 " + tableName + "共有 " + columeCount + " 个字段。字段信息如下：\r\n");
            for (int i = 1; i < columeCount + 1; i++) {
                // sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                sbCloumns.append(tableName + "." + meta.getColumnName(i) + ",");
                // sbCloumns.append("r."+meta.getColumnName(i)+",");
                // sbCloumns.append("#{"+meta.getColumnName(i)+"},");
            }
            sbCloumns.append("\r\n");
            for (int i = 1; i < columeCount + 1; i++) {
                // sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                sbCloumns.append(meta.getColumnName(i) + ",");
                // sbCloumns.append("r."+meta.getColumnName(i)+",");
                // sbCloumns.append("#{"+meta.getColumnName(i)+"},");
            }
            sbCloumns.append("\r\n");
            for (int i = 1; i < columeCount + 1; i++) {
                // sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                sbCloumns.append("#{" + lineToHump(meta.getColumnName(i)) + "},");

            }
            sbCloumns.append("\r\n");
            for (int i = 1; i < columeCount + 1; i++) {
                // sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                sbCloumns.append("#{" + lineToHump(meta.getColumnName(i)) + "},");

            }
            sbCloumns.append("\r\n");
            for (int i = 1; i < columeCount + 1; i++) {
                // sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                sbCloumns.append(meta.getColumnName(i) + "=#{" + lineToHump(meta.getColumnName(i)) + "},");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(sbCloumns.toString());
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String lineToHump(
            String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
