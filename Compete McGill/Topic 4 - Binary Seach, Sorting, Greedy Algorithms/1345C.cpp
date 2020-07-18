// https://codeforces.com/problemset/problem/1345/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
using namespace std;
typedef long long ll;

const int N=200000;
int t, n;
ll a[N];
bitset<N> bs;

int mod(int k){
    int out=k%n;
    if(out<0) out+=n;
    return out;
}

void solve(){
    for(int i=0; i<n; cin >> a[i], ++i);
    for(ll i, k=0; k<n; ++k){
        i=mod(k+a[mod(k)]);
        if(bs[i]){ cout << "NO\n"; return; }
        bs[i]=true;
    }
    for(int i=0; i<n; ++i){
        if(!bs[i]){ cout << "NO\n"; return; }
    }
    cout << "YES\n";
}

int main(){
    speedup();
    for(cin >> t; t-- && cin >> n; bs.reset())
        solve();
}