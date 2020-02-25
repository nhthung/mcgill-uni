function gaussian = makeGaussian(N, sig)
%
%  returns Nx1 column vector which is a Gaussian with standard deviation sig
%

x = -N/2 : N/2-1
gaussian = normpdf(x,0,sig)'
end

