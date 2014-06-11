/** SJFSchedulingAlgorithm.java
 *
 * A shortest job first scheduling algorithm.
 *
 * @author: Name1, Name2, Name3
 * Spring 2014
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

// for this algorithm, we just need to extend from priority algorithm and change the priority to be the job time
public class SJFSchedulingAlgorithm extends PrioritySchedulingAlgorithm {
    private boolean preemptive;
    
    private PriorityQueue<Process> pQ;
    
    SJFSchedulingAlgorithm(){
    	activeJob = null;
    	preemptive = false;
    	pQ = new PriorityQueue<Process>(8, new SJFComparator());
    }
    
    @Override
    public String getName(){
	return "Shortest job first";
    }

}