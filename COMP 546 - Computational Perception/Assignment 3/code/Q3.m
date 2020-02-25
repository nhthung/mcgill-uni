clear; 
close all;
blocklength = 600;
Fs=44100;
w0=15000;
w1=1000;
T=88200;
t=0:T-1;

spect = zeros(blocklength/2, T/blocklength);
y=((0:blocklength/2)-1)*Fs/blocklength;
x=(0:T/Fs)*1000;

%pick whichever sound to produce a spectogram for
[y,Fs] = audioread('audioclip-1555296141-2044.mp4');
%sound(y,Fs);

buzz = zeros(1,T);
for i = 0:(sqrt(T))
    buzz(i^2+1) = 1;
end
buzz = flip(buzz);
audiowrite('buzz.wav',buzz,Fs);
%sound(buzz,Fs)

chirp = cos((2*pi*t/Fs).*(w0+t*((w1-w0)/(2*T))));
audiowrite('chirp.wav',chirp,Fs);
%sound(chirp,Fs)

I=chirp;

for b=0:T/blocklength-1
    W=I(b*blocklength+1:min(b*blocklength+blocklength-1, end));
    F=log(abs(fft(W)));
    Fcolumn=(F(1:blocklength/2))';
    spect(:,b+1) = Fcolumn;
end
imagesc(spect);


