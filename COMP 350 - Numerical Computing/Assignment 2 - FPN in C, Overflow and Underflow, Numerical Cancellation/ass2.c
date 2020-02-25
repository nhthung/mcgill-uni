#include <stdio.h>
#include <math.h>

void question1Single() {
  float x, invOfX;

  x = 1.0;
  invOfX = 1.0 / x;

  while (x == 1.0 / invOfX) {
    x++;
    invOfX = 1.0 / x;
  }

  printf("Question 1 single precision: %e\n", x);
}

void question1Double() {
  double x, invOfX;

  x = 1.0;
  invOfX = 1.0 / x;

  while (x == 1.0 / invOfX) {
    x++;
    invOfX = 1.0 / x;
  }

  printf("Question 1 double precision: %e\n", x);
}

double myFact(double n) {
  double one, zero;

  one = 1.0;
  zero = 0.0;

  if (n == zero)
    return one;
  else {
    return n * myFact(n - one);
  }
}

void question2() {
  float x_n;
  int n;

  x_n = 100;
  n = 2;

  for (; n <= 70; n++) {
    printf("100 * x_%d = %e\n", n - 1, 100 * x_n);
    printf("100 * x_%d / %d = %e\n", n - 1, n, 100 * x_n / n);

    x_n = (100 * x_n) / n;
    printf("x_%d = %e\n\n", n, x_n);
  }
}

void question2Bonus() {
  double x, n,;

  n = 2.0;

  for (int i = 2; i <= 200; i++) {
    printf("n = %e\n", n);
    printf("100^%d = %e\n", i, pow(100.0, n));
    printf("%d! = %e\n", i, myFact(n));

    x = pow(100.0, n) / myFact(n);
    printf("x_%d = %e\n\n", i, x);
    n++;
  }
}

void question3a() {
  double p_nMinus1, nMinus1;

  nMinus1 = 2.0;
  p_nMinus1 = 2.0 * sqrt(2.0);

  for (int i = 3; i <= 35; i++) {
    printf("p_%d / 2^%d = %e\n", i-1, i-1, p_nMinus1  / pow(2.0, nMinus1));
    printf("(p_%d / 2^%d)^2 = %e\n", i-1, i-1, pow(p_nMinus1  / pow(2.0, nMinus1), 2.0));
    printf("1 - (p_%d / 2^%d)^2 = %e\n", i-1, i-1, 1.0 - pow(p_nMinus1  / pow(2.0, nMinus1), 2.0));
    
    p_nMinus1 =
      pow(2.0, nMinus1) *
      sqrt(
        2.0 * (1.0 - sqrt(
          1.0 - pow(p_nMinus1  / pow(2.0, nMinus1), 2.0)
        ))
      );

    printf("p_%d = %e\n\n", i, p_nMinus1);
    nMinus1++;
  }
}

void question3b() {
  double p_n, circumscribed_n, inscribed_n;

  inscribed_n = 2.0 * sqrt(2.0);
  circumscribed_n = 4.0;

  for (int n = 3; n <= 35; n++) {
    circumscribed_n = 2.0 * circumscribed_n * inscribed_n / (circumscribed_n + inscribed_n);
    inscribed_n = sqrt(circumscribed_n * inscribed_n);

    p_n = (circumscribed_n + inscribed_n) / 2;
    printf("p_%d = %20.15e\n", n, p_n);
  }
}

int main() {
  question1Single();
  question1Double();
  question2();
  //question2Bonus();
  question3a();
  question3b();
}
