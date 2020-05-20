// Link: https://codeforces.com/problemset/problem/1352/A

#include <bits/stdc++.h>
using namespace std;

void sum_round_nums(int n){
    vector<int> nums;
    int k=0;

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

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t,n;
    cin >> t;
    while(t--){
        cin >> n;
        sum_round_nums(n);
    }
}