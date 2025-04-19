bool Find(int target, int** array, int arrayRowLen, int* arrayColLen ) {
    // write code here
    int row=0;
    int col=*arrayColLen-1;
    while (row<arrayRowLen&&col>=0) {
        if (target==array[row][col]) {
            return true;
        }
        else if(target<array[row][col]) {
            col--;
        }
        else {
            row++;
        }
         
    }
    return false;
}