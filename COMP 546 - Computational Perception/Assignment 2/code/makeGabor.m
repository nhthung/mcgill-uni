function [cosGabor,sinGabor] = makeGabor(N,k)
%MAKEGABOR
%   Return a sine Gabor function and a cosine Gabor function
%   N: sample size or length of vector
%   k: number of cycles
%
sig = N/(3*k)
gaussian = @(x) normpdf(x,0,sig)
cosine = @(x) cos( 2*pi/N * k * x )
sine = @(x) sin( 2*pi/N * k * x )

cosGabor = @(x) cosine(x) .* gaussian(x)
sinGabor = @(x) sine(x) .* gaussian(x)
end