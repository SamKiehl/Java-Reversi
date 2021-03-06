import java.util.ArrayList;
import java.util.Scanner;
public class Reversi{
    private Scanner input; // The game's input Scanner.
    private ArrayList<String> goodInputs; // Available valid spaces depending on the current player, should be updated every turn.
    private TextIO instance; // TextIO object used to test for good inputs.
    private char cPlayer; // Char variable for the current player. Always either [@] or [O].
    private TernaryBoard tBoard; // Ternary Board object to represent the game board.
    private boolean debug; // It provides useful information about each move in real time.
    private final String SPLASHIMAGE =  
    "****    *****   *   *   *****   ****      ***   *****\n" + 
    "*   *   *       *   *   *       *   *    *        *\n" + 
    "*   *   *       *   *   *       *   *   *         *\n" + 
    "****    ****     * *    ****    ****     ***      *\n" + 
    "*   *   *        * *    *       *   *       *     *\n" + 
    "*   *   *        * *    *       *   *      *      *\n" + 
    "*   *   *****     *     *****   *   *   ***     *****\n";
    
    public Reversi(){ // No argument Reversi constructor.
        input = new Scanner(System.in);
        goodInputs = new ArrayList<String>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                String addition = i + ", " + j;
                goodInputs.add(addition);
            }
        }
        instance = new TextIO(goodInputs, SPLASHIMAGE, "Thanks for playing Reversi!");
        
        tBoard = new TernaryBoard(8, 8);
        cPlayer = '@'; // Black is the current player at the start of the game.
        debug = false; // Debug is off by default. use the .debug() method to enable it.
    }

    public void debugOn(){this.debug = true;} // Toggles debug mode on for a Reversi instance.

    public void debugOff(){this.debug = false;} // Toggles debug mode off for a Reversi instance.

    public int checkHL(int x, int y){ // Flips tiles to the left of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = x;
        if(x == 0)
            return 0;
        boolean isBroken = false;
        
        for(int i = x - 1; i >= 0; i--){
            if(i < 0)
                break;
            if(this.tBoard.get(i, y) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(i, y) == cPlayer){
                bound = i;
                break;
            }
        }
                // Flip 
        if(!isBroken){
            for(int j = x; j > bound; j--){
                if(this.tBoard.get(j, y) == ' ')
                    isBroken = true;
                if(this.tBoard.get(j, y) == opponent && !isBroken){
                    this.tBoard.set(j, y, cPlayer);
                    flips++;
                }
            }
        }
        return flips;
    }

    public int checkHR(int x, int y){ // Flips tiles to the right of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = x;
        if(x == this.tBoard.cols() - 1)
            return 0;
        boolean isBroken = false;
        
        for(int i = x + 1; i < this.tBoard.cols(); i++){
            if(i > this.tBoard.cols() - 1)
                break;
            if(this.tBoard.get(i, y) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(i, y) == cPlayer){
                bound = i;
                break;
            }
        }
                // Flip 
        if(!isBroken){
            for(int j = x; j < bound; j++){
                if(this.tBoard.get(j, y) == ' ')
                    isBroken = true;
                if(this.tBoard.get(j, y) == opponent && !isBroken){
                    this.tBoard.set(j, y, cPlayer);
                    flips++;
                }
            }
        }
        return flips;
    }

    public int checkVU(int x, int y){ // Flips tiles above a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = y;
        if(y == 0)
            return 0;
        boolean isBroken = false;
        
        for(int i = y - 1; i >= 0; i--){
            if(i < 0)
                break;
            if(this.tBoard.get(x, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(x, i) == cPlayer){
                bound = i;
                break;
            }
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j > bound; j--){
                if(this.tBoard.get(x, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(x, j) == opponent && !isBroken){
                    this.tBoard.set(x, j, cPlayer);
                    flips++;
                }
            }
        }
        return flips;
    }

    public int checkVD(int x, int y){ // Flips tiles below a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = y;
        if(y == this.tBoard.rows() - 1)
            return 0;
        boolean isBroken = false;
        
        for(int i = y + 1; i < this.tBoard.rows(); i++){
            if(i > this.tBoard.rows() - 1)
                break;
            if(this.tBoard.get(x, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(x, i) == cPlayer){
                bound = i;
                break;
            }
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j < bound; j++){
                if(this.tBoard.get(x, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(x, j) == opponent && !isBroken){
                    this.tBoard.set(x, j, cPlayer);
                    flips++;
                }
            }
        }
        return flips;
    }

    public int checkDiagDR(int x, int y){ // Flips tiles below and to the right of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = y;
        if(x == this.tBoard.cols() - 1 || y == this.tBoard.rows() - 1)
            return 0;
        int nx = x + 1;
        int nnx = x;
        boolean isBroken = false;
        
        for(int i = y + 1; i < this.tBoard.rows(); i++){
            if(nx > this.tBoard.cols() - 1)
                break;
            if(this.tBoard.get(nx, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(nx, i) == cPlayer){
                bound = i;
                break;
            }
            nx++;
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j < bound; j++){
                if(this.tBoard.get(nnx, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(nnx, j) == opponent && !isBroken){
                    this.tBoard.set(nnx, j, cPlayer);
                    flips++;
                }
                nnx++;
            }
        }
        return flips;
    }

    public int checkDiagUL(int x, int y){ // Flips tiles above and to the left of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        int bound = y;
        if(x == 0 || y == 0)
            return 0;
        int nx = x - 1;
        int nnx = x;
        boolean isBroken = false;
        
        for(int i = y - 1; i >= 0; i--){
            if(nx < 0)
                break;
            if(this.tBoard.get(nx, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(nx, i) == cPlayer){
                bound = i;
                break;
            }
            nx--;
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j > bound; j--){
                if(this.tBoard.get(nnx, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(nnx, j) == opponent && !isBroken){
                    this.tBoard.set(nnx, j, cPlayer);
                    flips++;
                }
                nnx--;
            }
        }
        return flips;
    }

    public int checkDiagDL(int x, int y){ // Flips tiles below and to the left of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();
        
        int bound = y;
        int nx;
        if(x == 0 || y == this.tBoard.rows() - 1)
            return 0;
        else
            nx = x - 1;
        int nnx = x;
        boolean isBroken = false;
        
        for(int i = y + 1; i < this.tBoard.rows() - 1; i++){
            if(nx < 0 )
                break;
            if(this.tBoard.get(nx, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(nx, i) == cPlayer){
                bound = i;
                break;
            }
            nx--;
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j < bound; j++){
                if(this.tBoard.get(nnx, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(nnx, j) == opponent && !isBroken){
                    this.tBoard.set(nnx, j, cPlayer);
                    flips++;
                }
                nnx--;
            }
        }
        return flips;
    }

    public int checkDiagUR(int x, int y){ // Flips tiles above and to the right of a placed tile.
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
        int bound = y;
        int nx;
        if(x == this.tBoard.cols() - 1 || y == 0)
            return 0;
        else
            nx = x + 1;
        int nnx = x;
        boolean isBroken = false;
        
        for(int i = y - 1; i >= 0; i--){
            if(nx > this.tBoard.cols() - 1)
                break;
            if(this.tBoard.get(nx, i) == ' '){
                isBroken = true;
                break;
            }
            if(this.tBoard.get(nx, i) == cPlayer){
                bound = i;
                break;
            }
            nx++;
        }
                // Flip 
        if(!isBroken){
            for(int j = y; j > bound; j--){
                if(this.tBoard.get(nnx, j) == ' ')
                    isBroken = true;
                if(this.tBoard.get(nnx, j) == opponent && !isBroken){
                    this.tBoard.set(nnx, j, cPlayer);
                    flips++;
                }
                nnx++;
            }
        }
        return flips;
    }

    public int placePiece(int x, int y, boolean c){ // Place a piece at coordinates x, y. Flips all pieces that are affected.
            this.tBoard.set(x, y, cPlayer);
            if(!c) // c is a checking variable for isValidSpace(). PlacePiece not debug or output game info when checking.
                System.out.println("Set space (" + x + ", " + y + ") to: " + "[" + cPlayer+ "]");


            // HORIZONTAL: 
            int HLTotal = this.checkHL(x, y);
            int HRTotal = this.checkHR(x, y);
            if(this.debug && !c){
                System.out.println("Checking...\n Horizontal: ");
                System.out.println("  HL:\t" + HLTotal);
                System.out.println("  HR:\t" + HRTotal);
            }
            // VERTICAL: 
            int VUTotal = this.checkVU(x, y);
            int VDTotal = this.checkVD(x, y);
            if(this.debug && !c){
                System.out.println(" Vertical: ");
                System.out.println( "  VU:\t" + VUTotal);
                System.out.println( "  VD:\t" + VDTotal);
            }

            
            // DIAGONAL ( \ ): 
            int DiagDRTotal = this.checkDiagDR(x, y);
            int DiagULTotal = this.checkDiagUL(x, y);
            if(this.debug && !c){
                System.out.println(" Diagonal ( \\ ): ");
                System.out.println( "  DiagDR:\t" + DiagDRTotal);
                System.out.println( "  DiagUL:\t" + DiagULTotal);
            }
            
            // DIAGONAL ( / ): 
            int DiagDLTotal = this.checkDiagDL(x, y);
            int DiagURTotal = this.checkDiagUR(x, y);
            if(this.debug && !c){
                System.out.println(" Diagonal ( / ): ");
                System.out.println("  DiagDL:\t" + DiagDLTotal);
                System.out.println("  DiagUR:\t" + DiagURTotal);
            }
            
            int totalFlipped = HLTotal + HRTotal + VUTotal + VDTotal + DiagDRTotal + DiagULTotal + DiagDLTotal + DiagURTotal;
            if(!c)
                System.out.println("Tiles " + "[" + this.getOpponent() + "] flipped to " + "[" + this.cPlayer + "]: " + totalFlipped + "\n");

            return totalFlipped;
    }

    public void checkAndPlacePiece(int x, int y){ // Places piece if slot is available and valid.
        if(this.isValidSpace(x, y))
            this.placePiece(x, y, false);
    }

    public static Reversi copy(Reversi other){ // Returns a copy of a Reversi object.
        Reversi output = new Reversi();
        output.cPlayer = other.cPlayer;
        output.tBoard = TernaryBoard.copy(other.tBoard);
        output.debug = false; // output.debug is false by default.
        return output;
    }

    public boolean isValidSpace(int x, int y){ // Checks whether a move will cause other tiles to flip.
        if(x < 0 || y < 0 || x > 7 || y > 7)
            return false;
        if(this.flips(x, y) > 0 && this.tBoard.get(x, y) == ' ')
            return true;
        return false;
    }

    public int flips(int x, int y){
        Reversi temp = copy(this);
        int totalFlipped = temp.placePiece(x, y, true);
        temp = null;
        return totalFlipped;
    }

    public void updateGoodInputs(){ // Updates goodInputs ArrayList after each turn.
        ArrayList<String> nArr = new ArrayList<String>();
        
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                if(this.isValidSpace(i, j)){
                    nArr.add(i + ", " + j);
                }
            }
        }
        this.goodInputs = nArr;
        this.instance.setGoodInputs(nArr);
        nArr = null;
    }

    

    public char getOpponent(){ // Returns current opponent.
        if(this.cPlayer == '@')
            return 'O';
        return '@';
    }

    public void changePlayer(){ // Switches the cPlayer to the opposite playyer. (if [@], then [O] and vice versa)
        if(cPlayer == '@')
            this.cPlayer = 'O';
        else 
            this.cPlayer = '@';
    }

    public void play(){ // Contains all code required to run in a game of Reversi.
        this.tBoard.set(3, 3, 'O');
        this.tBoard.set(4, 3, '@');
        this.tBoard.set(3, 4, '@');
        this.tBoard.set(4, 4, 'O');
        System.out.println(SPLASHIMAGE);
        this.updateGoodInputs();
        String in = "start";
        String players = "start";
        int x = -1;
        int y = -1;

        System.out.println("How many players? (enter \"1\", or \"2\") (Type \"quit\" to quit):");
        players = input.nextLine();
        while(!players.equals("1") && !players.equals("2") && !players.equals("quit")){
            System.out.println("Please enter \"1\" or \"2\" (Type \"quit\" to quit):");
            players = input.nextLine();
        }
        if(players.equals("1")){
            System.out.println(this);
            while(!in.equals("quit") && !this.tBoard.isFull()){
                this.updateGoodInputs();
                String choices = TextIO.oxfordComma(this.goodInputs);
                if(goodInputs.size() == 0){
                    System.out.println(cPlayer + " has no possible moves... switching player to " + this.getOpponent() + "...");
                    this.changePlayer();
                    this.updateGoodInputs();
                    if(this.goodInputs.size() == 0)
                        break;
                    choices = TextIO.oxfordComma(this.goodInputs);
                }
                if(cPlayer == '@'){
                    System.out.println("It is " + cPlayer + "\'s turn!" + "\n" + "Please Enter your choice (as x, y) (Type \"quit\" to quit): ");
                    System.out.println("Possible choices: " + choices);
                    while(!instance.isGoodInput(in)){
                            in = input.nextLine();
                            if(in.equals("quit"))
                                break;
                        if(!instance.isGoodInput(in))
                            System.out.println("Make sure that your choice is valid and formatted correctly (x, y) and enter it again: (Type \"quit\" to quit)");
                    }
                    if(!in.equals("quit")){
                        int[] coords = getCoords(in);
                        x = coords[0];
                        y = coords[1];
                        System.out.println("Player: " + cPlayer + " chose: " + in);
                        this.checkAndPlacePiece(x, y);
                        System.out.println(this);
                        if(this.tBoard.isFull())
                            break;
                        this.changePlayer();
                        this.updateGoodInputs();
                        
                    }
                }
                else if(this.cPlayer == 'O'){
                    System.out.println("It is " + cPlayer + "\'s turn!" + "\n" + "Please Enter your choice (as x, y) (Type \"quit\" to quit): ");
                    System.out.println("Possible choices: " + choices);
                    if(goodInputs.size() == 0){
                        break;
                    }
                    int max = 0;
                    int[] best = {-1, -1};
                    for(String m : goodInputs){
                        int[] move = getCoords(m);
                        if(this.flips(move[0], move[1]) > max){
                            max = this.flips(move[0], move[1]);
                            best = move;
                        }
                    }
                    System.out.println("Player: " + cPlayer + " chose: " + best[0] + ", " + best[1]);
                    this.checkAndPlacePiece(best[0], best[1]);
                    System.out.println(this);
                    this.changePlayer();
                    this.updateGoodInputs();
                    //break;
                    
                }
            }
            int cb = this.tBoard.countBlack();
            int cw = this.tBoard.countWhite();
            if(cb == cw)
                System.out.println("Tie!");
            else if(cb > cw)
                System.out.println("[@] Wins! ( @ = " + cb + " : O = " + cw + " )");
            else if(cw > cb)
                System.out.println("[O] Wins! ( @ = " + cb + " : O = " + cw + " )");
        }
        else if(players.equals("2")){
            System.out.println(this);
            while(!in.equals("quit") && !this.tBoard.isFull()){
                String choices = TextIO.oxfordComma(this.goodInputs);
                if(goodInputs.size() == 0){
                    System.out.println(cPlayer + " has no possible moves... switching player to " + this.getOpponent() + "...");
                    this.changePlayer();
                    this.updateGoodInputs();
                    choices = TextIO.oxfordComma(this.goodInputs);
                }
                System.out.println("It is " + cPlayer + "\'s turn!" + "\n" + "Please Enter your choice (as x, y) (Type \"quit\" to quit): ");
                System.out.println("Possible choices: " + choices);
                while(!instance.isGoodInput(in)){
                        in = input.nextLine();
                        if(in.equals("quit"))
                            break;
                    if(!instance.isGoodInput(in))
                        System.out.println("Make sure that your choice is valid and formatted correctly (x, y) and enter it again: (Type \"quit\" to quit)");
                }
                if(!in.equals("quit")){
                    int[] coords = getCoords(in);
                    x = coords[0];
                    y = coords[1];
                    System.out.println("Player: " + cPlayer + " chose: " + in);
                    this.checkAndPlacePiece(x, y);
                    System.out.println(this);
                    if(this.tBoard.isFull())
                        break;
                    this.changePlayer();
                    this.updateGoodInputs();
                }
            }
            int cb = this.tBoard.countBlack();
            int cw = this.tBoard.countWhite();
            if(cb == cw)
                System.out.println("Tie!");
            else if(cb > cw)
                System.out.println("[@] Wins! ( @ = " + cb + " : O = " + cw + " )");
            else if(cw > cb)
                System.out.println("[O] Wins! ( @ = " + cb + " : O = " + cw + " )");
        }
    }

    public static int[] getCoords(String in){ // Accepts a String in the format: x, y where x and y are integers anc returns an array of length 2 of x at index 0 and y at index 1.
        int[] output = new int[2];
        output[0] = Integer.parseInt(in.substring(0, 1));
        output[1] = Integer.parseInt(in.substring(3, 4));
        return output;
    }

    public String toString(){ // Prints the desired game's TernaryBoard object and the current game's score.
        String output = "\n  [@]: " + this.tBoard.countBlack() + "\t[O]: "+ this.tBoard.countWhite() + "\n";
        output += this.tBoard.toString();
        return output;
    }

    public static void main(String[] args){
        Reversi r = new Reversi();
        /*
        r.debugOn();
        r.debugOff();
        */
        r.play();
    }
}
