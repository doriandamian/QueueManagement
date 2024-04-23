package Model;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    public ArrayBlockingQueue<Task> tasks;
    public AtomicInteger waitingPeriod;

    public Server(int noOfClients) {
        tasks = new ArrayBlockingQueue<Task>(noOfClients);
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.serviceTime);
    }

    @Override
    public void run() {
        while(true){
            try {
                Task temp = tasks.peek();
                if(temp!=null){
                    while(temp.serviceTime > 0){
                        Thread.sleep(1000);
                        temp.serviceTime--;
                        waitingPeriod.addAndGet(-1);
                    }
                    tasks.poll();
                }
//                else Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getNoOfTasks(){
        return tasks.size();
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }
}
