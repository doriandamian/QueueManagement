package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SimulationManager implements Runnable{
    public static Integer timeLimit;
    public static Integer maxProcessingTime;
    public static Integer minProcessingTime;
    public static Integer maxArrivalTime;
    public static Integer minArrivalTime;
    public static Integer numberOfServers;
    public static Integer numberOfClients;
    public static SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;
    private PrintWriter logWriter;

    public SimulationManager(Integer numberOfClients, Integer numberOfServers, Integer timeLimit, Integer minArrivalTime, Integer maxArrivalTime, Integer minProcessingTime, Integer maxProcessingTime, SelectionPolicy selectionPolicy, SimulationFrame frame) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.selectionPolicy = selectionPolicy;
        generatedTasks = new ArrayList<>();
        this.frame = frame;
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        generateRandomTasks();
        try {
            logWriter = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            System.err.println("Error opening log file: " + e.getMessage());
        }
        System.out.println("SIMULATION STARTED");
    }

    public void generateRandomTasks(){
        Random random = new Random();
        for(int i=0; i<numberOfClients; i++){
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int processingTime = random.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            int id = i;
            generatedTasks.add(new Task(id,arrivalTime,processingTime));
        }
        Collections.sort(generatedTasks);
    }

    public void appendToLog(String text) {
        if (logWriter != null) {
            logWriter.println(text);
            logWriter.flush();
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        System.out.println("RUNNING");
        frame.outputArea.setText("");
        while(currentTime < timeLimit && (!scheduler.areServersEmpty() || !generatedTasks.isEmpty())){
            final int finalCurrTime = currentTime;
            List<Task> removeList = new ArrayList<>();
            for(Iterator<Task> iterator = generatedTasks.iterator(); iterator.hasNext();){
                Task task = iterator.next();
                if(task.arrivalTime == currentTime){
                    scheduler.dispatchTask(task);
                    removeList.add(task);
                }
            }
            generatedTasks.removeAll(removeList);
            SwingUtilities.invokeLater(() -> frame.outputArea.append("Time: " + finalCurrTime + "\n"));
            SwingUtilities.invokeLater(() -> frame.outputArea.append("Waiting clients: "));
            for(Task task:generatedTasks){
                SwingUtilities.invokeLater(() -> frame.outputArea.append("("+task.id+", "+task.arrivalTime+", "+task.serviceTime+"); "));
            }
            SwingUtilities.invokeLater(() -> frame.outputArea.append("\n"));
            int serverId =1;
            for(Server server: scheduler.getServers()){
                int finalServerId = serverId;
                SwingUtilities.invokeLater(() -> frame.outputArea.append("Queue "+ finalServerId +": "));
                for(Task task: server.tasks){
                    SwingUtilities.invokeLater(() -> frame.outputArea.append("("+task.id+", "+task.arrivalTime+", "+task.serviceTime+"); "));
                }
                SwingUtilities.invokeLater(() -> frame.outputArea.append("\n"));
                serverId++;
            }
            SwingUtilities.invokeLater(() -> frame.outputArea.append("\n"));
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        appendToLog(frame.outputArea.getText());
        System.out.println("STOPPING");
    }

}

