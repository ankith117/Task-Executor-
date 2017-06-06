package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public interface BlockingFIFO {
	void put(Task task) throws Exception;
	Task take() throws Exception;
}
