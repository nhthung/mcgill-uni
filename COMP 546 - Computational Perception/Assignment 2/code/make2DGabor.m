function [cosGabor, sinGabor] = make2DGabor(N,k0,k1)
%
% This function returns a 2D, cosine and sine Gabor with standard 
% deviation center frequency (k0,k1) cycles per N samples.   
%
%  The sigma of the Gaussian is chosen to be one third of a cycle of the
%  underlying sinusoidal wave.

k      =  sqrt(k0*k0 + k1*k1);
sigma =  N/k/3;   

%  the mk2DcosineShifted and mk2DsineShifted will put the origin at (N/2+1,N/2+1)
%  The underlying sinusoids (ignoring shifts) are:
%   cos( 2*pi/N (k0 x + k1 y) )
%   sin( 2*pi/N (k0 x + k1 y) )
cos2D = mk2DcosineShifted(N,k0,k1) ;
sin2D = mk2DsineShifted(N,k0,k1) ;

shiftx = -N/2 : N/2-1;
Gaussian = 1/(sqrt(2*pi)*sigma) * ...
    exp(- shiftx.*shiftx/ (2 * sigma*sigma) );  
Gaussian2D = Gaussian' * Gaussian;

cosGabor = Gaussian2D .* cos2D;
sinGabor = Gaussian2D .* sin2D;

