/* ----------------------------------------------------------------------------
 * File: Data.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

/**
 *
 * @author marco
 */
public abstract class Data {
    
    public abstract DataType type();

    public abstract boolean isEmpty();

    public abstract void empty();
    
    public abstract boolean set(String data);

    @Override
    public abstract Data clone();
    
}
