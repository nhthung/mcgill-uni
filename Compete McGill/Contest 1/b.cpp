// Link: https://codeforces.com/group/zGkaRYSeWm/contest/282369/problem/0

#include <bits/stdc++.h>
#define print(iter, n) for(int i=0; i<n; i++){cout << iter[i];}; cout << "\n";
using namespace std;

int t, a, b;

void solve(){
    int gcdiv;
    cin>>a>>b;
    if(a==1 || b==1){ cout<<"Finite\n"; return; }
    if(a==b){ cout<<"Infinite\n"; return; }
    gcdiv=__gcd(a,b);
    if(gcdiv>1){
        cout<<"Infinite\n";
    }else{
        cout<<"Finite\n";
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin>>t;
    while(t--) solve();
}