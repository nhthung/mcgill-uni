disp('||Xc - Xt||_F / ||Xt||_F   eps ||A||_F ||A^-1||_F     ||B − AXc||_F / (||A||_F ||Xc||_F)     eps');

for k = 1:10
    A = hilb(10);
    X_t = randn(10, 5);
    B = A * X_t;
    
    [L, U, P] = lupp(A);
    PB = P * B;
    for i = 1:5
        Y(:,i) = gepp(L, PB(:,i));
    end
    for i = 1:5
        X_c(:,i) = gepp(U, Y(:,i));
    end
    
    a = norm(X_c - X_t,'fro') / norm(X_t,'fro');
    b = eps * cond(A,'fro');
    c = norm(B - A*X_c,'fro') / (norm(A,'fro') * norm(X_c,'fro'));
    
    res = [a, b, c, eps];
    g = sprintf('%d                   ', res);
    fprintf('%s\n', g)
end