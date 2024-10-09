package Elevator_question;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Iterator;

public class Elevator {
    private int currentFloor;
    private boolean isMovingUp;
    private Queue<Request> requests; // Queue to store the floor requests
    private Set<Integer> requestedFloor; //make a set to check if you are already on the floor

    //constructor
    public Elevator(){
        this.currentFloor = 1;
        this.isMovingUp = true;
        this.requests = new LinkedList<>();// Initialize an empty queue for floor requests
        this.requestedFloor = new HashSet<>(); // initialize an empty set
    }

    public void addRequest(int sourceFloor, int destinationFloor){
        requests.add(new Request(sourceFloor, destinationFloor));
        System.out.println("Request is made from floor " + sourceFloor + " to floor " + destinationFloor);
    }

    //method for moving the elevator up
    public void moveUp(int destinationFloor){
        System.out.println("Going up from floor " + currentFloor + " to " + destinationFloor );
        while (currentFloor < destinationFloor) {
            currentFloor++; // increment floor until it reaches the targeted floor
            checkStops(currentFloor, destinationFloor, true);
            
        }
        System.out.println("Stopped on floor " + currentFloor);
        isMovingUp = true; // its moving up so this is true
    }

    //method for moving elevator down
    public void moveDown(int targetFloor){
        System.out.println("Going down from floor " + currentFloor + " to "+ targetFloor );
        while (currentFloor > targetFloor) {
            currentFloor--; // increment floor until it reaches the targeted floor
            checkStops(currentFloor, targetFloor, false);
            
        }
        System.out.println("Stopped at floor " + currentFloor);
        isMovingUp = false; // its moving up so this is true
    }

    //getter for current floor
    public int currentFloor(){
        return currentFloor;
    }

    //getter for is moving up
    public boolean isMovingUp(){
        return isMovingUp;
    }
    public void stopAtFloor(int floor){
        System.out.println("Stopped at floor " + floor);
        requestedFloor.add(floor);
    }

    private void checkStops(int current, int target, boolean movingup){
        Iterator<Request> iterator = requests.iterator();
        while (iterator.hasNext()){
            Request request = iterator.next();
            int requestSource = request.getSourceFloor();
            int requestDestination = request.getDestinationFloor();

            if(!requestedFloor.contains(requestSource) && !requestedFloor.contains(requestDestination)){
                if (movingup){
                    if(requestSource >= current && requestSource <= target && requestDestination > requestSource){
                        System.out.println("Going up to floor  " + requestDestination);
                        iterator.remove();
                        stopAtFloor(requestSource);
                        requestedFloor.add(requestSource); 
                    }
                }else{
                    if(requestSource <= current && requestSource >= target && requestDestination < requestSource){
                        System.out.println("Stopping at floor " + requestSource);
                        iterator.remove();
                        stopAtFloor(requestSource);
                        requestedFloor.add(requestSource); 
                    }
                }
            }
        }
    }
    

    // now we want to simulate the movement dependent on the request
    public void move(){
        while(!requests.isEmpty()){
            Request nextRequest = requests.poll();
            int sourceFloor = nextRequest.getSourceFloor();
            int destinationFloor = nextRequest.getDestinationFloor();
            //check if the floor is above or below the current and stop there

            if (sourceFloor != currentFloor && !requestedFloor.contains(sourceFloor)){
                if(sourceFloor > currentFloor){
                    moveUp(sourceFloor);
                }
                else if (sourceFloor < currentFloor){
                    moveDown(sourceFloor);
                }
            }
            if (destinationFloor != currentFloor && !requestedFloor.contains(destinationFloor)){
                if (destinationFloor > currentFloor){
                    moveUp(destinationFloor);
                }
                else if (destinationFloor < currentFloor){
                    moveDown(destinationFloor);
                }
            }
        }
        requestedFloor.clear();
    }
}