import visualization
import pandas as pd
from scipy.sparse import isspmatrix

def dimension_reduction(method, X_train, X_test, y_train, y_test):

    if method == "PCA":

        pca_red = visualization.myPCA()

        if isspmatrix(X_train):
            train_result = pca_red.fit_transform(X_train.toarray())
            test_result = pca_red.transform(X_test.toarray())
        else:
            train_result = pca_red.fit_transform(X_train)
            test_result = pca_red.transform(X_test)

    # TSNE는 transform 메소드가 없음 -> 새로운 데이터에 대한 분류를 사용할 수 없음
    elif method == "MDS":

        mds_red = visualization.myMDS()

        if isspmatrix(X_train):
            train_result = mds_red.fit_transform(X_train.toarray())
            test_result = mds_red.fit_transform(X_test.toarray())
        else:
            train_result = mds_red.fit_transform(X_train)
            test_result = mds_red.fit_transform(X_test)

    # TSNE는 transform 메소드가 없음 -> 새로운 데이터에 대한 분류를 사용할 수 없음
    elif method == "TSNE":

        tsne_red = visualization.myTSNE()

        if isspmatrix(X_train):
            train_result = tsne_red.fit_transform(X_train.toarray())
            test_result = tsne_red.fit_transform(X_test.toarray())
        else:
            train_result = tsne_red.fit_transform(X_train)
            test_result = tsne_red.fit_transform(X_test)

    elif method == "user_defined_dimension_reduction":
        pass

    y_train_df = pd.DataFrame(y_train, columns=['target'])
    y_test_df = pd.DataFrame(y_test, columns=['target'])

    train_df = pd.DataFrame(train_result, columns=['r0', 'r1'])
    train_df = pd.concat([train_df, y_train_df], axis=1)

    test_df = pd.DataFrame(test_result, columns=['r0', 'r1'])
    test_df = pd.concat([test_df, y_test_df], axis=1)

    path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

    train_df.to_csv(path + 'embedding_and_visualization_train.csv', index=False)
    test_df.to_csv(path + 'embedding_and_visualization_test.csv', index=False)