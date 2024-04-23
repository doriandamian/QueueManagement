package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    public void addTask(List<Server> servers, Task task){
        Server temp = servers.get(0);
        for(Server server : servers){
            if(server.getNoOfTasks() < temp.getNoOfTasks())
                temp = server;
        }
        temp.addTask(task);
    }
}
