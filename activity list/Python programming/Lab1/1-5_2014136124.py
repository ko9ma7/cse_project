"""문제 5번"""
"""현재 시간"""

#time함수를 사용하기 위해 time 모듈을 이용한다.

import time

# 현재 시간

currentTime = time.time() 

# 1970년 1월 1일 자정 이후로의 전체 초 값
 
totalSeconds = int(currentTime)

# 현재 시간의 초 값

currentSecond = totalSeconds % 60

# 전체 분 값

totalMinutes = totalSeconds // 60

# 현재 시간의 분 값

currentMinute = totalMinutes % 60

# 전체 시 값

totalHours = totalMinutes // 60 

# 현재 시간의 시 값

currentHour = totalHours % 24

#GMT와 시간대 차이를 입력 받는다.

dis = eval(input("GMT 와 시간대 차이를 입력하세요:"))

#입력된 차이 시간대를 GMT와 더해서 현재 시간을 구한다. 

PresentHour = currentHour + dis

#차이는 시간만 계산한다.

print("현재 시간은 "+str(PresentHour)+":"+str(currentMinute)+":"+str(currentSecond)+" 입니다.")
