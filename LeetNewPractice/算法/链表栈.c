#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Node {
    int data;          // 存储栈元素
    struct Node* next; // 指向下一个节点
} Node;

typedef struct {
    Node* top; // 栈顶指针
} Stack;

// 初始化栈
void initStack(Stack* stack) {
    stack->top = NULL; // 栈顶指针初始化为NULL，表示栈为空
}

// 判断栈是否为空
bool isEmpty(Stack* stack) {
    return stack->top == NULL;
}

// 入栈操作
void push(Stack* stack, int value) {
    Node* newNode = (Node*)malloc(sizeof(Node)); // 创建新节点
    if (newNode == NULL) {
        printf("Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    newNode->data = value;
    newNode->next = stack->top; // 新节点的next指向当前栈顶
    stack->top = newNode;       // 更新栈顶指针
}

// 出栈操作
void pop(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack underflow: No elements to pop\n");
        return;
    }
    Node* temp = stack->top; // 保存当前栈顶节点
    stack->top = stack->top->next; // 更新栈顶指针
    free(temp); // 释放原栈顶节点
}

// 查看栈顶元素
int top(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack is empty\n");
        exit(EXIT_FAILURE);
    }
    return stack->top->data;
}

// 获取栈的大小
int size(Stack* stack) {
    int count = 0;
    Node* current = stack->top;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;
}

// 测试栈的操作
int main() {
    Stack stack;
    initStack(&stack);

    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);

    printf("Top element is: %d\n", top(&stack)); // 输出：Top element is: 30
    printf("Stack size is: %d\n", size(&stack)); // 输出：Stack size is: 3

    pop(&stack);
    printf("Top element after pop is: %d\n", top(&stack)); // 输出：Top element after pop is: 20

    return 0;
}