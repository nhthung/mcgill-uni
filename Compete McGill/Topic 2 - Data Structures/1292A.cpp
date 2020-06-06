// Link: https://codeforces.com/problemset/problem/1292/A

#include <bits/stdc++.h>

using namespace std;

const int N=100000;
int n, q, r, c, maze[2][N+2];

void solve(){
    /**
     * Count blockages each time a lava cell appears or disappears
     **/
    int sign, block_count=0;
    for(cin >> n >> q; q--;){
        cin >> r >> c; r--;
        maze[r][c]^=1; // flip b/w 0 and 1
        sign=maze[r][c]?1:-1;
        block_count+=sign*(maze[r^1][c-1]+maze[r^1][c]+maze[r^1][c+1]);
        puts(block_count?"No":"Yes");
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
}