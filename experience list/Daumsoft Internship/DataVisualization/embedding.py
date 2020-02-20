def embedding(method, train, test):
    # x열은 토크나이징 된 단어들 목록
    # y열은 타겟 라벨

    X_train_corpus = []
    for words in train['x']:
        sentence = ""
        for word in words.split(","):
            sentence += word + " "
        sentence = sentence[:len(sentence) - 1]

        X_train_corpus.append(sentence)

    X_test_corpus = []
    for words in test['x']:
        sentence = ""
        for word in words.split(","):
            sentence += word + " "
        sentence = sentence[:len(sentence) - 1]

        X_test_corpus.append(sentence)

    target_names = list(set(train['y']))
    target_mapping_table = {}
    for idx, names in enumerate(target_names):
        target_mapping_table[names] = idx

    # train['y'] = train['y'].map(target_mapping_table)
    # test['y'] = test['y'].map(target_mapping_table)

    if method == "CounterVector":

        from sklearn.feature_extraction.text import CountVectorizer
        cnt_vectorizer = CountVectorizer(min_df=3, max_features=1000)

        X_train_vectors = cnt_vectorizer.fit_transform(X_train_corpus)
        # 테스트 데이터는 훈련 데이터로 맞춰놓은 벡터 변환기를 사용해야 함
        X_test_vectors = cnt_vectorizer.transform(X_test_corpus)

    elif method == "TF-IDF":

        from sklearn.feature_extraction.text import TfidfVectorizer
        tfidf_vectorizer = TfidfVectorizer(min_df=3, max_features=1000)

        X_train_vectors = tfidf_vectorizer.fit_transform(X_train_corpus)
        X_test_vectors = tfidf_vectorizer.transform(X_test_corpus)

    elif method == "Doc2Vec":

        train_corpus = []
        for words in train['x']:
            a = []
            a.append(words)
            train_corpus.append(a)

        test_corpus = []
        for words in test['x']:
            a = []
            a.append(words)
            test_corpus.append(a)

        train_token_review = []
        for corpus in train_corpus:
            c = corpus[0].split(",")
            train_token_review.append(c)

        test_token_review = []
        for corpus in test_corpus:
            c = corpus[0].split(",")
            test_token_review.append(c)

        from collections import namedtuple

        TaggedDocument = namedtuple('TaggedDocument', 'words tags')

        from gensim.models.doc2vec import Doc2Vec

        tagged_train_docs = [TaggedDocument(d, c) for d, c in zip(train_token_review, train['y'].values)]
        tagged_test_docs = [TaggedDocument(d, c) for d, c in zip(test_token_review, test['y'].values)]

        doc_vectorizer = Doc2Vec(dm=0,
                                 min_count=3,
                                 vector_size=256,
                                 window=5,
                                 negative=20,
                                 epochs=20)

        doc_vectorizer.build_vocab(tagged_train_docs)

        from time import time

        start = time()
        for epoch in range(10):
            doc_vectorizer.train(tagged_train_docs, total_examples=doc_vectorizer.corpus_count,
                                 epochs=doc_vectorizer.epochs)
            doc_vectorizer.alpha -= 0.002  # decrease the learning rate
            doc_vectorizer.min_alpha = doc_vectorizer.alpha
        end = time()
        print("During Time: {}".format(end - start))

        import numpy as np

        X_train = [doc_vectorizer.infer_vector(doc.words) for doc in tagged_train_docs]
        X_test = [doc_vectorizer.infer_vector(doc.words) for doc in tagged_test_docs]
        y_train = [doc.tags for doc in tagged_train_docs]
        y_test = [doc.tags for doc in tagged_test_docs]

        X_train_np = np.asarray(X_train)
        X_test_np = np.array(X_test)
        y_train_np = np.asarray(y_train)
        y_test_np = np.asarray(y_test)

        return X_train_np, X_test_np, y_train_np, y_test_np

    elif method == "user_defined_embedding":
        pass

    return X_train_vectors, X_test_vectors, train['y'].values, test['y'].values