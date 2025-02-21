package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func allPairSums(arr []int) []int {
	n := len(arr)
	sums := make([]int, n)

	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			sums = append(sums, arr[i]+arr[j])
		}
	}

	if len(sums) == 1 {
		for i := 1; i < n; i++ {
			sums = append(sums, sums[0])
		}
	}

	return sums
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer

	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)

	defer out.Flush()

	var n, k int
	fmt.Fscan(in, &n, &k)

	in.ReadString('\n')

	str_numbers, _ := in.ReadString('\n')
	str_numbers = strings.TrimSpace(str_numbers)
	str_msv_numbers := strings.Fields(str_numbers)

	numbers := make([]int, n)
	for i, d := range str_msv_numbers {
		numbers[i], _ = strconv.Atoi(d)
	}

	sums := allPairSums(numbers)

	orig_sums := make([]int, len(sums))
	copy(orig_sums, sums)

	totalSum := 0
	for _, sum := range sums {
		totalSum += sum
	}
	fmt.Println(totalSum % 998244353)

	for i := 1; i < k; i++ {
		totalSum = 0
		for j := 0; j < len(sums); j++ {
			sums[j] = sums[j] * orig_sums[j]
			totalSum += sums[j]
		}
		fmt.Println(totalSum % 998244353)
	}
}
