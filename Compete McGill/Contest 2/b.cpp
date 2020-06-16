#include <bits/stdc++.h>

using namespace std;

int n, ai, bi, ci, a_found, b_found;
vector<int> a, b, c;


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> n;
    for(int i=0; i<n; ++i){
        cin >> ai;
        a.push_back(ai);
    }
    for(int i=0; i<n-1; ++i){
        cin >> bi;
        b.push_back(bi);
    }
    for(int i=0; i<n-2; ++i){
        cin >> ci;
        c.push_back(ci);
    }
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    sort(c.begin(), c.end());

    for(int i=0; i<n && !(a_found && b_found); ++i){
        if(!a_found && i<n-1 && a[i]!=b[i]) a_found=a[i];
        if(!b_found && i<n-2 && b[i]!=c[i]) b_found=b[i];
    }
    if(!a_found) a_found=a[n-1];
    if(!b_found) b_found=b[n-2];
    cout << a_found << "\n" << b_found << "\n";
}