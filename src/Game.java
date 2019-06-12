public class Game {

    // !!! 0 = sea, 1 = ship, 2 = missed, 3 = hit, 4 = sunk !!!


    public static void main(String[] args){


        //Computer Board
        Opponent computer1= new Opponent();
        for(int i=0;i<computer1.computerBoard.shipsLengths.length;i++) {
            computer1.makeComputerBoard(computer1.computerBoard.shipsLengths[i], i);
        }


        //Player Board
        Player p1 = new Player();
        for(int i=0;i<computer1.computerBoard.shipsLengths.length;i++) {
            p1.makePlayerBoard(computer1.computerBoard.shipsLengths[i], i);
            p1.p1board.displayBoard();
        }

        System.out.println("Your Board:");
        p1.p1board.displayBoard();
        System.out.println('\n');
        System.out.println('\n');
        System.out.println('\n');
        System.out.println("Game has Started...");

        while(true){
            //Game Starts

            //Player Shoots
            System.out.println("Next Turn:");
            System.out.println('\n');
            System.out.println('\n');
            computer1.computerBoard.getShot();

            System.out.println('\n');
            System.out.println("Enemy Board:");
            computer1.computerBoard.displayBoard();

            if(computer1.computerBoard.gameEnd(0)){
                System.out.println("You Won!!");
                return;
            }

            System.out.println('\n');
            System.out.println('\n');
            System.out.println('\n');

            //Computer Shoots
            p1.p1board.getShot();
            p1.p1board.getShot();
            System.out.println("Your Board:");
            p1.p1board.displayBoard();

            System.out.println('\n');
            System.out.println('\n');
            System.out.println('\n');

            if(p1.p1board.gameEnd(0)) {
                System.out.println("You Lost!!");
                return;
            }
        }
    }
}