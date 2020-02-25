fn  = @(x) x.*x.*x - 5.*x + 3;
fd  = @(x) 3.*x.*x - 5;
fd2 = @(x) 6.*x

x = -3:1/100:3;
plot(x,fn(x));

disp('Secant method:');
root = secant(fn,1,2,1.e-12,1.e-12,100,1);

disp(' ');
disp('Bisection method:');
root = bisection(fn,1,3,1.e-12,1);

disp(' ');
disp('Newton''s method:');
root = newton(fn,fd,2,1.e-12,1.e-12,100,1);

disp(' ');
disp('Cubic Newton''s method:');
root = newtonCubic(fn,fd,fd2,2,1.e-12,1.e-12,100,1);