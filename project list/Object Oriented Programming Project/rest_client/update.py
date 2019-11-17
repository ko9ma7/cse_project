import requests

if __name__ == "__main__":
    res = requests.put(
        url='http://localhost:8080/resource/t1',
        data={
            'temperature': 1010.0,
            'datetime': '2019-10-29 08:18:00',
            'location': 'BackTracking'
        }
    )

    print(res.status_code)
    print(res.json())