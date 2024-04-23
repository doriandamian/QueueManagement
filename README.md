

# QUEUE MANAGEMENT SYSTEM

# Assignment objective
## Main objective
Developing a queue management system app for simulating and analyzing performance.
## Second objective
### Modeling queues
Designing and implementing a data structure so that the queues are represented correctly and working with variable arrival and service time.
### Simulation management
Developing a system to simulate the queue dynamics over a period of time.
### User interface
Creating a user-friendly interface where data can be entered, representing the connection between the user and the app.
### Input validation
Verifying if the input data is correct and providing clear feedback if errors occur.
### Performance analysis
Implementing the functionality of the app to calculate and print the average results of the queue.

# Problem analysis, Modeling, Scenarios, Use Cases
## Functional requirements
Users should be able to input simulation information such as the number of clients, the number of queues, the total simulation time, etc.

Simulation must work with different arrival and service time.

The system should display the queue’s information in real time and (at the end of execution) provide final performance metrics.
## Non-functional requirements
The user interface should be intuitive and easy to use.

The application should be operational and accessible whenever required.
## Use cases

## User data structures
### <a name="_int_g95z18vj"></a>ArrayList
The <a name="_int_kd7xwnnk"></a>ArrayList is used in the Scheduler class to maintain a dynamic list of Server instances. It allows the scheduler to dynamically add or remove servers based on the simulation's requirements.
### <a name="_int_2afe8q9e"></a>LinkedBlockingQueue
Each server uses a <a name="_int_97vvwzgr"></a>LinkedBlockingQueue to manage tasks. This data structure is suitable for concurrent processing of tasks. It provides a thread-safe method for adding and removing tasks. 
### <a name="_int_z3ev65ig"></a>AtomicInteger
This data structure is used in the Server class for storing the total <a name="_int_3wndn1nx"></a>waitingPeriod of a queue. It guarantees the atomicity of changes to the integer value.
## Class description
### Scheduler
Purpose: Assigning tasks to servers based on the chosen selection policy.

Methods: 

changeStrategy(): initializes the local variable strategy with the correct selection policy.

dispatchTask(): calls the <a name="_int_tjavtxeo"></a>addTask() method with the correct approach.

areServersEmpty(): checks if all servers have empty queues. Used for determining when the app stops.
### SimulationData
Purpose: Saves the input data provided in the user interface when the program decides that all data given is valid.
### SimulationManager
Purpose: Coordinates the entire simulation process, including task generation, dispatch to servers, etc.

Methods:

generateRandomTasks(): generates a random task for each client based on arrival and service intervals provided by the user.

appendToLog(): appends the simulation information to the log file in the project folder.

run(): Main method for the simulation process. Responsible for calling all the simulation methods and printing simulation results.
## GUI Implementation

This is the application GUI. The user inputs data in the text areas and selects the wanted policy.

The first button (VALIDATE DATA) checks if all required data is correctly given by the user. If it is, then the SIMULATE BUTTON is enabled, giving the user access to start the simulation.

# Results
For the queue management simulation project, the evaluation methodology consisted of manually conducted test cases. These cases were carefully selected to mirror the application’s expected use, drawn directly from the project's specifications, guaranteeing that the testing was both pertinent and exhaustive.

The outcomes for each testing instance were meticulously documented in a series of three distinct text documents: test1.txt, test2.txt, and test3.txt. These documents recorded the system’s behavior, including a chronological event log.
# Conclusions
The project has successfully to the development queue management simulation system capable of modeling and analyzing the behavior of queues. It is equipped to manage tasks with various arrival and service times.
## Future developments
Analytical tools: Integration of real-time analytical data could offer deeper insights into the queues.

GUI Improvements: A future development can bring a more dynamic GUI, providing a more precise and easier to read visualization of the queue’s status during running.		

