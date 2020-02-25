f = @(x) cos(2*x)/exp(x);

disp('Recursive trapzoid:');
I_T = recTrapezoid(f, 0, 2*pi, 100000, 10^(-4));
fprintf('Result: %23.15e\n\n', I_T);

global count
count = 0;
disp('Adaptive Simpson:');
I_S = adaptiveSimpson(f, 0, 2*pi, 10^(-4), 0, 20);
fprintf('Number of iterations: %d\n', count);
fprintf('Result: %23.15e\n\n', I_S);

fprintf('Real result: %23.15e\n', (1 - exp(-2*pi))/5);