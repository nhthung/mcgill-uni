#include <bits/stdc++.h>
using namespace std;
#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N=100001;
int n, p, k, leaves[N];
vector<int> ans, adj[N];

void dfs(int u){
    if(u>1 && adj[u].size()==0){
        leaves[u]=1;
        return;
    }
    for(int v: adj[u]){
        dfs(v);
        leaves[u]+=leaves[v];
    }
    ans.push_back(leaves[u]);
}

int main(){
    /**
     * Find number of leaves each subtree has,
     * return sorted in ascending order
     * k <= n_leaves -> 1
     * k >  n_leaves -> find through dfs 
     */
    speedup();

    cin >> n;
    if(n==1){ cout << "1\n"; return 0; }

    int n_leaves=n;
    for(int i=2; cin >> p; ++i){
        adj[p].push_back(i);
        if(adj[p].size()==1) --n_leaves;
    }
    if(adj[1].size()==0) --n_leaves;


    for(k=1; k<=n_leaves; ++k) cout << "1 ";
    dfs(1);
    sort(ans.begin(), ans.end());
    for(auto x: ans) cout << x << " "; cout << '\n';
}