// https://codeforces.com/problemset/problem/20/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
using namespace std;
typedef long long ll;

const ll N=200001;
int n, m, previous[N];
vector<pair<int, ll>> adj[N]; // adj[u]: (v, w)

void dijkstra(int start){
    priority_queue<pair<ll, int>> q; // contains pairs of the form (−d, x)
    ll dist[n+1];
    bool processed[n+1];

    for (int i=1; i<=n; ++i) {
        dist[i]=LLONG_MAX;
        previous[i]=0; // undefined previous
        processed[i]=false;
    }

    dist[start] = 0;
    q.push({0, start});
    while(!q.empty()) {
        int a = q.top().second; q.pop();
        if(processed[a]) continue;
        processed[a] = true;
        for(auto u : adj[a]) {
            int b = u.first;
            ll w = u.second;

            if(dist[a]+w>=dist[b]) continue;
            dist[b] = dist[a]+w;
            previous[b] = a;
            q.push({-dist[b], b});
        }
    }
}

int main(){
    speedup();
    
    cin >> n >> m;
    for(ll u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w}), adj[v].push_back({u, w}));

    dijkstra(1);

    vector<ll> path;
    for(ll u=n; u>=1; u=previous[u]){
        if(u>1 && previous[u]==0){
            cout << "-1\n";
            return 0;
        }
        path.push_back(u);
    }
    reverse(path.begin(), path.end());
    for(auto u: path) cout << u << " "; cout << '\n';
}