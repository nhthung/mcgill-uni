// Link: https://codeforces.com/problemset/problem/1104/B

#include <bits/stdc++.h>

using namespace std;

const int N=100000;
char s[N+1];
int win;

int main(){
    for(int i=1; scanf("%c", &s[i])==1; s[i]==s[i-1]?--i, ++win:++i);
    puts(win%2?"Yes":"No");
}