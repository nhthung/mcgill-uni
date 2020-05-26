package main

import (
	"bufio"
	"fmt"
	"os"
)

func bachet(D *[]bool, ks *[]int, n int) {
	fmt.Println((*D)[0])
	// fmt.Println(n)
	// fmt.Println(ks)
}

func main() {
	max_n := 1000000
	max_m := 10

	D := make([]bool, max_n)
	ks := make([]int, max_m)

	var n, m int
	scanner := bufio.NewScanner(os.Stdin)

	// for scanned, _ := fmt.Scanf("%d %d", &n, &m); scanned == 2; {
	// 	fmt.Println(n, m)
	// 	for i := 0; i < m; i++ {
	// 		fmt.Scanf("%d", &ks[i])
	// 	}
	// 	fmt.Println(ks)
	// 	bachet(&D, &ks, n)
	// 	// break
	// }

	for scanner.Scan() {
		// fmt.Println(scanner.Text())
		// fmt.Sscanf(scanner.Text(), "%d %d", &n, &m)
		fmt.Scanf("%d %d", &n, &m)
		fmt.Println(n, m)
		// for i := 0; i < m; i++ {
		// 	fmt.Sscanf(scanner.Text(), "%d", &ks[i])
		// }
		fmt.Println(ks)
		bachet(&D, &ks, n)
		// fmt.Println(n, m)
		break
	}
}
