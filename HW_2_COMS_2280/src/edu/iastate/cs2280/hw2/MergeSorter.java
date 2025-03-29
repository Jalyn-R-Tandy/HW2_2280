package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *  
 * @author Jalyn Tandy
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort() {
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts) {
		    if (pts.length <= 1)
		        return;
		        
		    int mid = pts.length / 2;
		    
		    Point[] left = Arrays.copyOfRange(pts, 0, mid);
		    Point[] right = Arrays.copyOfRange(pts, mid, pts.length);
		    
		    mergeSortRec(left);
		    mergeSortRec(right);
		    
		    int a = 0;
		    int b = 0;
		    int c = 0;
		    
		    while (a < left.length && b < right.length) {
		        if (left[a].compareTo(right[b]) < 0) {
		            pts[c] = left[a];
		            a++;
		        } else {
		            pts[c] = right[b];
		            b++;
		        }
		        c++;
		    }
		    
		    while (b < right.length) {
		        pts[c] = right[b];
		        b++;
		        c++;
		    }
		    
		    while (a < left.length) {
		        pts[c] = left[a];
		        a++;
		        c++;
		    }
	}

	
	// Other private methods if needed ...

}
