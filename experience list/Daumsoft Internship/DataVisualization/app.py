import os
import logging
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename

from embedding import embedding
import machine_learning
from dimension_reduction import dimension_reduction

import pandas as pd

from sklearn.metrics import accuracy_score


UPLOAD_FOLDER = 'C:/Users/daumsoft/PycharmProjects/visualization/uploads'
ALLOWED_EXTENSIONS = set(['csv'])

app = Flask(__name__)

app.secret_key = "secret key"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route("/", methods=["GET", "POST"])
def main():
    if request.method == "POST":

        train_file = request.files["train_file"]
        test_file = request.files["test_file"]

        if train_file and allowed_file(train_file.filename):
            filename1 = secure_filename(train_file.filename)
            train_file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename1))
            return render_template('index.html', filename1=filename1)

        if test_file and allowed_file(test_file.filename):
            filename2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename2))
            return render_template('index.html', filename2=filename2)

        checked_list = request.form.getlist('checked_list')
        print(checked_list)

        # 시각화 버튼을 눌렀을 경우
        if request.form.get("visual_button"):

            train = pd.read_csv("C:/Users/daumsoft/PycharmProjects/visualization/uploads/train.csv", encoding='CP949')
            test = pd.read_csv("C:/Users/daumsoft/PycharmProjects/visualization/uploads/test.csv", encoding='CP949')

            print(train)
            print(test)

            # 첫 번째 순서: 임베딩
            X_train, X_test, y_train, y_test = embedding(checked_list[0], train, test)

            print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
            print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))

            logging.basicConfig(format='%(asctime)s %(levelname)s %(message)s', level=logging.DEBUG)

            # 두 번째 순서: 기계학습
            if checked_list[1] == 'Logistic':

                log_params = {
                    "C": [0.001, 0.01, 0.1, 1, 10, 100, 1000],
                    "eta0": [0.01, 0.001],
                    'random_state': [42],
                }
                log_clf = machine_learning.myLogisticRegression(log_params)

            elif checked_list[1] == 'SVC':

                svc_params = {
                    "C": [0.001, 0.01, 0.1, 1, 10, 100, 1000],
                    "gamma": [0.01, 0.1, 1],  # 커널 폭의 역수, 작을 수록 커널 폭이 넓어져, 데이터 영향 범위 증가
                    "probability": [True],  # SVC가 'predict_proba'를 계산하기 위해 지정
                    'random_state': [42],
                }
                svc_clf = machine_learning.mySVC(svc_params)

                svc_clf.fit(X_train, y_train)

                # 평가
                train_y_pred = svc_clf.predict(X_test)
                train_acc = accuracy_score(y_test, train_y_pred)
                test_y_pred = svc_clf.predict(X_test)
                test_acc = accuracy_score(y_test, test_y_pred)

                print("svc train acc : {:.4f}, test acc :{:.4f}".format(train_acc, test_acc))

            elif checked_list[1] == 'RandomForest':

                rnd_params = {
                    "n_estimators": [100],  # [100, 300, 500, 700],
                    "max_depth": [3],  # [i for i in range(1, 31)],
                    'random_state': [42],
                }
                rnd_clf = machine_learning.myRandomForestClassifier(rnd_params)

                rnd_clf.fit(X_train, y_train)

                # 평가
                train_y_pred = rnd_clf.predict(X_test)
                train_acc = accuracy_score(y_test, train_y_pred)
                test_y_pred = rnd_clf.predict(X_test)
                test_acc = accuracy_score(y_test, test_y_pred)

                print("random forest train acc : {:.4f}, test acc :{:.4f}".format(train_acc, test_acc))

                # 히트맵으로 시각화하기
                from sklearn.metrics import confusion_matrix

                index = list(set(train.y))
                rnd_clf_df = pd.DataFrame(confusion_matrix(y_test, test_y_pred), index=index, columns=index)

                rnd_clf_df.to_csv('confusion_matrix.csv', index=False)
                df_json = rnd_clf_df.to_json()



            elif checked_list[1] == 'FNN':
                pass
            elif checked_list[1] == 'user_defined_machine_learning':
                pass

            # 세 번째 순서: 차원축소
            dimension_reduction_train_data = dimension_reduction(checked_list[2], machine_learning_train_data)
            dimension_reduction_test_data = dimension_reduction(checked_list[2], machine_learning_test_data)

        return render_template("index.html")

    elif request.method == "GET":
        return render_template("index.html")

@app.route("/file_upload")
def file_upload():
    return render_template("upload.html")

@app.route("/confusion_matrix")
def matrix():
    df = pd.read_csv('heatmap_data.csv')
    return df.to_csv()

@app.route('/confusion_matrix/heatmap')
def heatmap():
    return render_template('visualization.html')

if __name__ == "__main__":
    app.run(host='localhost', port=5000, debug=True)