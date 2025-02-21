package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func getPairSums(arr []int) []int {
	sums := make(map[int]bool)
	n := len(arr)

	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			sums[arr[i]+arr[j]] = true
		}
	}

	result := make([]int, 0, len(sums))
	for sum := range sums {
		result = append(result, sum)
	}

	if len(result) == 1 {
		for i := 1; i < n; i++ {
			result = append(result, result[0])
		}

	}

	return result
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer

	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)

	defer func(out *bufio.Writer) {
		_ = out.Flush()
	}(out)

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

	sums := getPairSums(numbers)

	orig_sums := make([]int, len(sums))
	copy(orig_sums, sums)

	sum := 0
	for j := 0; j < len(sums); j++ {
		sum += sums[j]
	}
	fmt.Println(sum % 998244353)

	for i := 1; i < k; i++ {
		sum := 0
		for j := 0; j < len(sums); j++ {
			sums[j] = sums[j] * orig_sums[j]
			sum += sums[j]
		}
		fmt.Println(sum % 998244353)
	}
}
