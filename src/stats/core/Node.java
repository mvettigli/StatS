/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Node.java
 *   Date: Jul 6, 2013
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
package stats.core;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Node {

  //<editor-fold defaultstate="collapsed" desc="Private Members">
  /**
   * Stores the tables generated by the {@code Node}.
   */
  private ArrayList<Table> tables;

  /**
   * Stores selection groups related to any table of the {@code Node} object.
   */
  private ArrayList<SelectionGroup> groups;

  /**
   * Variable pointing to the parent node, if null the node is the header.
   */
  private Node parent;

  /**
   * Stores the name of the node;
   */
  private String name;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor for {@code Node} class. By default the node is an
   * header.
   */
  public Node() {
    this(null, "Header");
  }

  public Node(String name) {
    this(null, name);
  }

  /**
   * Constructor for {@code Node} class with given parent node. If node
   * argument is null, the node will be considered as header.
   *
   * @param parent parent node.
   */
  public Node(Node parent, String name) {

    if (name == null) name = "Node";

    // initialize object members
    this.parent = parent;
    this.name = name;
    tables = new ArrayList<>();
    groups = new ArrayList<>();

  }
  //</editor-fold>

  /**
   * Checks if the {@code Node} object is an header.
   *
   * @return true if header, else false.
   */
  public boolean isHeader() {
    return (parent == null);
  }

  /**
   * Returns the parent {@code Node} object of the instance.
   *
   * @return the parent node
   */
  public Node parent() {
    return this.parent;
  }

  public String getName() {
    return name;
  }

  public int getTableNumber() {
    return tables.size();
  }

  public Table getTable(int index) {
    if (index < 0 || index >= tables.size()) return null;
    return tables.get(index);
  }

  public boolean setParent(Node node) {
    if (node == this) return false;
    if (node == null) return false;
    // check for node loops
    boolean isUnlinked = true;
    Node backNode = this;
    while (backNode.isHeader())
    {
      isUnlinked &= (backNode != node);
      backNode = backNode.parent;
    }
    if (!isUnlinked) return false;
    // assign parent node
    parent = node;
    return true;
  }

  public boolean setHeader() {
    if (parent == null) return false;
    parent = null;
    return true;
  }

  public boolean setName(String name) {
    if (name == null || name.isEmpty()) return false;
    this.name = name;
    return true;
  }

  /**
   * Checks if the given {@code Table} is already present in the {@code Node}
   * object.
   *
   * @return true if present, else false.
   */
  public boolean hasTable(Table table) {
    return (tables.contains(table));
  }

  public boolean addTable(Table table) {

    if (table == null) return false;
    if (tables.contains(table)) return false;
    // add the table to the node
    tables.add(table);
    return true;

  }

  public boolean removeTable(Table table) {

    if (table == null) return false;
    if (!tables.contains(table)) return false;
    // removes the table from the node
    tables.remove(table);
    return true;

  }

}
