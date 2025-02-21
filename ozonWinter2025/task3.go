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

	var validationCount, productCount int

	validationCount_line, _ := in.ReadString('\n')
	validationCount_line = strings.TrimSpace(validationCount_line)
	validationCount, _ = strconv.Atoi(validationCount_line)

	var input_products = make([]map[string]int, validationCount) // входные продукты
	var coworker = make([]map[string]int, validationCount)       // продукты коллеги
	var uniqPrices = make([][]int, validationCount)

	i := 0
	for i < validationCount {

		productCount_line, _ := in.ReadString('\n')
		productCount_line = strings.TrimSpace(productCount_line)
		productCount, _ = strconv.Atoi(productCount_line)

		input_products[i] = make(map[string]int, productCount) // создаю хэщ мапу для каждого цикла продуктов
		coworker[i] = make(map[string]int, productCount)       // создаю хэщ мапу для каждого цикла коллеги

		// заполняем продукты для цикла
		j := 0
		for j < productCount {
			line, _ := in.ReadString('\n')
			line = strings.TrimSpace(line)
			input_msv := strings.Fields(line)
			data, _ := strconv.Atoi(input_msv[1])
			input_products[i][input_msv[0]] = data
			j++
		}

		// заполняем данные коллеги
		coworker_line, _ := in.ReadString('\n')
		coworker_line = strings.TrimSpace(coworker_line)
		pairs := strings.Split(coworker_line, ",")
		if strings.Count(coworker_line, ":") < 2 {
			coworker[i]["no"] = -1
		} else {
			for _, pair := range pairs {
				keyValue := strings.Split(pair, ":")
				if len(keyValue) < 2 {
					coworker[i]["no"] = -1
				} else {
					s := keyValue[1]
					if s[0] != '0' {
						value, _ := strconv.Atoi(keyValue[1])
						if _, exists := coworker[i][keyValue[0]]; exists {
							coworker[i][keyValue[1]] = -1
						} else {
							coworker[i][keyValue[0]] = value
						}
					} else {
						coworker[i][s] = -1
					}
				}
			}

		}

		i++
	}
	for i < validationCount {
		for key, value := range coworker[i] {
			if _, exists := input_products[i][key]; !exists {
				fmt.Fprintf(out, "NO")
				break
			}

			flag := false
			for k := 0; k < len(uniqPrices[i]); k++ {

				if uniqPrices[i][k] == value {
					flag = true
				}
			}

			if flag != true {
				fmt.Fprintf(out, "NO")
			}
			
		}
	}

	//for i := 0; i < validationCount; i++ {
	//	_, _ = fmt.Fprintln(out, input_products[i], coworker[i])
	//}
}
