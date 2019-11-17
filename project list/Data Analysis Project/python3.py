import itertools

first_letter = lambda x: x[0]
names = ['Alan', 'Adam', 'Wes', 'Will', 'Albert', 'Steven']

for letter, names in itertools.groupby(names, first_letter): # iterable에서 각각의 고유한 키에 따라 그룹을 생성
    print(letter, list(names))