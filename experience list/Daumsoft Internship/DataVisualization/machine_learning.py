import model

def machine_learning(method, X_train, X_test, y_train, y_test):

    if method == 'Logistic':

        log_params = {
            "C": [0.001, 0.01, 0.1, 1, 10, 100, 1000],
            "eta0": [0.01, 0.001],
            'random_state': [42],
        }
        log_clf = model.myLogisticRegression(log_params)

    elif method == 'SVC':

        svc_params = {
            "C": [0.001, 0.01, 0.1, 1, 10, 100, 1000],
            "gamma": [0.01, 0.1, 1],  # 커널 폭의 역수, 작을 수록 커널 폭이 넓어져, 데이터 영향 범위 증가
            "probability": [True],  # SVC가 'predict_proba'를 계산하기 위해 지정
            'random_state': [42],
        }
        svc_clf = model.mySVC(svc_params)

    elif method == 'RandomForest':

        rnd_params = {
            "n_estimators": [100],  # [100, 300, 500, 700],
            "max_depth": [3],  # [i for i in range(1, 31)],
            'random_state': [42],
        }
        rnd_clf = model.myRandomForestClassifier(rnd_params)

        rnd_clf.fit(X_train, y_train)

        # 예측
        test_y_pred = rnd_clf.predict(X_test)

        return y_test, test_y_pred

    elif method == 'FNN':
        pass
    elif method == 'user_defined_machine_learning':
        pass