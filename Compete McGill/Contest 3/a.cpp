#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const ll L=1000000000LL;
const int N=100000;
int n, m, k, u, v, l, ai, cur_x, i;
ll min_l=L;
vector<pair<int, ll>> adj[N+1];
unordered_set<int> a;

void dfs(int x, ll cur_l){
    if(!x) x=ai;
    if(cur_l>min_l) return;
    else if(a.find(x)==a.end()){ // x not storage
        min_l=min(min_l, cur_l);
        return;
    }
    for(auto ui: adj[x]){
        dfs(ui.first, cur_l + ui.second);
    }
}

void solve(){
    cin >> n >> m >> k;

    if(!k){ cout << "-1\n"; return; }

    for(i=0; i<m; ++i){
        cin >> u >> v >> l;
        adj[u].push_back({v, l});
        adj[v].push_back({u, l});
    }

    // for(i=1; i<=n; ++i){
    //     cout << i << ": ";
    //     for(auto x: adj[i]) cout << x.first << " " << x.second << ", ";
    //     cout << "\n";
    // }

    for(i=0; i<k; ++i){
        cin >> ai;
        if(k==1 && !adj[ai].size()){ cout << "-1\n"; return; }
        a.insert(ai);
    }


    for(auto x: a){
        ai=x;
        sort(adj[ai].begin(), adj[ai].end());
        cur_x=0;
        for(auto x: adj[ai]){
            if((!cur_x && x.first==cur_x) || x.second>min_l) continue;
            cout << "ok\n";
            dfs(x.first, x.second);
        }
    //     // int cur_x=0, cur_l=0;
    //     // for(auto x: adj[ai]){
    //     //     if(cur_x && x.first==cur_x) continue;
    //     //     if(a.find(x.first)==a.end()){ // x not storage
    //     //         min_l=min(min_l, x.second);
    //     //         if(min_l==1){ cout << "1\n"; return; }
    //     //         cur_x=x.first;
    //     //     } else{ // x storage
    //     //         if(x.second>min_l) continue; // path already longer than found minimum
    //     //         cur_l=x.second;
                
    //     //     }
    //     // }
    }
    cout << min_l << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}