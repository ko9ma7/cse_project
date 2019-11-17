import requests

if __name__ == "__main__":
    res = requests.post(
        url='http://localhost:8080/resource_creation',
        # 'python'의 'dictionary'는 웹으로 가면서 'json'으로 자동 변환(Serializing, Marshalling)
        data={
            'sensor_id': 't6',
            'temperature': 3425235.0,
            'datetime': '2019-10-29 12:26:00',
            'location': 'KoreaTech'
        }
    )

    print(res.status_code)
    print(res.json())