% Warmup

% Trying out Matlab's Fourier transform (fft)
v1 = [0 0 0 0 0 0 0 0]
v2 = [1 0 0 0 0 0 0 0]
v3 = [0 1 0 0 0 0 0 0]
v4 = [1 1 1 1 1 1 1 1]

y = randn([2^16 2])
yamp = abs(y)
Fs = 44100

sound(yamp, Fs)