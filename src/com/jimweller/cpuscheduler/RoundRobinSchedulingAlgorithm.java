/** RoundRobinSchedulingAlgorithm.java
 *
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Name1, Name2, Name3
 * Spring 2014
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {
	
	static final int DEFAULTQUANTUM = 10;
	
    /** the timeslice each process gets */
    private int quantum;
    
    // process we already have and haven't been added to the robin
    // we would keep adding them
    private PriorityQueue<Process> allJobs;
    
    // jobs in the robin
    private LinkedList<Process> robinQueue;
    
    // current job
    private Process activeJob;
    
    // index of current job in robin queue
    private int activeJobIndex;
    
    // how much time current job has already ran
    private int curRunTime;
    
    RoundRobinSchedulingAlgorithm() {
    	allJobs = new PriorityQueue<Process>(8, new ProcessArrivalTimeComparator());
    	robinQueue = new LinkedList<Process>();
    	quantum = DEFAULTQUANTUM;
    	curRunTime = 0;
    	activeJobIndex = 0;
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
    	/*
    	 * we need to put all jobs in the priority queue first. And put newly arriving jobs
    	 * arriving at doing round robin time in robin queue gradually.
    	 */
    	allJobs.add(p);
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
    	return allJobs.remove(p) || robinQueue.remove(p);
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	transferNewJobs();
    	Iterator<Process> iterator = robinQueue.iterator();
    	while (iterator.hasNext()) {
    		otherAlg.addJob(iterator.next());
			iterator.remove();
    	}
    	activeJob = null;
    }

    /**
     * Get the value of quantum.
     *
     * @return Value of quantum.
     */
    public int getQuantum() {
	return quantum;
    }

    /**
     * Set the value of quantum.
     *
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
	this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
    	transferNewJobs();
    	if (quantum == 0) {
    		// if quantum is 0, we dont need to do anything
			activeJob = null;
		}
    	else if (robinQueue.size() > 0) {
			if (activeJob == null) {
				// at the beginning, get the job with index 0
				activeJob = robinQueue.get(activeJobIndex);
				curRunTime = 0;
			}
			else if ((curRunTime == quantum) && !activeJob.isFinished()) {
			activeJobIndex = (activeJobIndex + 1) % robinQueue.size();
			activeJob = robinQueue.get(activeJobIndex);
			curRunTime = 0;
			}
			else if (activeJob.isFinished()) {
				if (activeJobIndex == robinQueue.size()) {
					activeJobIndex--;
				}
				activeJob = robinQueue.get(activeJobIndex);
				curRunTime = 0;
			}
		}
    	else {
			activeJob = null;
		}
    	return activeJob;
    	
    }

    public String getName() {
    	return "Round Robin";
    }
    /*
     * this function is used to move newly coming jobs to robin queue
     */
    public void transferNewJobs() {
    	while (allJobs.size() > 0) {
    		robinQueue.add(allJobs.poll());
    	}
    }
}