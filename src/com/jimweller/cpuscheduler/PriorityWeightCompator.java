package com.jimweller.cpuscheduler;

import java.util.Comparator;

public class PriorityWeightCompator implements Comparator<Process> {
	public int compare(Process p1, Process p2) {
		return (int)(p1.getPriorityWeight() - p2.getPriorityWeight());
	}
}
