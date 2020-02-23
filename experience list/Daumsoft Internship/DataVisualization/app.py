import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from embedding import embedding
from machine_learning import machine_learning, pre_train_machine_learning
from dimension_reduction import dimension_reduction
import pandas as pd

# train_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/train_list/'
# test_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/test_list/'
# save_model_path = 'C:/Users/daumsoft/PycharmProjects/visualization/model/'
# path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

train_file_path = 'C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/train_list/'
test_file_path = 'C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/test_list/'
save_model_path = 'C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/model/'
path = r'C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/csv_files/'

app = Flask(__name__)

app.secret_key = "secret key"
app.config['train_file_path'] = train_file_path
app.config['test_file_path'] = test_file_path
app.config['save_model_path'] = save_model_path


@app.route("/", methods=["GET", "POST"])
def page1():
    return render_template('introduction.html')


@app.route('/fileUpload', methods=["GET", "POST"])
def page2():

    train_file_list = os.listdir(train_file_path)
    test_file_list = os.listdir(test_file_path)
    model_list = os.listdir(save_model_path)

    if request.method == "POST":

        train_file = request.files["train_file"]
        test_file = request.files["test_file"]

        if train_file:
            f1 = secure_filename(train_file.filename)
            train_file.save(os.path.join(app.config['train_file_path'], f1))

        if test_file:
            f2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['test_file_path'], f2))

        return render_template('fileUpload.html', filename1=f1, filename2=f2, train_file_lis=train_file_list, test_file_list=test_file_list, model_list=model_list)

    else:
        return render_template('fileUpload.html', train_file_list=train_file_list, test_file_list=test_file_list, model_list=model_list)


