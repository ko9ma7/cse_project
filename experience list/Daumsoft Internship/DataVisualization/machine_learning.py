import model
import numpy as np
import pandas as pd
import tensorflow as tf
from scipy.sparse import isspmatrix
from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, f1_score


def machine_learning(method, X_train, X_test, y_train, y_test, params):

    target_names = list(set(y_train))

    if isspmatrix(X_train):
        X_train = X_train.toarray()
        X_test = X_test.toarray()

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

        from keras.utils import to_categorical

        train_label = to_categorical(y_train)

        input_layer_units = int(params['input_layer_units'][0])
        hidden_layer_units = int(params['hidden_layer_units'][0])
        output_layer_units = len(target_names)

        input_layer_activation = params['input_layer_activation'][0]
        hidden_layer_activation = params['hidden_layer_activation'][0]
        output_layer_activation = params['output_layer_activation'][0]

        optimizer = params['optimizer'][0]
        epochs = int(params['epochs'][0])
        batch_size = int(params['batch_size'][0])

        fnn_clf = tf.keras.Sequential()
        fnn_clf.add(tf.keras.layers.Dense(input_layer_units, activation=input_layer_activation, input_shape=(len(X_train[0]), )))
        fnn_clf.add(tf.keras.layers.Dense(hidden_layer_units, activation=hidden_layer_activation))
        fnn_clf.add(tf.keras.layers.Dense(output_layer_units, activation=output_layer_activation))

        fnn_clf.compile(optimizer=optimizer,
                        loss='categorical_crossentropy',
                        metrics=['accuracy'])

        fnn_clf.fit(X_train, train_label, epochs=epochs, batch_size=batch_size)

        train_prediction = fnn_clf.predict(X_train)
        test_prediction = fnn_clf.predict(X_test)

        train_y_pred = []
        for i in range(len(train_prediction)):
            train_y_pred.append(np.argmax(train_prediction[i]))

        test_y_pred = []
        for i in range(len(test_prediction)):
            test_y_pred.append(np.argmax(test_prediction[i]))

    elif method == 'user_defined_machine_learning':
        pass

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
    train_score_df['Score'] = [round(train_accuracy, 2), round(train_precision, 2), round(train_recall, 2), round(train_f1, 2)]

    train_score_df.to_csv(path + 'metrics_score_train.csv', index=False)

    test_score_df = pd.DataFrame(columns=['Metrics', 'Score'])
    test_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
    test_score_df['Score'] = [round(test_accuracy, 2), round(test_precision, 2), round(test_recall, 2), round(test_f1, 2)]

    test_score_df.to_csv(path + 'metrics_score_test.csv', index=False)

    return train_y_pred, test_y_pred