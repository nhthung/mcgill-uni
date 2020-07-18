// https://codeforces.com/problemset/problem/479/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
using namespace std;
typedef long long ll;

int n;

int main(){
    /**
     * Sweep line algorithm
     */
    speedup();
    cin >> n;
    vector<pair<ll, ll>> v(n);
    for(auto &[a, b]: v) cin >> a >> b;
    sort(v.begin(), v.end());
    
    ll ans=0;
    for(auto [a, b]: v) ans=min(a, b)>=ans ? min(a, b) : max(a, b);
    cout << ans << '\n';
}