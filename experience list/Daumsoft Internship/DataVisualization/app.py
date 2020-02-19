import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from embedding import embedding
from machine_learning import machine_learning, pre_train_machine_learning
from dimension_reduction import dimension_reduction
import pandas as pd

train_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/train_list/'
test_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/test_list/'
path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

app = Flask(__name__)

app.secret_key = "secret key"
app.config['train_file_path'] = train_file_path
app.config['test_file_path'] = test_file_path


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
            train_file.save(os.path.join(app.config['train_file_path'], f1))

        if test_file:
            f2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['test_file_path'], f2))

        return render_template('fileUpload.html', filename1=f1, filename2=f2)

    else:
        return render_template('fileUpload.html')


@app.route('/machineLearning', methods=["GET", "POST"])
def page3():

    train_file_list = os.listdir(train_file_path)
    test_file_list = os.listdir(test_file_path)

    print("file_list: {}".format(train_file_list))
    print("file_list: {}".format(test_file_list))

    if request.method == "POST":

        # 시각화 버튼을 눌렀을 경우
        if request.form.get("visual_button"):

            checked_list = request.form.getlist("visual_button")
            checked_list = checked_list[0].split(",")
            print(checked_list)

            # is_pre_train = False
            #
            # if checked_list[5] == 'pre_train':
            #     is_pre_train = True
            #
            # else:
            #     parameters = []
            #     # 5개 파라미터
            #     if checked_list[3] == 'Logistic':
            #         for i in range(6, len(checked_list)):
            #             checked_list[i] = checked_list[i].split(" ")
            #
            #         parameters.append([str(i) for i in checked_list[6]])
            #         parameters.append([float(i) for i in checked_list[7]])
            #         parameters.append([int(i) for i in checked_list[8]])
            #         parameters.append([int(i) for i in checked_list[9]])
            #
            #         if checked_list[10][0] != 'None':
            #             parameters.append([float(i) for i in checked_list[10]])
            #         else:
            #             parameters.append([None])
            #
            #         params = {
            #             "penalty": parameters[0],
            #             "C": parameters[1],
            #             "random_state": parameters[2],
            #             "max_iter": parameters[3],
            #             "l1_ratio": parameters[4],
            #         }
            #
            #         print(params)
            #
            #     # 8개 파라미터
            #     elif checked_list[3] == 'SVM':
            #         for i in range(6, len(checked_list)):
            #             checked_list[i] = checked_list[i].split(" ")
            #
            #         parameters.append([str(i) for i in checked_list[6]])
            #         parameters.append([str(i) for i in checked_list[7]])
            #         parameters.append([float(i) for i in checked_list[8]])
            #         parameters.append([float(i) for i in checked_list[9]])
            #         parameters.append([int(i) for i in checked_list[10]])
            #         parameters.append([int(i) for i in checked_list[11]])
            #         parameters.append([str(i) for i in checked_list[12]])
            #         parameters.append([float(i) for i in checked_list[13]])
            #
            #         params = {
            #             "loss": parameters[0],
            #             "penalty": parameters[1],
            #             "alpha": parameters[2],
            #             "l1_ratio": parameters[3],
            #             "max_iter": parameters[4],
            #             "random_state": parameters[5],
            #             "learning_rate": parameters[6],
            #             "eta0": parameters[7],
            #         }
            #
            #         print(params)
            #
            #     # 3개 파라미터
            #     elif checked_list[3] == 'RandomForest':
            #         for i in range(6, len(checked_list)):
            #             checked_list[i] = checked_list[i].split(" ")
            #
            #         parameters.append([int(i) for i in checked_list[6]])
            #
            #         if checked_list[7][0] != 'None':
            #             parameters.append([int(i) for i in checked_list[7]])
            #         else:
            #             parameters.append([None])
            #
            #         parameters.append([int(i) for i in checked_list[8]])
            #
            #         params = {
            #                 "n_estimators": parameters[0],
            #                 "max_depth": parameters[1],
            #                 "random_state": parameters[2],
            #             }
            #
            #         print(params)
            #
            #     # 8개 파라미터
            #     elif checked_list[3] == 'FNN':
            #         for i in range(6, len(checked_list)):
            #             checked_list[i] = checked_list[i].split(" ")
            #
            #         parameters.append([int(i) for i in checked_list[6]])
            #         parameters.append([int(i) for i in checked_list[7]])
            #         parameters.append([str(i) for i in checked_list[8]])
            #         parameters.append([str(i) for i in checked_list[9]])
            #         parameters.append([str(i) for i in checked_list[10]])
            #         parameters.append([str(i) for i in checked_list[11]])
            #         parameters.append([int(i) for i in checked_list[12]])
            #         parameters.append([int(i) for i in checked_list[13]])
            #
            #         params = {
            #             "input_layer_units": parameters[0],
            #             "hidden_layer_units": parameters[1],
            #             "input_layer_activation": parameters[2],
            #             "hidden_layer_activation": parameters[3],
            #             "output_layer_activation": parameters[4],
            #             "optimizer": parameters[5],
            #             "epochs": parameters[6],
            #             "batch_size": parameters[7],
            #         }
            #
            #         print(params)
            #
            #     else:
            #         pass
            #
            # train = pd.read_csv(train_file_path + checked_list[0])
            # test = pd.read_csv(test_file_path + checked_list[1])
            #
            # # 결측치가 있는지 확인하기(우선은 제거하는 방식)
            # if pd.isnull(train['x']).sum() > 0 or pd.isnull(train['y']).sum() > 0:
            #     train = train.dropna()
            # if pd.isnull(test['x']).sum() > 0 or pd.isnull(test['y']).sum() > 0:
            #     test = test.dropna()
            #
            # train = train.sample(frac=1).reset_index(drop=True)
            # test = test.sample(frac=1).reset_index(drop=True)
            #
            # # y값을 레이블마다 200개씩 추출
            # train.sort_values(by='y', inplace=True)
            # # test.sort_values(by='y', inplace=True)
            # target_names = list(set(train['y']))
            #
            # train_ = pd.DataFrame(columns=['x', 'y'])
            # test_ = pd.DataFrame(columns=['x', 'y'])
            #
            # for i in range(len(target_names)):
            #     df = pd.DataFrame(train[train['y'] == target_names[i]].values[:1000], columns=['x', 'y'])
            #     train_ = pd.concat([train_, df], axis=0, ignore_index=True)
            #
            # for i in range(len(target_names)):
            #     df = pd.DataFrame(train[train['y'] == target_names[i]].values[:200], columns=['x', 'y'])
            #     test_ = pd.concat([test_, df], axis=0, ignore_index=True)
            #
            # train_ = train_.sample(frac=1).reset_index(drop=True)
            # test_ = test_.sample(frac=1).reset_index(drop=True)
            #
            # X_train, X_test, y_train, y_test = embedding(checked_list[2], train_, test)
            # print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
            # print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))
            #
            # dimension_reduction(checked_list[4], X_train, X_test, y_train, y_test)
            #
            # # 임베딩만 시각화할 경우
            # if checked_list[3] == '':
            #
            #     print('임베딩만 시각화할 경우')
            #     return render_template('visualization.html', visualization="embedding_and_visualization")
            #
            # # 임베딩 -> 머신러닝 -> 시각화할 경우
            # else:
            #
            #     print('임베딩 및 머신러닝 시각화할 경우')
            #     if is_pre_train == False:
            #         train_y_pred, test_y_pred = machine_learning(checked_list[3], X_train, X_test, y_train, y_test, params=params)
            #
            #     else:
            #         train_y_pred, test_y_pred = pre_train_machine_learning(checked_list[3], X_train, X_test, y_train, y_test)
            #
            #     train_df = pd.read_csv(path + 'embedding_and_visualization_train.csv')
            #     test_df = pd.read_csv(path + 'embedding_and_visualization_test.csv')
            #
            #     train_df['pred'] = train_y_pred
            #     train_df['success'] = train_df['pred'] == train_df['target']
            #     train_df['success'] = train_df['success'].astype(int)
            #
            #     test_df['pred'] = test_y_pred
            #     test_df['success'] = test_df['pred'] == test_df['target']
            #     test_df['success'] = test_df['success'].astype(int)
            #
            #     train_df.to_csv(path + 'embedding_and_machinelearning_visualization_train.csv', index=False)
            #     test_df.to_csv(path + 'embedding_and_machinelearning_visualization_test.csv', index=False)
            #
            # return render_template('visualization.html', visualization="embedding_and_machineLearning_visualization")


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