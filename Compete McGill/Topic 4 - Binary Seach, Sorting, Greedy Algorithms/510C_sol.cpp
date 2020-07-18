// https://codeforces.com/problemset/problem/510/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
#define print(name, var) cout << name << ": " << var << '\n';
using namespace std;

const int N=100;
int n, cnt, vis[26], topo[26];
string names[N];
vector<int> adj[26];

bool construct(string s, string t){
    int len=min(s.size(), t.size());
    for(int i=0; i<len; ++i){
        if(s[i]==t[i]) continue;
        adj[s[i]-'a'].push_back(t[i]-'a');
        return true;
    }
    return s.size()<t.size();
}

bool dfs(int u){
    /**
     * Detects cycle
     **/
    vis[u]=1; // under processing
    for (auto v: adj[u]) {
        if (vis[v]==1) return true; // cycle detected
        if (!vis[v] && dfs(v)) return true;
    }
    vis[u]=2; // processed
    topo[cnt++]=u;
    return false;
}

bool Find(){    
    for (int i=0; i<26; ++i)
        if(!vis[i] && dfs(i))
            return true;

    return false;
}

bool ok(){
    for(int i=1; i<n; i++)
        if(!construct(names[i-1], names[i]))
            return false;

    if(Find()) return false;
    return true;
}

void solve(){
    /**
     * Find topological sort
     */
    cin >> n;
    for(int i=0; cin >> names[i]; ++i);
    if(!ok()){ cout << "Impossible\n"; return; }

    for(int i=cnt-1; i>=0; --i)
        cout << (char)(topo[i]+'a');
    cout << '\n';
}

int main(){
    speedup();
    solve();
}