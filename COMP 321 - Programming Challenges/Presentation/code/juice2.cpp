#include <bits/stdc++.h>
// #include <cstdio>

using namespace std;

int juice2(int N, int P[][3]) {
    vector<vector<int>> abc {{0}, {0}};
    int max_ppl = 0;

    // Adding possible choices for a, b, c
    // In range(2), because only considering a, b pairs
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < N; j++) {
            if (P[j][i] > 0 && find(abc[i].begin(), abc[i].end(), P[j][i]) == abc[i].end()) {
                // if P[j][i] not in abc[i]
                abc[i].push_back(P[j][i]);
            }
        }
    }

    // Counting for all a, b, c combos
    int count, a, b, c;
    for (int i = 0; i < abc[0].size(); i++) {
        for (int j = 0; j < abc[1].size(); j++) {
            a = abc[0][i]; b = abc[1][j]; c = 10000-a-b;
            if (c < 0) continue;

            count = 0;
            for (int k = 0; k < N; k++)
                if (a >= P[k][0] && b >= P[k][1] && c >= P[k][2])
                    count++;
            max_ppl = count > max_ppl ? count : max_ppl;
        }
    }
    return max_ppl;
}

int main() {
    int T, N, a, b, c;
    scanf("%d", &T);
    for (int case_idx = 0; case_idx < T; case_idx++) {
        scanf("%d", &N);
        int P[N][3];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < 3; j++)
                scanf("%d", &P[i][j]);

        printf("Case #%d: %d\n", case_idx+1, juice2(N, P));
    }
    return 0;
}