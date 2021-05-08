public class TernaryBoard {
    private char[][] board;
    private int rows;
    private int cols;
    
    // Max Board size is 10 x 10
    public TernaryBoard(int rows, int cols){
        if(rows > 10)
            this.rows = 10;
        else
            this.rows = rows;
        
        if(cols > 10)
            this.cols = 10;
        else
            this.cols = cols;

        board = new char[this.rows][this.cols];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                this.board[i][j] = ' ';
            }
        }
    }

    public int filledIn(){
        int total = 0;
        for(char[] i : this.board){
            for(char j : i){
                if(j != ' ')
                    total++;
            }
        }
        return total;
    }

    public boolean isFull(){
        if(this.filledIn() == (this.rows * this.cols))
            return true;
        return false;
    }

    public int countBlack(){
        int output = 0;
        for(char[] i: this.board)
            for(char j: i)
                if(j == '@')
                    output++;
        return output;
    }

    public int countWhite(){
        int output = 0;
        for(char[] i: this.board)
            for(char j: i)
                if(j == 'O')
                    output++;
        return output;
    }

    public static TernaryBoard copy(TernaryBoard other){
        int rows = other.rows();
        int cols = other.cols();
        TernaryBoard output = new TernaryBoard(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                output.board[i][j] = other.board[i][j];
            }
        }
        return output;
    }

    public String toString(){
        String output = "";
        String borderH = "  ";
        String numbers = "    ";
        for(int i = 0; i < this.cols; i++){
            numbers += i + " ";
        }
        numbers += "\n";
        for(int i = 0; i < this.cols + 2; i++){
            borderH += "# ";
        }
        borderH += "\n";
        output += numbers;
        output += borderH;

        for(int i = 0; i < this.rows; i++){
            output += i + " # ";
            for(int j = 0; j < this.cols; j++){
                output += this.board[i][j];
                if(j < this.cols - 1)
                    output += " ";
            }
            output += " # " + i +"\n";
        }
        output += borderH;
        output += numbers;
        return output;
    }

    public void set(int x, int y, char item){this.board[y][x] = item;}

    public char get(int x, int y){return this.board[y][x];}

    public int rows(){return this.board.length;}

    public int cols(){return this.board[0].length;}
}
