// Link: https://codeforces.com/problemset/problem/1352/A

#include <bits/stdc++.h>
using namespace std;

void sum_round_nums(){
    vector<int> nums;
    int n,k=0;

    cin >> n;

    for(int d=10;n>0;d*=10){
        int r=n%d;
        if(r){
            nums.push_back(r);
            k++;
        }
        n-=r;
    }
    cout << k << endl;
    for(int i=nums.size()-1;i>=0;i--){
        cout << nums.at(i) << ' ';
    }
    cout << endl;
}

void solve(){
    /* Input number as string, print each char an corresponding 0s */
    int poscount=0;
    string n;
    cin >> n;
    for(int i=0;i<n.size();i++) if(n[i]!='0') poscount++;
    cout << poscount << "\n";
    for(int i=n.size()-1;i>=0;i--){
        if(n[i] == '0') continue;
        cout << n[i];
        for(int j=0;j<n.size()-i-1;j++) cout << "0";
        cout << " ";
    }
    cout << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t;
    cin >> t;
    while(t--){
        // sum_round_nums();
        solve();
    }
}