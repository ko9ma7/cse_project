"""문제 1번"""
"""파운드를 킬로그램으로 변환하기"""

#파운드의 변수를 선언 후 eval(input())을 이용하여 콘솔에서 값을 입력받는다.

pound = eval(input("파운드 값을 입력하세요:"))

#킬로그램은 파운드의 변수를 이용하여 구할 수 있다.

kilo = pound * 0.454

print(pound , "파운드는" , kilo , "킬로그램입니다")
