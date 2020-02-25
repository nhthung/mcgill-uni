function I = recTrapezoid(fname, a, b, n, tol)
%RECTRAPEZOID Summary of this function goes here
%   fname: name of function
%   a: left endpoint of [a,b]
%   b: right endpoint of [a,b]
%   n: max num of iterations
%   tol: gap between I and previous I

m = 1;
h = b - a;
diff = realmax;
T = zeros(1,n+1);
T(1) = h*(feval(fname,a) + feval(fname,b))/2;

for i = 1:n
    m = 2 * m;
    h = h / 2;
    s = 0;
    for j = 1 : m / 2
        x = a + h * (2 * j - 1);
        s = s + feval(fname,x);
    end
    T(i + 1) = T(i) / 2 + h * s;
    I = T(i + 1);
    diff = abs(T(i) - T(i + 1));
    
    if diff <= tol, break; end
end

fprintf('Number of iterations: %d\n', i);
