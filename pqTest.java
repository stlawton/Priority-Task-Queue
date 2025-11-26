//This program is designed to allow users to create a task list with priorities that acts as a queue. The program prints a menu
//that prompts the user to enter an integer that relates to a menu option. The menu contains all the possible options for
//working with the queue of tasks. The user is able to add or remove items from the task list, view the highest priority task name
//and its priority, clear the list, check the size of the list, and check if the list is empty. The list in this program is limited
//to 10 tasks at one time and will generate an error message if the user tries to enter more than that. There are three classes in 
//this program, the pqTest which contains the printMenu and main methods, the priorityQueue class that creates the array to hold
//tasks as well as all the methods to work on that list, and the Job class which creates the task objects to store in the array.

import java.util.Scanner; //Import scanner for to record user input. 

public class pqTest{
//This class contains the main method for the code as well at the printMenu method to prompt and return user input.
//The menu gives the user the options they have for editing the task list in the printMenu method. The main method calls
//the printMenu method as well as the priorityQueue class.

  static Scanner input = new Scanner(System.in); //Initialize Scanner object for user input

  public static int printMenu(){
  //This method contains the scanner object to hold user input. It also prints the menu to prompt the user for input.
  //Once the user has chosen a menu option they input the matching integer. That integer is then returned by the method
  //for use by the main method.

    System.out.println("Type a choice from the menu:");                     //Print the menu instructions
    System.out.println();                                                   //Blank line to improve visual
    System.out.println("0)  EXIT the program.");                            //Print option 1
    System.out.println("1)  Enqueue a task.");                              //Print option 2
    System.out.println("2)  Dequeue a task.");                              //Print Option 3
    System.out.println("3)  Peek at the most urgent task.");                //Print option 4
    System.out.println("4)  Peek at the priority of the most urgent task.");//Print option 5
    System.out.println("5)  Clear the queue of all elements.");             //Print option 6
    System.out.println("6)  Return the number of tasks in the queue.");     //Print option 7
    System.out.println("7)  Determine if the queue is empty.");             //Print option 8
    System.out.println();                                                   //Print blank line for visual

    int response = input.nextInt();                                         //Initialize variable to hold user input
    input.nextLine();                                                       //Prevent input for task name from newline character

    return response;                                                        //Return user input as integer
  }
  
  public static void main(String[] args){
  //This is the main method, which is where the priorityQueue object is created. It also is where the user input from the menu is
  //processed using if-else statements. This method calls the priorityQueue methods to perform operations on the task list. It also
  //calls the printMenu method to print the menu and record the user input.

    int userInput = printMenu();                       //Calls printMenu method and stores user input.

    priorityQueue taskQueue = new priorityQueue();  //Create new task list object to hold tasks in an array.

    while (userInput != 0){                                 //Keeps the code running until the user inputs 0 to exit
      if (userInput > 7 || userInput < 0){                  //If loop to check that integer input is within range. 
        System.out.println("Invlaid integer input. Must be from 0 - 7."); //Return error message if integer is not valid. 
        System.out.println();                         //Print blank line for visual
      }
      if (userInput == 1){                           //Check if input is 1 for enqueue
        System.out.print("Task name: ");             //Prompt user to enter task name
        String newTaskName = input.nextLine();         //Store task name in variable
        System.out.print("Task priority from 1(high) - 10(low): ");  //Prompt user for task priority.
        int newPriority = input.nextInt();              //Store priority in variable. 
        System.out.println();                           //Print blank line for visual.
        taskQueue.enqueue(newTaskName, newPriority);    //Call enqueue method with user input as parameters. 
      }
      if(userInput == 2){                                  //Check if input was 2 for dequeue
        System.out.println(taskQueue.dequeue());        //Call the dequeue method and print
        System.out.println();                           //Print blank line for clarity
      }
      if (userInput ==3){                                  //Check if input was 3 for peek
        System.out.println(taskQueue.peek());           //Call peek method and print
        System.out.println();                           //Print blank line for clarity
      }
      if (userInput == 4){                                 //Check if input was 4 for peek at priority
        if (taskQueue.peekPriority() == -1)             //Check if priority is -1 indicating empty queue
          System.out.println("There are no tasks in the list.");  //Print error message for empty queue
        else                                                                                          //If queue is not empty
          System.out.println("The highest priority task has priority: " + taskQueue.peekPriority());  //call peekPriority and print
        System.out.println();                                                                         //Print blank line for clarity
      }
      if (userInput == 5){                                                                   //Check if input was 5 for clear list
        taskQueue.clear();                                                                //Call clear method
      }
      if (userInput == 6){                                                                   //Check if input was 6 for size check
        if (taskQueue.size() == 1)                                                           //Check if list size is one
          System.out.println("There is " + taskQueue.size() + " task in the list.");         //call size method and print singular case
        else                                                                                 //If the size is not one
          System.out.println("There are " + taskQueue.size() + " tasks in the list.");       //call method and print plural cases
        System.out.println();                                                                //Print blank line for clarity
      }
      if (userInput == 7){                                                                   //Check if input was 7 for isEmpty
        if (taskQueue.isEmpty())                                                             //Call the isEmpty method
          System.out.println("The task list is empty.");                                  //Print message if list is empty
      else                                                                                   //If list is not empty
          System.out.println("The task list is not empty.");                              //print message that list is not empty
        System.out.println();                                                                //Print blank line for clarity
      }

      userInput = printMenu();                                                              //Call printMenu method again to continue
    }

    input.close();                                                                          //Close scanner at end of program
  }
}

