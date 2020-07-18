// https://codeforces.com/problemset/problem/510/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
#define print(name, var) cout << name << ": " << var << '\n';
using namespace std;
// typedef long long ll;
// typedef long double ld;

int n, idx, al[26];
bitset<26> bs;

bool compare(string s, string t){
    // Return char coming first
    string a=s, b=t;
    if(a.size()>b.size()) swap(a, b); // a not longer than b 
    for(int i=0; i<b.size(); ++i){
        if(i==a.size()){ // a prefix of b
            // todo
            break;
        }
        if(a[i]==b[i]){
            ++idx;
            continue;
        }
        if(bs[s[i]-97]) return false;
        al[idx]=(int)s[i]; bs[s[i]-97]=1;
        if(!al[idx+1]) al[idx+1]=(int)t[i];
        break;
        // print("al[idx]", (char)al[idx]);
    }
    idx++;
    return true;
    // print("a", a);
    // print("b", b);
    // print("s", s);
    // print("t", t);
}

void solve(){
    cin >> n;
    vector<string> name(n);
    for(auto &s: name) cin >> s;
    // for(auto s: name) cout << s << " "; cout << '\n';
    
    for(int i=0; i<n-1; ++i){
        if(!compare(name[i], name[i+1])){
            cout << "Impossible\n";
            return;
        }
        for(int i=0; i<26; ++i) cout << (char)(al[i] ? al[i] : 48); cout << '\n';
    }

    // for(int i=0; i<26; ++i){
    //     if(al[i]){
    //         int ali=al[i]-97;
    //         // print("al[i]", (char)al[i]);
    //         // print("i", (char)(i+97));
    //         // print("ali", (char)(ali+97));
    //         // if(!al[ali]) al[ali]=i+97;
    //         // print("al[ali]", (char)al[ali]);
    //         // al[ali]=i+97;
    //         if(!al[ali]) al[ali]=i+97;
    //         else{
    //             int x=al[ali];
    //             // print("x", (char)x);
    //             // print("al[x-97]", (char)al[x-97]);
    //             // print("al[x-97]==ali", (bool)(al[x-97]==ali+97));
    //             while(al[x-97]!=ali+97){
    //                 x=al[x-97];
    //                 // print("x", (char)x);
    //             }
    //             // print("x", (char)x);
    //             if(x!=i+97) al[x-97]=i+97;
    //             // print("al[x-97]", (char)al[x-97]);
    //             // print("al[x-97]==ali+97", (bool)(al[x-97]==ali+97));
    //         }
    //         // print("al[ali]", (char)al[ali]);
    //     }
    //     // else al[i]=i+97;
    //     // else al[i]=48;
    //     // cout << (char)al[i];
    //     // for(int i=0; i<26; ++i) cout << (char)(al[i] ? al[i] : 48); cout << '\n';
    // }
    // // for(int i=0; i<26; ++i) cout << (char)(al[i] ? al[i] : 48); cout << '\n';
    // for(int i=0; i<26; ++i) cout << (char)(al[i] ? al[i] : i+97);
    // cout << '\n';
    // // print("z", (int)'z');
}

int main(){
    speedup();
    // solve();
    // cout << "S\n";
    cout << (bool)0;
}