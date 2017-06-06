package edu.utdallas.taskExecutorImpl;

import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.blockingFIFO.implement.BlockingFIFOImpl;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskRunner.TaskRunner;

public class TaskExecutorImpl implements TaskExecutor
{
	BlockingFIFO blocking_FIFO;
	
	public TaskExecutorImpl(int threadCount){
		blocking_FIFO = new BlockingFIFOImpl(100);
		for(int i=0;i<threadCount;i++){
			Thread thread = new Thread(new TaskRunner(blocking_FIFO));
			thread.start();
		}
	}
	@Override
	public void addTask(Task task)
	{
		// TODO Complete the implementation
		try {
			blocking_FIFO.put(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
