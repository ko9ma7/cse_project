import pandas as pd

def embedding(method, train, test):
    # 데이터 섞어주기
    # x열은 토크나이징 된 단어들 목록
    # y열은 타겟 라벨
    train = train.sample(frac=1).reset_index(drop=True)
    test = test.sample(frac=1).reset_index(drop=True)

    # 결측치가 있는지 확인하기(우선은 제거하는 방식)
    if pd.isnull(train['x']).sum() > 0 or pd.isnull(train['y']).sum() > 0:
        train = train.dropna()
    if pd.isnull(test['x']).sum() > 0 or pd.isnull(test['y']).sum() > 0:
        test = test.dropna()

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
        pass

    elif method == "user_defined_embedding":
        pass

    return X_train_vectors, X_test_vectors, train['y'].values, test['y'].values