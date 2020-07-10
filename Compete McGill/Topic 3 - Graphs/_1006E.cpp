// https://codeforces.com/problemset/problem/1006/E

#include <bits/stdc++.h>
using namespace std;
#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N=200001;
int n, q, k, ans;
vector<int> adj[N];

void dfs(int u){
    if(k==1){ if(ans==0) ans=u; return; }
    // if(k==0){ cout << u << '\n'; return; }
    --k;
    for(int v: adj[u]){
        dfs(v);
        // if(k==0) return;
    }
}

void solve(){
    cin >> n >> q;

    for(int p, i=2; i<=n && cin >> p; adj[p].push_back(i), ++i);

    // for(int i=1; i<=n; ++i){
    //     cout << i << ": ";
    //     for(auto x: adj[i]) cout << x << " ";
    //     cout << "\n";
    // }

    for(int u; cin >> u >> k;){
        // cout << u << " " << k << ":\n";
        if(k==1){ cout << u << '\n'; continue; }
        if(!adj[u].size() && k>1){ cout << "-1\n"; continue; }
        dfs(u);
        cout << (ans? ans:-1) << '\n';
        ans=0;
    }
}

int main(){
    speedup();
    solve();
}