package delta.common.utils.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clean-up tool methods for JDBC.
 * @author DAM
 */
public class CleanupManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(CleanupManager.class);

  /**
   * Close a result set.
   * @param resultSet Result set to close.
   */
  public static void cleanup(ResultSet resultSet)
  {
    try
    {
      if(resultSet!=null)
      {
        resultSet.close();
      }
    }
    catch(Exception exception)
    {
      LOGGER.error("Error on ResultSet closing.", exception);
    }
  }

  /**
   * Close a statement.
   * @param statement Statement to close.
   */
  public static void cleanup(Statement statement)
  {
    try
    {
      if(statement!=null)
      {
        statement.close();
      }
    }
    catch(Exception exception)
    {
      LOGGER.error("Error on Statement closing.", exception);
    }
  }

  /**
   * Close a statement and a result set.
   * @param statement Statement to close.
   * @param resultSet Result set to close.
   */
  public static void cleanup(Statement statement, ResultSet resultSet)
  {
    cleanup(resultSet);
    cleanup(statement);
  }
}
