#include <bits/stdc++.h>

using namespace std;

const int
    a_idx = 0,
    b_idx = 1,
    c_idx = 2;

void insertion_sort(int P[][3], int key, int start, int end) {
    /**
     * Insertion sort: sort 2D array with start-end rows,
     * with column value as sorting key
     */
    int temp[3], j;
    for (int i = start; i <= end; ++i) {
        for (int k = 0; k < 3; ++k) temp[k] = P[i][k];
        j = i-1;
        while (j >= start && P[j][key] > temp[key]) {
            for (int k = 0; k < 3; ++k) P[j+1][k] = P[j][k];
            j--;
        }
        for (int k = 0; k < 3; ++k) P[j+1][k] = temp[k];
    }
}

int juice2(int N, int P[][3]) {
    int a, b, c, j_prev, count, size, max_ppl = 0;
    vector<int> S; // List of people satisfied with current ABC combo

    // Sort people by C value
    insertion_sort(P, c_idx, 0, N-1);

    for (int i = 0; i < N; ++i) {
        // Let c = current C value
        c = P[i][c_idx];
        // In sublist of people with the same C value,
        // skip to the last person
        if (i < N-1 && c == P[i+1][c_idx]) continue;
        
        // Select people satisfied with current C and
        // sort them by A value
        insertion_sort(P, a_idx, 0, i);
        
        // Clear list of satisfied people
        S.clear();

        // Loop through sorted A values
        for (int j = 0; j <= i; ++j) {
            // Let a, b current A, B values
            a = P[j][a_idx];
            if (j < i && j < N - 1 && a == P[j+1][a_idx]) continue;
            b = 10000-a-c;

            // Stop searching once the maximum possible B is < 0
            if (b < 0) break; 

            size = S.size();
            count = size; // Assume everyone in S is currently satisfied
            // if (P[j][b_idx] <= b) {
            for (int k = j_prev; k <= j; ++k) {
                if (P[k][b_idx] <= b) {
                    // Add newly satisfied people by B value
                    S.push_back(P[k][b_idx]); 
                    count++;
                }
            }
            j_prev = j + 1;

            // Among previously assumed satisfied people,
            // check for anyone unsatisfied
            // for (int k = 0; k < size; ++k) {
            for (int k = 0; k < size; ++k) {
                if (S[k] > b) S[k] = -1; // Set unsatisfied people to -1
                if (S[k] == -1) count--; // Decrement count
            }
            // count = S.size() - std::count(S.begin(), S.end(), -1);
            max_ppl = count > max_ppl ? count : max_ppl;
        }
        j_prev = 0;
    }
    return max_ppl;
}

// Driver code
int main() {
    int T, N, a, b, c;
    scanf("%d", &T);
    for (int case_idx = 1; case_idx <= T; ++case_idx) {
        scanf("%d", &N);
        int P[N][3];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < 3; j++)
                scanf("%d", &P[i][j]);

        printf("Case #%d: %d\n", case_idx, juice2(N, P));
    }
    return 0;
}