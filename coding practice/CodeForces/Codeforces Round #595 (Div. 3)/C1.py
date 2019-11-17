Q = int(input())

for q in range(Q):
    n = int(input())

    sum = 0
    idx = 0
    for i in range(n):
        for j in range(10):
            if pow(3, j) >= n:
                idx = j
                break
        print('idx:', idx)

        if pow(3, idx) >= n:
            print(pow(3, idx))
        else:
            for id in range(idx):
                sum += pow(3, id)
                if sum >= n:
                    print(sum)