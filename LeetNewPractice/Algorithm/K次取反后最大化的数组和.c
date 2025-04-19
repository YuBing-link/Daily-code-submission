int compare(const void*a,const void*b){
    return *(int*)a-*(int*)b;
}
int largestSumAfterKNegations(int* nums, int numsSize, int k) {
    qsort(nums,numsSize,sizeof(int),compare);
    int a=0;
    for(int i=0;i<numsSize;i++){
        if(nums[i]>=0)
        {   
            a=i;
            if(i-1<0)break;
            if(abs(nums[i-1])<nums[i]){
                a=i-1;
            }
            break;
        }
        if(nums[i]<=0){
            a=i;
        }
    }
    for(int i=0;i<k;i++){
        if(i>=a){
            nums[a]=-nums[a];
        }
        else{
            nums[i]=-nums[i];
        }
    }
    int sum=0;
    for(int i=0;i<numsSize;i++){
        sum+=nums[i];
    }
    return sum;
}