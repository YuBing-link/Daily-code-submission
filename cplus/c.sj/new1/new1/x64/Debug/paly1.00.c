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

	while (1) {	printf("������:");
	scanf_s("%d", &guess);
		if (guess < ret)printf("��С��\n");
		else if (guess > ret)printf("�´���\n");
		else {
			printf("������̫˧�ˣ�����Ϊ%d\n", ret);
			break;
		}
	}
}

int main() {
	int input=0;srand((unsigned int)time(NULL));
	do
	  {
		menu();
			printf("��ѡ��");
		scanf_s("%d",&input);
		if (input == 1) {
	
				game();
		}
		 else if(input == 0) {
			printf("�˳���Ϸ\n");	

		}
		 else{
			printf("ѡ�����\n");
			
		}

		
			

	} while (input);
	return 0;
}
