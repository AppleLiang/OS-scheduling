package com.jimweller.cpuscheduler;

import java.util.Comparator;

/*
 * this comparator is used to compare the arrival time of every process 
 * in order to sort them in the priority queue
 */
public class ProcessArrivalTimeComparator implements Comparator<Process> {
	
	public int compare(Process p1, Process p2) {
		return (int)(p1.arrival - p2.arrival);
	}
}
