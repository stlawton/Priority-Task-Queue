import java.util.Scanner;

public class pqTest{
  public static int printMenu(){
    Scanner input = new Scanner(System.in);

    System.out.println("Type a choice from the menu:");
    System.out.println();
    System.out.println("0)  EXIT the program.");
    System.out.println("1)  Enqueue a task.");
    System.out.println("2)  Dequeue a task.");
    System.out.println("3)  Peek at the most urgent task.");
    System.out.println("4)  Peek at the priority of the most urgent task.");
    System.out.println("5)  Clear the queue of all elements.");
    System.out.println("6)  Return the number of tasks in the queue.");
    System.out.println("7)  Determine if the queue is empty.");
    System.out.println();

    int response = input.nextInt();

    return response;
  }
  
    public static void main(String[] args){
    int output = printMenu();

    System.out.println("User input was: " + output);


  }
}

class priorityQueue{


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