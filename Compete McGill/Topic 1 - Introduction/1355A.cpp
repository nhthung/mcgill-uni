// Link: https://codeforces.com/problemset/problem/1355/A

#include <bits/stdc++.h>
#define print(iter,n) for(int i=0;i<n;i++){cout << iter[i] << " ";}; cout << "\n";
typedef long long ll;
using namespace std;

int t;
ll _min,_max;

void digits(ll n){
    _min=9;_max=0;
    ll d;
    do{
        d=n%10;
        _min=min(d,_min); // min digit
        _max=max(d,_max); // max digit
    }while(n/=10);
}

void solve(){
    ll a,k,next;
    cin >> a >> k;
    while(--k){
        digits(a);
        next=a+_min*_max;
        if(next==a) break;
        a=next;
    }
    cout << a << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t;
    cin >> t;
    while(t--){
        solve();
    }
}