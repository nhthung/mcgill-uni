// Link: https://codeforces.com/problemset/problem/1324/C

#include <bits/stdc++.h>

using namespace std;

int t, d;
string s;

void solve(){
    /**
     * Count consectutive L's, find longest chain of L's in one pass,
     * return length of longest chain + 1
     **/
    cin >> s;
    int gap=0, max_gap=0;
    for(int i=0; i<=s.size(); i++){
        if(i<s.size() && s[i]=='L'){
            gap++;
            continue;
        }
        max_gap=max(gap, max_gap);
        gap=0;
    }
    cout << max_gap+1 << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;
    while(t--) solve();
}
