#include<stdlib.h>
#include<stdio.h>

int give(int *ap, int *bp, int *cp, int sz, int a, int e, int o) {
  int i,count=0;
  for(i=0;i<sz;i++)
    if(a>=ap[i] && e>=bp[i] && o>=cp[i])
      count++;
  return count;
}

void sort(int *p, int sz) {
  int t,i,j;
  for(i=0;i<sz;i++) {
    for(j=0;j<sz-1;j++) {
      if(p[j]<p[j+1]) {
        t=p[j];
        p[j]=p[j+1];
        p[j+1]=t;
      } else {}
    }
  }
  return ;
}

int main() {
  int min,n,i,j,x,y,z,*a,*p,*pb,*pc,*b,*c,test,k=1;
  scanf("%d", &test);
  do {
    scanf("%d",&n);
    a=(int *)malloc(n*sizeof(int));
    b=(int *)malloc(n*sizeof(int));
    c=(int *)malloc(n*sizeof(int));
    p=(int *)malloc(n*sizeof(int));
    pb=(int *)malloc(n*sizeof(int));
    pc=(int *)malloc(n*sizeof(int));
    for(i=0;i<n;i++)
      scanf("%d %d %d",&a[i],&b[i],&c[i]);
    for(i=0;i<n;i++) {
      p[i]=a[i];
      pb[i]=b[i];
      pc[i]=c[i];
    }
    sort(a,n);
    sort(b,n);
    sort(c,n);
    x=0;
    y=0;
    z=0;
    while(a[x]+b[y]+c[z]>10000 && x<n-1 && y<n-1 && z<n-1) {
      min=32066;
      int f=(a[x]+b[y]+c[z])-10000;
      if(a[x]-a[x+1]>=f) {
        if(a[x]-a[x+1]<min)
        min=a[x]-a[x+1];
      }
      if(b[y]-b[y+1]>=f) {
        if(b[y]-b[y+1]<min)
        min=b[y]-b[y+1];
      }
      if(c[z]-c[z+1]>=f) {
        if(c[z]-c[z+1]<min)
        min=c[z]-c[z+1];
      }
      if(min==32066) {
        min=a[x]-a[x+1];
        if(b[y]-b[y+1]>min)
        min=b[y]-b[y+1];
        if(c[z]-c[z+1]>min)
        min=c[z]-c[z+1];
      }
      if(min==a[x]-a[x+1]) {
        x++;
        continue;
      }
      if(min==b[y]-b[y+1]) {
        y++;
        continue;
      }
      if(min==c[z]-c[z+1]) {
        z++;
        continue;
      }
    }
    int e=a[x];
    int r=b[y];
    int t=c[z];
    int ans=give(p,pb,pc,n,e,r,t);
    printf("Case #%d: %d\n", k, ans);
    k++;
  } while(k<=test);
  return 0;
}