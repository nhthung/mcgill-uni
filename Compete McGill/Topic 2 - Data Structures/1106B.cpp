#include <bits/stdc++.h>
#define fo(i, n) for(int i=1; i<=n; i++)
#define fill(iter, n) fo(i, n) cin >> iter[i]
 
typedef long long ll;
using namespace std;
 
const int N=100000;
int n, m, d, t;
ll a[N+1], c[N+1];
vector<pair<int,int>> c_idx;
 
void solve(){
    int leave;
    ll cost;
    auto cheapest=c_idx.begin();
 
    while(m--){
        cin >> t >> d;        
        if(d<=a[t]){
            a[t]-=d;
            cout << d*c[t] << "\n";
            continue;
        }
        // if d>a[t]
        cost=a[t]*c[t];
        d-=a[t];
        a[t]=0;
        leave=0;
        while(d){
            if(cheapest==c_idx.end()){
                cout << 0 << "\n";
                leave=1;
                break;
            }
            t=cheapest->second;
            if(d<=a[t]){
                cost+=d*c[t];
                a[t]-=d;
                break;
            }
            // if d>a[t]
            cost+=a[t]*c[t];
            d-=a[t];
            a[t]=0;
            cheapest++;
        }
        if(!leave) cout << cost << "\n";
    }
}
 
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
 
    cin >> n >> m;
    fill(a, n);
    fo(i, n){
        cin >> c[i]; // fill c
        c_idx.push_back(make_pair(c[i], i)); // build c_map
    }
    sort(c_idx.begin(), c_idx.end());    
    solve();
}
