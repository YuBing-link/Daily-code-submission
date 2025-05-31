int pivotsort(int*a,int low,int high){
    int pivot=a[low];
    while (low<high) {
        if (low<high&&a[high]>=pivot) 
            high--;
            a[low]=a[high];
        
      if (low<high&&a[low]<=pivot) 
            low++;
            a[high]=a[low];
        
    }
    a[low]=pivot;
    return low;
}
void quicksort(int*a,int low,int high){
    
    if (low<high) {
       int pivot=pivotsort(a,low,high);
        quicksort(a,low,pivot-1);
        quicksort(a,pivot+1,high);
    }

}