@app.route('/machineLearning', methods=["GET", "POST"])
def page3():

    train_file_list = os.listdir(train_file_path)
    test_file_list = os.listdir(test_file_path)

    if request.method == "POST":

        # 시각화 버튼을 눌렀을 경우
        if request.form.get("visual_button"):

            checked_list = request.form.getlist("visual_button")
            checked_list = checked_list[0].split(",")
            print(checked_list)

            is_pre_train = False

            if checked_list[5] == 'pre-train':
                is_pre_train = True

            else:

                ##### embedding parameters #####
                if checked_list[2] == 'CounterVector':

                    embed_params = {
                        "tokenizer": None if checked_list[6] == 'None' else str(checked_list[6]),
                        "stop_words": None if checked_list[7] == 'None' else str(checked_list[7]),
                        "min_df": int(checked_list[8]),
                        "max_df": int(checked_list[9]),
                        "max_features": None if checked_list[10] == 'None' else int(checked_list[10]),
                        "binary": False if checked_list[11] == 'False' else True,
                    }

                    print(embed_params)

                elif checked_list[2] == 'TF-IDF':

                    embed_params = {
                        "tokenizer": None if checked_list[6] == 'None' else str(checked_list[6]),
                        "stop_words": None if checked_list[7] == 'None' else str(checked_list[7]),
                        "min_df": int(checked_list[8]),
                        "max_df": int(checked_list[9]),
                        "max_features": None if checked_list[10] == 'None' else int(checked_list[10]),
                        "binary": False if checked_list[11] == 'False' else True,
                    }

                    print(embed_params)

                elif checked_list[2] == 'Doc2Vec':

                    embed_params = {
                        "dm": int(checked_list[6]),
                        "vector_size": int(checked_list[7]),
                        "window": int(checked_list[8]),
                        "alpha": float(checked_list[9]),
                        "epochs": int(checked_list[10]),
                        "negative": int(checked_list[11]),
                    }

                    print(embed_params)

                elif checked_list[2] == 'user_defined_embedding':
                    pass


                ##### machine learning parameters #####
                if checked_list[3] == 'Logistic':
                    for i in range(12, len(checked_list)):
                        checked_list[i] = checked_list[i].split(" ")

                    machine_params = {
                        "penalty": [str(i) for i in checked_list[12]],
                        "C": [float(i) for i in checked_list[13]],
                        "random_state": [int(i) for i in checked_list[14]],
                        "max_iter": [int(i) for i in checked_list[15]],
                        "l1_ratio": [None] if checked_list[16][0] == 'None' else [float(i) for i in checked_list[16]],
                    }

                    print(machine_params)

                # 8개 파라미터
                elif checked_list[3] == 'SVM':
                    for i in range(12, len(checked_list)):
                        checked_list[i] = checked_list[i].split(" ")

                    machine_params = {
                        "loss": [str(i) for i in checked_list[12]],
                        "penalty": [str(i) for i in checked_list[13]],
                        "alpha": [float(i) for i in checked_list[14]],
                        "l1_ratio": [float(i) for i in checked_list[15]],
                        "max_iter": [int(i) for i in checked_list[16]],
                        "random_state": [int(i) for i in checked_list[17]],
                        "learning_rate": [str(i) for i in checked_list[18]],
                        "eta0": [float(i) for i in checked_list[19]],
                    }

                    print(machine_params)

                # 3개 파라미터
                elif checked_list[3] == 'RandomForest':
                    for i in range(12, len(checked_list)):
                        checked_list[i] = checked_list[i].split(" ")

                    machine_params = {
                            "n_estimators": [int(i) for i in checked_list[12]],
                            "max_depth": [None] if checked_list[13][0] == 'None' else [int(i) for i in checked_list[13]],
                            "random_state": [int(i) for i in checked_list[14]],
                        }

                    print(machine_params)

                # 8개 파라미터
                elif checked_list[3] == 'FNN':
                    for i in range(12, len(checked_list)):
                        checked_list[i] = checked_list[i].split(" ")

                    machine_params = {
                        "input_layer_units": [int(i) for i in checked_list[12]],
                        "hidden_layer_units": [int(i) for i in checked_list[13]],
                        "input_layer_activation": [str(i) for i in checked_list[14]],
                        "hidden_layer_activation": [str(i) for i in checked_list[15]],
                        "output_layer_activation": [str(i) for i in checked_list[16]],
                        "optimizer": [str(i) for i in checked_list[17]],
                        "epochs": [int(i) for i in checked_list[18]],
                        "batch_size": [int(i) for i in checked_list[19]],
                    }

                    print(machine_params)

                elif checked_list[3] == 'user_defined_machine_learning':
                    pass

            train = pd.read_csv(train_file_path + checked_list[0])
            test = pd.read_csv(test_file_path + checked_list[1])

            # 결측치가 있는지 확인하기(우선은 제거하는 방식)
            if pd.isnull(train['x']).sum() > 0 or pd.isnull(train['y']).sum() > 0:
                train = train.dropna()
            if pd.isnull(test['x']).sum() > 0 or pd.isnull(test['y']).sum() > 0:
                test = test.dropna()

            train = train.sample(frac=1).reset_index(drop=True)
            test = test.sample(frac=1).reset_index(drop=True)

            X_train, X_test, y_train, y_test = embedding(checked_list[2], train, test, embed_params)
            print('X_train type: {}, y_train type: {}'.format(type(X_train), type(y_train)))
            print('X_test type: {}, y_test type: {}'.format(type(X_test), type(y_test)))

            print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
            print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))

            dimension_reduction(checked_list[4], X_train, X_test, y_train, y_test)

            # 임베딩만 시각화할 경우
            if checked_list[3] == '':

                print('임베딩만 시각화할 경우')
                return render_template('visualization.html', visualization="embedding_and_visualization")

            # 임베딩 -> 머신러닝 -> 시각화할 경우
            else:

                print('임베딩 및 머신러닝 시각화할 경우')
                if is_pre_train == False:
                    train_y_pred, test_y_pred = machine_learning(checked_list[3], X_train, X_test, y_train, y_test, machine_params)

                else:
                    train_y_pred, test_y_pred = pre_train_machine_learning(checked_list[3], X_train, X_test, y_train, y_test)

                train_df = pd.read_csv(path + 'embedding_and_visualization_train.csv')
                test_df = pd.read_csv(path + 'embedding_and_visualization_test.csv')

                train_df['pred'] = train_y_pred
                train_df['success'] = train_df['pred'] == train_df['target']
                train_df['success'] = train_df['success'].astype(int)

                test_df['pred'] = test_y_pred
                test_df['success'] = test_df['pred'] == test_df['target']
                test_df['success'] = test_df['success'].astype(int)

                train_df.to_csv(path + 'embedding_and_machinelearning_visualization_train.csv', index=False)
                test_df.to_csv(path + 'embedding_and_machinelearning_visualization_test.csv', index=False)

            return render_template('visualization.html', visualization="embedding_and_machineLearning_visualization")

        return render_template('machineLearning.html', train_file_list=train_file_list, test_file_list=test_file_list)
    else:
        return render_template("machineLearning.html", train_file_list=train_file_list, test_file_list=test_file_list)


