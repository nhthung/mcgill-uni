#include <bits/stdc++.h>
using namespace std;

int main(){
    map<int,int> m;
    m[1]=10;
    m[2]=1;
    m[10]=1;
    m[5]=2;
    cout << m.rbegin()->first << "\n";
    for(auto i=m.rbegin();i!=m.rend();i++){
        cout << i->first << "\n";
    }
}