#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
int dir[4][2]={0,1,1,0,-1,0,0,-1};
void bfs(int add[][501],int visit[][501],int x,int y,int n){
  int queue[501][2];
  int front=0,rear=0;
  queue[rear][0]=x;
  queue[rear][1]=y;
  rear++;
  while(front<rear){
    int currX = queue[front][0];
    int currY = queue[front][1];
    front++;
    for(int i=0;i<4;i++){
      int nextx=currX+dir[i][0];
      int nexty=currY+dir[i][1];
      if(nextx<0||nextx>=n||nexty<0||nexty>=n)
        continue;
      if(visit[nextx][nexty]==0&&add[nextx][nexty]==1){
        visit[nextx][nexty]=1;
        queue[rear][0]=nextx;
        queue[rear][1]=nexty;
        rear++;
      }


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
       bfs(add,visit,i,j,n); 
      }
    }
  }
  printf("%d\n",result);
  return 0;
}