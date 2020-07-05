// https://codeforces.com/problemset/problem/959/C

#include <bits/stdc++.h>
using namespace std;

void wrong(int n){
    /**
     * When n >= 6, algorithm finds the wrong vertex cover size
     * for this tree structure:
     *         1  _  _
     *        /    \  \
     *       2  _  3  4    nodes 2, 3, 4 at depth 1
     *      / \  \
     *     5  6  7 ...     nodes 5, 6, 7, ... at depth 2
     * 
     * When n < 6, the algorithm finds the right vertex cover size
     * even while following the above structure, hence we return -1
     */
    if(n<6){ cout << "-1\n"; return; }
    for(int i=2; i<=n; ++i) cout << (i<5 ? "1 ":"2 ") << i << "\n";
}
 
void right(int n){
    /**
     * The algorithm always finds the right vertex cover size
     * for this tree structure:
     *       1  _  _
     *      / \  \  \
     *     2  3  4  5 ...    all nodes > 1 at depth 2
     * 
     * The right vertex cover size is 1 (the root node),
     * which the algorithm correctly finds
     */
    for(int i=2; i<=n; ++i)
        cout << "1 " << i << "\n";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int n; cin >> n;
    wrong(n);
    right(n);
}