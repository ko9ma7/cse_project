import time

d = {1:1, 2:1}
def fibo(x):
    if x == 0:
        return 0
    if x not in d:
        d[x] = fibo(x-1) + fibo(x-2)
    return d[x]

start = time.time()
print(fibo(100))
print("time: ", time.time() - start)

def fibonacci(n):
    a, b = 1, 0
    for i in range(n):
        a, b = b, a + b
    return b

start = time.time()
print(fibonacci(100))
print("time: ", time.time() - start)