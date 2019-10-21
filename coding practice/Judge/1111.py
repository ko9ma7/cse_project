'''
    1111. 나무 쌓기 2

    초등학교에 입학한 한기대는 격차 모양의 마닥에 나무 쌓기 놀이를 하고 있습니다.
    나무를 바닥에 쌓으면서 놀던 기대는 문득 바닥에 놓여있는 나무의 보이는 면의 개수를 세기 시작했습니다.
    바닥에 하나의 나무 블록을 놓으면, 아래와 같이 바닥면을 제외하고 총 5개의 면을 볼 수 있습니다.

    여기에 나무 블럭을 기존의 블럭에 맞닿게 붙여 넣으면 바닥 면을 제외하고 8개의 면을 볼 수 있게 됩니다.
    하지만 나무 블럭을 한칸 띄워서 두면 바닥 면을 제외하고 10개의 면을 볼 수 있게 되지요.

    더 많은 나무를 쌓아둔 기대는 스스로 보이는 면의 개수를 세기가 힘들어져서 당신에게 도움을 요청하려 합니다.
    한기대를 도와서 바닥에 쌓인 나무 블록의 보이는 면이 몇 개인지를 출력하는 프로그램을 작성해 주세요.

    # 입력
        첫 줄에는 나무를 쌓을 격자의 크기가 가로(X), 세로 (Y)로 주어집니다. (1 <= X, Y <= 50)
        그다음 줄부터는 (X, Y) 위치에 쌓인 나무의 개수 n (0 <= n <= 100) 이 주어집니다.

    # 출력
        나무 블록을 보았을 때 보이는 면의 총 개수를 출력해 주세요.
'''

X, Y = map(int, input().split())

block = [list(map(int, input().split())) for y in range(Y)]

l_r = 0
f_b = 0
up = 0

# left & right
for y in range(Y):
    ans = 0
    for x in range(X):
        if ans < block[y][x]:
            ans = block[y][x]
    l_r += ans
l_r = l_r * 2

# front & back
for x in range(X):
    ans = 0
    for y in range(Y):
        if ans < block[y][x]:
            ans = block[y][x]
    f_b += ans
f_b = f_b * 2

# up
for y in range(Y):
    for x in range(X):
        if block[y][x] > 0:
            up += 1

print(l_r)
print(f_b)
print(up)
print(l_r + f_b + up)