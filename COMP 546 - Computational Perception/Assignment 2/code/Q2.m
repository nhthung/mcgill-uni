% Q2
N = 32 % vector length of Gabors
k = 2 % number of cycles of Gabors
kx = [2,0,sqrt(2)]
ky = [0,2,sqrt(2)]
[vCosGabor,vSinGabor] = make2DGabor(N,kx(1),ky(1))
[hCosGabor,hSinGabor] = make2DGabor(N,kx(2),ky(2))
[dCosGabor,dSinGabor] = make2DGabor(N,kx(3),ky(3))

offset = N + 1
vCosGaborShifted = zeros(N, N, 2*N + 1)
hCosGaborShifted = zeros(N, N, 2*N + 1)
dCosGaborShifted = zeros(N, N, 2*N + 1)
for d = -N:N
    i = d + offset
    vCosGaborShifted(:,:,i) = circshift(vCosGabor,[0 d])
    hCosGaborShifted(:,:,i) = circshift(hCosGabor,[d 0])
    dCosGaborShifted(:,:,i) = circshift(dCosGabor,[-d d])
    vSinGaborShifted(:,:,i) = circshift(vSinGabor,[0 d])
    hSinGaborShifted(:,:,i) = circshift(hSinGabor,[d 0])
    dSinGaborShifted(:,:,i) = circshift(dSinGabor,[-d d])
end
vcg = @(d) vCosGaborShifted(:,:,d + offset)
hcg = @(d) hCosGaborShifted(:,:,d + offset)
dcg = @(d) dCosGaborShifted(:,:,d + offset)
vsg = @(d) vSinGaborShifted(:,:,d + offset)
hsg = @(d) hSinGaborShifted(:,:,d + offset)
dsg = @(d) dSinGaborShifted(:,:,d + offset)

M = 10*N
noise = randn(M,M)
noiseShifted = circshift(noise,[0 4])

d = -N:N


% Create the gabors
cosGaborVShift = circshift(cosGaborV, [0 d]);
sinGaborVShift = circshift(sinGaborV, [0 d]);

cosGaborHShift = circshift(cosGaborH, [-d 0]);
sinGaborHShift = circshift(sinGaborH, [-d 0]);

cosGaborRDShift = circshift(cosGaborRD, [d d]);
sinGaborRDShift = circshift(sinGaborRD, [d d]);

% Compute the mean R Sums
meanRsum = computeMeanRSum(frame, frameShift, ...
    cosGaborV, sinGaborV, cosGaborVShift, sinGaborVShift);
rsumFrameMeansV(d + N + 1) = meanRsum;

meanRsum = computeMeanRSum(frame, frameShift, ...
    cosGaborH, sinGaborH, cosGaborHShift, sinGaborHShift);
rsumFrameMeansH(d + N + 1) = meanRsum;

meanRsum = computeMeanRSum(frame, frameShift, ...
    cosGaborRD, sinGaborRD, cosGaborRDShift, sinGaborRDShift);
rsumFrameMeansRD(d + N + 1) = meanRsum;
end