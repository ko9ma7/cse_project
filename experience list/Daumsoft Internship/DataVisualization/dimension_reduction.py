import visualization
import pandas as pd
import numpy as np
import time

def dimension_reduction(method, X_train, X_test, y_train, y_test):

    if method == "PCA":

        start = time.time()
        pca_red = visualization.myPCA()

        train_result = pca_red.fit_transform(X_train.toarray())
        test_result = pca_red.transform(X_test.toarray())
        end = time.time()

        print('PCA done time: {}'.format(end - start))

    # TSNE는 transform 메소드가 없음 -> 새로운 데이터에 대한 분류를 사용할 수 없음
    elif method == "MDS":

        start = time.time()
        mds_red = visualization.myMDS()

        size = X_train.shape[0]
        total_X = np.vstack((X_train.toarray(), X_test.toarray()))

        X_tsne = mds_red.fit_transform(total_X)

        train_result = X_tsne[0:size, :]
        test_result = X_tsne[size:, :]
        end = time.time()

        print('MDS done time: {}'.format(end - start))

    # TSNE는 transform 메소드가 없음 -> 새로운 데이터에 대한 분류를 사용할 수 없음
    elif method == "TSNE":

        start = time.time()
        tsne_red = visualization.myTSNE()

        size = X_train.shape[0]
        total_X = np.vstack((X_train.toarray(), X_test.toarray()))

        X_tsne = tsne_red.fit_transform(total_X)

        train_result = X_tsne[0:size,:]
        test_result = X_tsne[size:,:]
        end = time.time()

        print('TSNE done time: {}'.format(end - start))

    elif method == "user_defined_dimension_reduction":
        pass

    train_df = pd.DataFrame(train_result, columns=['r0', 'r1'])
    train_df['target'] = y_train

    test_df = pd.DataFrame(test_result, columns=['r0', 'r1'])
    test_df['target'] = y_test

    path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

    train_df.to_csv(path + 'embedding_and_visualization_train.csv', index=False)
    test_df.to_csv(path + 'embedding_and_visualization_test.csv', index=False)