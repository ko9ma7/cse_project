"""문제 2번"""
"""정수의 자릿수 합산하기"""

#0부터 100사이의 숫자를 입력 받기 위한 변수 num을 생성하여 eval(input)을 이용하여 값을 입력받는다.

num = eval(input("0과 1000 사이의 숫자를 입력하세요:"))

#백의 자리는 num을 100으로 나눈 몫이다.

hundred = num // 100

#십의 자리는 num을 100으로 나눈 나머지를 다시 10으로 나눈 몫이다.

ten = (num % 100) // 10

#일의 자리는 num을 num을 100으로 나눈 나머지를 다시 10으로 나눈 나머지이다.

one = (num % 100) % 10

#주어진 문제의 결과는 각 자릿수의 합이므로 백의 자리, 십의 자리, 일의 자리를 합한다.

result = hundred + ten + one

print("각 자릿수의 합은 ",result,"입니다")
