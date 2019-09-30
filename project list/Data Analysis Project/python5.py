import random
import numpy as np
import matplotlib.pyplot as plt

'''
# 내장 random 모듈로 walk 구하기
position = 0
walk = [position] # walk는 계단을 오르거나(+1) 내려간(-1) 값의 누적합
steps = 1000
for i in range(steps):
    step = 1 if random.randint(0, 1) else -1
    position += step
    walk.append(position)

plt.plot(walk[:100])

# numpy로 walk 구하기
nsteps = 1000
draws = np.random.randint(0, 2, size=nsteps)
steps = np.where(draws > 0, 1, -1) # draw의 값이 양수이면 1을 반환, 양수가 아니면 -1을 반환
walk = steps.cumsum() # 일차원 배열의 누적합

print(walk)
print(walk.min())
print(walk.max())

# 계단의 처음 위치에서 최초로 10칸 떨어지기까지 얼마나 걸렸는지
print((np.abs(walk) >= 10).argmax()) # boolean 배열에서 최댓갑의 처음 색인을 반환하는 argmax
'''

# 한 번에 시뮬레이션 하기
nwalks = 5000
nsteps = 1000
draws = np.random.randint(0, 2, size=(nwalks, nsteps))
steps = np.where(draws > 0, 1, -1)
walks = steps.cumsum(1)

print(walks)
print(walks.max())
print(walks.min())

# 누적합이 30 혹은 -30이 되는 최소 시점 계산
hits30 = (np.abs(walks) >= 30).any(1)
print(hits30.sum()) # 누적합이 30 또는 -30이 되는 경우의 수

# walks에서 컬럼을 선택하고 절댓값이 30을 넘는 경우에 대해 축 1의 argmax를 구하면 처음 위치에서 30칸 이상 멀어지는 최소 횟수를 구할 수 있다.
crossing_times = (np.abs(walks[hits30]) >= 30).argmax(1)
print(crossing_times.mean())