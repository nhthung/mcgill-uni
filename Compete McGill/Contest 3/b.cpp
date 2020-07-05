#include <bits/stdc++.h>
using namespace std;

int n, ans;

void solve(){
    cin >> n;
    vector<int> bills {100, 20, 10, 5, 1};
    for(int d: bills){
        ans+=n/d;
        n%=d;
    }
    cout << ans << '\n';
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}