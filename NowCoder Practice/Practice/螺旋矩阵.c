int** generateMatrix(int n, int* returnSize, int** returnColumnSizes) {
    int x = 0, y = 0, i, j, count = 1, offset = 1;
    int temp = n / 2;
    int** add = (int**)malloc(n * sizeof(int*));
    for (int i = 0; i < n; i++) {
        add[i] = (int*)malloc(n * sizeof(int));
    }

    while (temp--) {
        i = x, j = y;
        for (j = y; j < n - offset; j++) {
            add[i][j] = count++;
        }
        for (i = x; i < n - offset; i++) {
            add[i][j] = count++;
        }
        for (; j > y; j--) {
            add[i][j] = count++;
        }
        for (; i > x; i--) {
            add[i][j] = count++;
        }
        x++;
        y++;
        offset++;
    }
    if (n % 2 == 1) {
        add[n / 2][n / 2] = count;
    }
    *returnSize = n;
    *returnColumnSizes = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        (*returnColumnSizes)[i] = n;
    }
    return add;
}