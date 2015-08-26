package prob.count;

class SquareRotate {

static int ROW = 4;
static int COL = 4;

private static Integer[][] create() {
    Integer A[][] = new Integer[ROW][COL];
    int count = 0;
    for(int i=0; i<ROW; i++)
        for(int j=0; j<COL; j++)
            A[i][j] = count++;
    return A;
}

private static void print(Integer A[][], int r, int c) {
    System.out.println(A[r][c]);
}

private static void print(Integer A[][]) {
    for(int i=0; i<ROW; i++) {
        for(int j=0; j<COL; j++)
            System.out.print(A[i][j] + " ");
        System.out.println("");
    }
}

public static void printClockWise(Integer A[][]) {
    int x=0, y=0;
    do {
        if ( y==COL-1 && x<ROW-1)
            print (A, x++, y);
        else if ( y==0 && x>0 )
            print (A, x--, y);
        else if ( x==ROW-1 && y<=COL-1 )
            print(A, x, y--);
        else
            print(A, x, y++);
    } while ( !(x==0 && y==0) );
}

/**
 * http://stackoverflow.com/questions/42519/how-do-you-rotate-a-two-dimensional-array
 **/

public static Integer[][] rotate(Integer A[][]) {
    Integer T[][] = new Integer[COL][ROW];
    for(int i=0; i<COL; i++)
       for(int j=0; j<ROW; j++)
            T[i][j] = A[COL-j][i];
    return T;
}

public static void main (String[] args) {

    System.out.println("hi...");

    Integer A[][] = create();
    printClockWise(A);


}

}