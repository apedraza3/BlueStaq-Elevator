// Things to remember
// press a floor button and the elevator takes you to that floor
// should store if the requests have already been made
// multiple poeple might call the elevator so you need to set priorities
// the different methods that are needed moveup and movedown which are used in the move method
// a method to stop at a floor, get the current floor, and to check if it is moving up
//create a building class with a specified amount of floors 
// the building class will simualte where the request is made and to run the move method

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Elevator {
    private int currentFloor;
    private boolean isMovingUp;
    private Queue<Integer> requests; // Queue to store the floor requests
    private Set<Integer> requestedFloor; //make a set to check if you are already on the floor

    //constructor
    public Elevator(){
        this.currentFloor = 0;
        this.isMovingUp = true;
        this.requests = new LinkedList<>();// Initialize an empty queue for floor requests
        this.requestedFloor = new HashSet<>(); // initialize an empty set
    }

    public void addRequest(int floor){
        // first check for invalid inputs 
        if (floor < 0){
            System.out.println("Invlaid floor request");
            return;
        }
        if(!requestedFloor.contains(floor)){
            requests.add(floor);// Add the requested floor to the queue
            requestedFloor.add(floor);//also add requested floor to set
        }
        else{
            System.out.println("Request has already been made");
        }
        
    }
    //method for moving the elevator up
    public void moveUp(int targetFloor){
        System.out.println("Moving up from floor " + currentFloor + " to " + targetFloor );
        while (currentFloor < targetFloor) {
            currentFloor++; // increment floor until it reaches the targeted floor
            System.out.println("You have reached floor " + currentFloor);
        }
        isMovingUp = true; // its moving up so this is true
    }
    //method for moving elevator down
    public void moveDown(int targetFloor){
        System.out.println("Moving down from floor " + currentFloor + " to "+ targetFloor );
        while (currentFloor > targetFloor) {
            currentFloor--; // increment floor until it reaches the targeted floor
            System.out.println("You have reached floor " + currentFloor);
        }
        isMovingUp = false; // its moving up so this is true
    }

    public void stopAtFloor(int floor){
        System.out.println("Stopping at floor " + floor);
    }
    //getter for current floor
    public int currentFloor(){
        return currentFloor;
    }
    //getter for is moving up
    public boolean isMovingUp(){
        return isMovingUp;
    }
    // now we want to simulate the movement dependent on the request
    public void move(){
        while(!requests.isEmpty()){
            int nextFloor = requests.poll();//assigns the 
            //check if the floor is above or below the current and stop there
            if (nextFloor > currentFloor){
                moveUp(nextFloor);
            }
            else if (nextFloor < currentFloor){
                moveDown(nextFloor);
            }
            stopAtFloor(nextFloor);
        }
        requestedFloor.clear();
    }
}

class Building{
    private Elevator elevator;
    private int numberOfFloors;

    //constructor
    public Building(int numberOfFloors){
        this.numberOfFloors = numberOfFloors;
        this.elevator = new Elevator();
    }

    public void requestElevator(int floor){
        //check for invalid requests
        if (floor < 0 || floor >= numberOfFloors ){
            System.out.println("Invalid floor request");
            return;
        }
        System.out.println("Request for floor " + floor + " has been made");
        elevator.addRequest(floor);
    }
    public void runElevator(){
        elevator.move();
    }
}


//now we want to simulate the elevator
public class ElevatorSimulation {
    public static void main(String[] args) {
        Building building = new Building(5);

        building.requestElevator(1);
        building.requestElevator(2);
        building.requestElevator(2);
        building.requestElevator(6);
        building.requestElevator(-1);

        building.runElevator();
    }
}