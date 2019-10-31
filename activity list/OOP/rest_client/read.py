import requests

if __name__ == "__main__":
    res = requests.get(
        url='http://localhost:8080/resource/t2'
    )
    print(res.status_code)
    d = res.json()
    print(d['sensor_id'])
    print(d['temperature'])
    print(d['datetime'])
    print(d['location'])
    print(res.json())

    res = requests.get(
        url='http://localhost:8080/resource_location/BackTracking'
    )
    print(res.status_code)
    print(res.json())

    res = requests.get(
        url='http://localhost:8080/resource_location/BackTracking'
    )

    temperature = (float)(res.json()['temperature'])
    print(temperature)