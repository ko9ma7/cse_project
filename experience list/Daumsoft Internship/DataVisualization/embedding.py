import pandas as pd
from scipy.sparse import csr_matrix

def embedding(method, train, test):
    # 데이터 섞어주기
    # x열은 토크나이징 된 단어들 목록
    # y열은 타겟 라벨
    train = train.sample(frac=1).reset_index(drop=True)
    test = test.sample(frac=1).reset_index(drop=True)

    if method == "One-Hot":

        # 데이터 준비
        X_train_corpus = []
        X_train_corpus2 = []
        for words in train['x']:
            sentence = []
            for word in words.split(","):
                sentence.append(word)

            X_train_corpus.append(sentence)
            X_train_corpus2.extend(sentence)

        X_test_corpus = []
        X_test_corpus2 = []
        for words in test['x']:
            sentence = []
            for word in words.split(","):
                sentence.append(word)

            X_test_corpus.append(sentence)
            X_test_corpus2.extend(sentence)

        # 정수 인코딩
        from collections import Counter

        X_train_counts = Counter(X_train_corpus2)
        X_test_counts = Counter(X_test_corpus2)

        X_train_counts_sorted = sorted(X_train_counts.items(), key=lambda x: x[1], reverse=True)
        X_test_counts_sorted = sorted(X_test_counts.items(), key=lambda x: x[1], reverse=True)

        X_train_vocab = sorted(X_train_counts, key=X_train_counts.get, reverse=True)
        X_test_vocab = sorted(X_test_counts, key=X_test_counts.get, reverse=True)

        # 높은 빈도수를 가진 단어일수록 낮은 정수 인덱스 부여(훈련 데이터)
        word_mapping_table = {}

        idx = 0
        for (word, frequency) in X_train_counts_sorted:
            word_mapping_table[word] = idx
            idx += 1

        word_mapping_table_sorted = sorted(word_mapping_table.items(), key=lambda x: x[1], reverse=False)

        X_train_Integer_Encoding = []
        for sentence in X_train_corpus:
            w = []
            for word in sentence:
                w.append(word_mapping_table[word])
            X_train_Integer_Encoding.append(w)

        X_test_Integer_Encoding = []
        for sentence in X_test_corpus:
            w = []
            for word in sentence:
                if word in word_mapping_table:
                    w.append(word_mapping_table[word])
                else:
                    pass

            X_test_Integer_Encoding.append(w)

        # 원-핫 인코딩
        X_train_Onehot_Encoding = list()
        for value in X_train_Integer_Encoding:
            letter = [0 for _ in range(len(word_mapping_table))]
            for v in value:
                letter[v] = 1
            X_train_Onehot_Encoding.append(letter)

        X_test_Onehot_Encoding = list()
        for value in X_test_Integer_Encoding:
            letter = [0 for _ in range(len(word_mapping_table))]
            for v in value:
                letter[v] = 1
            X_test_Onehot_Encoding.append(letter)

        sorted_mapping_keys = [word_mapping_table_sorted[idx][0] for idx in range(len(word_mapping_table_sorted))]

        X_train_df = pd.DataFrame(X_train_Onehot_Encoding, columns=sorted_mapping_keys)
        X_test_df = pd.DataFrame(X_test_Onehot_Encoding, columns=sorted_mapping_keys)

        y_train_df = pd.DataFrame(train['y'].values, columns=['my_target'])
        y_test_df = pd.DataFrame(test['y'].values, columns=['my_target'])

        train_df = pd.concat([X_train_df, y_train_df], axis=1)
        test_df = pd.concat([X_test_df, y_test_df], axis=1)

        onehot_X_train = train_df.iloc[:, 0:len(sorted_mapping_keys)].values
        onehot_y_train = train_df.loc[:, 'my_target'].values

        onehot_X_test = test_df.iloc[:, 0:len(sorted_mapping_keys)].values
        onehot_y_test = test_df.loc[:, 'my_target'].values

        # sparse data로 변환
        from scipy.sparse import csr_matrix

        onehot_X_train = csr_matrix(onehot_X_train)
        onehot_X_test = csr_matrix(onehot_X_test)

        return onehot_X_train, onehot_X_test, onehot_y_train, onehot_y_test

    elif method == "TF-IDF":

        train_corpus = []
        for words in train["x"].values:
            sum_sentence = ""
            for word in words.split(","):
                sum_sentence += " " + word

            sum_sentence = sum_sentence.strip()
            train_corpus.append(sum_sentence)

        test_corpus = []
        for words in test["x"].values:
            sum_sentence = ""
            for word in words.split(","):
                sum_sentence += " " + word

            sum_sentence = sum_sentence.strip()
            test_corpus.append(sum_sentence)


        from sklearn.feature_extraction.text import TfidfVectorizer

        tfidf = TfidfVectorizer()
        tfidf_vectors = tfidf.fit_transform(train_corpus)

        vocabs = tfidf.get_feature_names()
        vectors = tfidf_vectors.toarray()

        tfidf_df = pd.DataFrame(vectors, columns=vocabs)
        target_df = pd.DataFrame(train["y"].values, columns=["target"])
        tfidf_df = pd.concat([tfidf_df, target_df], axis=1)



        print(tfidf_df)

        X = tfidf_df.iloc[:, 0:len(vocabs)].values
        y = tfidf_df.loc[:, 'target'].values

        print(X.shape)
        print(y.shape)

    elif method == "Doc2Vec":
        pass
    elif method == "user_defined_embedding":
        pass