/**
 * Lab8: Simulating dynamic task arrival and processing in a queue
 */

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Lab8
{

    /**
     * queue of tasks
     */
    static TaskQueue processingQ;
    /**
     * queue to store the incoming tasks
     */
    static QueueInterface<Task> Q;

    public static void main(String[] argv)
    {
        // put all the task in an auxiliary queue
        try
        {
            Scanner scanInput = new Scanner(new File("Lab08/sim.in"));
            Q = new QueueReferenceBased<Task>();
            while (scanInput.hasNextLine())
            {
                Scanner scanLine = new Scanner(scanInput.nextLine());
                //		while(scanLine.hasNext()) {
                Task X = new Task();
                X.id = scanLine.nextInt();
                X.arrivalTime = scanLine.nextInt();
                X.transactionTime = scanLine.nextInt();
                Q.enqueue(X);
                //		}
            }
            scanInput.close();
        } catch (IOException e)
        {
            System.out.println("Caught IOException: " + e.getMessage());
        }
        processingQ = new TaskQueue();
        // enter tasks from the auxiliary task queue to the task queue
        while (!Q.isEmpty())
        {//while more tasks to be added
            while (!Q.isEmpty() // while more tasks to be added now
                    && processingQ.getTime() == Q.peek().arrivalTime)
            {
                processingQ.add(Q.dequeue());
            }
            processingQ.process();
        }// all task from the auxiliary queue have been sent to the taskQueue,
        //  but they may not have been performed
        while (!processingQ.isComplete()) // while not all tasks completed
            processingQ.process();
        // Write out the number of tasks processed and the average wait time
        System.out.println("Number of tasks processed : "
                + processingQ.getNumTasks() + "\n"
                + "Average wait time         : "
                + processingQ.getAvgWaitTime());

    }

}// end class Lab8
