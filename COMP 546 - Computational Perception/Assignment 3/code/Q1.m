% Q1

N = 256

B = zeros(1, N)
D = zeros(1, N)

B([1 2 end]) = [.5 .25 .25]
D([2 end]) = [-.5 .5]

I = 5

% Standard deviations for i = 0:5
S = zeros(1, I+1) 

% Frequencies at which the amplitude spectra of first and second
% derivatives of a Gaussian are maximal
K = zeros(1, I+1)


BW = zeros(1, I+1)

DD = cconv(D, D, length(D))

for i = 0 : I
    m = 2^i
    

G       = zeros(6, 256);
G(1, :) = B;

ct      = 1;
for ii = 1:5
    G(1+ii, :) = cconv(G(ii, :), G(ii, :), size(G, 2));
end

F       = zeros(6, 256);
S       = zeros(6, 256);
for ii = 1:size(G, 1)
    F(ii, :)    = cconv(D, G(ii, :), size(G, 2));
    S(ii, :)    = cconv(D2, G(ii, :), size(G, 2));
end
    
    % Find standard deviation by creating a population matching
    %   the statistics of G. For example, if G = [1/4, 1/2, 1/4],
    %   Then out of 1000 values, we should expect ~250 1's, ~500 2's,
    %   and ~250 3's.
    Gc  = cumsum(circshift(transpose(G), 128), 1);
    P   = rand(size(G, 1), 10000);
    for ii = 1:size(P, 1)
        for jj = 1:size(P, 2)
            P(ii, jj)   = find(P(ii, jj) < Gc(:, ii), 1);
        end
    end
    sigma   = std(P, [], 2);

    % Use the fourier transform to find the frequencies at which
    %   F and S are maximal
    Ff      = transpose(fft(transpose(F)));
    [~, Fmx]= max(abs(Ff(:, 1:floor(end/2))), [], 2);
    Fmx     = Fmx - 1;
    Sf      = transpose(fft(transpose(S)));
    [~, Smx]= max(abs(Sf(:, 1:floor(end/2))), [], 2);
    Smx     = Smx - 1;
      
    figure, Fo = OctaveBandwith(F, true)
    figure, So = OctaveBandwith(S, true)

    for ii = 1:size(G, 1)
        fprintf('%d & %.4f & %d & %d & %.4f & %.4f \\\\\n', (ii-1), sigma(ii), Fmx(ii), Smx(ii), Fo(ii), So(ii));
    end

function octave = OctaveBandwith(S, do_plot)

    % Find k_0 and k_1. Then log_2(k_1) - log_2(k_0) to get octave
    %   bandwidth at half-height.
    Sf      = transpose(fft(transpose(S)));
    Sf      = Sf(:, 1:floor(end/2));
    halfheight = max(abs(Sf), [], 2)/2;
    for ii = 1:size(Sf)
        x       = find(abs(Sf(ii, :)) >= halfheight(ii), 1, 'first') + [-1 0];
        y       = abs(Sf(ii, x));
        k0(ii)  = interpolate_x(x, y, halfheight(ii));
        x       = find(abs(Sf(ii, :)) >= halfheight(ii), 1, 'last') + [-1 0];
        y       = abs(Sf(ii, x));
        k1(ii)  = interpolate_x(x, y, halfheight(ii));
    end
    octave  = log2(k1) - log2(k0)
end

function X = interpolate_x(x, y, Y)
    
    a   = (y(1) - y(2)) / (x(1) - x(2));
    b   = y(2) - a*x(2);
    
    X   = (Y - b)/a;

end

