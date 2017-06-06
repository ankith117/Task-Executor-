package edu.utdallas.blockingFIFO.implement;

import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.taskExecutor.Task;

//Implementation of BlockingFIFO using java monitors 
 

public class BlockingFIFOImpl implements BlockingFIFO{

	private int size_queue; // size of FIFO	
	private Task[] task_queue; // BlockingFIFO Array
	private int task_count;
	
	private int in; //Buffer Pointer
	private int out; //Buffer Pointer
	private Object nonEmpty;  // condition variable for synchronization
	private Object nonFull;  // condition variable for synchronization
	
public BlockingFIFOImpl(int size_queue){ //Constructor
		
		if(size_queue<0) throw new IllegalArgumentException();
		
		this.size_queue = size_queue;
		this.task_queue = new Task[size_queue];
		
		task_count = in = out = 0;
		nonEmpty = new Object();
		nonFull = new Object();
		
	}
	//Method implementation to put task in the FIFO
	@Override
	public void put(Task task) throws Exception {
		// TODO Auto-generated method stub
		while(true){
			if(task_count == size_queue){
				synchronized (nonFull){
					nonFull.wait();
				}
			}
			synchronized(this) {
				if(task_count == size_queue) continue;
				task_queue[in] = task;
				in = (in+1)%size_queue;
				task_count++;
				synchronized(nonEmpty){
					nonEmpty.notify();
				}
				return;	
			}
		}
	}
	
//Method Implementation to remove task from FIFO

	@Override
	public Task take() throws Exception {
		// TODO Auto-generated method stub
		while(true){
			if(task_count == 0){
				synchronized (nonEmpty){
					nonEmpty.wait();
				}
			}
			synchronized(this) {
				if(task_count == 0) continue;
				
				Task task = task_queue[out];
				out = (out+1)%size_queue;
				task_count--;
				synchronized(nonFull){
					nonFull.notify();
				}
				return task;	
			}
		}
	}

}
