/* Implement this class. */

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class MyHost extends Host {

    private final PriorityBlockingQueue<Task> tasks =  new PriorityBlockingQueue<>(5, (Comparator<Task>) (o1, o2) -> {
        if (o1.getPriority() == o2.getPriority()) {
            return (int) (o1.getStart() - o2.getStart());
        }
        return o2.getPriority() - o1.getPriority();
    });

    // check if host is down
    private boolean isDown = false;

    // current running task
    private Task currentTask = null;
    
    // represents the time that the host executes a task
    private final int SLEEP_TIME = 100;

    @Override
    public void run() {
        while (!this.isDown) {
            if (this.tasks.isEmpty()) {
                continue;
            }

            // get the first task from the queue if there is no current task
            if (this.currentTask == null) {
                this.currentTask = this.tasks.peek();
            }


            try {
                // execute a part of the task
                Thread.sleep(SLEEP_TIME);
                currentTask.setLeft(currentTask.getLeft() - SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // if the task is finished, remove it from the queue
            if (currentTask.getLeft() <= 0) {
                currentTask.finish();
                this.tasks.remove(currentTask);
                currentTask = null;
            }
                
            // check if task is preemptible and stop its execution if it is
            if (currentTask != null && currentTask.isPreemptible()) {
                currentTask = null;
            }
        }
    }

    // add task to the queue
    @Override
    public void addTask(Task task) {
        this.tasks.put(task);
    }

    // get the size of the queue
    @Override
    public int getQueueSize() {
        return this.tasks.size();
    }

    // get the total work left for all the tasks in the queue
    @Override
    public long getWorkLeft() {
        long workLeft = 0;

        for (Task task : this.tasks) {
            workLeft += task.getLeft();
        }
        return workLeft;
    }

    // stop the host
    @Override
    public void shutdown() {
        for (Task task : this.tasks) {
            task.finish();
        }

        this.isDown = true;
    }
}
