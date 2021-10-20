package heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.datastructures.EmptyPriorityQueueException;
import net.datastructures.Entry;
import net.datastructures.InvalidEntryException;
import net.datastructures.InvalidKeyException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This class can be used to test the functionality of your MyHeap implementation.
 * You will find a few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own heap instance to ensure that the test cases are independent 
 * of each other. All of the given examples should pass once you've implemented your heap.
 * 
 *
 * The annotation @Test before each test case is JUnit syntax. It basically lets the compiler know
 * that this is a unit test method. Use this annotation for *every* test method. This class is 
 * also like any other java class, so should you need to add private helper methods to use in your 
 * tests, you can do so, simply without the @Test annotation.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have 
 * 		  slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the progam is in the state you expect it to be
 * 
 * We've given you four example of test cases below that should help you understand syntax and the
 * general structure of tests.
 */

public class MyHeapTest {
	
	/**
	 * A simple test to ensure that insert() works.
	 */
	@Test
	public void testInsertOneElement() {
		// set-up
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");

		// Assert that your data structure is consistent using
		// assertThat(actual, is(expected))
		assertThat(heap.size(), is(1));
		assertThat(heap.min().getKey(), is(1));
	}

	/**
	 * This is an example to check that the order of the heap is sorted as per the keys
	 * by comparing a list of the actual and expected keys.
	 */
	@Test
	public void testRemoveMinHeapOrderUsingList() {
	MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");

		// the expected ordering that keys come in
		List<Integer> expectedKeys = Arrays.asList(11, 13, 16, 44, 64);

		// the actual ordering of keys in the heap
		List<Integer> actualKeys = new ArrayList<Integer>();
		while(!heap.isEmpty()) {
			actualKeys.add(heap.removeMin().getKey());
		}

		// check that the actual ordering matches the expected ordering by using one assert
		// Note that assertThat(actual, is(expected)), when used on lists/ arrays, also checks that the
		// ordering is the same.
		assertThat(actualKeys, is(expectedKeys));
	}

	/**
	 * This is an example of testing heap ordering by ensuring that the min key is always at the root
	 * by checking it explicitly each time, using multiple asserts rather than a list.
	 */
	@Test
	public void testRemoveMinHeapOrder() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");


