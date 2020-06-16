#include <bits/stdc++.h>

using namespace std;

int n, k, ai;
vector<int> a;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> k;
    for(int i=0; i<n; ++i){
        cin >> ai;
        a.push_back(ai);
    }
    sort(a.begin(), a.end());
    if(k==0){
        if(a[0]==1) cout << "-1\n";
        else cout << a[0]-1 << "\n";
        return 0;
    }
    if(k==n){
        cout << a[k-1] << "\n";
        return 0;
    }
    if(k<n && k>0 && a[k-1]==a[k]) {
        cout << "-1\n";
        return 0;
    }
    cout << a[k-1] << "\n";
}