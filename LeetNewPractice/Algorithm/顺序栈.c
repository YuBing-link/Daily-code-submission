#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_SIZE 100 // 定义栈的最大容量

typedef struct {
    int data[MAX_SIZE]; // 存储栈元素的数组
    int top;            // 栈顶指针
} Stack;

// 初始化栈
void initStack(Stack* stack) {
    stack->top = -1; // 栈顶指针初始化为-1，表示栈为空
}

// 判断栈是否为空
bool isEmpty(Stack* stack) {
    return stack->top == -1;
}

// 判断栈是否已满
bool isFull(Stack* stack) {
    return stack->top == MAX_SIZE - 1;
}

// 入栈操作
void push(Stack* stack, int value) {
    if (isFull(stack)) {
        printf("Stack overflow: Cannot push element %d\n", value);
        return;
    }
    stack->data[++stack->top] = value; // 栈顶指针加1，然后将值存入栈顶
}

// 出栈操作
void pop(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack underflow: No elements to pop\n");
        return;
    }
    stack->top--; // 栈顶指针减1
}

// 查看栈顶元素
int top(Stack* stack) {
    if (isEmpty(stack)) {
        printf("Stack is empty\n");
        exit(EXIT_FAILURE);
    }
    return stack->data[stack->top];
}

// 获取栈的大小
int size(Stack* stack) {
    return stack->top + 1;
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