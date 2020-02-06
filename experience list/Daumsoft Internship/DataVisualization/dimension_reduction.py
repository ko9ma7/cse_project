import visualization

def dimension_reduction(method, X_train):

    if method == "PCA":

        pca = visualization.myPCA()
        pca_result = pca.fit_transform(X_train.toarray())

        return pca_result

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