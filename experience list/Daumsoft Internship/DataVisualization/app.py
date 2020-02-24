import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from embedding import embedding, pre_train_embedding
from machine_learning import machine_learning, pre_train_machine_learning
from dimension_reduction import dimension_reduction
from params import get_params1, get_params2
import pandas as pd

train_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/train_list/'
test_file_path = 'C:/Users/daumsoft/PycharmProjects/visualization/test_list/'
embed_model_path = 'C:/Users/daumsoft/PycharmProjects/visualization/embedding_model/'
machine_model_path = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/'

path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

app = Flask(__name__)

app.secret_key = "secret key"
app.config['train_file_path'] = train_file_path
app.config['test_file_path'] = test_file_path
app.config['embed_model_path'] = embed_model_path
app.config['machine_model_path'] = machine_model_path


@app.route("/", methods=["GET", "POST"])
def page1():
    return render_template('introduction.html')


@app.route('/fileUpload', methods=["GET", "POST"])
def page2():

    train_file_list = os.listdir(train_file_path)
    test_file_list = os.listdir(test_file_path)
    embed_model_list = os.listdir(embed_model_path)
    machine_model_list = os.listdir(machine_model_path)

    if request.method == "POST":

        train_file = request.files["train_file"]
        test_file = request.files["test_file"]

        if train_file:
            f1 = secure_filename(train_file.filename)
            train_file.save(os.path.join(app.config['train_file_path'], f1))

        if test_file:
            f2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['test_file_path'], f2))

        return render_template('fileUpload.html', filename1=f1, filename2=f2,
                               train_file_lis=train_file_list,
                               test_file_list=test_file_list,
                               embed_model_list=embed_model_list,
                               machine_model_list=machine_model_list)

    else:
        return render_template('fileUpload.html',
                               train_file_list=train_file_list,
                               test_file_list=test_file_list,
                               embed_model_list=embed_model_list,
                               machine_model_list=machine_model_list)


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
            is_pre_embed = False

            embed_params = ''
            machine_params = ''

            # 1) 임베딩 처음, 모델 처음 -> 모든 파라미터를 받아와야 함
            if checked_list[5] == '' and checked_list[6] == '':

                embed_params, machine_params = get_params1(checked_list)

            # 2) 임베딩 pretrain, 모델 처음 -> 훈련 파라미터만 받아오면 됨
            elif checked_list[5] == 'pre-embed' and checked_list[6] == '':

                is_pre_embed = True
                machine_params = get_params2(checked_list)

            # 3) 임베딩 처음, 모델 pretrain -> 피처가 다르므로 불가능
            elif checked_list[5] == '' and checked_list[6] == 'pre-train':
                pass

            # 4) 임베딩 pretrain, 모델 pretrain -> 파라미터를 받아올 필요 없음
            else:

                is_pre_embed = True
                is_pre_train = True

            print(embed_params)
            print(machine_params)

            train = pd.read_csv(train_file_path + checked_list[0])
            test = pd.read_csv(test_file_path + checked_list[1])

            # 결측치가 있는지 확인하기(우선은 제거하는 방식)
            if pd.isnull(train['x']).sum() > 0 or pd.isnull(train['y']).sum() > 0:
                train = train.dropna()
            if pd.isnull(test['x']).sum() > 0 or pd.isnull(test['y']).sum() > 0:
                test = test.dropna()

            train = train.sample(frac=1).reset_index(drop=True)
            test = test.sample(frac=1).reset_index(drop=True)

            # 임베딩만 시각화할 경우
            if checked_list[3] == '':

                # 미리 학습된 임베딩을 사용할 경우
                if is_pre_embed == True:
                    X_train, X_test, y_train, y_test = pre_train_embedding(checked_list[2], train, test)

                # 새로운 임베딩을 사용할 경우
                else:
                    X_train, X_test, y_train, y_test = embedding(checked_list[2], train, test, embed_params)

                print('X_train type: {}, y_train type: {}'.format(type(X_train), type(y_train)))
                print('X_test type: {}, y_test type: {}'.format(type(X_test), type(y_test)))

                print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
                print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))

                # 차원축소
                dimension_reduction(checked_list[4], X_train, X_test, y_train, y_test)

                return render_template('visualization.html', visualization="embedding_and_visualization")

            # 임베딩 -> 머신러닝 -> 시각화할 경우
            else:

                # 미리 학습된 임베딩을 사용할 경우
                if is_pre_embed == True:
                    X_train, X_test, y_train, y_test = pre_train_embedding(checked_list[2], train, test)

                # 새로운 임베딩을 사용할 경우
                else:
                    X_train, X_test, y_train, y_test = embedding(checked_list[2], train, test, embed_params)

                print('X_train type: {}, y_train type: {}'.format(type(X_train), type(y_train)))
                print('X_test type: {}, y_test type: {}'.format(type(X_test), type(y_test)))

                print('X_train shape: {}, y_train shape: {}'.format(X_train.shape, y_train.shape))
                print('X_test shape: {}, y_test shape: {}'.format(X_test.shape, y_test.shape))

                # 차원축소
                dimension_reduction(checked_list[4], X_train, X_test, y_train, y_test)

                # 미리 학습된 머신러닝을 사용할 경우
                if is_pre_train == True:
                    train_y_pred, test_y_pred = pre_train_machine_learning(checked_list[2], checked_list[3], X_train, X_test, y_train, y_test)

                # 새로운 머신러닝을 사용할 경우
                else:
                    train_y_pred, test_y_pred = machine_learning(checked_list[2], checked_list[3], X_train, X_test, y_train, y_test, machine_params)

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
    if os.path.isfile('C:/Users/daumsoft/PycharmProjects/visualization/csv_files/metrics_score_train.csv'):
        return render_template('visualization.html', visualization="embedding_and_machineLearning_visualization")
    else:
        return render_template('visualization.html', visualization="embedding_and_visualization")


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


# 데이터 다운로드 라우터
@app.route('/data')
def embedding_data():
    embed_model_list = os.listdir(embed_model_path)
    print(embed_model_list)

    return embed_model_list


if __name__ == "__main__":

    port = 8080
    os.system("open http://localhost:{0}".format(port))

    app.debug = True
    app.run(port=port)