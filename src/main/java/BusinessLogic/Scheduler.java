package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, Integer maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        changeStrategy();
        servers = new ArrayList<>();
        for(int i = 0; i < maxNoServers; i++){
            Server serv = new Server(maxTasksPerServer);
            servers.add(serv);
            Thread t = new Thread(serv);
            t.start();
        }
    }

    public void changeStrategy() {
        if(SimulationData.selectionPolicy == SelectionPolicy.SHORTEST_TIME)
            strategy = new TimeStrategy();
        else
            strategy = new ShortestQueueStrategy();

    }

    public void dispatchTask(Task task){
        strategy.addTask(servers,task);
    }

    public boolean areServersEmpty(){
        for(Server server: servers)
            if(server.waitingPeriod.get() > 0)
                return false;
        return true;
    }

    public List<Server> getServers() {
        return servers;
    }
}
