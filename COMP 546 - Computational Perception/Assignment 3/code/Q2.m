clear; close all
% size of window 
N = 64;

%create ramp
N_sin = 10 * N;
X = 0 : (N - 1);
I = ( sin(2*pi/N_sin * X) );

% define "window"
T = (1 - cos(2*pi/N * X) );

% filter with window
fltrd = I .* T;

% plot window
figure
plot(T);
title(('Image Window'),'fontsize',12);
% plot "linear ramp"
figure
plot(I);
title('Linear Ramp');
% fourier fourier transform "linear ramp"
figure
plot(abs(fft(I)));
title(('Amplitude Spectrum of Linear Ramp'), 'fontsize',12);
xlabel('Frequency k');
ylabel('Amplitude');
% filtered ramp
figure
plot(fltrd);
title('Ramp Multiplied with Window');
xlabel('x');
ylabel('y');
% FT of filtered ramp
figure
plot(abs(fft(fltrd)));
title('Amplitude Spectrum of Filtered Ramp');
xlabel('Frequency k');
ylabel('Amplitude');

% Amplitude spec of window
figure
plot(abs(fft(T)));
title('Amplitude Spectrum of Window');
xlabel("Frequency k");
ylabel('Amplitude');

% make high frequency sin
X = 0 : N-1;
I = ( sin(2*pi/N * 50 * X));

% window
W = (1 - cos(2*pi/N * X));
fltrd = I .* W;

% plot sin
figure
plot(I);
title('High Frequency Sin');
% plot fft of sin
figure
plot(abs(fft(I)));
title('Amplitude Spectrum of Sin');
xlabel('Frequency k');
ylabel('Amplitude');
% plot fltrd
figure
plot(fltrd);
title('Sin Multiplied by Window');
% plot fft of fltrd sin
figure
plot(abs(fft(fltrd)));
title('Amplitude Spectrum of Windowed Sin');
xlabel('Frequency k');
ylabel('Amplitude');