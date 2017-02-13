package assignment4;

// True

import java.util.ArrayList;

public class MyArrayList<E> {
	
	// Data members
	
	private Object [] arr;
	private int size;
	private int maxSize;
	
	// Constructors
	public MyArrayList(int initialCapacity){
		
		if(initialCapacity <= 0){
			throw new IndexOutOfBoundsException("Capacity must be greater than zero");
		}
		
		arr = new Object [initialCapacity];
		maxSize = initialCapacity;
		size = 0;
	}
	
	public MyArrayList(){
		
		arr = new Object [10];
		size = 0;
		maxSize = 10;
	}
	
	// Required public methods
	
	// this method decreases the size of an array list when certain values are deleted
	private E[] decreaseSize(Object arr[], int decrease, int index){
		
		if(this.isEmpty()){
			size = 0;
		}
		else{
		
// this for loop sets the values of our given array to a new array minus the elements that were deleted: when it encounters elements 
// (set to null), it skips that element and continues setting values into our new array
			
			size = size - decrease;
			Object [] arr1 = new Object [size]; 
		
		for(int i = 0; i < size; i++){
			if(i < index){
				arr1[i] = arr[i];
			} // end if
			else
				arr1[i] = arr[i+1];	
			
		} // end for loop
		
		arr = arr1;
		
			} // end if
		
		return (E[]) arr;
	} // end method
	
	
	// this method adds an element to the end of the ArrayList
	// look at all caps on top for help
	
	// only modify the already existing array, don't make a new one. assign to arr, if arr is too small, make a new temp
	//array, with one+ bigger size, pass in all the values, then assign arr = arr1
	public boolean add(E e) {
		
		boolean result = false;
		
		if( size == maxSize){
			Object[] arr1 = new Object[size+1];
			
			for(int i = 0; i < maxSize; i++){
				arr1[i] = arr[i];
			}
			arr1[maxSize-1] = e;
			
			this.arr = arr1;
			result = true;
			size++;
			maxSize++;
		}
		else{ 
			if(size == 0){
				arr[size] = e;
			size++;
			}
			else {
				++size;
			Object[] arr1 = new Object[size];
			
			for(int i = 0; i < size-1; i++){
				arr1[i] = arr[i];
			}
			arr1[size-1] = e;
			arr = arr1;
			result = true;
		}
			}
	return result;
	}

	// this method adds an element to the given index position in the array
	public void add(int index, E element) throws IndexOutOfBoundsException{
		
		if(index < 0 || index >= size+1){
			throw new IndexOutOfBoundsException();
		}
		else{
			
		Object[] arr1 = new Object[size+1];
		// this for loop decrements from the end of the arrayList moving the values up, and then sets the new element into the preferred position
		for(int i = size; i > -1; i--){
			if(i == index){
				arr1[index] = element;
				}
			else if(i < index)
				arr1[i] = arr[i];
				else
					arr1[i] = this.arr[i-1];
		}
		
		arr = arr1;
		size++;
		maxSize = size;
		}
		
	}

	// this method locates the element at a given index and returns the element
	public E get(int index) throws IndexOutOfBoundsException{
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		return (E) arr[index];
	}
	
	//this method sets a given element to a given index
	public E set(int index, E element)throws IndexOutOfBoundsException{
		
		Object replaced;
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		else{
		 replaced = arr[index];
		 arr[index] = element;
		}
		return (E) replaced;
	}
	
	// this method returns the size of the ArrayList
	public int size(){
		
		return size;
		
	}
	
	// this method finds the index number of the given object
	public int indexOf(Object o){ 
		
		int result = -1;
		for(int i = 0; i < size; i++){
			if(o.equals(arr[i])){
				result = i;
			break;
			}
		}
		return result;
	}
		
	// this method checks if the given ArrayList is empty or not
	public boolean isEmpty(){
		
		boolean result = false;
		
		if(size == 0)
			result = true;
		
		return result;	
	}
	
	// this method removes an object at a given index, then decreases the ArrayList size
	public E remove(int index)throws IndexOutOfBoundsException{
	
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
				E removed = (E) this.arr[index];
				this.arr[index] = null;
				this.arr = decreaseSize(this.arr, 1, index); // 1 is being passed here because we only removed one element, so the size should only be decreased by one
				//size--;
				
			return removed;
	}

	// this method finds the given object, removes it, and then decreases the ArrayList size
	public boolean remove(Object o){
		
		boolean result = false;
		
		for(int i = 0; i < size; i++){
			if(o.equals(this.arr[i])){
				this.arr[i] = null;
				arr = decreaseSize(this.arr, 1, i);
				result = true;
				break;
			}
		}
		return result;
	}
	
	// this method checks to see if the given ArrayList holds the given object
	public boolean contains(Object o){ 
		
		boolean result = false;
		
		for(int i = 0; i < size; i++){
			if(o.equals(arr[i])){
				result = true;
				break;
			}
		}
		return result;
	}
	
	
}
