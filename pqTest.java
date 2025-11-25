//This program is designed to allow users to create a task list with priorities that acts as a queue. The program prints a menu
//that prompts the user to enter an integer that relates to a menu option. The menu contains all of the possible options for
//working with the queue of tasks. The user is able to add or remove items from the task list, view the highest priority task name
//and its priority, clear the list, check the size of the list, and check if the list is empty. The list in this porgram is limited
//to 10 tasks at one time and will generate an error message if the user tries to enter more than that. There are three classes in 
//this program, the pqTest which contains the printMenu and main methods, the priorityQueue class that creates the array to hold
//tasks as well as all of the methods to work on that list, and the Job class which creates the task objects to store in the array.

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
    input.nextLine();                                                       //Prevent accidental input for task name with newline character

    return response;                                                        //Return user input as integer
  }
  
  public static void main(String[] args){
  //This is the main method, which is where the priorityQueue object is created. It also is where the user input from the menu is
  //processed using if-else statements. This method calls the priorityQueue methods to perfomr operations on the task list. It also
  //calls the printMenu method to rpint the menu and record the user input. 

    int userInput = printMenu();                       //Calls printMenu methos and stores user input.

    priorityQueue taskQueue = new priorityQueue();  //Create new task list object to hold tasks in an array.

    while (userInput != 0){                                 //While loop keeps the code running until the user inputs 0 to exit
      if (userInput > 7 || userInput < 0){                  //If loop to check that integer input is within range. 
        System.out.println("Invlaid integer input. Must be from 0 - 7."); //Return error message if integer is not valid. 
        System.out.println();                         //Print blank line for visual
      }
      if (userInput == 1){
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
        if (taskQueue.peekPriority() == -1)             //Check if priority is -1 indicating empy queue
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
//(leaveing index 0 empty). There are two helper methods (upheap and downheap) that help make sure the queue remains sorted
//according to the heap requirements with the minimum priority value at the root. There is an enqueue and dequeue method to
//add and remove tasks. There are peek methods to peek at the top task and its priority. There are methods to check if the queue
//is empty, what the size of the queue is, and to clear the queue.

  private Job[] taskList;
  private int size;
  private static final int CAPACTIY = 10;

  public priorityQueue(){
    taskList = new Job[CAPACTIY+1];
    size = 0;
  }

  private void upheap(int k){
    Job v = taskList[k];
    while (k> 1 && taskList[k/2].getPriority() >= v.getPriority()){
      taskList[k] = taskList[k/2];
      k = k/2;
    }
    taskList[k] = v;
  }

  private void downheap(int k){
    int j;
    Job v = taskList[k];
    while(k <= size/2){
      j = k + k;
      if(j < size && taskList[j].getPriority() > taskList[j+1].getPriority())
        j++;
      if (v.getPriority() <= taskList[j].getPriority())
        break;
      taskList[k] = taskList[j];
      k = j;
    }
    taskList[k] = v;
  }

  public void enqueue(String tName, int val){
    if (size == CAPACTIY){
      System.out.println("Task list is full. Cannot enqueue " + tName);
      System.out.println();
      return;
    }
    
    Job newTask = new Job();
    size++;
    newTask.setTaskName(tName);
    newTask.setPriority(val);
    taskList[size] = newTask;
    upheap(size);
  }

  public String dequeue(){
    if(isEmpty()){
      return "Task list is empty, cannot dequeue.";
    }
    Job t = taskList[1];
    taskList[1] = taskList[size];
    downheap(1);
    size--;
    return "Task being removed is: " + t.getTaskName();
  }

  public String peek(){
    if (isEmpty())
      return "There are no tasks in the list.";
    return "The highest priority task is: " + taskList[1].getTaskName();
  }

  public int peekPriority(){
    if (isEmpty())
      return -1;
    return taskList[1].getPriority();
  }

  public void clear(){
    size = 0;
    System.out.println("Task list has been cleared.");
    System.out.println();
  }

  public int size(){
    return size;
  }

  public boolean isEmpty(){
    return size == 0;
  }
}

class Job{
  private int priority;
  private String taskName;
  
  public int getPriority(){
    return priority;
  }

  public String getTaskName(){
    return taskName;
  }

  public void setPriority(int val){
    priority = val;
  }

  public void setTaskName(String tname){
    taskName = tname;
  }
}