package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

func main() {
	in := bufio.NewReader(os.Stdin)
	out := bufio.NewWriter(os.Stdout)
	defer out.Flush()

	var n, m int64
	fmt.Fscan(in, &n, &m)
	a := make([]int64, n)
	b := make([]int64, n-2)
	for i := int64(0); i < n; i++ {
		var tmp int64
		fmt.Fscan(in, &tmp)
		a[i] = tmp
		if i > 1 {
			b[i-2] = a[i]
		}
	}

	var low, high int64 = a[0], a[1]
	sort.Slice(b, func(i, j int) bool {
		return b[i] < b[j]
	})
	var answer int64 = 0
	var may bool = true
	for r := int64(m - 1); r < int64(len(b)); r++ {
		var l int64 = r - m + 1
		var low_wanted, high_wanted int64 = 0, 0
		if low > b[l] {
			low_wanted = low - b[l]
		}
		if high < b[r] {
			high_wanted = b[r] - high
		}
		if may {
			answer = low_wanted + high_wanted
			may = false
		} else {
			answer = min(answer, low_wanted+high_wanted)
		}
	}

	fmt.Fprintln(out, answer)
}
