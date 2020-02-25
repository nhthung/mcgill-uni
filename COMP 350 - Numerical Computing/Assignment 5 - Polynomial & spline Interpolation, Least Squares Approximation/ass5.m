% Here is the US Census data from 1900 to 2000.

t = (1900:10:2000)';
y = [75.9950 91.9720 105.7110 123.2030 131.6690 150.6970 ...
     179.3230 203.2120 226.5050 249.6330 281.422]';

z = splineCubicZ(t,y,11);
S = @(x) splineCubic(x,t,y,z,11);
LS = @(x) leastSquaresStraightLine(x,t,y,11);

% Plot the given 11 points
plot(t,arrayfun(S,t),'bx');
axis([1900 2020 0 400]);
title('Population of the U.S. 1900-2020');
ylabel('Millions');

hold on;

x = 1900:1:2020;

%{
% Plot the spline interpolation
plot(x,arrayfun(S,x),'k-');

% Mark the population in 1985
plot(1985,S(1985),'rx');
text(1970,S(1985)+10,num2str(S(1985)));

% Mark the population in 2010
plot(2010,S(2010),'rx');
text(1995,S(2010)+10,num2str(S(2010)));
%}

% Plot the least squares approximation
plot(x,LS(x));

% Mark the population in 1985
plot(1985,LS(1985),'rx');
text(1970,LS(1985)+10,num2str(LS(1985)));

% Mark the population in 2010
plot(2010,LS(2010),'rx');
text(1995,LS(2010)+10,num2str(LS(2010)));

hold off;