#include <bits/stdc++.h>
using namespace std;

const int N = 100001;
int n, c[N];
vector<int> adj[N];
bitset<N> visited;

bool bfs(int child, int root){
    queue<int> q;
    visited[child] = true;
    q.push(child);
    while(!q.empty()){
        int u = q.front(); q.pop();
        for(int v: adj[u]){
            if(v==root || visited[v]) continue;
            if(c[v]!=c[child]) return false;
            visited[v] = true;
            q.push(v);
        }
    }
    return true;
}

bool is_good(int root){
    visited.reset();
    for(int child: adj[root])
        if(!bfs(child, root))
            return false;
    return true;
}

void yes(int ans){ cout << "YES\n"; cout << ans << '\n'; }

void no(){ cout << "NO\n"; }

void solve(){
    cin >> n;
    for(int u, v, i=0; i<n-1; ++i){
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    for(int i = 1; i<=n; ++i) cin >> c[i];

    // Check if tree's good if rooted at 1
    if(is_good(1)) return yes(1);

    // Search for 1 unique border, if there are > 1 then bad tree
    queue<int> q;
    visited.reset();
    visited[1] = true;
    q.push(1);
    int border_node = 0;
    while(!q.empty()){
        int u = q.front(); q.pop();
        bool border = false;
        for(int v: adj[u]){
            if(c[u]!=c[v]){ border = true; continue; }
            if(visited[v]) continue;
            visited[v] = true;
            q.push(v);
        }
        if(!border) continue;
        if(border_node) return no();
        border_node = u;
    }

    // Check if tree's good if rooted at border_node
    if(border_node!=1 && is_good(border_node)) return yes(border_node);

    // Check if tree's good if rooted at node on opposite side of bordder
    int opposite_border_note = 0;
    for(int u: adj[border_node]){
        if(c[u]==c[border_node]) continue;
        if(opposite_border_note) return no();
        opposite_border_note=u;
    }
    if(is_good(opposite_border_note))
        return yes(opposite_border_note);
    return no(); 
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}