class priorityQueue{
//This class handles the creation of the queue for Job objects, as well as the methods that can operate on the queue.
//There is a constructor for priorityQueue that takes no parameters and creates an array of size 11 to hold 10 tasks 
//(leaving index 0 empty). There are two helper methods (upheap and downheap) that help make sure the queue remains sorted
//according to the heap requirements with the minimum priority value at the root. There is an enqueue and dequeue method to
//add and remove tasks. There are peek methods to peek at the top task and its priority. There are methods to check if the queue
//is empty, what the size of the queue is, and to clear the queue.

  private Job[] taskList;                                              //Instantiate array of Job objects
  private int size;                                                    //Instantiate variable to track size of list
  private static final int CAPACITY = 10;                              //Initialize variable with maximum task load

  public priorityQueue(){
  //Constructor method used to create priorityQueue objects
    taskList = new Job[CAPACITY+1];                                   //Initialize array to store Job objects
    size = 0;                                                         //Set size at 0 for new task list
  }

  private void upheap(int k){
  //This method is a helper method that performs an upheap when enqueueing a new task into a list. It takes the index
  //of the newest task as a parameter. It then checks to see if the parent of that index is lower priority than the new
  //Job. If the priority is lower it switches the parent with the newest Job and repeats until the parent priority is
  //lower than the newest Job.
    Job v = taskList[k];                                              //Save current (newest) Job in variable v
    while (k> 1 && taskList[k/2].getPriority() >= v.getPriority()){   //Loop while the parent is larger than the child
      taskList[k] = taskList[k/2];                                    //Save the parent Job in the current slot
      k = k/2;                                                        //Set the current index to the parent index
    }
    taskList[k] = v;                                                  //Save the newest job into index k
  }

  private void downheap(int k){
  //This is a helper method to downheap when a task is dequeued. On dequeue the newest task is moved into the root.
  //This method takes the root as a parameter, and checks the children to find the highest priority. If that priority
  //is higher than the current root, it moves the highest priority into the root, and moves the lower priority down
  //the heap until it is in its correct place.
    int j;                                                                      //Variable to hold index for comparison
    Job v = taskList[k];                                                        //Variable to hold the newest Job
    while(k <= size/2){                                                         //Loop check to see if left child exists
      j = k + k;                                                                //Move comparison index to left child
      if(j < size && taskList[j].getPriority() > taskList[j+1].getPriority())   //check if there is a right chile and which is lowest priority
        j++;                                                                    //Moves j to right child if it is higher priority
      if (v.getPriority() <= taskList[j].getPriority())                         //If newest job priority is higher than child
        break;                                                                  //Break from loop as order is correct
      taskList[k] = taskList[j];                                                //Save child in parent position
      k = j;                                                                    //Set k to child node index
    }
    taskList[k] = v;                                                            //Save newest Job in child index
  }

