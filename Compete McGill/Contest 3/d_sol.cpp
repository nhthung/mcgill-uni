#include <bits/stdc++.h>
using namespace std;
#define speedup() ios::sync_with_stdio(0); cin.tie(0)

typedef long long ll;

const int N=100001;
int n, m, p[N], rnk[N];
ll ans, c[N];

int rep(int i){
    return p[i]==i? i : rep(p[i]);
}

int main(){
    speedup();

    cin >> n >> m;
    for(int i=1; i<=n; ++i){
        cin >> c[i];
        p[i]=i;
    }
    for(int a, b, x, y; cin >> a >> b;){
        a=rep(a), b=rep(b);
        if(a==b) continue;
        if(rnk[a]>rnk[b]) swap(a, b);
        p[a]=b;
        c[b]=min(c[a], c[b]);
        if(rnk[a]==rnk[b]) ++rnk[b];
    }

    for(int i=1; i<=n; ++i)
        if(p[i]==i)
            ans+=c[i];
    cout << ans << '\n';
}