package main

import (
	"bufio"
	"fmt"
	"os"
)

func segmentSum(prefixSum []int, start, end int) int {
	sum := prefixSum[end]
	if start > 0 {
		sum -= prefixSum[start-1]
	}
	return sum
}

func main() {
	input := bufio.NewReader(os.Stdin)
	output := bufio.NewWriter(os.Stdout)
	defer output.Flush()

	var size, limit int
	fmt.Fscan(input, &size, &limit)

	values := make([]int, size)
	for i := 0; i < size; i++ {
		fmt.Fscan(input, &values[i])
	}

	prefix := make([]int, size)
	dp := make([]int, size)
	for i := 0; i < size; i++ {
		prefix[i] = values[i]
		if i > 0 {
			prefix[i] += prefix[i-1]
		}
	}

	total := 0
	for i := size - 1; i >= 0; i-- {
		left, right := i, size-1
		if segmentSum(prefix, i, size-1) <= limit {
			dp[i] = size - i
			total += dp[i]
			continue
		}
		for left < right {
			mid := (left + right) / 2
			if segmentSum(prefix, i, mid) <= limit {
				left = mid + 1
			} else {
				right = mid
			}
		}
		dp[i] = dp[left] + size - i
		total += dp[i]
	}

	fmt.Fprint(output, total)
}
