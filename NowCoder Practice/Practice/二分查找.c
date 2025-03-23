int search(int* nums, int numsLen, int target) {
    if (nums == NULL || numsLen == 0) {
        return -1;
    }

    int start = 0;
    int end = numsLen - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2; // 防止整数溢出
        
        if (nums[mid] == target) {
            return mid; // 找到目标直接返回
        } else if (nums[mid] < target) {
            start = mid + 1; // 调整左边界
        } else {
            end = mid - 1; // 调整右边界
        }
    }

    return -1; // 未找到
}