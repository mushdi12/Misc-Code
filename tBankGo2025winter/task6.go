package main

import (
	"fmt"
	"os"
)

type Point struct{ x, y int }

func normalizeSlope(dy, dx int) [2]int {
	if dx == 0 {
		return [2]int{1, 0}
	}
	if dy == 0 {
		return [2]int{0, 1}
	}
	if dx < 0 {
		dx, dy = -dx, -dy
	}
	g := gcD(abs(dy), abs(dx))
	return [2]int{dy / g, dx / g}
}

func gcD(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func maxTriplets(n int, points []Point) int {
	M := 1
	for i := 0; i < n; i++ {
		counts := make(map[[2]int]int)
		maxCollinear := 0
		for j := i + 1; j < n; j++ {
			slope := normalizeSlope(points[j].y-points[i].y, points[j].x-points[i].x)
			counts[slope]++
			if counts[slope] > maxCollinear {
				maxCollinear = counts[slope]
			}
		}
		if maxCollinear+1 > M {
			M = maxCollinear + 1
		}
	}

	remaining := n - M
	maxT := n / 3
	for t := maxT; t >= 0; t-- {
		for a := 0; a <= t; a++ {
			bMax := (M - 2*a) / 1
			if bMax < 0 {
				continue
			}
			c := t - a - bMax
			if c >= 0 && a+2*bMax+3*c <= remaining {
				return t
			}
		}
	}
	return 0
}

func main() {
	var n int
	fmt.Fscan(os.Stdin, &n)
	points := make([]Point, n)
	for i := range points {
		fmt.Fscan(os.Stdin, &points[i].x, &points[i].y)
	}
	fmt.Println(maxTriplets(n, points))
}
