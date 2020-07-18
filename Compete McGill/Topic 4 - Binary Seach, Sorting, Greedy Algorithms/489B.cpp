// https://codeforces.com/problemset/problem/489/B

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
#define print(name, var) cout << name << ": " << var << '\n';
#define piter(name, iter) cout << name << ": "; for(auto x: iter) cout << x << " "; cout << '\n';
using namespace std;

int n, m;

void solve(){
    cin >> n;
    vector<int> a(n);
    for(auto &ai: a) cin >> ai;

    cin >> m;
    vector<int> b(m);
    for(auto &bi: b) cin >> bi;

    sort(a.begin(), a.end());
    sort(b.begin(), b.end());

    int ans=0, i=0, j=0;
    while(i<a.size() && j<b.size()){
        if(abs(a[i]-b[j])<=1){
            ++ans, ++i, ++j;
            continue;
        }
        if(a[i]<b[j]) ++i;
        else ++j;
    }
    cout << ans << '\n';
}

int main(){
    speedup();
    solve();
}