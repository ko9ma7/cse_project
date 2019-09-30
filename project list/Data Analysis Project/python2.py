import re

some_dict = {'1':'a', '2':'b'}
key = '1'
default_value = 'c'

if key in some_dict:
    value = some_dict[key]
else:
    value = default_value
print(value)
print()

# get은 해당 키가 존재하지 않을 경우 None을 반환한다.
value = some_dict.get(key, default_value)
print(value)
print()

# 사전 표기법
string = ['a', 'as', 'bat', 'car', 'dove', 'python']
loc_mapping = { val : index for index, val in enumerate(string) }
print(loc_mapping)
print()

# 정규 표현식(map 사용)
states = ['Alabama', 'Georgia!', 'Georgia', 'georgia', 'FlOrIda', 'southcarolina##', 'West virginia?']
def remove_punctuation(value):
    return re.sub('[!#?]', '', value)

for x in map(remove_punctuation, states):
    print(x)