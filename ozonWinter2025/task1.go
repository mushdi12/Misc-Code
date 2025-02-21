package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func getCont(msv []string) []int {
	var answer []int
	for _, element := range msv {
		number, _ := strconv.Atoi(element)
		if 10 > number {
			answer = append(answer, number+1)
		} else {
			first, _ := strconv.Atoi(string(element[0]))
			cheker := strings.Repeat(string(element[0]), len(element))
			chekerInt, _ := strconv.Atoi(cheker)
			cnt := first + (len(element)-1)*10
			if chekerInt > number {
				cnt--
			}
			answer = append(answer, cnt)
		}
	}
	return answer
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer
	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)
	defer func(out *bufio.Writer) {
		_ = out.Flush()
	}(out)

	var count int
	var currNumber string
	_, _ = fmt.Fscan(in, &count)
	i := 0

	var a []string

	for i < count {
		_, _ = fmt.Fscan(in, &currNumber)
		a = append(a, currNumber)
		i++
	}

	results := getCont(a)
	for _, element := range results {
		_, _ = fmt.Fprintln(out, element)

	}
}
