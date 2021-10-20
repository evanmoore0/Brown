package heap;

import net.datastructures.BTPosition;
import net.datastructures.Entry;
import net.datastructures.Position;

import java.security.Key;

/**
 * Represents a key/value pair to be stored in a data 
 * structure, such as a heap. Entry<K,V> is a very 
 * limited accessing interface, so you may wish to add 
 * additional methods. In particular, think about the 
 * relationship of the Entry<K,V> to its location in 
 * the heap's binary tree. All methods must run in O(1)
 * time.
 *
 * Feel free to add additional comments. 
 */

public class MyHeapEntry<K,V> implements Entry<K,V> {

	/** 
	 * Default constructor. You may wish to modify the parameters.
	 */
	K _key;
	V _value;
	Position<MyHeapEntry<K,V>> _position;

	public MyHeapEntry(K key, V value) {
		_key = key;
		_value = value;
	}
	
	/**
	 * @return the key stored in this entry 
	 */
	public K getKey() {
		return _key;
	}

	/** 
	 * @return the value stored in this entry 
	 */
	public V getValue() {
		return _value;
	}
	
	/* Add any additional methods here */

	public void setKey(K key) {
		_key = key;
	}

	public void setValue(V value) {
		_value = value;
	}

	public Position<MyHeapEntry<K, V>> getPosition() {
		return _position;
	}

	public void setPosition(Position<MyHeapEntry<K, V>> position) {
		_position = position;
	}

}
