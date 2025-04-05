int maxSubArray(int* nums, int numsSize) {
    int result=INT_MIN;
    int sum=0;
    
    for(int i=0;i<numsSize;i++){
        sum+=nums[i];
        if(sum>result)result=sum;
        if(sum<=0){
            sum=0;
        }
       
    }
   
    return result;
}