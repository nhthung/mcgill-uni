// https://codeforces.com/problemset/problem/1006/E

#include <bits/stdc++.h>
using namespace std;
#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N=200001;
int n, q, dfs_i=1, sizee[N], dfs_to_node[N], node_to_dfs[N];
vector<int> adj[N];

void dfs(int u){
    sizee[u]=1;
    dfs_to_node[dfs_i]=u;
    node_to_dfs[u]=dfs_i++;
    for(int v: adj[u]){
        dfs(v);
        sizee[u]+=sizee[v];
    }
}

int main(){
    speedup();
    
    cin >> n >> q;
    for(int p, i=2; i<=n && cin >> p; adj[p].push_back(i), ++i);

    dfs(1);
    for(int u, k; cin >> u >> k;){
        if(k==1){ cout << u << '\n'; continue; }
        if(k>sizee[u]){ cout << "-1\n"; continue; }
        cout << dfs_to_node[node_to_dfs[u]+k-1] << '\n';
    }
}