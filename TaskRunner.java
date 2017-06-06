package edu.utdallas.taskRunner;

import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.taskExecutor.Task;


 //Task Runner class used for creating thread and execute task by taking from FIFO Queue



public class TaskRunner implements Runnable{
	BlockingFIFO blocking_FIFO;
	
	public TaskRunner(BlockingFIFO blocking_FIFO){
		super();
		this.blocking_FIFO = blocking_FIFO;
	}
	@Override
	public void run(){
		while(true){
			Task task;
			try {
				task = blocking_FIFO.take();
				task.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
