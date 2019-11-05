import requests

if __name__ == "__main__":
    res = requests.delete(
        url='http://localhost:8080/resource/t6'
    )

    print(res.status_code)