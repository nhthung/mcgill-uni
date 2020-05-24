// Link: https://codeforces.com/problemset/problem/1340/A

#include <bits/stdc++.h>
typedef long long ll;
#define print(iter,n) for(int i=1;i<=n;i++){cout << iter[i] << " ";}; cout << "\n";
#define printv(v) for(int i=0;i<v.size();i++){cout << v[i] << " ";} cout << "\n";
#define fo(i,s,n) for(int i=s;i<=n;i++)
using namespace std;

const int N=100000;
int r[N+1],c[N+1],
    // p[N+1],
    p,
    m[N+1],     // maps to p, m[i] is index of i in p
    // cur_p[N+1], // current permutation
    c_count[N+1],
    c_max=1;
// vector<int> c_max;
// unordered_map<int,int> c_count;
// map<int,int> c_count;

void reset(int n){
    fo(i,1,n){
        r[i]=i;
        c[i]=1;
        c_count[i]=0;
    }
    c_max=1;
    c_count[1]=n;
    // c_count.clear();
    // c_count[1]=n;
    // c_max.clear();
    // c_max.push_back(1);
}

// bool in_c_count(int k){
//     for(auto i=c_count.rbegin();i!=c_count.rend();i++){
//         if(i->first==k) return true;
//         if(i->first<k) return false;
//     }
//     return false;
// }

void step(int i,int n){
    int r_val=r[m[i]],
        r_val_right=m[i]<n?r[m[i]+1]:0;

    // cout << "r_val: " << r_val << "\n";
    // cout << "r_val_right: " << r_val_right << "\n";

    for(int j=m[i];j>=1 && r[j]==r_val;j--) {
        r[j]=r_val_right;
        if(c[j]) c_count[c[j]]--;
        
        // if(c_count[c[j]]==0) c_count.erase(c[j]); // remove from c_count if value 0
    }
    if(m[i]<n && r_val_right){
        c_count[c[r_val_right]]--;
        // if(c_count[c[r_val_right]]==0) c_count.erase(c[r_val_right]); // // remove from c_count if value 0
    
        c[r_val_right]+=c[m[i]];

        // if(in_c_count(c[r_val_right]))
        c_count[c[r_val_right]]++; // incr count if in already in c_count
        // else
        //     c_count[c[r_val_right]]=1; // new pair
        
        c_max=max(c[r_val_right],c_max); // c_max int approach
        // if(c[r_val_right]>c_max.back()) c_max.push_back(c[r_val_right]); // c_max vector approach
        // if(c[r_val_right]>c_count.rbegin()->first) c_count[c[r_val_right]]=1; // c_count map: add new max to map, count 1
    }
    c[m[i]]=0;
    while(c_max>=1 && c_count[c_max]==0) c_max--;
    // while(c_max.size()>=1 && c_count[c_max.back()]==0) c_max.pop_back(); // c_max vector approach
    // cout << "c_max: "; printv(c_max);
}

void solve(){
    int n;
    cin >> n;
    if(n==1){cout << "Yes\n"; cin >> p; return;}
    fo(i,1,n){
        // cin >> p[i];
        // m[p[i]]=i;   // build mapping
        cin >> p;
        m[p]=i;   // build mapping
    }
    reset(n); // reset r, c (count), cur_p
    fo(i,1,n){
        // check valid
        // c_max=*max_element(c+1,c+n+1);
        // cout << "cur_p: "; print(cur_p,n);
        // cout << "r: "; print(r,n);
        // cout << "c: "; print(c,n);
        // cout << "c_count: "; print(c_count,n);
        // cout << "c_max: " << c_max << "\n";
        // cout << "m: "; print(m,n);
        // cout << "m[i]: " << m[i] << "\n";
        // cout << "c[m[i]]: " << c[m[i]] << "\n";

        if(c_max!=c[m[i]]){cout << "No\n"; return;} // c_max int
        // if(c_max.back()!=c[m[i]]){cout << "No\n"; return;} // c_max vector
        // if(c_count.rbegin()->first!=c[m[i]]) {cout << "No\n"; return;} // c_count map


        // cur_p[m[i]]=i;
        
        // update r and c
        step(i,n);
    }
    cout << "Yes\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t;
    cin >> t;
    while(t--){
        // cout << "\ntest " << 5-t << ":\n";
        solve();
    }
}