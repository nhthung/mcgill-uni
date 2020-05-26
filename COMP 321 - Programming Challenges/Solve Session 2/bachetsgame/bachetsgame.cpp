#include <iostream>
#include <cstdio>

using namespace std;

// 1-D DP table:
// Boolean values: true => player making first move (player 1) wins
//                 false => player 2 wins
bool D[1000001];

bool bachet(int n, int m) {
    '''
    Return true if player making first move wins
    '''
    for (int i = 1; i <= n; i++) {
        D[i] = false;
        
        for (int j = 0; j < m; j++)
            if (i >= ks[j] && !D[i - ks[j]]) {
                // If can take k stones (i >= ks[j])
                // and at n = i - k, player 2 wins by playing optimally (!D[i - ks[j]])
                // then optimally, player 2 would make the winning move at n = i
                D[i] = true;
                break;
            }
    }
    return D[n];
}

int main(){
    int n, m;
    
    while (scanf("%d %d", &n, &m) == 2){
        for (int i = 0; i < m; i++)
            scanf("%d", &ks[i]);

        bachet(n, m);
        if (D[n]) printf("Stan wins\n");
        else printf("Ollie wins\n");
    }
    return 0;
}