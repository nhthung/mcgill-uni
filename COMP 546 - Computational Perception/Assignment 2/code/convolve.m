function conv = convolve(f,I)
%CONVOLVE Summary of this function goes here
%   Detailed explanation goes here
sz = size(I)
N = sz(2)
disp(N)
conv = zeros(1,N)
for i = 1:N
    for x = 1:N
        conv(i) = conv(i) + f(i-x) * I(x)
    end
end
end

