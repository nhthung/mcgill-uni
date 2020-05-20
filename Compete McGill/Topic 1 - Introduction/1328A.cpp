// Link: https://codeforces.com/problemset/problem/1328/A

#include <bits/stdc++.h>
using namespace std;

void div(){
    int a,b,q,incr_count;
    cin >> a >> b;

    q = a/b;
    if(a%b) incr_count=(q+1)*b-a;
    else incr_count=q*b-a;
    cout << incr_count << endl;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int t,n;
    cin >> t;
    while(t--){
        div();
    }
}