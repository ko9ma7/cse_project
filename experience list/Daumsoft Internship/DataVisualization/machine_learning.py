import model
import pandas as pd
from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, f1_score

def machine_learning(method, X_train, X_test, y_train, y_test, params):

    if method == 'Logistic':

        log_clf = model.myLogisticRegression(params)
        log_clf.fit(X_train, y_train)

        train_y_pred = log_clf.predict(X_train)
        test_y_pred = log_clf.predict(X_test)

    elif method == 'SVM':

        svm_clf = model.mySVM(params)
        svm_clf.fit(X_train, y_train)

        train_y_pred = svm_clf.predict(X_train)
        test_y_pred = svm_clf.predict(X_test)

    elif method == 'RandomForest':

        rnd_clf = model.myRandomForestClassifier(params)
        rnd_clf.fit(X_train, y_train)

        train_y_pred = rnd_clf.predict(X_train)
        test_y_pred = rnd_clf.predict(X_test)

    elif method == 'FNN':
        pass

    elif method == 'user_defined_machine_learning':
        pass


    target_names = list(set(y_train))
    train_df = pd.DataFrame(confusion_matrix(y_train, train_y_pred),
                            index=target_names,
                            columns=target_names)

    test_df = pd.DataFrame(confusion_matrix(y_test, test_y_pred),
                           index=target_names,
                           columns=target_names)

    path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

    train_df.to_csv(path + 'confusion_matrix_train.csv', index=False)
    test_df.to_csv(path + 'confusion_matrix_test.csv', index=False)


    # 분류 평가 지표
    train_accuracy = accuracy_score(y_train, train_y_pred)
    train_precision = precision_score(y_train, train_y_pred, average='macro')
    train_recall = recall_score(y_train, train_y_pred, average='macro')
    train_f1 = f1_score(y_train, train_y_pred, average='macro')

    test_accuracy = accuracy_score(y_test, test_y_pred)
    test_precision = precision_score(y_test, test_y_pred, average='macro')
    test_recall = recall_score(y_test, test_y_pred, average='macro')
    test_f1 = f1_score(y_test, test_y_pred, average='macro')

    print('train accuracy: {}, test accuracy: {}'.format(train_accuracy, test_accuracy))
    print('train precision: {}, test precision: {}'.format(train_precision, test_precision))
    print('train recall: {}, test recall: {}'.format(train_recall, test_recall))
    print('train f1: {}, test f1: {}'.format(train_f1, test_f1))

    train_score_df = pd.DataFrame(columns=['Metrics', 'Score'])
    train_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
    train_score_df['Score'] = [round(train_accuracy, 3), round(train_precision, 3), round(train_recall, 3), round(train_f1, 3)]

    train_score_df.to_csv(path + 'metrics_score_train.csv', index=False)

    test_score_df = pd.DataFrame(columns=['Metrics', 'Score'])
    test_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
    test_score_df['Score'] = [round(test_accuracy, 3), round(test_precision, 3), round(test_recall, 3), round(test_f1, 3)]

    test_score_df.to_csv(path + 'metrics_score_test.csv', index=False)

    return train_y_pred, test_y_pred