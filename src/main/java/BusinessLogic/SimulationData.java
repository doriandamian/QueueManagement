package BusinessLogic;

public class SimulationData {
    public static int noClients;
    public static int noQueues;
    public static int simulationInterval;
    public static int minArrivalTime;
    public static int maxArrivalTime;
    public static int minServiceTime;
    public static int maxServiceTime;
    public static SelectionPolicy selectionPolicy;

    public SimulationData(String clients, String queues, String interval, String minArr, String maxArr, String minSer, String maxSer, int policy){
        noClients = Integer.parseInt(clients);
        noQueues = Integer.parseInt(queues);
        simulationInterval = Integer.parseInt(interval);
        minArrivalTime = Integer.parseInt(minArr);
        maxArrivalTime = Integer.parseInt(maxArr);
        minServiceTime = Integer.parseInt(minSer);
        maxServiceTime = Integer.parseInt(maxSer);
        if(policy == 1)
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        else
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    }
}
