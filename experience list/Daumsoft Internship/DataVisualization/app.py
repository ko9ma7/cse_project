import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from embedding import embedding
from machine_learning import machine_learning
from dimension_reduction import dimension_reduction
import pandas as pd

UPLOAD_FOLDER = 'C:/Users/daumsoft/PycharmProjects/visualization/uploads'

app = Flask(__name__)

app.secret_key = "secret key"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


@app.route("/", methods=["GET", "POST"])
def page1():
    return render_template('introduction.html')


@app.route('/fileUpload', methods=["GET", "POST"])
def page2():
    if request.method == "POST":

        train_file = request.files["train_file"]
        test_file = request.files["test_file"]

        if train_file:
            f1 = secure_filename(train_file.filename)
            train_file.save(os.path.join(app.config['UPLOAD_FOLDER'], f1))

        if test_file:
            f2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['UPLOAD_FOLDER'], f2))

        return render_template('fileUpload.html', filename1=f1, filename2=f2)

    else:
        return render_template('fileUpload.html')


@app.route('/machineLearning', methods=["GET", "POST"])
def page3():

    if request.method == "POST":

        # 시각화 버튼을 눌렀을 경우
        if request.form.get("visual_button"):

            checked_list = request.form.getlist('checked_list')
            print(checked_list)

            return render_template("machineLearning.html")

            # train = pd.read_csv("C:/Users/daumsoft/PycharmProjects/visualization/uploads/train.csv", encoding='CP949')
            # test = pd.read_csv("C:/Users/daumsoft/PycharmProjects/visualization/uploads/test.csv", encoding='CP949')
            #
            # # 임베딩
            # X_train, X_test, y_train, y_test = embedding(checked_list[0], train, test)
            # print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
            # print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))
            #
            # # 임베딩 -> 머신러닝
            # if checked_list[1] in ['Logistic', 'SVM', 'RandomForest', 'FNN', 'user_defined_machine_learning']:
            #
            #     y_test, test_y_pred = machine_learning(checked_list[1], X_train, X_test, y_train, y_test)
            #
            #     from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, f1_score
            #
            #     # 오차행렬
            #     index = list(set(y_test))
            #     confusion_matrix_df = pd.DataFrame(confusion_matrix(y_test, test_y_pred), index=index, columns=index)
            #     confusion_matrix_df.to_csv('confusion_matrix.csv', index=False)
            #
            #     # 분류 평가 지표
            #     accuracy = accuracy_score(y_test, test_y_pred)
            #     precision = precision_score(y_test, test_y_pred, average='macro')
            #     recall = recall_score(y_test, test_y_pred, average='macro')
            #     f1 = f1_score(y_test, test_y_pred, average='macro')
            #
            #     metrix_score_df = pd.DataFrame(columns=['Metrics', 'Score'])
            #
            #     metrix_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
            #     metrix_score_df['Score'] = [round(accuracy, 2), round(precision, 2), round(recall, 2), round(f1, 2)]
            #     metrix_score_df.to_csv('metrics_score.csv', index=False)
            #
            #     return render_template('visualization.html', visualization="embedding_machine_learning")
            #
            # # 임베딩 -> 차원축소
            # elif checked_list[1] in ['PCA', 'MDS', 'TSNE', 'user_defined_dimension_reduction']:
            #
            #     # 훈련 데이터를 차원축소
            #     dimension_reduction_result = dimension_reduction(checked_list[1], X_train)
            #     print(dimension_reduction_result)
            #
            #     dimension_reduction_result_df = pd.DataFrame(dimension_reduction_result, columns=['r0', 'r1'])
            #     y_df = pd.DataFrame(y_train, columns=['target'])
            #
            #     dimension_reduction_result_df = pd.concat([dimension_reduction_result_df, y_df], axis=1)
            #     dimension_reduction_result_df.to_csv('dimension_reduction.csv', index=False)
            #
            #     return render_template('visualization.html', visualization="embedding_dimension_reduction")
            #
            # # 임베딩 -> 머신러닝 -> 차원축소
            # elif checked_list[1] in ['Logistic', 'SVM', 'RandomForest', 'FNN', 'user_defined_machine_learning'] and \
            #     checked_list[2] in ['PCA', 'MDS', 'TSNE', 'user_defined_dimension_reduction']:
            #
            #     return render_template('visualization.html', visualization="embedding_machine_learning_dimension_reduction")

        return render_template("machineLearning.html")

    else:
        return render_template("machineLearning.html")


@app.route('/visualization', methods=["GET", "POST"])
def page4():
    return render_template('visualization.html')

# 평가 지표 값을 받는 라우터
@app.route('/metrics_score')
def data1():
    df = pd.read_csv('metrics_score.csv')
    return df.to_csv()


# 정확도-재현율 값을 받는 라우터
@app.route('/precision_recall_data')
def data2():
    df = pd.read_csv('pre_rec_data.csv')
    return df.to_csv()


# 오차 행렬 값을 받는 라우터
@app.route('/confusion_matrix')
def data3():
    df = pd.read_csv('confusion_matrix.csv')
    values = df.values

    # 그래프를 그리기 위한 데이터 정렬
    new_df = pd.DataFrame(columns=['group', 'variable', 'value'])

    cnt = 0
    for idx in range(len(values)):
        for jdx in range(len(values[idx])- 1, -1, -1):
            new_df.loc[cnt, ['group']] = idx
            new_df.loc[cnt, ['variable']] = jdx
            new_df.loc[cnt, ['value']] = values[jdx][idx]
            cnt += 1

    new_df = new_df.astype('int')
    print(new_df)
    return new_df.to_csv()


# 차원 축소 값을 받는 라우터
@app.route('/dimension_reduction')
def data4():
    df = pd.read_csv('dimension_reduction.csv')
    return df.to_csv()


if __name__ == "__main__":

    port = 8080
    os.system("open http://localhost:{0}".format(port))

    app.debug = True
    app.run(port=port)