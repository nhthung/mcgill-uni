// Link: https://codeforces.com/problemset/problem/1340/A

#include <bits/stdc++.h>
using namespace std;

const int N=100000;
int m[N+1], // m[i] = j means number i is at jth position in permutation
    t,n;

void solve(){
    int s,e,p;
    cin >> n;
    for(int i=1;i<=n;i++){cin >> p; m[p]=i;}
    s=m[1];
    e=m[1]<n?n:n-1;
    for(int i=2;i<=n;i++){
        if(( s<e && m[i]!=m[i-1]+1) ||
            (s>e && m[i]>e)){
            cout << "No\n";
            return;
        }
        if(s>e) s=m[i];
        if(m[i]==e) e=s-1;
    }
    cout << "Yes\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> t;
    while(t--) solve();
}