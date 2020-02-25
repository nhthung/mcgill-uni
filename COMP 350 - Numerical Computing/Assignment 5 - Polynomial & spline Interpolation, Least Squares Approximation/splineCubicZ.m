function Z = splineCubicZ(t,y,n)
%SPLINECUBICZ Summary of this function goes here
%   Detailed explanation goes here

for i = 1:n-1
    h(i) = t(i+1) - t(i);
    b(i) = (y(i+1) - y(i)) / h(i);
end

% Forward elimination
u(2) = 2*(h(1)+h(2));
v(2) = 6*(b(2)-b(1));
for i = 3:n-1
    mult = h(i-1) / u(i-1);
    u(i) = 2*(h(i-1) + h(i)) - mult*h(i-1);
    v(i) = 6*(b(i) - b(i-1)) - mult*v(i-1);
end

% Back substitution
z(n) = 0;
for i = n-1:-1:1
    z(i) = (v(i) - h(i)*z(i+1)) / u(i);
end
z(1) = 0;

Z = z;