function I = mk2Dcosine(N,KX,KY)
%  I = mk2Dcosine(N,KX,KY);
%
%  This program makes a 2D cosine wave cos( 2pi/N (KX x + KY y)).
%  where x,y are in 0,1,..N-1.
%
%  Here the x are the column indices and y are the row indices, which is what we want.
%  (Often matlab notation has it the other way around, more consistent with Matrix A_ij notation 

%display('arguments should be (N,KX,KY)');
if nargin < 3
  return;
end

x = 0: (N-1);
y = 0: (N-1);
[X,Y] = meshgrid(x,y);
I = cos(  2*pi/N * (KX * X + KY * Y) );

%  If you want to have a look at it, then set the boolean condition in
%  the following code to 1.

if (0)
    imagesc(I)
    colormap('gray')
    axis('square')
    xlabel('Y (second coordinate i.e. row)')
    ylabel('X (first  coordinate i.e. column)')
end

