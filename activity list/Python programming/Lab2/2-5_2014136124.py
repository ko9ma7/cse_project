"""문제5번"""
"""자동판매기 프로그램"""
#물건값
things = eval(input("물건값을 입력하시오:"))
#1000원 지폐개수
thounsand_count = eval(input("1000원 지폐개수:"))
#500원 동전개수
fivehundred_count = eval(input("500원 동전개수:"))
#100원 동전개수
onehundred_count = eval(input("100원 동전개수:"))
#가지고 있는 돈
money = (thounsand_count * 1000) + (fivehundred_count * 500) + (onehundred_count * 100)
#거스름돈
charge = money - things
#거스름돈에서 500원 동전개수
fivehundred_charge_count = int(charge / 500)
#거스름돈에서 100원 동전 개수
onehundred_charge_count = int((charge % 500) / 100)
#거스름돈에서 50원 동전 개수
fifty_charge_count = int((((charge % 500) % 100)) / 50)
#거스름돈에서 10원 동전 개수
ten_charge_count = int((((charge % 500) % 100)) / 10)
#거스름돈에서 1원 동전 개수
one_charge_count = int(((((charge % 500) % 100)) % 10))
print("500원 =",fivehundred_charge_count,"100원 =",onehundred_charge_count,"10원 =",ten_charge_count,"1원 =",one_charge_count)

