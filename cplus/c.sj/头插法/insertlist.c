#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
	int data;
	struct node *next;
}node;
node* createnode(int data)
{
	node* newnode=(node*)malloc(sizeof(node));
	if (newnode==NULL)
	{
		printf("Memory allocation failed\n");
		exit(1);
	}
	newnode->data=data;
	newnode->next=NULL;
    return newnode;
}


node* beinsert(node* head,node* newnode)
{
	node dummy;
    dummy.next=head;
	if(head==NULL)return newnode;
	newnode->next=head;
	dummy.next=newnode;
    return dummy.next;

}
int main()
{	
	int i=1;
	node* head = createnode(0);
	while (i<10)
	{
		node* newnode=createnode(i);
		newnode=beinsert(head,newnode);
		head=newnode;
		i++;
	}
	for (node* p=head;p!=NULL;p=p->next)
	{
		printf("%d ",p->data);
	}
	
	for (node* p=head;p!=NULL;p=p->next)
	{
		free(p);
		p=NULL;
	}

	return 0;
}
