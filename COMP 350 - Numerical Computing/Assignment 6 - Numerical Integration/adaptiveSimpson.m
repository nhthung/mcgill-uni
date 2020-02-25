function I = adaptiveSimpson(fname,a,b,delta,level,level_max)
%ADAPTIVESIMPSON Summary of this function goes here
%   Detailed explanation goes here

global count
count = count + 1;

h = b - a;
c = (a + b)/2;
I1 = h*(feval(fname,a) + 4*feval(fname,c) + feval(fname,b)) / 6;
level = level + 1;
d = (a + c)/2;
e = (c + b)/2;
I2 = h*(feval(fname,a) + 4*feval(fname,d) + 2*feval(fname,c) + 4*feval(fname,e) + feval(fname,b))/12;

if level >= level_max
    I = I2;
else
    if abs(I2-I1) <= 15*delta
        I = I2 + (I2-I1)/15;
    else
        I = adaptiveSimpson(fname,a,c,delta/2,level,level_max)...
            + adaptiveSimpson(fname,c,b,delta/2,level,level_max);
    end
end