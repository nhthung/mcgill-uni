// Link: https://codeforces.com/problemset/problem/981/C

#include <bits/stdc++.h>

using namespace std;

int main(){
    /**
     * Find longest path length
     */
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, i, j, len, longest=1;
    cin >> n;
    int g[n+1];

    for(i=1; i<=n; ++i) cin >> g[i];
    for(i=1; i<=n; ++i){
        len=1;
        j=i;
        while(g[j]!=-1){
            j=g[j];
            len++;
        }
        longest=max(longest, len);
    }
    cout << longest << "\n";
}