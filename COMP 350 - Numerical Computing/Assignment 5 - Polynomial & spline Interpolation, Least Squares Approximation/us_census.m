% Here is the US Census data from 1900 to 1990.

x = (1900:10:1990)';
y = [75.9950 91.9720 105.7110 123.2030 131.6690 150.6970 ...
     179.3230 203.2120 226.5050 249.6330]';


plot(x, y, 'bo');
axis([1900 2010 0 400]);
title('Population of the U.S. 1900-1990');
ylabel('Millions');
disp('press any key to continue')
pause 

% Compute the coefficients of the Newton IP

a = newton_coef(x,y);

% Evaluate the Newton IP at every year from 1900 to 2010
% and plot the results.

hold on
xx = 1900:2010;
yy = newton_pval(a,x,xx);
plot(xx,yy,'r-');

% Evaluating the Newton IP at 1975 and plot the results.

z = newton_pval(a,x,1975);
plot(1975,z,'ks');
text(1975,z-10,num2str(z));
hold off
