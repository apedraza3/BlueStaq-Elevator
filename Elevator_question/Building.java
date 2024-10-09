package Elevator_question;

public class Building {
    private Elevator elevator;
    private int numberOfFloors;

    //constructor
    public Building(int numberOfFloors){
        this.numberOfFloors = numberOfFloors;
        this.elevator = new Elevator();
    }

    public void requestElevator(int sourceFloor, int destinationFloor){
        //check for invalid requests
        if (sourceFloor < 1 || sourceFloor > numberOfFloors || destinationFloor < 1 || destinationFloor > numberOfFloors ){
            System.out.println("You are not able to move from floor " + sourceFloor + " to floor " + destinationFloor);
            return;
        }
        //System.out.println("Request for floor " + floor + " has been made");
        elevator.addRequest(sourceFloor, destinationFloor);
    }
    public void runElevator(){
        elevator.move();
    }
}
