package solver;

public class Solver {

    //board attribute
    private int[][] board;

    //initialize the board
    public Solver(int[][] board){
        this.board = board;
    }

    //find empty cell
    private int[] find_empty_cell(int[][] board){
        int[] pos = new int[2];
        for(int i=0;i< board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==0){
                    pos [0] = i;
                    pos[1] = j;
                    return pos;
            }
            }
        }
        return null;
    }

    //valid
    private boolean valid(int num,int[] pos,int[][] board){

        //check for row
        for(int i=0;i< board.length;i++){
            if(board[pos[0]][i] == num && pos[1] != i)
                return false;
        }

        //check for column
        for(int i=0;i< board[pos[1]].length;i++){
            if(board[i][pos[1]] == num && pos[0] != i)
                return false;
        }


        //check for each sub grid
        int x_of_box = pos[0]/3;
        int y_of_box = pos[1]/3;

        for(int i =x_of_box*3; i < (x_of_box*3)+3;i++){
            for(int j =x_of_box*3; j < (x_of_box*3)+3;j++){
                int[] currentPos = new int[2];
                currentPos[0] = i;
                currentPos[1] = j;
                if(board[i][j]==num && pos == currentPos)
                    return false;
            }
        }

        return true;
    }

    //try solve board recursively
    public boolean solution(){
        int[] empty_cell = find_empty_cell(this.board);
        if (empty_cell == null){
            //board is solved
            return true;
        }


        //try inserting values from 1-9 into that cell
        for (int i=1;i<10;i++){
            if (valid(i,empty_cell,this.board)){
                board[empty_cell[0]][empty_cell[1]] = i;
                if(solution()){
                    return true;
                }
                this.board[empty_cell[0]][empty_cell[1]] = 0;
            }
        }

        return false;
    }

    public void print_board() {
        for (int i = 0; i < this.board.length; i++) {
            if(i%3==0)
                System.out.println("---------------------------");
            for(int j=0;j<this.board[i].length;j++){
                if(j%3 == 0)
                    System.out.print(" | ");
                if(j == 8){
                    System.out.println(this.board[i][j]);
                }else{
                   System.out.print(this.board[i][j]+" ");
                }

            }
        }
    }
}
