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

    train['y'] = train['y'].map(target_mapping_table)
    test['y'] = test['y'].map(target_mapping_table)

    if method == "CounterVector":

        from sklearn.feature_extraction.text import CountVectorizer
        cnt_vectorizer = CountVectorizer(min_df=1)

        X_train_vectors = cnt_vectorizer.fit_transform(X_train_corpus)
        # 테스트 데이터는 훈련 데이터로 맞춰놓은 벡터 변환기를 사용해야 함
        X_test_vectors = cnt_vectorizer.transform(X_test_corpus)

    elif method == "TF-IDF":

        from sklearn.feature_extraction.text import TfidfVectorizer
        tfidf_vectorizer = TfidfVectorizer()

        X_train_vectors = tfidf_vectorizer.fit_transform(X_train_corpus)
        X_test_vectors = tfidf_vectorizer.transform(X_test_corpus)

    elif method == "Doc2Vec":

        from gensim.models.doc2vec import Doc2Vec, TaggedDocument

        train_documents = [TaggedDocument(doc, tags=[str(i)]) for i, doc in enumerate(X_train_corpus)]
        test_documents = [TaggedDocument(doc, tags=[str(i)]) for i, doc in enumerate(X_test_corpus)]

        d2v_train_model = Doc2Vec(train_documents,
                                  dm=0,  # skip-gram
                                  vector_size=256,
                                  window=5,
                                  negative=20,
                                  epochs=15)

        d2v_test_model = Doc2Vec(test_documents,
                                 dm=0,  # skip-gram
                                 vector_size=256,
                                 window=5,
                                 negative=20,
                                 epochs=15)

        X_train_vectors = d2v_train_model.docvecs.vectors_docs
        X_test_vectors = d2v_test_model.docvecs.vectors_docs

    elif method == "user_defined_embedding":
        pass

    return X_train_vectors, X_test_vectors, train['y'].values, test['y'].values