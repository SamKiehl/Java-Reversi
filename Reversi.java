import java.util.ArrayList;
import java.util.Scanner; // TODO update description comments, formatting, AI.
public class Reversi{
    private final Scanner input;
    private ArrayList<String> goodInputs;
    private TextIO instance;
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
    

    public Reversi(){
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
        debug = false; // Debug is off by default. use the .default() method to enable it.
    }

    public void debug(){this.debug = true;}

    public int checkHL(int x, int y){
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
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

    public int checkHR(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
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

    public int checkVU(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being reworked 3 times on a thursday.
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

    public int checkVD(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
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

    public int checkDiagDR(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
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

    public int checkDiagUL(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();

        // Check to flip diagonaly Upwards and to the right. Working after being remade 3 times on a thursday.
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

    public int checkDiagDL(int x, int y){ // Done
        int flips = 0;
        char opponent = this.getOpponent();
        

        // Check to flip diagonaly downwards and to the left. Working
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

    public int checkDiagUR(int x, int y){ // Done
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

    // Place a piece at coordinates x, y. Flips all pieces that are affected.
    public int placePiece(int x, int y, boolean c){
        // TODO if(valid space)
            this.tBoard.set(x, y, cPlayer);
            if(!c)
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

    public void checkAndPlacePiece(int x, int y){
        if(this.isValidSpace(x, y))
            this.placePiece(x, y, false);
    }

    public static Reversi copy(Reversi other){
        Reversi output = new Reversi();
        output.cPlayer = other.cPlayer;
        output.tBoard = TernaryBoard.copy(other.tBoard);
        output.debug = false; // output.debug is false by default.
        return output;
    }

    public boolean isValidSpace(int x, int y){
        if(x < 0 || y < 0 || x > 7 || y > 7)
            return false;
        Reversi temp = copy(this);
        int totalFlipped = temp.placePiece(x, y, true);
        if(totalFlipped > 0 && this.tBoard.get(x, y) == ' ')
            return true;
        return false;
    }

    public void updateGoodInputs(){
        ArrayList<String> nArr = new ArrayList<String>();;
        
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                if(this.isValidSpace(i, j)){
                    nArr.add(i + ", " + j);
                }
            }
        }
        this.goodInputs = nArr;
        this.instance.setGoodInputs(nArr);
    }

    

    public char getOpponent(){
        if(this.cPlayer == '@')
            return 'O';
        return '@';
    }

    public void changePlayer(){
        if(cPlayer == '@')
            this.cPlayer = 'O';
        else 
            this.cPlayer = '@';
    }

    public void play(){
        this.tBoard.set(3, 3, 'O');
        this.tBoard.set(4, 3, '@');
        this.tBoard.set(3, 4, '@');
        this.tBoard.set(4, 4, 'O');
        System.out.println(SPLASHIMAGE);
        this.updateGoodInputs();
        String in = "start";
        int x = -1;
        int y = -1;

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
                System.out.println(this);
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
            System.out.println("[@] Wins! ( @ = " + cb + " : O = " + cw + ")");
        else if(cw > cb)
            System.out.println("[O] Wins! ( @ = " + cb + " : O = " + cw + ")");
    }

    public static int[] getCoords(String in){
        int[] output = new int[2];
        output[0] = Integer.parseInt(in.substring(0, 1));
        output[1] = Integer.parseInt(in.substring(3, 4));
        return output;
    }

    public String toString(){return this.tBoard.toString();}

    public static void main(String[] args){
        Reversi r = new Reversi();
        //r.debug();
        r.play();
        
    }
}