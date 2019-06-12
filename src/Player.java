import java.util.Scanner;

public class Player {

    Board p1board = new Board(true);


    public void makePlayerBoard(int shipLength, int iteration){
        Scanner sc = new Scanner(System.in);

        System.out.println("enter a number");
        int xcoordinate = sc.nextInt();
        System.out.println(xcoordinate);

        System.out.println("enter a number");
        int ycoordinate = sc.nextInt();
        System.out.println(ycoordinate);

        System.out.println("Rotate");
        boolean rotated = sc.nextBoolean();
        System.out.println(rotated);


        if(this.p1board.testBeforePlace(xcoordinate,ycoordinate,shipLength,rotated)) {

            this.p1board.placeShips(xcoordinate, ycoordinate, shipLength, rotated);
            this.p1board.shipLog[iteration][0] = xcoordinate;
            this.p1board.shipLog[iteration][1] = ycoordinate;
            this.p1board.shipLog[iteration][2] = shipLength;
            if(rotated){this.p1board.shipLog[iteration][3] = 1;}else{this.p1board.shipLog[iteration][3] = 0;};

        }else{
            System.out.println("Ship overlap error!");
            this.makePlayerBoard(shipLength, iteration);
        }
    }
}