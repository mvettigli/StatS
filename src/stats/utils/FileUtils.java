/* ----------------------------------------------------------------------------
 * File: FileUtils.java
 * Date: June 1st, 2013
 * ----------------------------------------------------------------------------
 */
package stats.utils;

import java.io.File;

/**
 *
 * @author marco
 */
public class FileUtils {

  public static String CSV = "csv";

  public static String TXT = "txt";

  public static String getExtension(File file) {
    // check if given file is a directory and return null
    if (!file.isFile()) return null;
    // get the index of the last point in the file name
    String extension = file.getName();
    int index = extension.lastIndexOf('.');
    // get the file extension if it exists and return
    if (index > 0 && index < extension.length() - 1)
      extension = extension.substring(index + 1).toLowerCase();
    return extension;
  }

  public static String getFileName(File file) {
    if (!file.isFile()) return null;
    // get the index of the last point in the file name
    String name = file.getName();
    int index = name.lastIndexOf('.');
    // get the file extension if it exists and return
    if (index > 0 && index < name.length() - 1)
      name = name.substring(0, index).toLowerCase();
    return name;
  }

}
