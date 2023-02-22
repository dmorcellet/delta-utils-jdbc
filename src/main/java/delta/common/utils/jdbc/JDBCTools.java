package delta.common.utils.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * Tools related to JDBC.
 * @author DAM
 */
public class JDBCTools
{
  private static final Logger LOGGER=Logger.getLogger(JDBCTools.class);

  /**
   * Get the value of a database generated value (for instance a primary key).
   * @param connection Database connection.
   * @param index Index of value in the previous insert.
   * @return A long value or <code>null</code>.
   */
  public static Long getPrimaryKey(Connection connection, int index)
  {
    Long primaryKey=null;
    Statement s=null;
    ResultSet rs=null;
    try
    {
      s=connection.createStatement();
      rs=s.executeQuery("CALL IDENTITY();");
      primaryKey=Long.valueOf(rs.getLong(index));
    }
    catch(SQLException sqlException)
    {
      LOGGER.error("",sqlException);
    }
    finally
    {
      CleanupManager.cleanup(s,rs);
    }
    return primaryKey;
  }
}
