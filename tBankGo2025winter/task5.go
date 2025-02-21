package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	in := bufio.NewReader(os.Stdin)
	out := bufio.NewWriter(os.Stdout)
	defer out.Flush()

	var n int
	var s int64
	fmt.Fscan(in, &n, &s)
	a := make([]int64, n+1)
	pref := make([]int64, n+1)
	pref[0] = 0
	a[0] = 0
	for i := 1; i <= n; i++ {
		var tmp int64
		fmt.Fscan(in, &tmp)
		a[i] = tmp
		pref[i] = pref[i-1] + a[i]
	}

	var j int = 1
	loss := make([]int64, n+1)
	loss[0] = 0
	for i := 1; i <= n; i++ {
		for j <= n && pref[j]-pref[i-1] <= s {
			j++
		}
		if j == n+1 {
			break
		}
		j -= 1
		loss[j] += 1 + loss[i-1]
	}

	var answer int64 = 0
	for i := 1; i <= n; i++ {
		answer += loss[i] * int64(n-i)
	}

	answer += int64(n) * (int64(n) + 1) / 2
	fmt.Fprintln(out, answer)

}