@app.route('/visualization', methods=["GET", "POST"])
def page4():
    return render_template('visualization.html', visualization="embedding_and_machineLearning_visualization")


# 훈련 데이터 평가 지표 값을 받는 라우터
@app.route('/metrics_score_train')
def data1_1():
    df = pd.read_csv(path + 'metrics_score_train.csv')
    return df.to_csv()


# 테스트 데이터 평가 지표 값을 받는 라우터
@app.route('/metrics_score_test')
def data1_2():
    df = pd.read_csv(path + 'metrics_score_test.csv')
    return df.to_csv()


# 훈련 데이터 오차 행렬 값을 받는 라우터
@app.route('/confusion_matrix_train')
def data2_1():
    df = pd.read_csv(path + 'confusion_matrix_train.csv')
    values = df.values
    columns = df.columns

    # 그래프를 그리기 위한 데이터 정렬
    new_df = pd.DataFrame(columns=['group', 'variable', 'value'])

    cnt = 0
    for idx in range(len(values)):
        for jdx in range(len(values[idx]) - 1, -1, -1):
            new_df.loc[cnt, ['group']] = columns[idx]
            new_df.loc[cnt, ['variable']] = columns[jdx]
            new_df.loc[cnt, ['value']] = values[jdx][idx]
            cnt += 1

    return new_df.to_csv()


# 테스트 데이터 오차 행렬 값을 받는 라우터
@app.route('/confusion_matrix_test')
def data2_2():
    df = pd.read_csv(path + 'confusion_matrix_test.csv')
    values = df.values
    columns = df.columns

    # 그래프를 그리기 위한 데이터 정렬
    new_df = pd.DataFrame(columns=['group', 'variable', 'value'])

    cnt = 0
    for idx in range(len(values)):
        for jdx in range(len(values[idx])- 1, -1, -1):
            new_df.loc[cnt, ['group']] = columns[idx]
            new_df.loc[cnt, ['variable']] = columns[jdx]
            new_df.loc[cnt, ['value']] = values[jdx][idx]
            cnt += 1

    return new_df.to_csv()


# 훈련 데이터 차원 축소 값을 받는 라우터
@app.route('/embedding_and_visualization_train')
def data3_1():
    print('훈련 csv 파일 생성 완료')
    df = pd.read_csv(path + 'embedding_and_visualization_train.csv')
    return df.to_csv()


# 테스트 데이터 차원 축소 값을 받는 라우터
@app.route('/embedding_and_visualization_test')
def data3_2():
    print('테스트 csv 파일 생성 완료')
    df = pd.read_csv(path + 'embedding_and_visualization_test.csv')
    return df.to_csv()


# 훈련 데이터 머신러닝 후의 차원 축소 값을 받는 라우터
@app.route('/embedding_and_machinelearning_visualization_train')
def data4_1():
    df = pd.read_csv(path + 'embedding_and_machinelearning_visualization_train.csv')
    return df.to_csv()


# 테스트 데이터 머신러닝 후의 차원 축소 값을 받는 라우터
@app.route('/embedding_and_machinelearning_visualization_test')
def data4_2():
    df = pd.read_csv(path + 'embedding_and_machinelearning_visualization_test.csv')
    return df.to_csv()


if __name__ == "__main__":

    port = 8080
    os.system("open http://localhost:{0}".format(port))

    app.debug = True
    app.run(port=port)