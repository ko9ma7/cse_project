"""문제 1번"""
"""기하학: 오각형의 넓이"""
import math
r = eval(input("중심에서 꼭짓점까지의 길이를 입력하세요."))
s = 2 * r * math.sin(math.pi / 5)
Area = (3 * math.sqrt(3) * s * s)/2
print("오각형의 넓이는",format(Area,"0.2f"),"입니다.")
