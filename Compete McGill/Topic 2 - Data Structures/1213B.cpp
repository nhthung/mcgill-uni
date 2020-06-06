// Link: https://codeforces.com/problemset/problem/1213/B

#include <bits/stdc++.h>

using namespace std;

const int N=150000, A=1000000;
int t, n, a[N+1];

void solve(){
    /**
     * Iterate through prices in reverse, save minimum price so far,
     * if current price is larger than the minimum price so far then
     * it's a bad price
     **/
    cin >> n;
    int bad=0, cur_min=A;
    for(int i=1; i<=n; i++) cin >> a[i];
    for(int i=n; i>0; i--){
        cur_min=min(a[i], cur_min);
        if(a[i]>cur_min) bad++;
    }
    cout << bad << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;
    while(t--) solve();
}