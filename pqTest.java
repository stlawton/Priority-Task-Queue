import java.util.Scanner;

public class pqTest{
//This class contains the main method for the code as well at the printMenu method to prompt and return user input. The menu gives
//the user all of the options they have for editing the task list in the printMenu method. The main method calls the printMenu
//method as well as the priorityQueue class. 

  public static int printMenu(){
  //This method contains the scanner object to hold user input. It also prints the menu to prompt the user for input. Once the user has
  //chosen a menu option they input the matching integer. That integer is then returned by the method for use by the main method.

    Scanner input = new Scanner(System.in); //Initialize Scanner opbject for user input

    System.out.println("Type a choice from the menu:");                    //Print the menu instructions
    System.out.println();                                                     //Blank line to imporve visual
    System.out.println("0)  EXIT the program.");                           //Print option 1
    System.out.println("1)  Enqueue a task.");                              //Print option 2
    System.out.println("2)  Dequeue a task.");                              //Print Option 3
    System.out.println("3)  Peek at the most urgent task.");                //Print option 4
    System.out.println("4)  Peek at the priority of the most urgent task.");//Print option 5
    System.out.println("5)  Clear the queue of all elements.");             //Print option 6
    System.out.println("6)  Return the number of tasks in the queue.");     //Print option 7
    System.out.println("7)  Determine if the queue is empty.");             //Print option 8
    System.out.println();                                                     //Print blank line for visual

    int response = input.nextInt();                                           //Initialize variable to hold user input

    return response;                                                          //Return user input as integer
  }
  
    public static void main(String[] args){
    int output = printMenu();

    priorityQueue taskQueue = new priorityQueue();

    System.out.println("User input was: " + output);


  }
}

class priorityQueue{

  private Job[] taskList;
  private int size;
  private int CAPACTIY = 10;

  public priorityQueue{
    taskList = new Job[10];
     
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