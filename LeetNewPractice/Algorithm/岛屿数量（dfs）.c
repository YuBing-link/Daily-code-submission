#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
int dir[4][2]={0,1,1,0,-1,0,0,-1};
void dfs(int add[][501],int visit[][501],int x,int y,int n){
  for(int i=0;i<4;i++){
    int nextx=x+dir[i][0];
    int nexty=y+dir[i][1];
    if(nextx<0||nextx>=n||nexty<0||nexty>=n)
      continue;
    if(visit[nextx][nexty]==0&&add[nextx][nexty]==1){
      visit[nextx][nexty]=1;
      dfs(add,visit,nextx,nexty,n);
    }

  }

}
int main(int argc, char *argv[])
{
  // 请在此输入您的代码
  int n=0;
  scanf("%d",&n);
  int add[501][501];
  int visit[501][501];
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      scanf("%d",&add[i][j]);
      visit[i][j]=0;
    }
  }
  int result=0;
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      if(visit[i][j]==0&&add[i][j]==1)
      {result++;
      visit[i][j]=1;
      dfs(add,visit,i,j,n); 
      }
    }
  }
  printf("%d\n",result);
  return 0;
}