import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from embedding import embedding
from machine_learning import machine_learning
from dimension_reduction import dimension_reduction
import pandas as pd

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

            # 첫 번째 순서: 임베딩
            X_train, X_test, y_train, y_test = embedding(checked_list[0], train, test)
            print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
            print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))

            # 두 번째 순서: 기계학습
            y_test, test_y_pred = machine_learning(checked_list[1], X_train, X_test, y_train, y_test)

            # 세 번째 순서: 시각화(임베딩->기계학습)
            from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, f1_score

            # 히트맵
            index = list(set(y_test))
            confusion_matrix_df = pd.DataFrame(confusion_matrix(y_test, test_y_pred), index=index, columns=index)
            confusion_matrix_df.to_csv('confusion_matrix.csv', index=False)

            # 분류 평가 지표
            accuracy = accuracy_score(y_test, test_y_pred)
            precision = precision_score(y_test, test_y_pred, average='macro')
            recall = recall_score(y_test, test_y_pred, average='macro')
            f1 = f1_score(y_test, test_y_pred, average='macro')

            metrix_score_df = pd.DataFrame(columns=['Metrics', 'Score'])

            metrix_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
            metrix_score_df['Score'] = [int(accuracy * 100), int(precision * 100), int(recall * 100), int(f1 * 100)]
            metrix_score_df.to_csv('metrics_score.csv', index=False)

            # # 세 번째 순서: 차원축소
            # dimension_reduction_train_data = dimension_reduction(checked_list[2], machine_learning_train_data)
            # dimension_reduction_test_data = dimension_reduction(checked_list[2], machine_learning_test_data)

            return render_template("index.html")

        return render_template("index.html")

    return render_template('index.html')

# 평가 지표 값을 받는 라우터
@app.route('/metrics_score')
def score():
    df = pd.read_csv('metrics_score.csv')
    return df.to_csv()

# 오차 행렬 값을 받는 라우터
@app.route('/confusion_matrix')
def confusion_matrix():
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

@app.route('/metrics_score/graph')
def metrics_score_graph():
    return render_template('metrics_score.html')

@app.route('/confusion_matrix/graph')
def confusion_matrix_graph():
    return render_template('heatmap.html')

@app.route('/embedding_and_machine_learning_visualization')
def embedding_and_machine_learning_visualization():
    return render_template('score_heatmap_visualization.html')

if __name__ == "__main__":
    import os

    port = 8080
    os.system("open http://localhost:{0}".format(port))

    app.debug = True
    app.run(port=port)