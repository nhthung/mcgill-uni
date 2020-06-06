// Link: https://codeforces.com/problemset/problem/1007/A

#include <bits/stdc++.h>
#define fill(iter, n) for(int i=0; i<n; i++) cin >> iter[i]

using namespace std;

int n;

int main(){
    /**
     * Run two pointers on sorted array,
     *     if a[i]==a[j]: j++
     *     else:          count++, i++, j++
     **/
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    int i=0, j=1, count=0, a[n];
    fill(a, n);
    sort(a, a+n);

    while(j<n){
        if(a[i]==a[j]){
            j++; continue;
        }
        count++; i++; j++;
    }
    cout << count << "\n";
}
