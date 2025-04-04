int* sortedSquares(int* nums, int numsSize, int* returnSize) {
    if(nums==NULL||nums+1==NULL)return nums;
    for(int i=0;i<numsSize;i++){
        nums[i]=nums[i]*nums[i];
    }
    int a=0,b=numsSize-1;    
    int*add=(int*)malloc(numsSize*sizeof(int));if(add==NULL)return NULL;
    int i=numsSize-1;
    while(a<=b)
    {
        if(nums[a]<nums[b]){
            add[i]=nums[b];
            b--;
        }
        else{
            add[i]=nums[a];
            a++;
        }
        i--;
    }
    *returnSize=numsSize;
    return add;
}