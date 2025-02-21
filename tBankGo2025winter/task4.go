package main

import (
	"bufio"
	"fmt"
	"os"
)

func gcd(a int64, b int64) int64 {
	for b != 0 {
		a %= b
		a, b = b, a
	}
	return a
}

func lcm(a int64, b int64) int64 {
	var g int64 = gcd(a, b)
	return a / g * b
}

func get_cost(a int64, m int64) int64 {
	return (m - a%m) % m
}

func main() {
	in := bufio.NewReader(os.Stdin)
	out := bufio.NewWriter(os.Stdout)
	defer out.Flush()

	var n, x, y, z int64
	fmt.Fscan(in, &n, &x, &y, &z)
	a := make([]int64, n)
	for i := int64(0); i < n; i++ {
		var tmp int64
		fmt.Fscan(in, &tmp)
		a[i] = tmp
	}

	var xy, xz, yz int64 = lcm(x, y), lcm(x, z), lcm(y, z)
	var xyz int64 = lcm(xy, z)
	answer := make([][]int64, n)
	for i := int64(0); i < n; i++ {
		answer[i] = make([]int64, 7)
		answer[i][0] = get_cost(a[i], x)
		answer[i][1] = get_cost(a[i], y)
		answer[i][2] = get_cost(a[i], z)
		answer[i][3] = get_cost(a[i], xy)
		answer[i][4] = get_cost(a[i], xz)
		answer[i][5] = get_cost(a[i], yz)
		answer[i][6] = get_cost(a[i], xyz)
	}

	for i := int64(1); i < n; i++ {
		tmp_list := make([]int64, 7)
		for j := 0; j < 7; j++ {
			tmp_list[j] = min(answer[i][j], answer[i-1][j])
		}
		for j := 0; j < 7; j++ {
			for k := 0; k < 7; k++ {
				var p int = ((j + 1) | (k + 1)) - 1
				tmp_list[p] = min(tmp_list[p], answer[i-1][j]+answer[i][k])
			}
		}
		answer[i] = tmp_list
	}

	fmt.Fprint(out, answer[n-1][6])

}
