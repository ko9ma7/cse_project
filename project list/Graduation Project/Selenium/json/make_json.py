import json
from collections import OrderedDict

file_data = OrderedDict()

file_data['user_name'] = 'Haewoong Kwak'
file_data['user_email'] = 'kimsc@koreatech.ac.kr'
file_data['curr_url'] = 'https://www.google.com/search?q=4%EC%B0%A8%EC%82%B0%EC%97%85%ED%98%81%EB%AA%85%EC%9D%B4%EB%9E%80&oq=4%EC%B0%A8%EC%82%B0%EC%97%85%ED%98%81%EB%AA%85%EC%9D%B4%EB%9E%80&aqs=chrome..69i57j0l4.3317j0j7&sourceid=chrome&ie=UTF-8'
file_data['prev_url'] = 'https//www.google.com'
file_data['paths'] = ['www.google.com', '4차산업혁명이란']
file_data['level'] = 1
file_data['tagged'] = 'False'
file_data['memo'] = ''
file_data['project_name'] = '시연용 프로젝트'

with open('1_1.json', 'w', encoding='utf-8') as make_file:
    json.dump(file_data, make_file, ensure_ascii=False, indent='\t')