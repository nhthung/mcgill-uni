function T = RecTrap(f)
%---------------------------------------------------------------------------
%RCTRAP   Quadrature using the recursive trapezoidal rule.
% Sample call
%   T = rctrap('f',a,b)
% Inputs
%   f   name of the function
%   a   left  endpoint of [a,b]
%   b   right endpoint of [a,b]
% Return
%   T   recursive trapezoidal rule list
%---------------------------------------------------------------------------

err = 10^(-4);
diff=100;
n = 100;
m = 1;
a = 0;
b = 2*pi;

h = b - a;
T = zeros(1,n+1);
T(1) = h*(feval(f,a) + feval(f,b))/2;
for j = 1:n
    while (diff > err)
    m = 2*m;
    h = h/2;
    s = 0;
    for k=1:m/2
        x = a + h*(2*k-1);
        s = s + feval(f,x);
    end
    T(j+1) = T(j)/2 + h*s;
    diff = abs(T(j)-T(j+1));
    end
end