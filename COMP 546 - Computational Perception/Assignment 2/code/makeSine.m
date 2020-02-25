function sine = makeSine(N,k)
%MAKESINE Summary of this function goes here
%   Detailed explanation goes here
sine = @(x) sin( 2*pi/N * k * x )
end