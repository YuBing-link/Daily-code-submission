#include <stdio.h>
#include <time.h>
#include <stdlib.h>
void menu()
{
	printf("*************************\n");
	printf("*******  1.paly  ********\n");
	printf("*******  0.exit  ********\n");
	printf("*************************\n");
}
void game()
{
	int guess;
	int ret = rand()%100+1;
	//printf("%d\n", ret);

	while (1) {	printf("猜数字:");
	scanf_s("%d", &guess);
		if (guess < ret)printf("猜小了\n");
		else if (guess > ret)printf("猜大了\n");
		else {
			printf("猜中了太帅了，数字为%d\n", ret);
			break;
		}
	}
}

int main() {
	int input=0;srand((unsigned int)time(NULL));
	do
	  {
		menu();
			printf("请选择：");
		scanf_s("%d",&input);
		if (input == 1) {
	
				game();
		}
		 else if(input == 0) {
			printf("退出游戏\n");	

		}
		 else{
			printf("选择错误\n");
			
		}

		
			

	} while (input);
	return 0;
}
