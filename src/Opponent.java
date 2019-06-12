import java.util.Random;

public class Opponent {

    Board computerBoard= new Board(false);

    public void makeComputerBoard(int shipLength, int iteration){

        int xcoordinate= new Random().nextInt(10);
        int ycoordinate= new Random().nextInt(10);
        boolean rotated= Math.random() < 0.5;


        if(this.computerBoard.testBeforePlace(xcoordinate,ycoordinate,shipLength,rotated)) {

            this.computerBoard.placeShips(xcoordinate, ycoordinate, shipLength, rotated);
            this.computerBoard.shipLog[iteration][0] = xcoordinate;
            this.computerBoard.shipLog[iteration][1] = ycoordinate;
            this.computerBoard.shipLog[iteration][2] = shipLength;
            if(rotated){this.computerBoard.shipLog[iteration][3] = 1;}else{this.computerBoard.shipLog[iteration][3] = 0;};

        }else{
            this.makeComputerBoard(shipLength, iteration);
        }

    }
}