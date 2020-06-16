#include <bits/stdc++.h>
// #define fill(iter) for(int i=1; i<=n; i++) cin >> iter[i]

using namespace std;

// const int N=100000;
int n, ai, mi=100000, ma=1, ma_prev=1;
vector<int> a;
// bool max_dup=false;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    if(n==2){ cout << 0 << "\n"; return 0; }
    for(int i=0; i<n; ++i){
        cin >> ai;
        a.push_back(ai);
    }
    sort(a.begin(), a.end());
    cout << min(a[n-2]-a[0], a[n-1]-a[1]) << "\n";

    // for(int i=0; i<n; ++i){
    //     cin >> a;
    //     // cout << "a: " << a << "\n";
    //     if(a>=ma){
    //         if(i==0){
    //             ma=a;
    //         }
    //         else if(a==ma){
    //             ma_prev=ma;
    //             max_dup=true;
    //         }
    //         else{
    //             ma_prev=ma;
    //             ma=a;
    //             max_dup=false;
    //         }
    //     }
    //     if(a<mi) mi=a;
    //     // cout << mi << "\n";
    //     // cout << ma_prev << "\n";
    //     // cout << ma << "\n";
    // }
    // cout << ma_prev-mi << "\n";
}