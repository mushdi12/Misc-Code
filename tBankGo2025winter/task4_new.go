package main

import (
	"bufio"
	"fmt"
	"os"
)

func greatestCommonDivisor(a, b int64) int64 {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func leastCommonMultiple(a, b int64) int64 {
	gcd := greatestCommonDivisor(a, b)
	return (a / gcd) * b
}

func computeOffset(value, mod int64) int64 {
	return (mod - value%mod) % mod
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

func main() {
	var in *bufio.Reader
	var out *bufio.Writer

	in = bufio.NewReader(os.Stdin)
	out = bufio.NewWriter(os.Stdout)
	defer out.Flush()

	var count, valX, valY, valZ int64
	fmt.Fscan(in, &count, &valX, &valY, &valZ)

	values := make([]int64, count)
	for idx := int64(0); idx < count; idx++ {
		fmt.Fscan(in, &values[idx])
	}

	xyLCM, xzLCM, yzLCM := leastCommonMultiple(valX, valY), leastCommonMultiple(valX, valZ), leastCommonMultiple(valY, valZ)
	xyzLCM := leastCommonMultiple(xyLCM, valZ)

	costMatrix := make([][]int64, count)
	for idx := int64(0); idx < count; idx++ {
		costMatrix[idx] = []int64{computeOffset(values[idx], valX), computeOffset(values[idx], valY), computeOffset(values[idx], valZ), computeOffset(values[idx], xyLCM), computeOffset(values[idx], xzLCM), computeOffset(values[idx], yzLCM), computeOffset(values[idx], xyzLCM)}
	}

	for idx := int64(1); idx < count; idx++ {
		updatedRow := make([]int64, 7)
		for col := 0; col < 7; col++ {
			updatedRow[col] = min(costMatrix[idx][col], costMatrix[idx-1][col])
		}
		for col1 := 0; col1 < 7; col1++ {
			for col2 := 0; col2 < 7; col2++ {
				mask := ((col1 + 1) | (col2 + 1)) - 1
				updatedRow[mask] = min(updatedRow[mask], costMatrix[idx-1][col1]+costMatrix[idx][col2])
			}
		}
		costMatrix[idx] = updatedRow
	}

	fmt.Fprint(out, costMatrix[count-1][6])
}
