package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	var in *bufio.Reader
	in = bufio.NewReader(os.Stdin)
	var count int
	var splitLine []string
	line, _ := in.ReadString('\n')
	count, _ = strconv.Atoi(strings.TrimSpace(line))
	xList := make([]int, count)
	yList := make([]int, count)
	for i := 0; i < count; i++ {
		line, _ = in.ReadString('\n')
		splitLine = strings.Split(strings.TrimSpace(line), " ")
		xList[i], _ = strconv.Atoi(splitLine[0])
		yList[i], _ = strconv.Atoi(splitLine[1])
	}
	for i := 0; i < count; i++ {
		if xList[i] == 1 || yList[i] == 1 {
			if xList[i] == 1 {
				fmt.Println(1)
				fmt.Printf("%d %d R\n", 1, 1)
			} else if yList[i] == 1 {
				fmt.Println(1)
				fmt.Printf("%d %d D\n", 1, 1)
			}
		} else if xList[i] <= yList[i] {
			fmt.Println(2)
			fmt.Printf("%d %d R\n", 1, 1)
			fmt.Printf("%d %d L\n", xList[i], yList[i])
		} else {
			fmt.Println(2)
			fmt.Printf("%d %d D\n", 1, 1)
			fmt.Printf("%d %d U\n", xList[i], yList[i])
		}
	}
}
