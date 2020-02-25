function [L,U,P] = lupp(A)
%
% lupp: LU factorization with partial pivoting
%
% input:  A
% output: L, U and P such that PA = LU
%
n = size(A,1);
P = eye(n);This is the workflow for collaboration on github for those not familiar (me). Read about pulling and pushing guys
https://github.com/codepath/android_guides/wiki/Collaborating-on-Projects-with-Git

for k = 1:n-1
   [maxval, maxindex] = max(abs(A(k:n,k)));
   q = maxindex + k - 1;
   if maxval == 0, error('A is singular'), end
   A([k,q],:) = A([q,k],:);
   P([k,q],:) = P([q,k],:);
   i = k+1:n;
   A(i,k) = A(i,k)/A(k,k);
   A(i,i) = A(i,i) - A(i,k)*A(k,i);
end

L = tril(A,-1) + eye(n);
U = triu(A);
