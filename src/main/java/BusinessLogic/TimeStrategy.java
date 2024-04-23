package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy{
    public void addTask(List<Server> servers, Task task){
        Server temp = servers.get(0);
        for(Server server : servers){
            if(server.waitingPeriod.get() < temp.waitingPeriod.get())
                temp = server;
        }
        temp.addTask(task);
    }
}
