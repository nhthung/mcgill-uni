// Link: https://codeforces.com/group/zGkaRYSeWm/contest/282369/problem/0

#include <bits/stdc++.h>
#define print(iter,n) for(int i=0;i<n;i++){cout << iter[i];}; cout << "\n";
using namespace std;

int t,n,a,b,c;
string bob;

void solve(){
    // vector<string> alice;
    // int abc[3]={0};
    vector<int> empty;
    cin >> n >> a >> b >> c >> bob;
    int win=0;
    char alice[n];
    for(int i=0;i<bob.size();i++){
        if(bob[i]=='R'){
            // abc[1]++;
            if(b){
                alice[i]='P';
                b--;
                win++;
                continue;
            }
            empty.push_back(i);
            // if(a){alice[i]='R'; a--; continue;}
            // if(c){alice[i]='S'; c--; continue;}
        }else if(bob[i]=='P'){
            if(c){
                alice[i]='S';
                c--;
                win++;
                continue;
            }
            empty.push_back(i);
            // if(a){alice[i]='R'; a--; continue;}
            // if(b){alice[i]='P'; a--; continue;}
        }else if(bob[i]=='S'){
            if(a){
                alice[i]='R';
                // cout << "alice[" << i << "]: " << alice[i] << "\n";
                a--;
                win++;
                continue;
            }
            empty.push_back(i);
            // if(b){alice[i]='P'; b--; continue;}
            // if(c){alice[i]='S'; c--; continue;}
        }
    }
    int min_win=n%2?n/2+1:n/2;
    if(win>=min_win){
        while(empty.size()){
            int i=empty.back();
            empty.pop_back();
            if(a){
                alice[i]='R';
                a--;
            }else if(b){
                alice[i]='P';
                b--;
            }else if(c){
                alice[i]='S';
                c--;
            }
        }
        cout << "YES\n";
        print(alice,n);
    }else{
        cout << "NO\n";
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> t;
    while(t--) solve();
}