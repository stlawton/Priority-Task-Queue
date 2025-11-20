public class priorityQueue{
  public int printMenu(){
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

    int response = input.nextInt();

    return response;
  }

  public static void main(String[] args){
    int output = printMenu();
    System.out.println("User input was: " + output);


  }
  
}
