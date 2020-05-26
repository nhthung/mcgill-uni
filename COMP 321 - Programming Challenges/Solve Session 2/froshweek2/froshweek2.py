'''
Each task that Zac completes must be completed in one quiet interval. He cannot pause working on a task when music plays (he loses his train of thought). Interstingly, the lengths of the tasks and quiet intervals are such that it is impossible to finish more than one task per quiet interval!

Given a list of times ti (in milliseconds) that each task will take and a list of times ℓj (in milliseconds) specifying the lengths of the intervals when no music is being played, what is the maximum number of tasks that Zac can complete?

Input
The first line of input contains a pair of integers n and m, where n is the number of tasks and m is the number of time intervals when no music is played. The second line consists of a list of integers t1,t2,…,tn indicating the length of time of each task. The final line consists of a list of times ℓ1,ℓ2,…,ℓm indicating the length of time of each quiet interval when Zac is at work this week.

You may assume that 1≤n,m≤200000 and 100000≤ti,ℓj≤199999 for each task i and each quiet interval j.

Output
Output consists of a single line containing a single integer indicating the number of tasks that Zac can accomplish from his list during this first week.

Link: https://open.kattis.com/problems/froshweek2
'''
import sys

def frosh(task_lens, time_lens):
    task_lens = sorted(task_lens)
    time_lens = sorted(time_lens)

    task_count = 0

    j = 0
    for i in range(len(task_lens)):
        while j < len(time_lens):
            if task_lens[i] <= time_lens[j]:
                task_count += 1
                j += 1
                break
            j += 1
            
        if j == len(time_lens):
            break
    return task_count

inp = [* map(lambda x: [* map(int, x.strip().split())], sys.stdin)][1:]

print(frosh(*inp))