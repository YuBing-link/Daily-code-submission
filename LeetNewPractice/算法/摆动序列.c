int wiggleMaxLength(int* nums, int numsSize) {
    if(numsSize==0)
    return 0;
    int prediff=0,curdiff=0,count=1;
    for(int i=0;i<numsSize-1;i++){
        curdiff=nums[i+1]-nums[i];
        if((prediff>=0&&curdiff<0)||(prediff<=0&&curdiff>0)){
            count++;
            prediff=curdiff;
        }
    }
    return count;
}