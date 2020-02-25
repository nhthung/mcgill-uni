function cosine = makeCosine(N,k)
%MAKECOSINE Summary of this function goes here
%   Detailed explanation goes here
cosine = @(x) cos( 2*pi/N * k * x )
end

