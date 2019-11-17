"""문제2번"""
"""기하학: 정다각형의 넓이"""
import math
n = eval(input("변의 개수를 입력하세요:"))
s = eval(input("변의 길이를 입력하세요:"))
Area = (n * s * s) / (4 * math.tan(math.pi / n))
print("다각형의 넓이는",Area,"입니다.")
