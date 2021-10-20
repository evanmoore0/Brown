package heap;

import java.util.Comparator;

import net.datastructures.*;
import support.heap.HeapWrapper;

/**
 * An implementation of an adaptable priority queue by 
 * means of a heap. Be certain that your running times 
 * match those specified in the program documentation, 
 * and remember that the running time of a "called" 
 * method sets the minimum running time of the "calling" 
 * method. Feel free to add additional comments. 
 */

public class MyHeap<K,V> implements HeapWrapper<K,V>, AdaptablePriorityQueue<K,V> {
	
	// This the underlying data structure of your heap
	private MyLinkedHeapTree<MyHeapEntry<K,V>> _tree;
	public Comparator<K> _comparator;

	/** 
	 * Creates an empty heap with the given comparator. 
	 * 
	 * @param the comparator to be used for heap keys
	 */
	public MyHeap(Comparator<K> comparator) {
		_tree = new MyLinkedHeapTree<MyHeapEntry<K, V>>();
		this.setComparator(comparator);
	}

	/**
	 * Sets the comparator used for comparing items in the heap to the
	 * comparator passed in.
	 * 
	 * @param comparator, the comparator to be used for heap keys
	 * @throws IllegalStateException if priority queue is not empty
	 * @throws IllegalArgumentException if null comparator is passed in
	 */
	public void setComparator(Comparator<K> comparator)
			throws IllegalStateException, IllegalArgumentException {
		if(comparator == null) {
			throw new IllegalArgumentException("Please pass in a correct comparator");
		}
		if(!this.isEmpty()) {
			throw new IllegalStateException("Priority Queue must be empty!");
		}
		_comparator = comparator;
	}

	/**
	 * Returns a CompleteBinaryTree that will allow the visualizer 
	 * access to private members, shattering encapsulation, but 
	 * allowing visualization of the heap. This is the only method 
	 * needed to satisfy HeapWrapper interface implementation.
	 *
	 * Do not modify or call this method. It is solely
	 * necessary for the visualizer to work properly.
	 * 
	 * @return the underlying binary tree on which the heap is based
	 */
	public CompleteBinaryTree<MyHeapEntry<K,V>> getTree() {
		return _tree;
	}
	
	/** 
	 * Returns the size of the heap.
	 * This method must run in O(1) time.
	 *
	 * @return an int representing the number of entries stored
	 */
	public int size() {
		return _tree.size();
	}

	/** 
	 * Returns whether the heap is empty.
	 * This method must run in O(1) time.
	 * 
	 * @return true if the heap is empty; false otherwise
	 */
	public boolean isEmpty() {
		return _tree.isEmpty();
	}

