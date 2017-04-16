package top.builbu.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.naming.NamingException;

public abstract class DateUtil {

  private final static String myFmt4 = new String("yyyy-MM-dd HH:mm:ss:SSS");
  private final static String myFmt = new String("yyyy-MM-dd HH:mm:ss");
  private final static String myFmt2 = new String("yyyy-MM-dd");
  private final static String myFmt3 = new String("EEE MMM dd HH:mm:ss zzz yyyy");
  private final static String myFmt5 = new String("yyyyMMddHHmmss");
  private final static String myFmt6 = new String("yyyyMM");

  public static String Date6Str(Date date) {
	return new SimpleDateFormat(myFmt6).format(date);
  }
  
  public static String Date5Str(Date date) {
    return new SimpleDateFormat(myFmt5).format(date);
  }
  
  public static String Date4Str(Date date) {
    return new SimpleDateFormat(myFmt4).format(date);
  }
  
  public static String Date2Str(Date date) {
    return new SimpleDateFormat(myFmt).format(date);
  }

  public static String Date2Str2(Date date) {
    return new SimpleDateFormat(myFmt2).format(date);
  }

  public static Date Str2D(String str) {
    if (null == str) {
      return null;
    }
    try {
      if (str.length() > 10) {
        return new SimpleDateFormat(myFmt).parse(str);
      } else {
        return new SimpleDateFormat(myFmt2).parse(str);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date Str2D2(String str) {
    try {
      return new SimpleDateFormat(myFmt3, java.util.Locale.ENGLISH).parse(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date createDate(int year, int month, int date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.clear();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, date);
    return calendar.getTime();
  }

  /*****
   * @author LIYE 字符串转时间
   * @param date
   * @return
   */
  public static Date Str2Date(String date) {
    try {
      SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
      if (date.length() > 10) {
        ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      }
      java.util.Date d = ft.parse(date);
      return new java.sql.Date(d.getTime());
    } catch (Exception ex) {
      return new Date(Calendar.getInstance().getTime().getTime());
    }
  }

  /**
   * 获取当前日期的前一天
   * 
   * @author JJ
   * 
   * @return String format is:yyyy-MM-dd
   */
  public static String getPrevDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.sql.Date(System.currentTimeMillis()));
    int day = calendar.get(Calendar.DATE);
    calendar.set(Calendar.DATE, day - 1);
    return new SimpleDateFormat(myFmt).format(calendar.getTime());
  }

  /**
   * 获取当前日期的后一天
   * 
   * @author JJ
   * 
   * @return String format is:yyyy-MM-dd
   */
  public static String getNextDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.sql.Date(System.currentTimeMillis()));
    int day = calendar.get(Calendar.DATE);
    calendar.set(Calendar.DATE, day + 1);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(calendar.getTime());
  }

  /**
   * 获取当前日期
   * 
   * @return String format is:yyyy-MM-dd
   */
  public static String getCurrentDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.sql.Date(System.currentTimeMillis()));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(calendar.getTime());
  }

  /**
   * 获取当前数据库日期
   * 
   * @return String format is:yyyy-MM-dd HH:mm:ss（年月日时分秒）
   */
  public static Date getSysdate() {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    Date date = null;
    try {
      conn = DateUtil.getJdbcConnection();
      String sql = "select SYSDATE() as count";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs != null) {
        while (rs.next()) {
          date = rs.getTimestamp("COUNT");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      freeConnection(conn, stmt, rs);
    }
    return date;
  }

  /**
   * 获取当前月
   * 
   * @author tzs 2011.06.21 pm
   * @return month
   */
  public static int getCurrentMonth() {
    Calendar ca = Calendar.getInstance();
    int month = ca.get(Calendar.MONTH) + 1;// 获取月
    return month;
  }

  /**
   * //获取当前日期
   * 
   * @author tzs
   */
  public static Timestamp getCurrentTimestamp() {
    Timestamp d = new Timestamp(System.currentTimeMillis());
    return d;
  }

  // 获取当前时间
  @SuppressWarnings("deprecation")
  public static Time getCurrentTime() {
    Calendar ca = Calendar.getInstance();
    // int year = ca.get(Calendar.YEAR);// 获取年份
    // int month = ca.get(Calendar.MONTH);// 获取月份
    // int day = ca.get(Calendar.DATE);// 获取日
    int minute = ca.get(Calendar.MINUTE);// 分
    int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
    int second = ca.get(Calendar.SECOND);// 秒
    // int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK);

    Time ts = new Time(hour, minute, second);
    return ts;
  }

  /**
   * 获取两位日
   * 
   * @return
   */
  public static String getDay() {
    SimpleDateFormat formatter;
    formatter = new SimpleDateFormat("dd");
    String ctime = formatter.format(new Date());
    return ctime;
  }

  /**
   * 获取两位月份
   * 
   * @return
   */
  public static String getMonth() {
    SimpleDateFormat formatter;
    formatter = new SimpleDateFormat("MM");
    String ctime = formatter.format(new Date());
    return ctime;
  }

  /**
   * 获取四位年份
   * 
   * @return
   */
  public static String getYear() {
    SimpleDateFormat formatter;
    formatter = new SimpleDateFormat("yyyy");
    String ctime = formatter.format(new Date());
    return ctime;
  }

  /**
   * 获取两位年份
   * 
   * @return
   */
  public static String getShortYear() {
    SimpleDateFormat formatter;
    formatter = new SimpleDateFormat("yy");
    String ctime = formatter.format(new Date());
    return ctime;
  }

  public static Date getCurrentDate() {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    Date date = null;
    try {
      conn = DateUtil.getJdbcConnection();
      // conn.setAutoCommit(false);
      String sql = "select SYSDATE() as count";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs != null) {
        while (rs.next()) {
          date = rs.getDate("COUNT");
        }
      }
      // conn.commit();
    } catch (Exception e) {
      // logger.errorT("/查询操作异常!SQL:" + sql);
      e.printStackTrace();
    } finally {
      freeConnection(conn, stmt, rs);
    }
    return date;
  }

  public static Connection getJdbcConnection() throws NamingException, SQLException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://10.101.4.65:3306/sc_marketing", "bsdyun", "BSDyun$1976");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new SQLException("未找到驱动oracle.jdbc.OracleDriver");
    }
    // return null;
  }

  public static void freeConnection(Statement stmt, ResultSet rs) {
    try {
      freeConnection(rs);
      freeConnection(stmt);
    } catch (Exception ex) {
      // log.error("释放连接时出错！",ex);
    }
  }

  public static void freeConnection(ResultSet rs) {
    try {
      if (rs != null)
        rs.close();
    } catch (SQLException ex) {
      // log.error("释放连接时出错！",ex);
    }
  }

  public static void freeConnection(Statement stmt) {
    try {
      if (stmt != null)
        stmt.close();
    } catch (SQLException ex) {
      // log.error("释放连接时出错！",ex);
    }
  }

  public static void freeConnection(Connection conn) {
    try {
      if (conn != null)
        conn.close();
    } catch (SQLException ex) {
      // log.error("释放连接时出错！",ex);
    }
  }

  public static void freeConnection(Connection conn, Statement stmt, ResultSet rs) {
    try {
      freeConnection(rs);
      freeConnection(stmt);
      freeConnection(conn);
    } catch (Exception ex) {
      // log.error("释放连接时出错！",ex);
    }
  }

  /**
   * 获取当前周数
   * 
   * @author ShenLiang
   * 
   * @return int
   */
  public static int getCurWeek() {
    int week = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK);
    int weekDay = 0;
    if (week == 1) {
      weekDay = 7;
    } else {
      weekDay = week - 1;
    }
    return weekDay;
  }
}
