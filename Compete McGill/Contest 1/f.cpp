// Link: https://codeforces.com/group/zGkaRYSeWm/contest/282369/problem/F

#include <bits/stdc++.h>
#define print(iter,n) for(int i=1;i<=n;i++){cout << iter[i] << " ";}; cout << "\n";
#define fo(i,n) for(int i=1;i<=n;i++)
using namespace std;

const int N=100000;
int t,n,a[N+1],b[N+1];
// int c[N+1];

void solve(){
    int d=0,d_cur=0,gap=0;
    cin>>n;
    fo(i,n) cin>>a[i];
    fo(i,n) cin>>b[i];
    // cout<<"a: "; print(a,n);
    // cout<<"b: "; print(b,n);
    fo(i,n){
        d_cur=b[i]-a[i];
        if(d_cur<0){
            cout<<"NO\n";
            return;
        }
        if(d_cur>0){
            if(d==0){ // first positive num
                d=d_cur;
                continue;
            }
            if(d_cur!=d || gap==1){
                // cout<<"i: "<<i<<"\n";
                // cout<<"d_cur: "<<d_cur<<"\n";
                // cout<<"d: "<<d<<"\n";
                // cout<<"d_cur!=d: "<<bool(d_cur!=d)<<"\n";
                // cout<<"gap: "<<gap<<"\n";
                cout<<"NO\n";
                return;
            }
            continue;
        }
        if(d_cur==0 && d>0 && gap==0){
            gap=1;
        }
    }
    cout<<"YES\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin>>t;
    while(t--) solve();
}