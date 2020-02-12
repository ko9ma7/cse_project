import visualization
import pandas as pd

def dimension_reduction(method, X_train, X_test, y_train, y_test):

    if method == "PCA":

        dim_red = visualization.myPCA()
        train_result = dim_red.fit_transform(X_train.toarray())
        test_result = dim_red.transform(X_test.toarray())


    elif method == "MDS":

        mds = visualization.myMDS()
        train_result = mds.fit_transform(X_train.toarray())
        test_result = mds.transform(X_test.toarray())


    elif method == "TSNE":

        tsne = visualization.myTSNE()
        train_result = tsne.fit_transform(X_train.toarray())
        test_result = tsne.transform(X_test.toarray())


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