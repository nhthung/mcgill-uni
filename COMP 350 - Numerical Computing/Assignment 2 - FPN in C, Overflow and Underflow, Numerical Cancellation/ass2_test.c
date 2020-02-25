#include <stdio.h>
#include <math.h>

double calcSide(int n) {
  double temp;

  n = n - 3;
  temp = sqrt(2.0 - sqrt(2.0));

  for(; n > 0; n--) {
    temp = sqrt(2.0 - sqrt(-pow(temp, 2.0) + 4.0));
  }

  return temp / 2.0;
}

void question3b() {
  double p_n, s_n, n;

  n = 3.0;
  //p_nMinus1 = two * sqrt(two);

  for (int i = 3; i <= 35; i++, n = n + 1.0) {
    p_n = pow(2.0, n) * calcSide(i);
    printf("p_%d = %e\n", i, p_n);
  }
}

int main() {
  //question1Single();
  //question1Double();
  //question2();
  //question2Bonus();
  //question3a();
  question3b();
}
