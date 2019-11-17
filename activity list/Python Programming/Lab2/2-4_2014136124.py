"""문제4번"""
"""금융 어플리케이션: 급여"""
name = input("사원이름을 입력하세요:")
hour = eval(input("주당 근무시간을 입력하세요:"))
pay = eval(input("시간당 급여를 입력하세요:"))
origin_tax_rate = eval(input("원천징수세율을 입력하세요:"))
residence_tax_rate = eval(input("주민세율을 입력하세요:"))
#총 급여(근무시간 * 임금)
salary = hour * pay
#원천징수세(20.0%)
origin_tax = salary * 0.2
#주민세(9.0%)
residence_tax = salary * 0.09
#총 공제(원천징수세 + 주민세)
total_tax = origin_tax + residence_tax
#공제 후 급여(총 급여 - 총 공제)
final_salary = salary - total_tax
print("\n")
print("사원 이름:" + name)
print("주당 근무시간:",hour)
print("임금:",pay)
print("총 급여:",salary)
print("공제:")
print("    원천징수세(20.0%):",int(origin_tax))
print("    주민세(9.0%):",int(residence_tax))
print("    총 공제:",int(total_tax))
print("공제 후 급여:",int(final_salary))
