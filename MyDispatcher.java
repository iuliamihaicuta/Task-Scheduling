/* Implement this class. */

import java.util.List;
// import java.util.concurrent.Semaphore;

public class MyDispatcher extends Dispatcher {
    private int indexRR = 0;
    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
    }

    @Override
    public void addTask(Task task) {
        
        // add task to host depending on the algorithm
        switch (this.algorithm) {
            case ROUND_ROBIN:
                addTaskRR(task);
                break;
            case SHORTEST_QUEUE:
                addTaskSQ(task);
                break;
            case SIZE_INTERVAL_TASK_ASSIGNMENT:
                addTaskSITA(task);
                break;
            case LEAST_WORK_LEFT:
                addTaskLWL(task);
                break;
        }
    }

    // add task to host using round robin algorithm
    private void addTaskRR(Task task) {
        this.hosts.get(this.indexRR).addTask(task);
        this.indexRR = (this.indexRR + 1) % this.hosts.size();
    }

    // add task to host using shortest queue algorithm
    private void addTaskSQ(Task task) {
        int index = 0;
        for (int i = 0; i < this.hosts.size(); i++) {
            if (this.hosts.get(i).getQueueSize() < this.hosts.get(index).getQueueSize()) {
                index = i;
            }
        }
        this.hosts.get(index).addTask(task);
    }

    // add task to host using size interval task assignment algorithm
    private void addTaskSITA(Task task) {
        switch (task.getType()) {
            case SHORT:
                this.hosts.get(0).addTask(task);
                break;
            case MEDIUM:
                this.hosts.get(1).addTask(task);
                break;
            case LONG:
                this.hosts.get(2).addTask(task);
                break;
        }
    }

    // add task to host using least work left algorithm
    private void addTaskLWL(Task task) {
        int index = 0;
        for (int i = 0; i < this.hosts.size(); i++) {
            if (this.hosts.get(i).getWorkLeft() < this.hosts.get(index).getWorkLeft()) {
                index = i;
            }
        }
        this.hosts.get(index).addTask(task);
    }
}
