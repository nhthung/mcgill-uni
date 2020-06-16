
#include <bits/stdc++.h>
// #define fill(iter, n) for(int i=1; i<=n; i++) cin >> iter[i]

using namespace std;

int n, wi, i;
string s;
vector<int> w;
vector<size_t> w_idx;
stack<int> stk;

template <typename T>
vector<size_t> sort_indexes(const vector<T> &v) {
    // initialize original index locations
    vector<size_t> idx(v.size());
    iota(idx.begin(), idx.end(), 0);

    // sort indexes based on comparing values in v
    // using std::stable_sort instead of std::sort
    // to avoid unnecessary index re-orderings
    // when v contains elements of equal values 
    stable_sort(idx.begin(), idx.end(),
        [&v](size_t i1, size_t i2) {return v[i1] < v[i2];});

    return idx;
}

int main(){
    /**
     * sort_indexes(w) sorts w and maps each element to its original index:
     *     w                = [10 8  9  11 13 5]
     *     indexes          = [0  1  2  3  4  5]
     *     sort_indexes(w) -> [5  1  2  0  3  4]
     **/
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    while(n--){ cin >> wi; w.push_back(wi); }
    cin >> s;
    w_idx=sort_indexes(w);
    
    for(auto w_it=w_idx.begin(); i<s.size(); ++i){
        if(s[i]=='0'){
            stk.push(*w_it++ +1), cout << stk.top() << " ";
            continue;
        }
        cout << stk.top() << " ", stk.pop();
    }
    cout << "\n";
}