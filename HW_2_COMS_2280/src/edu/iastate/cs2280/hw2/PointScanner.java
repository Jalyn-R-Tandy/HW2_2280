package edu.iastate.cs2280.hw2;

/**
 * 
 * @author Jalyn Tandy
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner {
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	private String fileName;
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		
		points = pts;
		sortingAlgorithm = algo;
		
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		int newInt = 0; // initialize
		sortingAlgorithm = algo; // sorting algorithm
		fileName = inputFileName; // input file name

		try (Scanner scnr = new Scanner(new File(inputFileName))) {
			while (scnr.hasNext()) {
				scnr.nextInt(); // read an integer from the file
				newInt++; // increment the count
			}
			if (newInt % 2 != 0) {
				throw new InputMismatchException(); // exception if the count is odd
			}
			points = new Point[newInt / 2]; // create new array of points
			scnr.close(); // close scanner
		}

		try (Scanner anotherNewScanner = new Scanner(new File(inputFileName))) {
			int anotherNewInt = 0;
			while (anotherNewScanner.hasNext()) {
				int x = anotherNewScanner.nextInt();
				int y = anotherNewScanner.nextInt();
				points[anotherNewInt] = new Point(x, y);
				anotherNewInt++;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter = null; 
		
		if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		if (sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		
		long startTime;
		long endTime;
		
		// x-coordinate
		aSorter.setComparator(0);
		startTime = System.currentTimeMillis();
		aSorter.sort();
		endTime = System.currentTimeMillis();
		long xTime = endTime - startTime;
		int x = aSorter.getMedian().getX();
		
		// y-coordinate
		aSorter.setComparator(1);
		startTime = System.currentTimeMillis();
		aSorter.sort();
		endTime = System.currentTimeMillis();
		long yTime = endTime - startTime;
		int y = aSorter.getMedian().getY();
		
		scanTime = xTime + yTime;
		medianCoordinatePoint = new Point(x, y);		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats() {
		if (sortingAlgorithm == Algorithm.MergeSort) {
			return "MergeSort " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			return "InsertionSort " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.QuickSort) {
			return "QuickSort " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			return "SelectionSort " + points.length + " " + scanTime;
		}
		return null; 
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString() {
		return medianCoordinatePoint.toString();
	}
	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		try {
			FileWriter file = new FileWriter(fileName); 
			file.write(toString()); 
			file.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
