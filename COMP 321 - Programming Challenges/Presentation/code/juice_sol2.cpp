#include <string>
#include <iostream>
#include <cstring>
#include <vector>

#include <fstream>
#include <algorithm>
using namespace std;

class Order {
    private:
        int nApple;
        int nBanana;
        int nCarrot;
        int sSelect;
    public:
        Order(){}
        Order(int nA, int nB, int nC, int avg){
            nApple = (nA == 0 ? avg : nA);
            nBanana = (nB == 0 ? avg : nB);
            nCarrot = (nC == 0 ? avg : nC);
            sSelect = nApple + nBanana + nCarrot;
        }
        int getA(){
            return this->nApple;
        }
        int getB(){
            return this->nBanana;
        }
        int getC(){
            return this->nCarrot;
        }
        int getSum(){
            return this->sSelect;
        }   
};

void heapify(vector<Order>& arr, int n, int i) {
	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	if (l < n && arr[l].getSum() > arr[largest].getSum())
		largest = l;

	if (r < n && arr[r].getSum() > arr[largest].getSum())
		largest = r;

	if (largest != i) {
		swap(arr[i], arr[largest]);

		heapify(arr, n, largest);
	}
}

void heapSort(vector<Order>& arr, int n) {
	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(arr, n, i);

	for (int i = n - 1; i >= 0; i--) {
		swap(arr[0], arr[i]);

		heapify(arr, i, 0);
	}
}

int OutCase(vector<Order> arrOrder) {
	int result = 0;
	int sApple = 0;
	int sBanana = 0;
	int sCarrot = 0;
	heapSort(arrOrder, arrOrder.size());
	for (int i = 0; i < arrOrder.size(); i++){
		sApple += arrOrder[i].getA();
		sBanana += arrOrder[i].getB();
		sCarrot += arrOrder[i].getC();
		if (sApple <= 10000 && sBanana <= 10000 && sCarrot <= 10000){
			result++;
		}
		else{
			break;
		}
	}
	return result;
}

int main() {
	int T, N, nA, nB, nC, avg;
	vector<Order> arrOrder;

    scanf("%d", &T);
    for (int case_idx = 1; case_idx <= T; ++case_idx) {
        scanf("%d", &N);
        avg = 10000/N;
        for (int i = 0; i < N; ++i) {
            scanf("%d %d %d", &nA, &nB, &nC);
            if (nA + nB + nC <= 10000)
                arrOrder.push_back(Order(nA, nB, nC, avg));
        }
        cout << "Case #" << case_idx << ": " << OutCase(arrOrder) << endl;
    }
	return 0;
}