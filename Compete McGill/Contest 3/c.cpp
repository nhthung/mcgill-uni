#include <bits/stdc++.h>
#define print(iter_name, iter) cout << iter_name << ": "; for(auto x: iter) cout << x << " "; cout << '\n';
using namespace std;

const int N=100001;
int n, c[N];
vector<int> adj[N];
bitset<N> bs;

bool bfs(int root, int pa){
    queue<int> q;
    bs[root]=true;
    q.push(root);
    while(!q.empty()){
        int u=q.front(); q.pop();
        for(int v: adj[u]){
            if(v==pa) continue;
            if(bs[v]) continue;
            if(c[v]!=c[root]) return false;
            bs[v]=true;
            q.push(v);
        }
    }
    return true;
}

void yes(int ans){
    cout << "YES\n" << ans << '\n';
}

void no(){
    cout << "NO\n";
}

bool check(int u){
    /**
     * Check if tree's good if rooted at u
     */
    bool res=true;
    bs.reset();
    for(int v: adj[u])
        res&=bfs(v, u);
    return res;
}

void solve(){
    cin >> n;
    for(int i=0, u, v; i<n-1; ++i){
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    for(int i=1; i<=n; ++i) cin >> c[i];

    if (check(1)) return yes(1);

    queue<int> q;
    bs.reset();
    bs[1]=true;
    q.push(1);
    int pot=0;
    while(!q.empty()){
        int u = q.front(); q.pop();
        cout << "u: " << u << '\n';
        bool border = false;
        for(int v: adj[u]){
            cout << "v: " << v << '\n';
            if(c[v] != c[u]){
                cout << "border true, u=" << u << '\n';
                border = true;
            } else if(!bs[v]){
                cout << "push to queue\n";
                bs[v] = true;
                // if(adj[v].size()>1) q.push(v);
                q.push(v);
            }
        }
        if(!border) continue;
        if(pot) return no();
        pot=u;
    }

    // cout << "pot: " << pot << '\n';

    if (pot!=1 && check(pot)) return yes(pot);
    int adjj=0;
    for(int v: adj[pot]){
        if(c[v]==c[pot]) continue;
        if(adjj) return no();
        adjj=v;
    }
    if (check(adjj)) return yes(adjj);
    return no();
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}