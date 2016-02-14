/******************************************************************************
 *                    Amortized Dictionary
 ******************************************************************************
 *
 * Implementation of an Amortized Array-Based Dictionary data structure.
 *
 * This data structure supports duplicates and does *NOT* support storage of
 * null references.
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
			 * item exists and has to be added to the linkedlist as a new node 
			 */
			Comparable [] a= new Comparable [1];//converting item into array of comparable since the node constructor expects an array
			a[0]=item;
			
			head = new Node (a, head);//made head the new node for the incoming item
            size++;                       
			//check if merge is needed by checking size of each array

			Node tmp1 = head;
			while(tmp1.next!=null)//for the whole size of the dictionary
			{	
				Node tmp2=tmp1.next;
				int size1=tmp1.returnSize();
				int size2=tmp2.returnSize();
				if(size1==size2)
				{
					mergeDown(size1,tmp1,tmp2);
					tmp1=head;
				}
				else{
					tmp1=tmp1.next;
				}
			}
		}
		//throw new RuntimeException("You need to implement this method!");
	}

	/**
	 * Starting with the smallest array, mergeDown() merges arrays of the same size together until
	 * all the arrays have different size.
	 *
	 * This is very useful for implementing add(item)!
	 */
	private void mergeDown(int a, Node tmp1,Node tmp2)
	{
		/*
		 * Your code goes here...
		 */
		Node tmp3=tmp2.next;
		int i=0;
		int j=0;
		int k=0;
		int combinedSize=2*a;
		Comparable[] b= new Comparable[combinedSize];
		//for(i=0,j=0;(i<a)||(j<a);)
		while((i<a)&&(j<a))
		{
			if(tmp1.array[i].compareTo(tmp2.array[j])>0)
			{//if i>j means in first comparison tmp1 element is bigger than tmp2 then put tmp2 element and increment i
				b[k]=tmp2.array[j];
				j++;
			}
			else
			{
				b[k]=tmp1.array[i];
				i++;
			}
			k++;
		}

		while((i==a)&&(j<a)){
			b[k]=tmp2.array[j];
			j++;
			k++;
		}

		while((j==a)&&(i<a)){
			b[k]=tmp1.array[i];
			i++;
			k++;
		}

		tmp1.array=b;
		tmp1.next=tmp3;
		//throw new RuntimeException("You need to implement this method!");
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
		
		Node tmp1 = head;
		while(tmp1!=null)
		{
		  if(tmp1.contains(item))
			{
			  return true;
			}
		  tmp1=tmp1.next;
		}
		return false;
		//throw new RuntimeException("You need to implement this method!");
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

			public int returnSize()
			{
				return array.length;
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
				if(Arrays.binarySearch(array,item)>=0){return true;}
				return false;
				//throw new RuntimeException("You need to implement this method!");
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