  public void enqueue(String tName, int val){
  //This method adds a new Job to the list. First it checks to see if the list is already full. If the list is not
  //full its adds the new Job to the lowest available index. The method then calls the upheap method to see if the
  //newly added Job needs to be moved up based on priority, ensuring the highest priority task is in the root.

    if (size == CAPACITY){                                                    //Checks if list is full
      System.out.println("Task list is full. Cannot enqueue " + tName);       //Prints error statement if list is full
      System.out.println();                                                   //Print blank line for visibility
      return;                                                                 //Exit method
    }
    
    Job newTask = new Job();                                                  //Creates new Job object with no parameters
    size++;                                                                   //Increase size of list
    newTask.setTaskName(tName);                                               //Sets new name for the Job
    newTask.setPriority(val);                                                 //Sets priority for new Job
    taskList[size] = newTask;                                                 //Saves the new Job object in the array
    upheap(size);                                                             //Call upheap method to sort array with new Job
  }

  public String dequeue(){
  //This method checks if the list is empty, and if it is not, removes the Job object at the top of the queue and
  //returns the task name to the terminal. The method then calls the downheap method to make sure the new root is
  //the next highest priority.

    if(isEmpty()){                                                            //Check if list is empty
      return "Task list is empty, cannot dequeue.";                           //If list is empty return error message
    }
    Job t = taskList[1];                                                      //Save root Job in variable
    taskList[1] = taskList[size];                                             //Puts newest Job in root position
    downheap(1);                                                           //Call downheap to sort the queue
    size--;                                                                   //Decrease size of list
    return "Task being removed is: " + t.getTaskName();                       //Return the name of the removed Job
  }

  public String peek(){
  //This method checks the highest priority task without changing anything in the list. It checks if the list is empty,
  //and if it is not empty returns the name of highest priority task.
    if (isEmpty())                                                            //Check if list is empty
      return "There are no tasks in the list.";                               //If it is return error message
    return "The highest priority task is: " + taskList[1].getTaskName();      //If not call method get name to return task name
  }

  public int peekPriority(){
  //This method checks the highest priority task without changing anything in the list. It checks if the list is empty,
  //and if it is not empty returns the priority of highest priority task.
    if (isEmpty())                                                            //Check if list is empty
      return -1;                                                              //If it is empty return -1
    return taskList[1].getPriority();                                         //If not call getPriority and return value
  }

  public void clear(){
  //This method clears the list of all tasks. It resets the size variable to 0 allowing users to refill the list.
  //The method then prints a message letting the use know the list has been emptied.
    size = 0;                                                                 //Set size to 0
    System.out.println("Task list has been cleared.");                        //Print message telling user the list is cleared
    System.out.println();                                                     //Print blank line for visibility
  }

  public int size(){
  //This method returns the number of tasks that are currently in the list.
    return size;                                                              //Returns size to calling method
  }

  public boolean isEmpty(){
  //This method checks if the list is empty and returns a true or false value.
    return size == 0;                                                         //Checks if size is 0 and returns boolean
  }
}

class Job{
//This class controls the Job objects that represent each task. It contains variables for the priority and the name
//of the task. It also contains methods to fetch the priority and the task name. It also contains methods to set the
//priority and task name.

  private int priority;                                                     //Variable to hold priority for Job object
  private String taskName;                                                  //Variable to hold task name for Job object
  
  public int getPriority(){
  //Method that allows other classes to fetch the priority value for a Job.
    return priority;                                                        //Return priority value for current object
  }

  public String getTaskName(){
  //Method allows other classes to fetch the task name for a Job.
    return taskName;                                                        //Return task name for current object
  }

  public void setPriority(int val){
  //Method allows other classes to set the priority value of a Job.
    priority = val;                                                         //Set current Job priority as parameter passed
  }

  public void setTaskName(String tname){
  //Method allows other classes to set the task name of a Job
    taskName = tname;                                                       //Set current Job task name as parameter passed
  }
}