		// test the heap ordering by asserting on all elements
		assertThat(heap.removeMin().getKey(), is(11));
		assertThat(heap.removeMin().getKey(), is(13));
		assertThat(heap.removeMin().getKey(), is(16));
		assertThat(heap.removeMin().getKey(), is(44));
		assertThat(heap.removeMin().getKey(), is(64));
	}
	

	/**
	 * Exception Tests
	 */
	@Test(expected=IllegalStateException.class)
	public void testSetComparatorThrowsIllegalStateException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.setComparator(new IntegerComparator());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetComparatorThrowsIllegalArgumentException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.setComparator(null);
	}

	@Test(expected= EmptyPriorityQueueException.class)
	public void testMinThrowsEmptyPriorityQueueException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.min();
	}

	@Test(expected= EmptyPriorityQueueException.class)
	public void testRemoveThrowsEmptyPriorityQueueException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.remove(new MyHeapEntry<>(1, "A"));
	}

	@Test(expected= InvalidKeyException.class)
	public void testInsertThrowsInvalidKeyException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(null, null);
	}

	@Test(expected= EmptyPriorityQueueException.class)
	public void testRemoveMinThrowsEmptyPriorityQueueException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.removeMin();
}

	@Test(expected= InvalidEntryException.class)
	public void testRemoveThrowsInvalidEntryExceptionNull() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.remove(null);
		//Add heap.remove(something that isn't an instance of MyHeapEntry
	}

	@Test(expected= InvalidEntryException.class)
	public void testReplaceKeyThrowsInvalidEntryException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.replaceKey(null, 1);
	}

	@Test(expected= InvalidKeyException.class)
	public void testReplaceKeyThrowsInvalidKeyException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = heap.insert(1, "A");
		heap.replaceKey(entry, null);
	}

	@Test(expected= InvalidEntryException.class)
	public void testReplaceValueThrowsInvalidEntryException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.replaceValue(null, "A");
	}

	/**
	 * MyHeap tests
	 */

	@Test
	public void testRemoveOnlyExternalNodes() {
		//Only remove external nodes
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(1, "A");
		Entry<Integer, String> entry2 = heap.insert(2, "B");
		Entry<Integer, String> entry3 = heap.insert(3, "C");
		Entry<Integer, String> entry4 = heap.insert(4, "D");
		Entry<Integer, String> entry5 = heap.insert(5, "E");
		Entry<Integer, String> entry6 = heap.insert(6, "F");
		Entry<Integer, String> entry7 = heap.insert(7, "G");

		assertTrue(heap.size() == 7);
		assertThat(heap.remove(entry7).getKey(), is(7));
		assertThat(heap.remove(entry6).getKey(), is(6));
		assertThat(heap.remove(entry5).getKey(), is(5));
		assertThat(heap.remove(entry4).getKey(), is(4));
		assertThat(heap.remove(entry3).getKey(), is(3));
		assertThat(heap.remove(entry2).getKey(), is(2));
		assertThat(heap.remove(entry1).getKey(), is(1));
		assertTrue(heap.size() == 0);
		assertTrue(heap.isEmpty());

	}

	@Test
	public void testRemoveLeftNode() {
		//Remove left nodes, and root node
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(1, "A");
		Entry<Integer, String> entry2 = heap.insert(2, "B");
		Entry<Integer, String> entry3 = heap.insert(3, "C");
		Entry<Integer, String> entry4 = heap.insert(4, "D");
		Entry<Integer, String> entry5 = heap.insert(5, "E");
		Entry<Integer, String> entry6 = heap.insert(6, "F");
		Entry<Integer, String> entry7 = heap.insert(7, "G");

		assertThat(heap.remove(entry6), is(entry6));
		assertThat(heap.remove(entry4), is(entry4));
		assertThat(heap.remove(entry2), is(entry2));
		assertThat(heap.remove(entry1), is(entry1));
		assertTrue(heap.size() == 3);
		//Check order of heap
		assertThat(heap.min(), is(entry3));
		assertThat(heap.removeMin(), is(entry3));
		assertThat(heap.removeMin(), is(entry5));
		assertThat(heap.removeMin(), is(entry7));
	}

	@Test
	public void insertAfterRemove() {
		//Insert something into heap after removing
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(1, "A");
		Entry<Integer, String> entry2 = heap.insert(2, "B");
		Entry<Integer, String> entry3 = heap.insert(3, "C");

		assertThat(heap.min() ,is(entry1));
		assertThat(heap.removeMin(), is(entry1));
		assertTrue(heap.size() == 2);
		assertThat(heap.min(), is(entry2));
		assertThat(heap.removeMin(), is(entry2));
		assertThat(heap.min(), is(entry3));
		Entry<Integer, String> entry4 = heap.insert(1, "D");
		assertThat(heap.removeMin(), is(entry4));

	}

	@Test
	public void replaceValueTest() {
		//Replace values with the same value/ different values
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(1, "A");
		Entry<Integer, String> entry2 = heap.insert(2, "B");
		Entry<Integer, String> entry3 = heap.insert(3, "C");
		heap.replaceValue(entry3, "A");
		heap.replaceValue(entry2, "B");
		heap.replaceValue(entry1, "C");

		assertThat(entry3.getValue(), is("A"));
		assertThat(entry2.getValue(), is("B"));
		assertThat(entry1.getValue(), is("C"));

		assertThat(heap.replaceValue(entry1, "1"), is("C"));
		assertThat(entry1.getValue(), is("1"));

	}

	@Test
	public void replaceKeyTest() {
		//Check when changing key the heap upHeaps/downHeaps
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(1, "A");
		Entry<Integer, String> entry2 = heap.insert(2, "B");
		Entry<Integer, String> entry3 = heap.insert(3, "C");
		Entry<Integer, String> entry4 = heap.insert(4, "C");
		Entry<Integer, String> entry5 = heap.insert(5, "C");
		Entry<Integer, String> entry6 = heap.insert(6, "C");
		Entry<Integer, String> entry7 = heap.insert(7, "C");


		heap.replaceKey(entry1, 10);

		assertThat(entry1.getKey(), is(10));
		assertThat(heap.min(), is(entry2));

		assertThat(heap.replaceKey(entry5, 1), is(5));

		assertThat(heap.min(), is(entry5));
		//Replace key with same key
		assertThat(heap.replaceKey(entry5, 1), is(1));

		//Check order of heap
		assertThat(heap.removeMin(), is(entry5));
		assertThat(heap.removeMin(), is(entry2));
		assertThat(heap.removeMin(), is(entry3));
		assertThat(heap.removeMin(), is(entry4));
		assertThat(heap.removeMin(), is(entry6));
		assertThat(heap.removeMin(), is(entry7));
		assertThat(heap.removeMin(), is(entry1));

	}

	@Test
	public void removeMinRootTest() {
		//Remove the root using removeMin
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry1 = heap.insert(-100, " ");
		Entry<Integer, String> entry2 = heap.insert(-2, "Joe");
		Entry<Integer, String> entry3 = heap.insert(0, "Jan");

		assertThat(heap.min().getValue(), is(" "));
		assertThat(heap.min().getKey(), is(-100));
		assertThat(heap.min(), is(entry1));
		assertThat(heap.removeMin(), is(entry1));
		assertThat(heap.min().getValue(), is("Joe"));
		assertThat(heap.min().getKey(), is(-2));
		assertThat(heap.min(), is(entry2));
		assertThat(heap.removeMin(), is(entry2));
		assertThat(heap.min().getKey(), is(0));
		assertThat(heap.min().getValue(), is("Jan"));
		assertThat(heap.min(), is(entry3));
		assertThat(heap.removeMin(), is(entry3));
		assertThat(heap.isEmpty(), is(true));
	}

	@Test
	public void removeTest() {
		//Test removing different nodes/ root node
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry7 = heap.insert(-1, "A");
		Entry<Integer, String> entry6 = heap.insert(-2, "B");
		Entry<Integer, String> entry5 = heap.insert(-3, "C");
		Entry<Integer, String> entry4 = heap.insert(-4, "C");
		Entry<Integer, String> entry3 = heap.insert(-5, "C");
		Entry<Integer, String> entry2 = heap.insert(-6, "C");
		Entry<Integer, String> entry1 = heap.insert(-7, "C");


		assertThat(heap.remove(entry3), is(entry3));
		assertThat(heap.min(), is(entry1));
		assertThat(heap.size(), is(6));

		assertThat(heap.remove(entry5), is(entry5));
		assertThat(heap.size(), is(5));
		assertThat(heap.min(), is(entry1));

		assertThat(heap.remove(entry1), is(entry1));
		assertThat(heap.size(), is(4));
		assertThat(heap.min(), is(entry2));

		assertThat(heap.remove(entry4), is(entry4));
		assertThat(heap.min(), is(entry2));
		assertThat(heap.size(), is(3));

		assertThat(heap.remove(entry2), is(entry2));
		assertThat(heap.min(), is(entry6));
		assertThat(heap.size(), is(2));

		assertThat(heap.remove(entry6), is(entry6));
		assertThat(heap.min(), is(entry7));
		assertThat(heap.size(), is(1));

		assertThat(heap.remove(entry7), is(entry7));
		assertThat(heap.size(), is(0));
		assertThat(heap.isEmpty(), is(true));


	}
}
