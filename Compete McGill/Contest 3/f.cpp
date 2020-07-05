#include <bits/stdc++.h>
using namespace std;

#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N = 10001;
 
int n, ans, p[N], rnk[N];
 
int rep(int i){
    return p[i]==i ? i : rep(p[i]);
}
 
int main(){
    speedup();

    cin >> n;
    for(int i=1; p[i]=i, rnk[i]=0, i<=n; ++i);
    ans = n;
    
    for (int v, u=1; u<=n; ++u){
        cin >> v;
        int x=rep(u), y=rep(v);
        if(x==y) continue;
        if(rnk[x] > rnk[y]) swap(x, y);
        p[x] = y;
        if(rnk[x] == rnk[y]) rnk[y]++;
        --ans;
    }
    cout << ans << '\n';
}
 