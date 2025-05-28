#include <stdio.h>
#include <stdlib.h>
#include <time.h>
 int main() {
	srand((unsigned int)time(NULL));
	int w, l, n;
	int arr[] = { w,9l,n };
	meau(&w, &l, &n);
	int sz = sizeof(arr) / sizeof(arr[0]);
	int k; while (scanf_s("%d", &k) != EOF) {
		int ret = er(arr, sz, k);
		if (ret == 999) {
			printf("badfind\n");

		}
		else {
			printf("goodfind,%d\n", ret);
		}	printf("%d %d %d\n", w, l, n);
	}

	return 0;
}
static int er(int arr[], int sz, int k) {
	int left = 0;
	int right = sz - 1;

	while (left <= right) {
		int mid = left + (right - left) / 2;
		if (arr[mid] < k) { left = mid + 1; }
		else if (arr[mid] > k) { right = mid - 1; }
		else { return mid; }

	}
	return 999;


}
int meau(int* w, int* l, int* n) {

	w = rand(), l = rand(), n = rand();



	return w, l, n;

}