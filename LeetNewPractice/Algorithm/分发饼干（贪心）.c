int compare(const void*a,const void*b){
    return *(int*)b-*(int*)a;
}
int findContentChildren(int* g, int gSize, int* s, int sSize) {
    if(s==NULL||g==NULL){
        return 0;
    }
    int j=0;
    int count=0;
    qsort(g,gSize,sizeof(int),compare);
    qsort(s,sSize,sizeof(int),compare);
    for(int i=0;i<gSize;i++){
        if (j >= sSize) {
            break; 
        }
        if(s[j]>=g[i]){
            count++;
            j++;
        }
    }
    return count;
}