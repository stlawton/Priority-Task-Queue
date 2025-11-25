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

    int output = printMenu();                       //Calls printMenu methos and stores user input.

    priorityQueue taskQueue = new priorityQueue();  //Create new task list object to hold tasks in an array.

    while (output != 0){
      if (output > 7 || output < 0){                  //If loop to check that integer input is within range. 
        System.out.println("Invlaid integer input. Must be from 0 - 7."); //Return error message if integer is not valid. 
        System.out.println();                         //Print blank line for visual
      }
      if (output == 1){
        System.out.print("Task name: ");             //Prompt user to enter task name
        String newTaskName = input.nextLine();         //Store task name in variable
        System.out.print("Task priority from 1(high) - 10(low): ");  //Prompt user for task priority.
        int newPriority = input.nextInt();              //Store priority in variable. 
        System.out.println();                           //Print blank line for visual.
        taskQueue.enqueue(newTaskName, newPriority);    //Call enqueue method with user input as parameters. 
      }
      if(output == 2){                                  //Check if input was 2 for dequeue
        System.out.println(taskQueue.dequeue());        //Call the dequeue method and print
        System.out.println();                           //Print blank line for clarity
      }
      if (output ==3){                                  //Check if input was 3 for peek
        System.out.println(taskQueue.peek());           //Call peek method and print
        System.out.println();                           //Print blank line for clarity
      }
      if (output == 4){                                 //Check if input was 4 for peek at priority
        if (taskQueue.peekPriority() == -1)             //Check if priority is -1 indicating empy queue
          System.out.println("There are no tasks in the list.");  //Print error message for empty queue
        else                                                                                          //If queue is not empty
          System.out.println("The highest priority task has priority: " + taskQueue.peekPriority());  //call peekPriority and print
        System.out.println();                                                                         //Print blank line for clarity
      }
      if (output == 5){                                                                   //Check if input was 5 for clear list
        taskQueue.clear();                                                                //Call clear method
      }
      if (output == 6){                                                                   //
        if (taskQueue.size() == 1)
          System.out.println("There is " + taskQueue.size() + " task in the list.");
        else
          System.out.println("There are " + taskQueue.size() + " tasks in the list.");
        System.out.println();                           //Print blank line for clarity
      }
      if (output == 7){
        if (taskQueue.isEmpty())
          System.out.println("The task list is empty.");
        else
          System.out.println("The task list is not empty.");
        System.out.println();                           //Print blank line for clarity
      }

      output = printMenu();
    }

    input.close();
  }
}

class priorityQueue{

  private Job[] taskList;
  private int size;
  private static final int CAPACTIY = 10;

  public priorityQueue(){
    taskList = new Job[CAPACTIY+1];
    size = 0;
    Job sentinel = new Job(Integer.MAX_VALUE, "Sentinel Value");
    taskList[0] = sentinel;
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
    
    Job newTask = new Job(val, tName);
    size++;
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

  public Job(int val, String tName){
    priority = val;
    taskName = tName;
  }
  
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