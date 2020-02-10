import visualization
import pandas as pd

def dimension_reduction(method, X_train, X_test, y_train, y_test):

    if method == "PCA":

        pca = visualization.myPCA()
        pca_train_result = pca.fit_transform(X_train.toarray())
        pca_test_result = pca.transform(X_test.toarray())

        y_train_df = pd.DataFrame(y_train, columns=['target'])
        y_test_df = pd.DataFrame(y_test, columns=['target'])

        pca_train_df = pd.DataFrame(pca_train_result, columns=['r0', 'r1'])
        pca_train_df = pd.concat([pca_train_df, y_train_df], axis=1)

        pca_test_df = pd.DataFrame(pca_test_result, columns=['r0', 'r1'])
        pca_test_df = pd.concat([pca_test_df, y_test_df], axis=1)

        path = r'C:/Users/daumsoft/PycharmProjects/visualization/csv_files/'

        pca_train_df.to_csv(path + 'embedding_and_visualization_train.csv', index=False)
        pca_test_df.to_csv(path + 'embedding_and_visualization_test.csv', index=False)

    elif method == "MDS":

        mds = visualization.myMDS()
        mds_result = mds.fit_transform(X_train.toarray())

        return mds_result

    elif method == "TSNE":

        tsne_params = {'perplexity': 30, 'learning_rate': 0.01}
        tsne = visualization.myTSNE(tsne_params)
        tsne_result = tsne.fit_transform(X_train.toarray())

        return tsne_result

    elif method == "user_defined_dimension_reduction":
        pass