#include <bits/stdc++.h>
using namespace std;

const int N=100001;
int n, m, k;
bitset<N> a;

void solve(){
    cin >> n >> m >> k;

    if(!k){ cout << "-1\n"; return; }

    vector<tuple<int, int, int>> edges(m);

    for(auto &[w, u, v]: edges) cin >> u >> v >> w;
    sort(edges.begin(), edges.end());

    for(int i=0, x; i<k; ++i){ cin >> x; a[x]=true; }

    for(auto [w, u, v]: edges){
        if(a[u]==a[v]) continue;
        cout << w << '\n';
        return;
    }
    cout << "-1\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}