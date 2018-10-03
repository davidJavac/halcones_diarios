package vista;

import javax.swing.SwingWorker;

class LongTask {
    private int lengthOfTask;
    private int current = 0;
    private String statMessage;

    LongTask () {
	//compute length of task ...
	//in a real program, this would figure out
	//the number of bytes to read or whatever
	lengthOfTask = 1000;
    }

    //called from ProgressBarDemo to start the task
    void go() {
	current = 0;
        final SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new ActualTask();
            }

			@Override
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }

    //called from ProgressBarDemo to find out how much work needs to be done
    int getLengthOfTask() {
	return lengthOfTask;
    }

    //called from ProgressBarDemo to find out how much has been done
    int getCurrent() {
	return current;
    }

    void stop() {
	current = lengthOfTask;
    }

    //called from ProgressBarDemo to find out if the task has completed
    boolean done() {
	if (current >= lengthOfTask)
	    return true;
	else
	    return false;
    }

    String getMessage() {
	return statMessage;
    }

    //the actual long running task, this runs in a SwingWorker thread
    class ActualTask {
	ActualTask () {
	    //fake a long task,
	    //make a random amount of progress every second
	    while (current < lengthOfTask) {
                try {
                    Thread.sleep(1000); //sleep for a second
		    current += Math.random() * 100; //make some progress
		    statMessage = "Completed " + current +
				  " out of " + lengthOfTask + ".";
                } catch (InterruptedException e) {
                }
	    }
	}
    }
}