#include <bits/stdc++.h>
using namespace std;

void bit(int x){
    for (int k = 31; k >= 0; k--) {
        if (x&(1<<k)) cout << "1";
        else cout << "0";
    }
    cout << "\n";
}

int main(){
    int x=12;
    bit(x);
    x&=x-1;
    bit(x);
}