	/** 
	 * Returns but does not remove the entry with minimum key.
	 * This method must run in O(1) time.
	 * 
	 * @return the entry with the minimum key in the heap
	 * @throws EmptyPriorityQueueException if the heap is empty
	 */
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		if(_tree.isEmpty()) {
			throw new EmptyPriorityQueueException("The heap is empty, cannot find min value");
		}
		//The entry with the minimum key will always be the root
		return _tree.root().element();
	}

	/** 
	 * Inserts a key-value pair and returns the entry created.
	 * This method must run in O(log n) time.
	 *
	 * @param key to be used as the key the heap is sorting with
	 * @param value stored with the associated key in the heap
	 * @return the entry created using the key/value parameters
	 * @throws InvalidKeyException if the key is not suitable for this heap
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		//Check to see if the key is valid
		try {
			_comparator.compare(key, key);
		} catch (Exception e) {
			throw new InvalidKeyException("Key is invalid");
		}

		//Create a new MyHeapEntry with the given key and value
		MyHeapEntry<K, V> element = new MyHeapEntry<>(key, value);
		//Add the element to the tree and store it's position
		Position<MyHeapEntry<K, V>> position = _tree.add(element);
		//Set the position
		position.element().setPosition(position);
		this.upHeap(position);
		return element;
	}

	/** 
	 * Removes and returns the entry with the minimum key.
	 * This method must run in O(log n) time.
	 * 
	 * @return the entry with the with the minimum key, now removed 
	 * @throws EmptyPriorityQueueException if the heap is empty
	 */
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if(_tree.isEmpty()) {
			throw new EmptyPriorityQueueException("You can't remove the minimum value from an empty tree");
		}
		Position<MyHeapEntry<K, V>> last = _tree.getDeque().getLast();

		//If there is only one item left in the tree you don't need to downHeap/swap
		if(_tree.size() == 1) {
			return _tree.remove();
		}

		//Swap the elements, remove after swapping, then downHeap
		_tree.swapElements(_tree.root(), last);
		_tree.remove();
		this.downHeap(_tree.root());
		return last.element();
	}

	/** 
	 * Removes and returns the given entry from the heap.
	 * This method must run in O(log n) time.
	 *
	 * @param entry to be removed from the heap
	 * @return the entry specified for removal by the parameter, now removed
	 * @throws InvalidEntryException if the entry cannot be removed from this heap
	 */
	public Entry<K,V> remove(Entry<K,V> entry) throws InvalidEntryException {

		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);;

		if(_tree.isEmpty()) {
			throw new EmptyPriorityQueueException("You can't remove the minimum value from an empty tree");
		}

		Position<MyHeapEntry<K, V>> position = checkedEntry.getPosition();
		Position<MyHeapEntry<K,V>> lastPosition = _tree.getDeque().getLast();

		//If there is only one item left in the tree remove it, don't need to swap/setPosition/upHeap/downHeap
		if(position == lastPosition) {
			_tree.remove();
			return entry;
		}
		_tree.swapElements(position, lastPosition);
		position.element().setPosition(position);
		lastPosition.element().setPosition(lastPosition);

		_tree.remove();
		this.upHeap(position);
		this.downHeap(position);

		return entry;
	}

	/** 
	 * Replaces the key of the given entry.
	 * This method must run in O(log n) time.
	 *
	 * @param entry within which the key will be replaced
	 * @param key to replace the existing key in the entry
	 * @return the old key formerly associated with the entry
	 * @throws InvalidEntryException if the entry is invalid
	 * @throws InvalidKeyException if the key is invalid
	 */
	public K replaceKey(Entry<K,V> entry, K key) throws InvalidEntryException, InvalidKeyException {
		//Catch invalid key
		try{
			_comparator.compare(key, key);
		} catch(Exception e) {
			throw new InvalidKeyException("Key in replaceKey is invalid!");
		}
		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);

		// Store the old key value
		K oldKeyValue = checkedEntry.getKey();
		//Set the key value of the entry to be the new key value, then upHeap/downHeap
		checkedEntry.setKey(key);
		this.upHeap(checkedEntry.getPosition());
		this.downHeap(checkedEntry.getPosition());

		return oldKeyValue;
	}

	/** 
	 * Replaces the value of the given entry.
	 * This method must run in O(1) time.
	 *
	 * @param entry within which the value will be replaced
	 * @param value to replace the existing value in the entry
	 * @return the old value formerly associated with the entry
	 * @throws InvalidEntryException if the entry cannot have its value replaced
	 */
	public V replaceValue(Entry<K,V> entry, V value) throws InvalidEntryException {		
		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);

		//Store the oldValue, then set the new value
		V oldValue = checkedEntry.getValue();
		checkedEntry.setValue(value);

		return oldValue;
	}
	

	/**
	 * Determines whether a given entry is valid and converts it to a
	 * MyHeapEntry. Don't change this method.
	 *
	 * @param entry to be checked for validity with respect to the heap
	 * @return the entry cast as a MyHeapEntry if considered valid 
	 *
	 * @throws InvalidEntryException if the entry is not of the proper class
	 */
	public MyHeapEntry<K,V> checkAndConvertEntry(Entry<K,V> entry)
			throws InvalidEntryException {
		if (entry == null || !(entry instanceof MyHeapEntry)) {
			throw new InvalidEntryException("Invalid entry");
		}
		return (MyHeapEntry<K, V>) entry;
	}

	/**
	 * Recursively UpHeaps a position until the keys are properly in order
	 *
	 * @param position to be swapped if the parents key value is larger than the
	 * current position
	 */
	public void upHeap(Position<MyHeapEntry<K, V>> position) {
		//If the position isn't the root and the parent's key value is greater then the positions key value
		if(!_tree.isRoot(position) && _comparator.compare(position.element().getKey(),
				_tree.parent(position).element().getKey()) < 0) {
			//Swap the elements, set positions, and upHeap the parent
			_tree.swapElements(position, _tree.parent(position));
			position.element().setPosition(position);
			_tree.parent(position).element().setPosition(_tree.parent(position));
			this.upHeap(_tree.parent(position));
		}
	}

	/**
	 * Recursively downHeaps a position until the keys are properly in order
	 * @param position to be swapped if the child's key value is smaller then position
	 */
	public void downHeap(Position<MyHeapEntry<K, V>> position) {
		Position<MyHeapEntry<K, V>> minChild = null;
		if(_tree.isInternal(position)) {
			//Find the lowest key value out of the position's children
			if(_tree.hasRight(position) && _comparator.compare(_tree.left(position).element().getKey(),
					_tree.right(position).element().getKey()) > 0) {
				minChild = _tree.right(position);
			} else {
				minChild = _tree.left(position);
			}
			//If the lowest key value of the position's children is smaller then the position's key value
			if(_comparator.compare(position.element().getKey(), minChild.element().getKey()) > 0) {
				//Swap the entries, set the position, and downHeap the child
				_tree.swapElements(position, minChild);
				position.element().setPosition(position);
				minChild.element().setPosition(minChild);
				this.downHeap(minChild);
			}
		}
	}
}
