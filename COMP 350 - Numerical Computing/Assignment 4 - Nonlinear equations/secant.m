function root = secant(fname,x0,x1,xtol,ftol,n_max,display)
% Secant Method.
%
% input:  fname is a string that names the function f(x).
%         x0 and x1 are two initial points
%         xtol and ftol are termination tolerances
%         n_max is the maximum number of iteration
%         display = 1 if step-by-step display is desired,
%                 = 0 otherwise
% output: root is the computed root of f(x)=0
%
n = 0;
fx0 = feval(fname,x0);
fx1 = feval(fname,x1);
if display, 
   disp('   n             x0                      x1                    f(x0)                    f(x1)')
   disp('-----------------------------------------------------------------------------------------------------')
   disp(sprintf('%4d %23.15e %23.15e %23.15e %23.15e', n, x0, x1, fx0, fx1))
end
if abs(fx0) <= ftol
   root = x0;
   return
elseif abs(fx1) <= ftol
   root = x1;
   return
end
for n = 1:n_max
    d = ((x1-x0)/(fx1-fx0))*fx1;
    x0 = x1;
    fx0 = fx1;
    x1 = x1 - d;
    fx1 = feval(fname,x1);
    if display, 
       disp(sprintf('%4d %23.15e %23.15e %23.15e %23.15e', n, x0, x1, fx0, fx1)), end
    if abs(d) <= xtol | abs(fx1) <= ftol
       root = x1;
       return
    end
end
root = x1;