package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

func getCnt(cnt_days, good_days int, days *[]int) int {

	first_day := &(*days)[0]
	second_day := &(*days)[1]
	corrections := 0
	corr_days := (*days)[2:]
	sort.Ints(corr_days)

	if *first_day > *second_day {
		corrections += int(math.Abs(float64(corr_days[len(corr_days)-1]-*second_day)) + math.Abs(float64(corr_days[0]-*first_day)))
		*second_day, *first_day = corr_days[len(corr_days)-1], corr_days[0]
	}

	for i := 0; i < len(corr_days); i++ {
		if good_days > 0 {
			if corr_days[i] > *first_day && corr_days[i] > *second_day {
				corrections += corr_days[i] - *second_day
				*second_day = corr_days[i]
				good_days--
			} else if corr_days[i] < *first_day {
				corrections += *first_day - corr_days[i]
				*first_day = corr_days[i]
				good_days--
			}
		} else {
			break
		}
	}

	return corrections
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer

	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)

	defer func(out *bufio.Writer) {
		_ = out.Flush()
	}(out)

	var cnt_days, good_days int

	_, _ = fmt.Fscan(in, &cnt_days, &good_days)

	in.ReadString('\n')

	str_days, _ := in.ReadString('\n')
	str_days = strings.TrimSpace(str_days)
	str_msv_days := strings.Fields(str_days)

	days := make([]int, cnt_days)
	for i, d := range str_msv_days {
		days[i], _ = strconv.Atoi(d)
	}

	fmt.Println(getCnt(cnt_days, good_days, &days))
}
