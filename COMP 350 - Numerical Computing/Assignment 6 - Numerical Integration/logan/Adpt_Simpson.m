function I = Adpt_Simpson(f,a,b,delta,level,l_max,count)

h=b-a;
c=(a+b)/2;
I1=h*(feval(f,a) + 4*feval(f,c) + feval(f,b))/6;
level=level+1;
d=(a+c)/2;
e=(c+b)/2;
I2=h*(feval(f,a) + 4*feval(f,d) + 2*feval(f,c) + 4*feval(f,e) + feval(f,b))/12;
count = count + 1;
disp(count);

if level >= l_max
    I = I2;
else
    if abs(I2-I1) <= 15*delta
        I = I2 + (I2-I1)/15;
    else
        I = Adpt_Simpson(f,a,c,delta/2,level,l_max,count)...
            + Adpt_Simpson(f,c,b,delta/2,level,l_max,count);
    end
end