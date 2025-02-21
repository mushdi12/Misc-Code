package main

import (
	"fmt"
)

func main() {
	var input_line string

	fmt.Scan(&input_line)

	if (input_line[0] == 'R') || (input_line[1] == 'R' && input_line[2] == 'M') {
		fmt.Println("YES")
	} else {
		fmt.Println("NO")
	}

}
