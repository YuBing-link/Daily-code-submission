bool isAnagram(char * s, char * t){
     if(!strcmp(s,t)){
        return false;
    }
    int arr[26]={0};
    memset(arr,0,sizeof(arr));
    for(int i=0;s[i]!=0;i++){
        arr[s[i]-'a']++;
    }
    for(int i=0;t[i]!=0;i++){
        arr[t[i]-'a']--;
    }
    for(int i=0;i<26;i++){
        if(arr[i]!=0)return false;
    }
   

    return true;
}