function y = leastSquaresStraightLine(x,X,Y,m)
%LEASTSQUARES Summary of this function goes here
%   Detailed explanation goes here

a = (m*sum(X.*Y) - sum(X)*sum(Y)) / (m*sum(X.*X) - sum(X)*sum(X));
b = (sum(X.*X)*sum(Y) - sum(X)*sum(X.*Y)) / (m*sum(X.*X) - sum(X)*sum(X));

y = a*x + b;
