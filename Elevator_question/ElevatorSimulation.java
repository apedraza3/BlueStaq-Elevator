package Elevator_question;
// Things to remember
// press a floor button and the elevator takes you to that floor
// should store if the requests have already been made
// multiple poeple might call the elevator so you need to set priorities
// the different methods that are needed moveup and movedown which are used in the move method
// a method to stop at a floor, get the current floor, and to check if it is moving up
//create a building class with a specified amount of floors 
// the building class will simualte where the request is made and to run the move method
import java.util.Scanner;

//now we want to simulate the elevator
public class ElevatorSimulation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of floors in the building");
        int floor_number = scanner.nextInt();
        Building building = new Building(floor_number);

        building.requestElevator(1,5);

        building.requestElevator(2,5);

        building.requestElevator(2,6);

        building.requestElevator(6,9);

        building.requestElevator(4,1);

        building.requestElevator(-1,-1);
        building.runElevator();

        scanner.close();
    }
}