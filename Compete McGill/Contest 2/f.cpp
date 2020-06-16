#include <bits/stdc++.h>

using namespace std;

int t;
string s;
map<char, int> m;

void solve(){
    cin >> s;
    if(s.length()<3){ cout << "0\n"; return; }
    // Count 0s and 1s
    m['0']=0;
    m['1']=0;
    for(int i=0; i<s.length(); ++i){
        if(s[i]=='0') m['0']++;
        else m['1']++;
    }
    if(s[0]==s.back() && m[s[0]]==2){
        cout << "1\n";
        return;
    }
    if(s[0]!=s.back() && (m[s[0]]==1 || m[s.back()]==1)){
        cout << "0\n";
        return;
    }
    // if((    m['0']==1 && (s[0]=='0' || s[s.length()-1]=='0'))
    //     || (m['1']==1 && (s[0]=='1' || s[s.length()-1]=='1'))){
    //     cout << "0\n";
    //     return;
    // }
    if(s[0]==s.back()){
        cout << "here1\n";
        cout << min(m[s[0]]-1,m[s[0]=='1'?'0':'1']) << "\n";
        return;
    }
    cout << "here2\n";
    cout << min(m['0']-1, m['1']-1) << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;
    while(t--) solve();
}