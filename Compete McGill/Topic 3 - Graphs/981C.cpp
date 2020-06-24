// Link: https://codeforces.com/problemset/problem/981/C

#include <bits/stdc++.h>

using namespace std;

int n, i, u, v, root;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> n;
    vector<int> adj[n+1];
    for(i=0; i<n-1; ++i){
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);

        if(adj[u].size()==3 || adj[v].size()==3){
            if(root){ cout << "No\n"; return 0; }
            root=adj[u].size()==3 ? u : v;
        }
    }
    cout << "Yes\n";
    cout << (root ? adj[root].size() : 1) << "\n";
    for(i=1; i<=n; ++i){
        if(adj[i].size()>1) continue;
        if(root) cout << root << " " << i << "\n";
        else     cout << i << " ";
    }
}