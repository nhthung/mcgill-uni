% Q1
N = 32 % vector length of Gabors
k = 2 % number of cycles of Gabors
[cosGabor, sinGabor] = makeGabor(N,k)

% Q1.a
%{
% Origin of Gabors at x=N/2=16
sinGaborShifted = @(x) sinGabor(x-N/2)
cosGaborShifted = @(x) cosGabor(x-N/2)

sinGabRmsList = zeros(1,N)
cosGabRmsList = zeros(1,N)
xrange = -N/2+1 : N/2
for wavelength = 1:N
    k = N/wavelength
    sine = makeSine(N,k)
    sineShifted = sine(xrange) % Origin of sine at x=N/2=16
    sinGabConv = convolve(sinGaborShifted,sineShifted)
    cosGabConv = convolve(cosGaborShifted,sineShifted)
    sinGabRmsList(wavelength) = rms(sinGabConv)
    cosGabRmsList(wavelength) = rms(cosGabConv)
end

wavelength = 1:N
figure
plot(wavelength, sinGabRmsList(wavelength))
hold on
plot(wavelength, cosGabRmsList(wavelength))
line([17 17],[0 0.25])
hold off
xlabel('wavelength of sine function')
legend('sine Gabor', 'cosine Gabor', 'wavelength = 17')
title('RMS of convolution of Gabor functions with sine function, against increasing wavelength')
%}

% Q1.b
%{
M = 10*N
noise = randn(M,1)'
L_c = convolve(cosGaborShifted,noise)
L_s = convolve(sinGaborShifted,noise)
response = sqrt(L_c.^2 + L_s.^2)

x = 1:M
figure
plot(x, L_c(x))
hold on
plot(x, L_s(x))
plot(x, response(x))
hold off
legend('L_c', 'L_s', 'sqrt(L_c^2 + L_s^2)')
title('Spatial variation of random signal convolved with Gabor functions')

[pks,locs_c] = findpeaks(L_c)
[pks,locs_s] = findpeaks(L_s)
[pks,locs_res] = findpeaks(response)

lambda_c = mean(diff(locs_c)) % gives 12.75
lambda_s = mean(diff(locs_s)) % gives 12.7083
lambda_res = mean(diff(locs_res)) % gives 20.1333
%}

% Q1.c

offset = N + 1
L_c_shifted = zeros(2*N + 1,M)
L_s_shifted = zeros(2*N + 1,M)
for d = -N:N
    i = d + offset
    L_c_shifted(i,:) = circshift(L_c,d)
    L_s_shifted(i,:) = circshift(L_s,d)
end
L_c2 = @(d) L_c_shifted(d + offset,:)
L_s2 = @(d) L_s_shifted(d + offset,:)
r_diff = @(d) sqrt((L_c - L_c2(d)).^2 + (L_s - L_s2(d)).^2)'
r_sum = @(d) sqrt((L_c + L_c2(d)).^2 + (L_s + L_s2(d)).^2)'
r_diff_mean = @(d) mean(r_diff(d))
r_sum_mean = @(d) mean(r_sum(d))

d = -N:N
figure
plot(d,r_diff_mean(d))
hold on
plot(d,r_sum_mean(d))
hold off
xlabel('d')
legend('mean r_{diff}','mean r_{sum}')
title('Mean r_{diff} and mean r_{sum} as functions of d')

