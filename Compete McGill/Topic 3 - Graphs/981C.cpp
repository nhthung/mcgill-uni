// https://codeforces.com/problemset/problem/981/C

#include <bits/stdc++.h>

using namespace std;

int n, i, u, root;

int main(){
    /**
     * Determine if graph only has at most one node with >= 2 neighbors
     * Count the number of extremities i.e. number of nodes with only 1 neighbor
     */
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> n;
    int neighbors[n+1]; // node i has neighbors[i] neighbors
    memset(neighbors, 0, sizeof(neighbors));
    
    for(i=0; i<2*(n-1); ++i){
        cin >> u;
        neighbors[u]++;

        if(neighbors[u]==3){
            if(root){ cout << "No\n"; return 0; }
            root=u;
        }
    }
    cout << "Yes\n";
    cout << (root ? neighbors[root] : 1) << "\n";
    for(i=1; i<=n; ++i){
        if(neighbors[i]>1) continue;
        if(root) cout << root << " " << i << "\n";
        else     cout << i << " ";
    }
}