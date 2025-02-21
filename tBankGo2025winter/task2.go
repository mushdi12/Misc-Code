package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func isEnough(msv []int) []int {
	var result []int
	for i := 0; i < len(msv); i++ {
		curr_sum := msv[i]
		if curr_sum > 6 {
			orig_sum := curr_sum
			my_sum := 0
			count := 0
			limit := int(math.Log2(float64(orig_sum - 3)))
			for j := limit; j >= 0; j-- {
				num := int(math.Pow(2, float64(j)))
				if (orig_sum-num > 0) && j != 0 && count < 3 {
					my_sum += num
					orig_sum -= num
					count++
				} else if j == 0 && orig_sum-num == 0 && count < 3 {
					my_sum += num
					orig_sum -= num
					count++
				}
			}
			if count >= 3 {
				result = append(result, my_sum)
			} else {
				result = append(result, -1)
			}

		} else {
			result = append(result, -1)
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

	var days, curr_money int

	str_days, _ := in.ReadString('\n')
	str_days = strings.TrimSpace(str_days)
	days, _ = strconv.Atoi(str_days)

	all_money := make([]int, days)

	i := 0
	for i < days {
		_, _ = fmt.Fscan(in, &curr_money)
		all_money[i] = curr_money
		i++
	}

	//fmt.Println("=================")
	answer := isEnough(all_money)
	for _, values := range answer {
		fmt.Println(values)
	}
}
