#include <bits/stdc++.h>
using namespace std;

const int n=4;
int col[n], diag1[2*n-1], diag2[2*n-1], res=0;

void search(int y) {
    if (y == n) {
        res++;
        return;
    }
    for (int x = 0; x < n; x++) {
        if (col[x] || diag1[x+y] || diag2[x-y+n-1]) continue;
        col[x] = diag1[x+y] = diag2[x-y+n-1] = 1;
        search(y+1);
        col[x] = diag1[x+y] = diag2[x-y+n-1] = 0;
    }
}

int main(){
    search(0);
    cout << res << "\n";
}