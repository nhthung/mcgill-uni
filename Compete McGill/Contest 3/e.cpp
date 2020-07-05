#include <bits/stdc++.h>
using namespace std;

#define speedup() ios::sync_with_stdio(0); cin.tie(0)

const int N=1001;
int n, p[N];
bitset<N> bs;

int main(){
    speedup();

    cin >> n;
    for(int i=1; i<=n; ++i) cin >> p[i];
    for(int i=1, j=i; i<=n; bs.reset(), ++i, j=i){
        do{
            bs[j]=true;
            j=p[j];
        } while(!bs[j]);
        cout << j << " ";
    }
}