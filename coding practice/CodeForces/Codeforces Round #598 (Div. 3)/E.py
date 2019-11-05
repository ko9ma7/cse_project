n = int(input())
students = sorted(list(map(int, input().split())))

if n // 3 == 1:
    max_program = max(students)
    min_program = min(students)
    diversity = max_program - min_program
else:
    r = n // 3
    new_s = []
    for j in range(3):
        new_s.append(students[j])
