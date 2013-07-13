/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: FileUtils.java
 *   Date: Jun 1, 2013
 *
 * ---------------------------------------------------------------------------- 
 *
 * Copyright (C) 2013 M. Vettigli <m.vettigli@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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
