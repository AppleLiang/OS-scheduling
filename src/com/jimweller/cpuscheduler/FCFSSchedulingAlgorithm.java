/** FCFSSchedulingAlgorithm.java
 *
 * A first-come first-served scheduling algorithm.
 *
 * @author: Name1, Name2, Name3
 * Spring 2014
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {
	
	private PriorityQueue<Process> pQ;
	
    FCFSSchedulingAlgorithm()
    {   	
    	pQ = new PriorityQueue<>(8, new ProcessArrivalTimeComparator());
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
    	pQ.add(p);
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
    	return pQ.remove(p);
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	Iterator<Process> iterator = pQ.iterator();
    	while (iterator.hasNext()) {
			otherAlg.addJob(iterator.next());
			iterator.remove();
		}
    }


//    public boolean shouldPreempt(long currentTime){
//    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
    	return pQ.peek();
    }

    public String getName(){
	return "First-come first-served";
    }
}