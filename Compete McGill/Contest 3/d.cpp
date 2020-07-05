#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
typedef long long ll;
using namespace std;

const int N=100001;
int n, m;
ll ans, c[N];
// vector<int> adj[N];
bitset<N> bs;

int main(){
    speedup();

    cin >> n >> m;
    for(int i=1; i<=n; ++i) cin >> c[i];
    vector<tuple<ll, int, int>> edges(m);
    for(auto &[w, u, v]: edges){
        cin >> u >> v;
        w=min(c[u], c[v]);
    }
    sort(edges.begin(), edges.end());

    for(auto [w, u, v]: edges){
        if(!bs[u] && !bs[v])
            ans += w;
        bs[u]=true;
        bs[v]=true;
    }

    for(int i=1; i<=n; ++i){
        if(bs[i]) continue;
        ans += c[i];
    }
    cout << ans << '\n';
}