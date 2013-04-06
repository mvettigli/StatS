/* ----------------------------------------------------------------------------
 * File: Array.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

import java.util.ArrayList;

/**
 * The {@code Array} class represents an array of {@code Data} elements. 
 * An array must contain elements of the same data type but the common interface
 * can be used with all {@code Data} types.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class Array {

/**
 * Variable used to store the name of the {@code Array} object.
 */
private String name;

/**
 * Array of {@code Data} object to actually store the data.
 */
private ArrayList<Data> array;

/**
 * Counter for unique untitled naming of {@code Array} objects.
 */
private static int untitled_number = 1;

/**
 * Default constructor for {@code Array} class.
 */
public Array() {
    this(newUntitledName());
}

/**
 * Constructor for {@code Array} class with name argument.
 *
 * @param name the name of the array.
 */
public Array(String name) {
    // check if argument is empty and use untitled name
    if (name.isEmpty()) name = newUntitledName();
    // assign the name to the Array
    this.name = name;
    // initialize the ArrayList
    array = new ArrayList<>();
}

/**
 * The function returns the name of the {@code Array} object.
 *
 * @return the name of the object.
 */
public String name() {
    return this.name;
}

/**
 * The function sets the name of the {@code Array} object. Empty names are
 * not allowed.
 *
 * @param name the new name of the object.
 * @return true if successful, else false.
 */
public boolean setName(String name) {
    // check if the name is not null
    if (name.isEmpty()) return false;
    // assign the name to the Array
    this.name = name;
    return true;
}

/**
 * The function returns the current size of the {@code Array} object.
 *
 * @return the size of the array.
 */
public int size() {
    return array.size();
}

/**
 * The function returns the data type of the objects stored in the
 * {@code Array} object. If no element is stored, {@code DataType.UNDEFINED}
 * is returned.
 *
 * @return the data type of {@code Array} elements.
 */
public DataType type() {
    if (array.isEmpty()) return DataType.UNDEFINED;
    return array.get(0).type();
}

/**
 * The function tells if the {@code index} argument is a valid index for 
 * the {@code Array} object.
 *
 * @param index the integer to be checked.
 * @return true if the index is valid, else false.
 */
public boolean isIndex(int index) {
    return (index < 0 || index >= array.size());
}

/**
 * The function adds a null element at the end of the {@code Array} 
 * object.
 */
public void add() {
    this.add(nullElement(), 1);
}

/**
 * The function adds a given number of null elements at the end of the
 * {@code Array} object.
 *
 * @param number number of null elements to be added.
 * @return true if successful, else false.
 */
public boolean add(int number) {
    return this.add(nullElement(), number);
}

/**
 * The function adds an element at the end of the {@code Array} object. 
 * The element must be of the same type of those already stored.
 *
 * @param data the element to be added.
 * @return true if successful, else false.
 */
public boolean add(Data data) {
    return this.add(data, 1);
}

/**
 * The function adds a given number of elements at the end of the
 * {@code Array} object. The element must be of the same type of those
 * already stored.
 *
 * @param data the element to be added.
 * @param number the number of elements to be added.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean add(Data data, int number) {
    // check if DataType is valid
    if (data.type() != this.type() && this.type() != DataType.UNDEFINED)
        return false;
    // check if number is valid
    if (number < 1) return false;
    // perform the insertion
    for (int i = 0; i < number; i++)
        array.add(data.clone());
    return true;
}

/**
 * The function inserts a null element at the beginning of the {@code 
 * Array} object.
 *
 * @return true if successful, else false.
 */
public boolean insert() {
    return this.insert(0, nullElement(), 1);
}

/**
 * The function inserts a null element to the {@code Array} object at a
 * specific position of the array.
 *
 * @param index the position of the insertion.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean insert(int index) {
    return this.insert(index, nullElement(), 1);
}

/**
 * The function inserts a given number of null elements to the {@code 
 * Array} object at a specific position of the array.
 *
 * @param index the position of the insertion.
 * @param number the number of elements to be inserted.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean insert(int index, int number) {
    return this.insert(index, nullElement(), number);
}

/**
 * The function inserts a given number of elements to the {@code Array}
 * object at a specific position of the array. The element must be of the
 * same type of those already stored.
 *
 * @param index the position for insertion.
 * @param data the element to be inserted.
 * @param number the number of elements to be inserted.
 * @return true if the insertion was successful, else false.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean insert(int index, Data data, int number) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // check if DataType is valid
    if (data.type() != this.type() && this.type() != DataType.UNDEFINED)
        return false;
    // check if number is valid
    if (number < 1) return false;
    // perform the insertion
    for (int i = 0; i < number; i++)
        array.add(index, data.clone());
    return true;
}

/**
 * The function removes an element of the {@code Array} at a given 
 * position.
 *
 * @param index the position for deletion.
 * @throws ArrayIndexOutOfBoundsException
 */
