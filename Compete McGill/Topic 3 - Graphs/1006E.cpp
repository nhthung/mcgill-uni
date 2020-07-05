// https://codeforces.com/problemset/problem/1006/E

#include <bits/stdc++.h>
using namespace std;

int n, q, p, u, k, i;

void solve(){
    vector<int> adj[n+1];
    bool visited[n+1];

    for(i=2; i<=n; ++i){
        cin >> p;
        adj[p].push_back(i);
    }
    for(i=1; i<=n; ++i){
        cout << i << ": ";
        for(auto x: adj[i]) cout << x << " ";
        cout << "\n";
    }
    while(q--){
        cin >> u >> k;
        if(!adj[u].size() && k==1){ cout << "-1\n"; continue; }
        while(k--){
            
        }
        cout << 
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> q;
    solve();
}