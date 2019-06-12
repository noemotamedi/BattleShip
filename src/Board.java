import java.util.Random;
import java.util.Scanner;

public class Board {


    boolean isPlayer;
    int[] shipsLengths = {2,3,3,4,5};
    int[][] shipLog = this.logShips();
    int[][] grid = new int[10][10];

    public Board(boolean isPlayer) {

        this.isPlayer = isPlayer;
        this.grid = createBoard();

    }



    public int[][] createBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
        return grid;
    }

    public int[][] logShips() {
        int[][] shipLog = new int[shipsLengths.length][5];
        //{xcoordinate, ycoordinate, length, rotated, times hit}

        for (int i = 0; i < shipsLengths.length; i++) {
            shipLog[i][0] = 0;
            shipLog[i][1] = 0;
            shipLog[i][2] = 0;
            shipLog[i][3] = 0;
            shipLog[i][4] = 0;
        }
        return shipLog;
    }

    public void placeShips(int xcoordinate, int ycoordinate, int shipLength, boolean rotated) {

        if (rotated) {
            for (int i = xcoordinate; i < xcoordinate + shipLength; i++) {
                grid[i][ycoordinate] = 1;
            }
        } else {
            for (int j = ycoordinate; j < ycoordinate + shipLength; j++) {
                grid[xcoordinate][j] = 1;
            }
        }
    }

    public boolean testBeforePlace(int xcoordinate, int ycoordinate, int shipLength, boolean rotated){

        if (rotated) {
            for (int i = xcoordinate; i < xcoordinate + shipLength; i++) {
                if((xcoordinate+shipLength>this.grid.length)||(ycoordinate>this.grid[0].length)){return false;}
                if(grid[i][ycoordinate]== 1){ return false; }
            }
        } else {
            for (int j = ycoordinate; j < ycoordinate + shipLength; j++) {
                if((xcoordinate>this.grid.length)||(ycoordinate+shipLength>this.grid[0].length)){return false;}
                if(grid[xcoordinate][j]== 1){ return false; }
            }
        }
        return true;
    }

    public void displayBoard(){

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){

                if(grid[i][j]==0) {System.out.print("~");}
                if(grid[i][j]==1) {if(this.isPlayer){System.out.print("#");}else{System.out.print("~");};}
                if(grid[i][j]==2) {System.out.print("-");}
                if(grid[i][j]==3) {System.out.print("@");}
                if(grid[i][j]==4) {System.out.print("X");}

                System.out.print("  ");
            }
            System.out.print(System.getProperty("line.separator"));
        }
    }

    public void getShot(){

        Scanner sc = new Scanner(System.in);
        int xcoordinate;
        int ycoordinate;

        if(!this.isPlayer){
            System.out.println("enter a number");
            xcoordinate = sc.nextInt();
            System.out.println(xcoordinate);

            System.out.println("enter a number");
            ycoordinate = sc.nextInt();
            System.out.println(ycoordinate);

            if(xcoordinate>9||ycoordinate>9){
                System.out.println("Out of bounds!");
                this.getShot();
                return;
            }

        }else{
            xcoordinate = new Random().nextInt(10);
            ycoordinate = new Random().nextInt(10);
        }


        if(this.grid[xcoordinate][ycoordinate]==0){
            this.grid[xcoordinate][ycoordinate]=2;
            System.out.println('\n');
            System.out.println("Missed!");
            System.out.println('\n');
            return;
        }
        if(this.grid[xcoordinate][ycoordinate]==1){
            this.grid[xcoordinate][ycoordinate]=3;
            System.out.println('\n');
            System.out.println("Hit!");
            System.out.println('\n');
            this.shotBoat(xcoordinate,ycoordinate);
            return;
        }
        if(this.grid[xcoordinate][ycoordinate]==2||this.grid[xcoordinate][ycoordinate]==3){
            if(!this.isPlayer){
                System.out.println('\n');
                System.out.println('\n'+"Already shot there!");
                System.out.println('\n');}
            this.getShot();
            return;
        }
    }

    public void shotBoat(int xShot,int yShot){
        //{xcoordinate, ycoordinate, length, rotated, times hit}
        for(int i=0;i<shipLog.length;i++){
            if(shipLog[i][3] == 0) {
                for (int j = shipLog[i][1]; j < shipLog[i][1]+shipLog[i][2]; j++) {
                    if(j==yShot && shipLog[i][0]==xShot){
                        shipLog[i][4]++;
                        sinkBoat(i);
                        return;
                    }
                }
            }else{
                for (int j = shipLog[i][0]; j < shipLog[i][0]+shipLog[i][2]; j++) {
                    if(shipLog[i][1]==yShot && j==xShot) {
                        shipLog[i][4]++;
                        sinkBoat(i);
                        return;
                    }
                }
            }
        }
    }

    public void sinkBoat(int shipNumber){
        if(shipLog[shipNumber][4] == shipLog[shipNumber][2]){
            if(shipLog[shipNumber][3]== 1) {
                for (int i = shipLog[shipNumber][0]; i < shipLog[shipNumber][0]+shipLog[shipNumber][2]; i++) {
                    this.grid[i][shipLog[shipNumber][1]] = 4;
                }
            }else{
                for (int i = shipLog[shipNumber][1]; i < shipLog[shipNumber][1]+shipLog[shipNumber][2]; i++) {
                    this.grid[shipLog[shipNumber][0]][i] = 4;
                }
            }
            System.out.println('\n');
            System.out.println('\n');
            System.out.println("Ship has sunk!");
            System.out.println('\n');
            System.out.println('\n');
        }
    }

    public boolean gameEnd(int shipToTest){
        if(shipToTest==shipLog.length){ /* end condition */
            return true;
        }
        if(shipLog[shipToTest][4]==shipLog[shipToTest][2]){
                return gameEnd(shipToTest+1);}
                return false;
    }
}