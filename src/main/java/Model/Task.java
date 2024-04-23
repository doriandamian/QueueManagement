package Model;

public class Task implements Comparable<Task>{
    public Integer id;
    public Integer arrivalTime;
    public Integer serviceTime;

    public Task(Integer id, Integer arrivalTime, Integer serviceTime) {
        this.serviceTime = serviceTime;
        this.arrivalTime = arrivalTime;
        this.id = id;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }

}
