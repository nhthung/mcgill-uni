#include <bits/stdc++.h>

using namespace std;

string a, b;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> a >> b;
    if(!a.compare(b)) cout << "-1\n";
    else cout << max(a.length(), b.length()) << "\n";
}