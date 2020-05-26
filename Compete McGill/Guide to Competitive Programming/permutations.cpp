#include <bits/stdc++.h>
using namespace std;

const int n=3;
bool chosen[n];
vector<int> permutation;

void search() {
    if(permutation.size()==n){
        for(int i=0;i<n;i++)
            cout << permutation.at(i) << " ";
        cout << "\n";
    } else {
        for(int i=0;i<n;i++){
            if(chosen[i]) continue;
            chosen[i]=true;
            permutation.push_back(i+1);
            search();
            chosen[i]=false;
            permutation.pop_back();
        }
    }
}

void builtin(){
    for (int i = 1; i <= n; i++) {
        permutation.push_back(i);
    }
    do {
        for(int i=0;i<n;i++)
            cout << permutation.at(i) << " ";
        cout << "\n";
    } while (next_permutation(permutation.begin(),permutation.end()));
}

int main(){
    search();
    // builtin();
}