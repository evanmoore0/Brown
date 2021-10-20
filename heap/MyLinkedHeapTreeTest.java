package heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import net.datastructures.EmptyTreeException;


/**
 * This class should be used to test the functionality of your MyLinkedHeapTree implementation.
 * You will find a few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own tree instance to ensure that the test cases are independent 
 * of each other. All of the given examples should pass once you've implemented your tree methods.
 * 
 *
 * The annotation @Test before each test case is JUnit syntax, it basically lets the compiler know
 * that this is a unit test method. Use this annotation for every test method. This class is also like
 * any other java class, so should you need to add private helper methods to use in your tests, 
 * you can do so, simply without the annotations as you usually would write a method.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the progam is in the state you expect it to be
 */
public class MyLinkedHeapTreeTest {
	
	/**
	 * A simple example of checking that the add() method adds the first element to the tree.
	 */
	@Test
	public void testAddOneElement() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		tree.add(1);
		
		/* These are two ways of asserting the same thing
		 * Use whichever you find more convenient out of
		 * assertThat(actual, is(expected)) and
		 * assertTrue(boolean)
		 * Take a look at the JUnit docs for more assertions you might want to use.
		 */
		assertThat(tree.size(), is(1));
		assertTrue(tree.size() == 1);
	}
	
	/**
	 * This is an example of how to test whether an exception you expect to be thrown on a certain line of code
	 * is actually thrown. As shown, you'd simply add the expected exception right after the @Test annotation.
	 * This test will pass if the exception expected is thrown by the test and fail otherwise.
	 */
	@Test(expected = EmptyTreeException.class)
	public void testRemoveThrowsEmptyTreeException() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		tree.remove();
	}
	
	/**
	 * TODO: Write your own tests below!
	 * Think of edge cases for add/remove and try to test your helper methods (if applicable).
	 */

	@Test
	public void testAddLeftRight() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		//Size of empty tree should be 0
		assertTrue(tree.size() == 0);
		//Tree should be empty
		assertTrue(tree.isEmpty() == true);
		assertThat(tree.add(1), is(tree.root()));
		//First add should be the left node of the root node
		assertThat(tree.add(2), is(tree.left(tree.root())));
		//Second add should add the node to the right of the root
		assertThat(tree.add(3), is(tree.right(tree.root())));
		//The right node of the root should be removed first
		assertThat(tree.right(tree.root()).element(), is(tree.remove()));
		//Tree size should be 2
		assertTrue(tree.size() == 2);
		//Tree isn't empty
		assertTrue(tree.isEmpty() == false);
		//Left node should be removed
		assertThat(tree.left(tree.root()).element(), is(tree.remove()));
		//Tree size should now be one
		assertTrue(tree.size() == 1);
		//Last element in the deque is the root
		assertThat(tree.root().element(), is(tree.remove()));
		//Tree should now be empty
		assertTrue(tree.isEmpty() == true);

	}
	@Test
	public void testDeque() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		//Tree should be empty
		tree.add(1);
		//Check the deque size
		assertTrue(tree.getDeque().size() == 1);
		//Deque should just contain the root node
		assertThat(tree.getDeque().getFirst(), is(tree.root()));
		tree.add(2);
		//Check deque size
		assertTrue(tree.getDeque().size() == 2);
		//Check that the left node is in the deque
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.root())));
		tree.add(3);
		//Deque size should be decreased because parent node was removed from deque
		assertTrue(tree.getDeque().size() == 2);
		//Parent node was removed, first node in deque should be left node
		assertThat(tree.getDeque().getFirst(), is(tree.left(tree.root())));
		//Last node in deque should be right node
		assertThat(tree.getDeque().getLast(), is(tree.right(tree.root())));
		tree.add(4);
		//Deque size should now be 3
		assertTrue(tree.getDeque().size() == 3);
		//First node in deque should still be the left node
		assertThat(tree.getDeque().getFirst(), is(tree.left(tree.root())));
		//Last node in deque should be last node added
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.left(tree.root()))));
		tree.add(5);
		//Deque size shouldn't have changed
		assertTrue(tree.getDeque().size() == 3);
		//Now left node has 2 children, first node should be the right node
		assertThat(tree.getDeque().getFirst(), is(tree.right(tree.root())));
		//Last node in deque should be last node added
		assertThat(tree.getDeque().getLast(), is(tree.right(tree.left(tree.root()))));
		tree.add(6);
		//Deque size should now be 4
		assertTrue(tree.getDeque().size() == 4);
		//First node in deque should still be the right node of the root
		assertThat(tree.getDeque().getFirst(), is(tree.right(tree.root())));
		//Last node in deque should be last node added
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.right(tree.root()))));
		tree.add(7);
		//Right node of the root should now be removed, size should stay the same
		assertTrue(tree.getDeque().size() == 4);
		//First node in the deque should now be the left node of the left node of the root
		assertThat(tree.getDeque().getFirst(), is(tree.left(tree.left(tree.root()))));
		//Last node in deque should be last node added
		assertThat(tree.getDeque().getLast(), is(tree.right(tree.right(tree.root()))));
		tree.remove();
		//Deque size should stay the same, right node should get added back into deque and last node should be removed
		assertTrue(tree.getDeque().size() == 4);
		//First node in the deque should now be the right node of the root
		assertThat(tree.getDeque().getFirst(), is(tree.right(tree.root())));
		//Last node in the deque should be the left node of the right node of the root
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.right(tree.root()))));
		tree.remove();
		//Deque size should now be 3
		assertTrue(tree.getDeque().size() == 3);
		//First node should still be the right node of the root
		assertThat(tree.getDeque().getFirst(), is(tree.right(tree.root())));
		assertThat(tree.getDeque().getLast(), is(tree.right(tree.left(tree.root()))));
		tree.remove();
		//Deque size should now be 3
		assertTrue(tree.getDeque().size() == 3);
		assertThat(tree.getDeque().getFirst(), is(tree.left(tree.root())));
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.left(tree.root()))));
		tree.remove();
		//Deque size should now be 2
		assertTrue(tree.getDeque().size() == 2);
		assertThat(tree.getDeque().getFirst(), is(tree.left(tree.root())));
		assertThat(tree.getDeque().getLast(), is(tree.right(tree.root())));
		tree.remove();
		//Deque size should now be 2
		assertTrue(tree.getDeque().size() == 2);
		assertThat(tree.getDeque().getFirst(), is(tree.root()));
		assertThat(tree.getDeque().getLast(), is(tree.left(tree.root())));
		tree.remove();
		//Deque size should now be 1
		assertTrue(tree.getDeque().size() == 1);
		assertThat(tree.getDeque().getFirst(), is(tree.root()));
		assertThat(tree.getDeque().getLast(), is(tree.root()));
		tree.remove();
		assertTrue(tree.getDeque().size() == 0);


	}

}
