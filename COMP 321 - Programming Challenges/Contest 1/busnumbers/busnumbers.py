import sys

def busnumbers(n, nums):
    nums = sorted(nums)
    i = 0
    while i < n:
        start = nums[i]
        while i + 1 < n and nums[i] == nums[i + 1] - 1:
            i += 1
        end = nums[i]
        
        if start == end:
            print(f'{start}', end='\n' if i == n - 1 else ' ')
        
        elif start == end - 1:
            print(f'{start} {end}', end='\n' if i == n - 1 else ' ')
        
        else:
            print(f'{start}-{end}', end='\n' if i == n - 1 else ' ')
        i += 1

inp = [*map(lambda x: [*map(int, x.split())], sys.stdin)]
busnumbers(inp[0][0], inp[1])