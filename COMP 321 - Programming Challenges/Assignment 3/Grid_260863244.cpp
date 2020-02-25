#include "bits/stdc++.h"
using namespace std;

//global variables: will be accessible throughout the code
int n,m;
vector<string> arr;
//a 2d array to store the number of moves made
int steps[501][501];

void search(int x,int y){
	//declare a 2d array, initialize to 0 first, then change to 1 when it gets visited
	//501 because n and m is always between 1 and 500 inclusively
	bool visit[501][501];
	for(int i = 0; i<501; i++){
		for(int j = 0; j<501; j++){
			visit[i][j] = 0;
		}
	}

	visit[x][y]=1;
	queue<pair<int,int>> q;
	q.push({x,y});

	steps[x][y]=0;

	while(!q.empty()){
		pair<int,int> t= q.front();
		q.pop();

		//check for available move in all four directions
		//invalid move when it gets out of the board or when it is already visited
		if(t.first+(arr[t.first][t.second]-'0') >= 0 && t.second >= 0 && t.first+(arr[t.first][t.second]-'0') < n && t.second < m && !visit[t.first+(arr[t.first][t.second]-'0')][t.second]){
			q.push({t.first+(arr[t.first][t.second]-'0'),t.second});

			//change to 1 when we decide to visit
			visit[t.first+(arr[t.first][t.second]-'0')][t.second]=1;

			//count how many steps were made until now and store the value in 'steps' array
			steps[t.first+(arr[t.first][t.second]-'0')][t.second]=1+steps[t.first][t.second];
		}
		if(t.first-(arr[t.first][t.second]-'0') >= 0 && t.second >= 0 && (t.first-(arr[t.first][t.second]-'0')) < n && t.second < m && !visit[(t.first-(arr[t.first][t.second]-'0'))][t.second]){
			q.push({t.first-(arr[t.first][t.second]-'0'),t.second});
			visit[t.first-(arr[t.first][t.second]-'0')][t.second]=1;
			steps[t.first-(arr[t.first][t.second]-'0')][t.second]=1+steps[t.first][t.second];
		}
		if(t.first >= 0 && t.second+(arr[t.first][t.second]-'0') >= 0 && t.first < n && t.second+(arr[t.first][t.second]-'0') < m && !visit[t.first][t.second+(arr[t.first][t.second]-'0')]){
			q.push({t.first,t.second+(arr[t.first][t.second]-'0')});
			visit[t.first][t.second+(arr[t.first][t.second]-'0')]=1;
			steps[t.first][t.second+(arr[t.first][t.second]-'0')]=1+steps[t.first][t.second];
		}
		if(t.first >= 0 && t.second-(arr[t.first][t.second]-'0') >= 0 && t.first < n && t.second-(arr[t.first][t.second]-'0') < m && !visit[t.first][t.second-(arr[t.first][t.second]-'0')]){
			q.push({t.first,t.second-(arr[t.first][t.second]-'0')});
			visit[t.first][t.second-(arr[t.first][t.second]-'0')]=1;
			steps[t.first][t.second-(arr[t.first][t.second]-'0')]=1+steps[t.first][t.second];
		}
	}
}
int main(){

	//return -1 when no possible solutions were found
	memset(steps,-1,sizeof steps);
	string s;

	//scanf, as the Scanner in java, to take the size/dimension of the board
	int check = scanf("%d%d",&n,&m);

	if(check == 2){
		for(int i=0;i<n;i++){
			cin>>s;
			arr.push_back(s);
		}
	}
	else{
		return 0;
	}

	//start from the top left
	search(0,0);
	//until bottom right, thus til (n-1, m-1)
	printf("%d\n",steps[n-1][m-1]);

	return 0;
}

