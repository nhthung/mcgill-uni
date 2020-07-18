// https://codeforces.com/problemset/problem/492/C

#include <bits/stdc++.h>
#define speedup() ios::sync_with_stdio(0); cin.tie(0)
using namespace std;
typedef long long ll;
typedef long double ld;

const int N=1e5;
ll n, r, a[N], b[N];
ld avg;
vector<pair<ll, int>> v; // vector of (b[i], i) pairs

bool zero(ld grade){
    return abs(grade)<1e-9;
}

bool valid(ll essays){
    ld grade=0; // Max extra points to the avg
    for(auto [bi, i]: v){
        if(a[i]==r) continue;
        ll ptns=min(essays/bi, r-a[i]);

        grade+=ptns;
        essays-=ptns*b[i];
        if(!essays) break;
    }
    grade/=n;

    // Check if avg+grade >= 0
    grade+=avg;
    return grade>=0 || zero(grade) ? 1 : 0; // zero() to compare with epsilon in case of float error
}

void solve(){
    cin >> n >> r >> avg;
    
    // Get current average grade
    ld grade=0;
    for(int i=0; i<n; ++i){
        cin >> a[i] >> b[i];
        v.push_back({b[i], i});
        grade+=a[i];
    }
    grade/=n;

    // Stop if average grade aready enough
    avg=grade-avg;
    if(avg>=0){ cout << "0\n"; return; }

    // Now, avg < 0

    // Sort vector of (b[i], i) pairs
    sort(v.begin(), v.end());
    
    // Find upper bound z
    ll z=0;
    for(int i=0; i<n; ++i){
        z+=b[i]*(r-a[i]);
        if(z>=0) continue;

        // Set z=LLONG_MAX if z overflows to negative
        z=LLONG_MAX; break;
    }

    // Binary search for the minimum valid number of essays
    // i.e. number of essays s.t. avg>=0
    ll ans=-1;
    for (ll b=z; b>=1; b/=2)
        while(!valid(ans+b))
            ans += b;

    // ans is now maximum number of essays which is invalid.
    // Increment ans to obtain minimum essays needed
    ++ans;
    cout << ans << '\n';
}

int main(){
    speedup();
    solve();
}