public void remove(int index) {
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    array.remove(index);
}

/**
 * The function removes all elements inside the {@code Array} object.
 */
public void clear() {
    array.clear();
}

/**
 * The function swaps two elements inside the {@code Array} object.
 *
 * @param index1 first position of the element to be swapped.
 * @param index2 second position of the element to be swapped.
 * @throws ArrayIndexOutOfBoundsException
 */
public void swap(int index1, int index2) {
    // check if indexes are valid
    if (index1 < 0 || index2 < 0
            || index1 >= array.size() || index2 >= array.size())
        throw new ArrayIndexOutOfBoundsException();
    // swap the elements
    Data data = array.get(index1);
    array.set(index1, array.get(index2));
    array.set(index2, data);
}

/**
 * The function fills the {@code Array} object with given null elements
 * until the final size is achieved.
 *
 * @param size the new size of the array.
 * @return true if successful, else false.
 */
public boolean fill(int size) {
    return this.fill(size, nullElement());
}

/**
 * The function fills the {@code Array} object with given {@code Data}
 * elements until the final size is achieved.
 *
 * @param size the new size of the array.
 * @param data the elements used to fill the array.
 * @return true if successful, else false.
 */
public boolean fill(int size, Data data) {
    // check if size is valid
    if (size <= array.size()) return false;
    // check if data type is valid
    if (data.type() != this.type() && this.type() != DataType.UNDEFINED)
        return false;
    // add the elements to the array
    int element_number = size - array.size();
    for (int i = 0; i < element_number; i++)
        array.add(data.clone());
    return true;
}

/**
 * The function returns the content of the {@code Array} at the specified
 * position.
 *
 * @param index the position inside the array.
 * @return the {@code Data} object stored in the array.
 * @throws ArrayIndexOutOfBoundsException
 */
public Data get(int index) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    return array.get(index);
}

/**
 * The function replaces the element of the {@code Array} object at the
 * given position.
 *
 * @param index the position of the element to be replaced.
 * @param data new data to substitute the element.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean set(int index, Data data) {
    // check if the index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // check if data type is valid        
    if (data.type() != this.type() && this.type() != DataType.UNDEFINED)
        return false;
    // assign the value
    array.set(index, data);
    return true;
}

/**
 * The function replaces the element of the {@code Array} object at the
 * given position parsing the string representation.
 *
 * @param index the position of the element to be replaced.
 * @param data a string representation of the new data.
 * @throws ArrayIndexOutOfBoundsException
 */
public boolean set(int index, String data) {
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    return array.get(index).set(data);
}

/**
 * The function sets an element of the {@code Array} object specified by
 * the index to null value.
 *
 * @param index the position inside the array.
 * @throws ArrayIndexOutOfBoundsException
 */
public void empty(int index) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // empty the element
    array.get(index).empty();
}

/**
 * Returns a string representation of the {@code Array} and its content.
 *
 * @return a string representation of the array.
 */
@Override
public String toString() {
    // initialize the string builder
    StringBuilder sb = new StringBuilder();
    // create the header
    sb.append("|").append(name).append(", ");
    if (!array.isEmpty()) sb.append(this.size()).append(" ");
    sb.append(this.type()).append("|> ( ");
    // add the content
    for (int i = 0; i < array.size(); i++)
    {
        if (array.get(i).isEmpty()) sb.append("_");
        else sb.append(array.get(i).toString());
        if (i + 1 != array.size()) sb.append(" , ");
    }
    sb.append(" )");
    return sb.toString();
}

/**
 * The function returns an untitled name for the {@code Array} object.
 *
 * @return a string containing the untitled array name.
 */
private static String newUntitledName() {
    return "Array" + untitled_number++;
}

/**
 * The function returns a null element based on current {@code DataType}
 * stored by the Array.
 *
 * @return a null {@code Data} object.
 */
private Data nullElement() {
    Data null_value;
    switch (this.type())
    {
        case NUMERIC:
            null_value = new Numeric();
            break;
        case CHARACTER:
        case UNDEFINED:
        default:
            null_value = new Character();
    }
    return null_value;
}

}
