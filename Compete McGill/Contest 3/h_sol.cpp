#include <bits/stdc++.h>
using namespace std;
 
const int MN = 100012;

int n;
vector<int> g[MN];
int cost[MN];
vector<int> res;

void dfs (int u, int p = 0) {
  if (u>1 && g[u].size()==1) {
    cost[u] = 1;
  }
  for (int v : g[u]) {
    if (v == p) continue;
    dfs(v, u);
    cost[u] += cost[v];
  }
  res.push_back(cost[u]);
}

int main (void) {
  ios::sync_with_stdio(false); cin.tie(0);
  cin >> n;
 
  if (n == 1) {
    cout << 1 << '\n';
    return 0;
  }
 
  for (int u = 2; u <= n; u++) {
    int v; cin >> v;
    g[u].push_back(v);
    g[v].push_back(u);
  }
  
//   for(int i=1; i<=n; ++i){ cout << i << ": "; for(auto u: g[i]) cout << u << " "; cout << '\n'; }
 
  dfs(1);
  sort(res.begin(), res.end());
//   for(int i=1; i<=n; ++i) cout << cost[i] << " "; cout << '\n';
  for (int x : res)
    cout << x << ' ';
  cout << '\n';
  return 0;
}