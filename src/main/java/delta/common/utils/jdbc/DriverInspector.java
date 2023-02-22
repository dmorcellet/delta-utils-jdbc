package delta.common.utils.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Driver inspector.
 * @author DAM
 */
public class DriverInspector
{
  private static final Logger LOGGER=Logger.getLogger(DriverInspector.class);

  /**
   * Print driver infos on the standard output.
   * @param d JDBC driver to use.
   * @param url Data source URL.
   */
  public static void dumpDriverInfos(Driver d, String url)
  {
    System.out.println("Driver class : "+d.getClass().getName());
    System.out.println("Version "+d.getMajorVersion()+"."+d.getMinorVersion());
    if(d.jdbcCompliant())
    {
      System.out.println("JDBC compliant");
    }
    else
    {
      System.err.println("Not JDBC compliant");
    }
    Properties p=new Properties();
    try
    {
      DriverPropertyInfo[] infos=d.getPropertyInfo(url, p);
      for(int i=0;i<infos.length;i++)
      {
        DriverPropertyInfo info=infos[i];
        System.out.println(info.name+"="+info.value+" required=("+info.required+") description=("+info.description+") choices=("+Arrays.toString(info.choices)+")");
      }
    }
    catch(Exception e)
    {
      LOGGER.error("Could not get properties for driver", e);
    }
  }

  /**
   * Dump some database metadata to the standard output.
   * @param metaData Data to show.
   */
  public static void dumpMetaData(DatabaseMetaData metaData)
  {
    try
    {
      System.out.println("Version JDBC "+metaData.getJDBCMajorVersion()+"."+metaData.getJDBCMinorVersion());
      System.out.println("Supports generated keys : "+metaData.supportsGetGeneratedKeys());
    }
    catch(Exception e)
    {
      LOGGER.error("Could not get properties for driver", e);
    }
  }
}
