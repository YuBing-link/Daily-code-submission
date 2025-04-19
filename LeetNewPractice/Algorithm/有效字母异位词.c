bool isAnagram(char* s, char* t) {
    int hash[26];
    if (strlen(s) != strlen(t)) return false;
    memset(hash,0,sizeof(hash));
    for(int i=0;i<strlen(s);i++){
        hash[s[i]-'a']++;
        hash[t[i]-'a']--;
    }
    for(int i=0;i<26;i++){
        if(hash[i]!=0)return false;
    }
    return true;
}