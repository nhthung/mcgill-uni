// https://codeforces.com/problemset/problem/371/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
using namespace std;
typedef long long ll;

string s;
ll r, n[3], p[3], recipe[3];

void solve(){
    int i=0;
    for(auto c: s){
        switch(c){
            case 'B': i=0; break;
            case 'S': i=1; break;
            case 'C': i=2; break;
        }
        ++recipe[i];
    }

    // Make burgers with current ingredients if possible
    ll ans=LLONG_MAX;
    for(i=0; i<3; ++i){
        if(!recipe[i]) continue; // Avoid division by 0
        ans=min(ans, n[i]/recipe[i]);
    }

    if(ans) for(i=0; i<3; ++i) n[i]-=ans*recipe[i];

    // Now at least 1 ingredient is not enough for a whole burger
    int price=0;
    while(true){
        price=0;
        for(i=0; i<3; ++i){
            if(!recipe[i] or n[i]>=recipe[i]) continue;
            price+=p[i]*(recipe[i]-n[i]);
            n[i]+=recipe[i]-n[i];
        }

        // Not enough money. Return
        if(price>r){ cout << ans << '\n'; return; }

        // Make another burger
        for(int i=0; i<3; ++i) n[i]-=recipe[i];
        r-=price;
        ++ans;

        bool stop=1;
        for(int i=0; i<3; ++i){
            if(recipe[i] and n[i]){
                stop=0;
                break;
            }
        }
        if(stop) break;
    }

    // Get price of ingredients for one full burger
    price=0;
    for(i=0; i<3; ++i) price+=p[i]*recipe[i];

    // Make rest of the burger with remaining money
    ans+=r/price;

    cout << ans << '\n';
}

int main(){
    speedup();
    cin >> s;
    for(auto &ni: n) cin >> ni;
    for(auto &pi: p) cin >> pi;
    cin >> r;
    solve();
}