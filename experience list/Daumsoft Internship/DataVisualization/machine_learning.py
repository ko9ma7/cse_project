import model

def machine_learning(method, X_train, X_test, y_train, y_test):

    if method == 'Logistic':

        log_params = {
            "C": [0.001], # [0.01, 0.1, 1, 10, 100, 1000],
            "eta0": [0.01], # [0.001],
            'random_state': [42],
        }
        log_clf = model.myLogisticRegression(log_params)
        log_clf.fit(X_train, y_train)

        # 예측
        test_y_pred = log_clf.predict(X_test)

        return y_test, test_y_pred

    elif method == 'SVM':

        svm_params = {
            "alpha": [0.0001], # [0.001, 0.1, 1, 10, 100, 1000], # 규제
            'random_state': [42],
        }
        svm_clf = model.mySVM(svm_params)
        svm_clf.fit(X_train, y_train)

        # 예측
        test_y_pred = svm_clf.predict(X_test)

        return y_test, test_y_pred

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