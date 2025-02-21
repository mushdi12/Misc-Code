package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func check(s1, s2 string) bool {
	// Проверка на равенство длины строк
	if len(s1) != len(s2) {
		return false
	}

	// Проверка символов на чётных позициях
	chet := true
	nechet := true

	for i := 0; i < len(s1); i++ {
		if i%2 == 0 {
			if s1[i] != s2[i] {
				chet = false
			}
		} else {
			if s1[i] != s2[i] {
				nechet = false
			}
		}
	}

	return chet || nechet
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer
	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)
	defer func(out *bufio.Writer) {
		_ = out.Flush()
	}(out)

	var allCount, currCount int

	allCount_line, _ := in.ReadString('\n')
	allCount_line = strings.TrimSpace(allCount_line)
	allCount, _ = strconv.Atoi(allCount_line)

	var allStrings = make([][]string, allCount)
	var count = make([]int, allCount)

	i := 0
	for i < allCount {

		currCount_line, _ := in.ReadString('\n')
		currCount_line = strings.TrimSpace(currCount_line)
		currCount, _ = strconv.Atoi(currCount_line)

		allStrings[i] = make([]string, currCount)

		for j := 0; j < currCount; j++ {
			line, _ := in.ReadString('\n')
			line = strings.TrimSpace(line)
			allStrings[i][j] = line
		}

		i++
	}

	for i := 0; i < len(input_msv); i++ {
		num, _ := strconv.Atoi(input_msv[i])
		a = append(a, num)
	}

	for l, str := range allStrings {
		for i := 0; i < len(str)-1; i++ {
			if check(str[i], str[i+1]) {
				fmt.Println(str[i], str[i+1])
				count[l] += 1
			}
		}
	}

	fmt.Println(count)
}
