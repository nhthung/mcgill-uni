#define _GNU_SOURCE

#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <time.h>


struct timespec begin, end;
float average = 0.0;

struct timespec diff(struct timespec start, struct timespec end) {
	struct timespec temp;
	if ((end.tv_nsec-start.tv_nsec) < 0) {
		temp.tv_sec = end.tv_sec - start.tv_sec - 1;
		temp.tv_nsec = 1000000000 + end.tv_nsec-start.tv_nsec;
	}
	else {
		temp.tv_sec = end.tv_sec - start.tv_sec;
		temp.tv_nsec = end.tv_nsec - start.tv_nsec;
	}

	return temp;
}

void start() {
    clock_gettime(CLOCK_REALTIME, &begin);
}

void stop() {
    clock_gettime(CLOCK_REALTIME, &end);
}

void c_average(int i) {
    average = (average * i + diff(begin, end).tv_nsec) / (i + 1);
}

float get_average() {
    return average;
}
