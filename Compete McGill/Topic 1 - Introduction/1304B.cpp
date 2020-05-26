// Link: https://codeforces.com/problemset/problem/1304/B

#include <bits/stdc++.h>
#define print(iter,n) for(int i=0;i<n;i++){cout << iter[i] << " ";}; cout << "\n";
using namespace std;

bool is_palin(string a, string b){
    if(a.size()!=b.size()) return false;
    for(int i=0;i<a.size();i++){
        if(a[i]!=b[b.size()-i-1]) return false;
    }
    return true;
}

void solve() {
    int n, m, len = 0, pal_idx = 0;
    string s;
    vector<string> ss; // all strings
    cin >> n >> m;
    int self = -1,     // index of a string that's itself a palindrome; self == i means string ss[i] is itself a palindrome
        pair[n] = {0}, // pair[i] == pair[j] means strings i,j are palindromes of each other
        pal[2*n]; memset(pal, -1, sizeof(pal)); // final palindrome, pal[i] is string index in ss
    
    while (n--) {
        cin >> s;
        ss.push_back(s);
    } n=ss.size();
    
    // fill pair[] and pal[]
    for (int i = 0; i < n; i++) {
        if (pair[i]) continue;
        if (is_palin(ss[i],ss[i])) self=i;
        for (int j = i+1; j < n; j++){
            if (pair[i] or !is_palin(ss[i],ss[j])) continue; // only proceed if new pair of palindromes found
            if (self == i || self == j) self = -1;
            pair[i] = pair[j]  = 1;
            pal[pal_idx]       = i;
            pal[2*n-pal_idx-1] = j;
            len += 2*m; // increase final palindrome length
            pal_idx++;
        }
    }
    if (self > -1) {
        pal[pal_idx] = self;
        len += m;
    }

    // output
    cout << len << "\n";
    for (int i = 0; i < 2*n; i++) {
        if (pal[i] < 0) continue;
        cout << ss[pal[i]];
    }
    cout << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}