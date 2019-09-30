'''
    zip 함수

    여러 개의 리스트나 튜플 또는 다른 순차 자료형을 서로 짝지어서 튜플의 리스트를 생성
'''

seq1 = ['foo', 'bar', 'baz']
seq2 = ['one', 'two', 'three']
zipped = zip(seq1, seq2)
zipped = list(zipped)
print(zipped)
print()

# zip 함수로 여러 개의 순차 자료형을 동시 순회하기
for i, (a, b) in enumerate(zipped):
    print('{0}: {1}, {2}'.format(i, a, b))
print()

# zip 함수로 짝지어진 순차 자료형을 다시 풀어내기
pitchers = [('Nolan', 'Ryan'), ('Roger', 'Clemens'), ('Schiling', 'Curt')]
first_names, last_names = zip(*pitchers)
print(first_names)
print(last_names)