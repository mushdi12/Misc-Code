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
	var out *bufio.Writer
	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)
	defer func(out *bufio.Writer) {
		_ = out.Flush()
	}(out)

	var n int
	n_s, _ := in.ReadString('\n')
	n_s = strings.TrimSpace(n_s)
	n, _ = strconv.Atoi(n_s)

	for i := 0; i < n; i++ {
		var a, b int
		_, _ = fmt.Fscan(in, &a, &b)

		if a == 1 || b == 1 {
			if a == 1 {
				_, _ = fmt.Fprintln(out, 1)
				_, _ = fmt.Fprintln(out, 1, 1)
			} else if b == 2 {
				_, _ = fmt.Fprintln(out, 1)
				_, _ = fmt.Fprint(out, 1, 1)
			}
		} else if a <= b {
			_, _ = fmt.Fprintln(out, 2)
			_, _ = fmt.Fprint(out, 1, 1)
			_, _ = fmt.Fprint(out, a, b)
		} else {
			_, _ = fmt.Fprintln(out, 2)
			_, _ = fmt.Fprint(out, 1, 1)
			_, _ = fmt.Fprint(out, a, b)
		}

	}

}
