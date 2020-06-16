#include <bits/stdc++.h>

using namespace std;

int n;
string s;
map<string, int> m;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    while(n--){
        cin >> s;
        if(m.count(s)){
            m[s]++;
            cout << s << m[s] << "\n";
            continue;
        }
        m[s]=0;
        cout << "OK\n";
    }
}