Q = int(input())

for q in range(Q):
    N = int(input())
    kids = list(map(int, input().split()))

    ans = []
    for i in range(len(kids)):
        ans.append(0)

    for i in range(len(kids)):
        if kids[i] == i+1:
            ans[i] = 1
        else:
            k = 0
            cnt = 0
            print('=======', i)
            while k != len(kids):
                print(i)

                kids[i], kids[kids[i]-1] = kids[kids[i]-1], kids[i]
                print(kids)
                i = kids[i]-1
                k += 1

            ans[i] += cnt

    print(ans)

    # for i in range(len(ans)):
    #     if i == len(ans) - 1:
    #         print(ans[i])
    #     else:
    #         print(ans[i], end=' ')