package edu.iastate.cs2280.hw2;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {	
		PointScanner[] scannerArr = new PointScanner[4];
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		Scanner scnr = new Scanner(System.in);
		int trial = 1;
		
		while (trial != -1) {
			System.out.println("Trial " + trial + ": ");
			int userChoice = scnr.nextInt();
			
			switch (userChoice) {
				case 1:
					System.out.println("Enter number of random points: ");
					int randPts = scnr.nextInt();
					Point[] pts = generateRandomPoints(randPts, new Random());
					scannerArr[0] = new PointScanner(pts, Algorithm.SelectionSort); 
					scannerArr[1] = new PointScanner(pts, Algorithm.InsertionSort);
					scannerArr[2] = new PointScanner(pts, Algorithm.MergeSort);
					scannerArr[3] = new PointScanner(pts, Algorithm.QuickSort);
					trial++;
					break;
				case 2:
					System.out.println("Points from a file");
					System.out.println("File name: ");
					String fileName = scnr.next();
					scannerArr[0] = new PointScanner(fileName, Algorithm.SelectionSort); 
					scannerArr[1] = new PointScanner(fileName, Algorithm.InsertionSort);
					scannerArr[2] = new PointScanner(fileName, Algorithm.MergeSort);
					scannerArr[3] = new PointScanner(fileName, Algorithm.QuickSort);
					trial++;
					break;
				case 3:
					trial = -1;
					break;
			}
			
			System.out.println();
			System.out.println("algorithm   size  time (ns)");
			System.out.println("----------------------------------");
			for (PointScanner s : scannerArr) {
				s.scan();
				System.out.println(s.stats());
			}
			System.out.println("----------------------------------");
			System.out.println();
		}
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4]; 
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException { 
		if (numPts < 1) {
			throw new IllegalArgumentException("Error");
		}
		
		Point[] newPoints = new Point[numPts];
		for (int i = 0; i < numPts; i++) {
			int x = rand.nextInt();
			int y = rand.nextInt();
			newPoints[i] = new Point(x, y);
		}
		return newPoints; 
	}
}
