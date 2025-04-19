#include <stdio.h>
#include <stdlib.h>

int* maxInWindows(int* num, int numLen, int size, int* returnSize) {
    if (size == 0 || size > numLen) {
        *returnSize = 0;
        return NULL;
    }

    *returnSize = numLen - size + 1;
    int* result = (int*)malloc((*returnSize) * sizeof(int));
    if (result == NULL) {
        return NULL;
    }

    int* deque = (int*)malloc(numLen * sizeof(int));
    if (deque == NULL) {
        free(result);
        return NULL;
    }
    int front = 0, rear = -1; 

    for (int i = 0; i < numLen; i++) {

        if (front <= rear && deque[front] < i - size + 1) {
            front++;
        }

        while (front <= rear && num[deque[rear]] <= num[i]) {
            rear--;
        }


        deque[++rear] = i;

        if (i >= size - 1) {
            result[i - size + 1] = num[deque[front]];
        }
    }

    free(deque);
    return result;
}