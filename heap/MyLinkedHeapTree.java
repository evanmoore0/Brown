package heap;

import net.datastructures.*;

/**
 * An implementation of a complete binary tree by means 
 * of a linked structure (LinkedBinaryTree). The LinkedBinaryTree class 
 * takes care of most of the mechanics of modifying 
 * the tree (you should read through the NDS4 documentation 
 * in order to fully understand how this class works. There's a link on
 * the website), but you will need 
 * to think about how to implement a CompleteBinaryTree such that
 * additions and removals operate *only* on the last node (hint: think
 * about other useful data structures). You must also ensure that you do not
 * violate the assignment runtime requirements when deciding how you will
 * track nodes within the tree.
 *  
 */

public class MyLinkedHeapTree<E> extends LinkedBinaryTree<E> 
		implements CompleteBinaryTree<E> {
	
	/**
	 * Default constructor. The tree begins empty.
	 */
	private NodeDeque<Position<E>> _deque;

	public MyLinkedHeapTree() {
		_deque = new NodeDeque();
	}

	/**
	 * Adds an element to the tree just after the last node. Returns the newly
	 * created position for the element.
	 *
	 * Note: You don't need to instantiate a new Position<E> as a local variable.
	 * Look at the NDS4 documentation for LinkedBinaryTree for how to add a
	 * new Position<E> to the tree.
	 * 
	 * This method must run in constant O(1) worst-case time.
	 * 
	 * @param element to be added to the tree as the new last node
	 * @return the Position of the newly inserted element
	 */
	@Override
	public Position<E> add(E element) {
		Position<E> position;
		//If there is nothing in the tree add the element to be the root of the tree, and add it
		//to the deque
		if(_deque.isEmpty()) {
			position = this.addRoot(element);
			_deque.addFirst(position);
			return position;
		}
		//If the first element in the deque doesn't have a left Node add it to the tree and the back of the deque
		if(!this.hasLeft(_deque.getFirst())) {
			position = this.insertLeft(_deque.getFirst(), element);
			_deque.addLast(position);
			return position;
		}

		//Otherwise add it the the right of the first node in the deque and remove the first node in the deque
		position = this.insertRight(_deque.getFirst(), element);
		_deque.addLast(position);
		_deque.removeFirst();
		return position;
	}

	/**
	 * Removes and returns the element stored in the last node of the tree.
	 * 
	 * This method must run in constant O(1) worst-case time.
	 * 
	 * @return the element formerly stored in the last node (prior to its removal)
	 * @throws EmptyTreeException if the tree is empty and no last node exists
	 */
	@Override
	public E remove() throws EmptyTreeException {

		//if the deque is empty throw an exception
		if(this.isEmpty()) {
			throw new EmptyTreeException("You can't remove from an empty tree");
		}

		//Last element in the deque
		Position<E> remove = _deque.removeLast();

		//If the last element in the deque is the root of the tree just remove and return it
		if(remove == this.root()) {
			this.remove(remove);
			return remove.element();
		}

		//If the parent of the node has a left node and isn't the first node in the deque add it
		//to the front of the deque
		if(this.hasLeft(this.parent(remove)) && this._deque.getFirst() != this.parent(remove)) {
			_deque.addFirst(this.parent(remove));
		}
		//Remove the element from the tree
		this.remove(remove);
		return remove.element();

	}
	
	/**
	 * Returns the deque
	 */
	public NodeDeque<Position<E>> getDeque() {
		return this._deque;
	}
	
}
