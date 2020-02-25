import model
import numpy as np
import pandas as pd
import tensorflow as tf
from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, f1_score
from joblib import dump, load


def pre_train_machine_learning(embedding_model_name, machine_type, X_train, X_test, y_train, y_test):

    target_names = list(set(y_train))

    if machine_type == 'Logistic':

        # load the machine_model from disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_logistic.pkl'
        log_clf = load(filename)

        train_y_pred = log_clf.predict(X_train)
        test_y_pred = log_clf.predict(X_test)

    elif machine_type == 'SVM':

        # load the machine_model from disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_svm.pkl'
        svm_clf = load(filename)

        train_y_pred = svm_clf.predict(X_train)
        test_y_pred = svm_clf.predict(X_test)

    elif machine_type == 'RandomForest':

        # load the machine_model from disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_randomforest.pkl'
        rnd_clf = load(filename)

        train_y_pred = rnd_clf.predict(X_train)
        test_y_pred = rnd_clf.predict(X_test)

    elif machine_type == 'FNN':

        # load the machine_model from disk
        fnn_clf = tf.keras.models.load_model('C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_fnn.pkl')

        train_prediction = fnn_clf.predict(X_train)
        test_prediction = fnn_clf.predict(X_test)

        train_y_pred = []
        for i in range(len(train_prediction)):
            train_y_pred.append(np.argmax(train_prediction[i]))

        test_y_pred = []
        for i in range(len(test_prediction)):
            test_y_pred.append(np.argmax(test_prediction[i]))

    elif machine_type == 'user_defined_machine_learning':
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
    train_score_df['Score'] = [round(train_accuracy, 2), round(train_precision, 2), round(train_recall, 2),
                               round(train_f1, 2)]

    train_score_df.to_csv(path + 'metrics_score_train.csv', index=False)

    test_score_df = pd.DataFrame(columns=['Metrics', 'Score'])
    test_score_df['Metrics'] = ['accuracy', 'precision', 'recall', 'f1']
    test_score_df['Score'] = [round(test_accuracy, 2), round(test_precision, 2), round(test_recall, 2),
                              round(test_f1, 2)]

    test_score_df.to_csv(path + 'metrics_score_test.csv', index=False)

    return train_y_pred, test_y_pred


def machine_learning(embedding_model_name, machine_type, X_train, X_test, y_train, y_test, params):

    target_names = list(set(y_train))

    if machine_type == 'Logistic':

        log_clf = model.myLogisticRegression(params)
        log_clf.fit(X_train, y_train)

        # save the machine_model to disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_logistic.pkl'
        dump(log_clf, filename)

        train_y_pred = log_clf.predict(X_train)
        test_y_pred = log_clf.predict(X_test)

    elif machine_type == 'SVM':

        svm_clf = model.mySVM(params)
        svm_clf.fit(X_train, y_train)

        # save the machine_model to disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_svm.pkl'
        dump(svm_clf, filename)

        train_y_pred = svm_clf.predict(X_train)
        test_y_pred = svm_clf.predict(X_test)

    elif machine_type == 'RandomForest':

        rnd_clf = model.myRandomForestClassifier(params)
        rnd_clf.fit(X_train, y_train)

        # save the machine_model to disk
        filename = 'C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_randomforest.pkl'
        dump(rnd_clf, filename)

        train_y_pred = rnd_clf.predict(X_train)
        test_y_pred = rnd_clf.predict(X_test)

    elif machine_type == 'FNN':

        from sklearn.preprocessing import LabelEncoder

        le = LabelEncoder()
        train_label = le.fit_transform(y_train)
        test_label = le.fit_transform(y_test)

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
        fnn_clf.add(tf.keras.layers.Dense(input_layer_units, activation=input_layer_activation, input_shape=(len(X_train.toarray()[0]), )))
        fnn_clf.add(tf.keras.layers.Dense(hidden_layer_units, activation=hidden_layer_activation))
        fnn_clf.add(tf.keras.layers.Dense(output_layer_units, activation=output_layer_activation))

        fnn_clf.compile(optimizer=optimizer,
                        loss='sparse_categorical_crossentropy',
                        metrics=['accuracy'])

        fnn_clf.fit(X_train.toarray(), train_label, epochs=epochs, batch_size=batch_size)

        # save the machine_model to disk
        fnn_clf.save('C:/Users/daumsoft/PycharmProjects/visualization/machine_model/' + embedding_model_name.lower() + '_fnn.pkl')

        train_prediction = fnn_clf.predict(X_train.toarray())
        test_prediction = fnn_clf.predict(X_test.toarray())

        tr_y_pred = []
        for i in range(len(train_prediction)):
            tr_y_pred.append(np.argmax(train_prediction[i]))

        te_y_pred = []
        for i in range(len(test_prediction)):
            te_y_pred.append(np.argmax(test_prediction[i]))

        train_y_pred = le.inverse_transform(tr_y_pred)
        test_y_pred = le.inverse_transform(te_y_pred)

    elif machine_type == 'user_defined_machine_learning':
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