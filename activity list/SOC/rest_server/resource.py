import requests
from flask_restful import Resource, abort, reqparse

from database.resource_db_access import TemperatureResourceDatabase
from keys import KAKAO_REST_KEY
from rest_client.read_kakao import KAKAO_BASE_URL

headers = {"Authorization": "KakaoAK " + KAKAO_REST_KEY}

# 'flask_restful'에 있는 부모 클래스 'Resource'를 상속하는 서브 클래스
class TemperatureResource(Resource):
    def __init__(self):
        self.temperature_resource_db = TemperatureResourceDatabase()
        self.parser = reqparse.RequestParser()
        # 서버에서 parsing하기 위해 받는 정보들 추가
        self.parser.add_argument('temperature')
        self.parser.add_argument('datetime')
        self.parser.add_argument('location')

    # get 요청을 받았을 경우
    def get(self, sensor_id):


        res1 = requests.get(
            url=KAKAO_BASE_URL + "/v3/search/book?target=title&query=자이스토리",
            headers=headers
        )

        if res1.status_code == 200:
            books = res1.json()
        else:
            books = {}

        return books, 200
        # temperature = self.temperature_resource_db.readBySensorId(sensor_id=sensor_id)
        # if temperature is None:
        #     abort(404, message="Sensor id {0} doesn't exist".format(sensor_id))
        # else:
        #     # Resource 부모 클래스에서 Serializable 개념을 통해 자동적으로 json 파일로 반환해줌
        #     return temperature, 200

    # put 요청을 받았을 경우
    def put(self, sensor_id):
        # parser add 된 인자 3개가 반환
        args = self.parser.parse_args()
        temperature = self.temperature_resource_db.readBySensorId(sensor_id=sensor_id)
        if temperature is None:
            abort(404, message="Error! Sensor id {0} doesn't exist".format(sensor_id))
        else:
            self.temperature_resource_db.update(
                sensor_id=sensor_id,
                temperature=args['temperature'],
                datetime=args['datetime'],
                location=args['location']
            )
            return {"sensor_id": sensor_id}, 200

    # delete 요청을 받았을 경우
    def delete(self, sensor_id):
        temperature = self.temperature_resource_db.readBySensorId(sensor_id=sensor_id)
        if temperature is None:
            # 이 방법이 좀 더 이론적
            return {"sensor_id": sensor_id}, 204
            # abort(404, message="Error! Sensor id {0} doesn't exist".format(sensor_id))
        else:
            self.temperature_resource_db.delete(sensor_id=sensor_id)
            return {"sensor_id": sensor_id}, 204 # delete 성공 시에는 204가 약속

# post 요청만 받을 수 있는 클래스
class TemperatureCreationResource(Resource):
    def __init__(self):
        self.temperature_resource_db = TemperatureResourceDatabase()
        self.parser = reqparse.RequestParser()
        # post 방식은 'sensor_id'가 따로 필요함
        self.parser.add_argument('sensor_id')
        self.parser.add_argument('temperature')
        self.parser.add_argument('datetime')
        self.parser.add_argument('location')

    def post(self):
        args = self.parser.parse_args()
        sensor_id = args['sensor_id']
        # 'sensor_id'가 데이터베이스에 있는지 확인
        temperature = self.temperature_resource_db.readBySensorId(sensor_id=sensor_id)
        if temperature is not None:
            # post 요청에 의하여 이미 존재하는 리소스에 대한 생성 요청을 하면 응답 코드가 409
            abort(409, message="Error! Sensor id {0} already exist".format(sensor_id))
        else:
            self.temperature_resource_db.crate(
                sensor_id=sensor_id,
                temperature=args['temperature'],
                datetime=args['datetime'],
                location=args['location']
            )
            # post 요청 성공시에 보내는 응답 코드가 201
            return {"sensor_id": sensor_id}, 201


class TemperatureByLocationResource(Resource):
    def __init__(self):
        self.temperature_resource_db = TemperatureResourceDatabase()

    def get(self, location):
        temperature = self.temperature_resource_db.readByLocation(location=location)
        if temperature is None:
            abort(404, message="The sensor where the location includes {0} doesn't exist".format(location))
        else:
            return temperature, 200

# 불쾌지수 구하는 클래스
class DiscomfortIndexResource(Resource):
    def __init__(self):
        self.temperature_resoure_db = TemperatureResourceDatabase()

    def get(self):
        pass