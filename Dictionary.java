/**
 ******************************************************************************
 *                    HOMEWORK, 15-351/650
 ******************************************************************************
 *                    Amortized Dictionary
 ******************************************************************************
 *
 * Implementation of an Amortized Array-Based Dictionary data structure.
 *
 * This data structure supports duplicates and does *NOT* support storage of
 * null references.
 *
 * Notes:
 * 		-It is *highly* recommended that you begin by reading over all the methods,
 *       all the comments, and all the code that has already been written for you.
 *
 * 		-the specifications provided are to help you understand what the methods
 *       are supposed to accomplish.
 * 		-See the lab documentation & recitation notes for implementation details.
 *
 *
 * User ID(s):nbawa
 *
 *****************************************************************************/


import static java.util.Arrays.binarySearch;
import java.util.Arrays;


public class Dictionary<E extends Comparable<E>>  implements DictionaryInterface<E>
{
	/*
	 * Keeps track of the number of elements in the dictionary.
	 * Take a look at the implementation of size()
	 */
	private int size;
	/*
	 * The head reference to the linked list of Nodes.
	 * Take a look at the Node class.
	 */
	private Node head;

	/**
	 * Creates an empty dictionary.
	 */
	public Dictionary()
	{
		size = 0;
		head = null;
	}

	/**
	 * Adds item to the dictionary, thus making contains(item) true.
	 * Increments size to ensure size() is correct.
	 */
	public void add(E item)
	{
		if(item == null)
		{
			throw new NullPointerException("Error passing null object to add");
		}
		
		/*
		 * Your code goes here...
		 */
		 else
		{
			/*
			when a new item is added, new node is made.
			*/
				if(head == null)
				{
					//makes a new node and prepends it at the beginning of the list
					head = new Node (item,head);
				}
				else
				{
					Node tmp1 = head;
					while(tmp1.next != null) 
					{
						tmp1 = tmp1.next;//traverse till u reach the end and add
					}
					tmp1.next = new Node(item, null);
				}
				size++;
				mergeDown();
			
		}

		throw new RuntimeException("You need to implement this method!");
	}

	/**
	 * Starting with the smallest array, mergeDown() merges arrays of the same size together until
	 * all the arrays have different size.
	 *
	 * This is very useful for implementing add(item)!
	 */
	private void mergeDown()
	{
		/*
		 * Your code goes here...
		 */
			Comparable[] a;
			Node tmp1 = head;
			Node tmp2 = tmp1.next;
			Node tmp3 = tmp2.next;
			
			while((tmp1 != null)&&(tmp2 != null) && !(tmp1.array.length==tmp2.array.length)) //unless length of current and next is equal, keep traversing
			{
				tmp1 = tmp1.next;
			}

			//if length is equal, then merge
			if((tmp1!=null)&&(tmp2 != null))
			{
				for(int i=0,j=0;i<tmp1.array.length,i<tmp2.array.length;)
				{
						if((tmp1.array[i]) > (tmp2.array[j]))	
						{
							a[i]=tmp2.array[j];
							j++;
						}						
						else
						{
							a[i]=tmp1.array[i];
							i++;
						}
				}
				tmp1.array=a;
				tmp3=tmp1.next;//delete tmp2
				size--;
			}
		throw new RuntimeException("You need to implement this method!");
	}


	/**
	 * Returns true if the dictionary contains an element equal to item, otherwise- false.
	 * Use the method contains() in the Node class.
	 */
	public boolean contains(E item)
	{
		if(item == null)
		{
			throw new NullPointerException("Error passing null object to contain");
		}

		/*
		 * Your code goes here...
		 */

		throw new RuntimeException("You need to implement this method!");
	}


	/**
	 * Returns the size of the dictionary.
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns a helpful string representation of the dictionary.
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		Node tmp = head;
		while(tmp != null)
		{
			sb.append( tmp.array.length + ": ");
			sb.append(tmp.toString());
			sb.append("\n");
			tmp = tmp.next;
		}
		return sb.toString();
	}



	/**
	 * Implementation of the underlying array-based data structure.
	 *
	 * You may add additional methods.
	 */
	@SuppressWarnings("unchecked")
	private static class Node
	{
		private Comparable[] array;
		private Node next;

		/**
		 * Creates an Node with the specified parameters.
		 */
		public Node(Comparable[] array, Node next)
		{
			this.array = array;
			this.next = next;
		}


		/**
		 * Returns	true, if there is an element in the array equal to item
		 * 			false, otherwise
		 */
		public boolean contains(Comparable item)
		{
			/*
			 * Your code goes here...
			 */

			throw new RuntimeException("You need to implement this method!");
		}

		/**
		 * Returns a useful representation of this Node.  (Note how this is used by Dictionary's toString()).
		 */
		public String toString()
		{
			return java.util.Arrays.toString(array);
		}
	}

}


