bool Find(int target, int** array, int arrayRowLen, int* arrayColLen ) {
    // write code here
    int i=0;
    while (i<arrayRowLen) {
    int start=0;
    int end=*arrayColLen-1;
    
    while (start<=end) {
        int mid=(end-start)/2+start;
        if (array[i][mid]==target) {
            return true;
        }
        else if(array[i][mid]<target) {
            start=mid+1;
        }
        else {
            end=mid-1;
        }
    }
    i++;
    }
    return false;
}