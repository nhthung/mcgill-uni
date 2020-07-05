#include <bits/stdc++.h>
using namespace std;
#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N=30001;
int n, t, i, a[N];

int main(){
    speedup();

    cin >> n >> t;
    for(i=1; cin >> a[i++];);
    for(i=1; i<t; i+=a[i]);
    cout << (i==t? "YES" : "NO") << '\n';
}