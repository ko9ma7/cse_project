def get_params1(checked_list):

    if checked_list[2] == 'CounterVector':

        embed_params = {
            "tokenizer": None if checked_list[7] == 'None' else str(checked_list[7]),
            "stop_words": None if checked_list[8] == 'None' else str(checked_list[8]),
            "min_df": int(checked_list[9]),
            "max_df": float(checked_list[10]),
            "max_features": None if checked_list[11] == 'None' else int(checked_list[11]),
            "binary": False if checked_list[12] == 'False' else True,
        }

    elif checked_list[2] == 'TF-IDF':

        embed_params = {
            "tokenizer": None if checked_list[7] == 'None' else str(checked_list[7]),
            "stop_words": None if checked_list[8] == 'None' else str(checked_list[8]),
            "min_df": int(checked_list[9]),
            "max_df": float(checked_list[10]),
            "max_features": None if checked_list[11] == 'None' else int(checked_list[11]),
            "binary": False if checked_list[12] == 'False' else True,
        }

    elif checked_list[2] == 'Doc2Vec':

        embed_params = {
            "dm": int(checked_list[7]),
            "vector_size": int(checked_list[8]),
            "window": int(checked_list[9]),
            "alpha": float(checked_list[10]),
            "epochs": int(checked_list[11]),
            "negative": int(checked_list[12]),
        }

    elif checked_list[2] == 'user_defined_embedding':
        pass


    if checked_list[3] == 'Logistic':
        for i in range(13, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "penalty": [str(i) for i in checked_list[13]],
            "C": [float(i) for i in checked_list[14]],
            "random_state": [int(i) for i in checked_list[15]],
            "max_iter": [int(i) for i in checked_list[16]],
            "l1_ratio": [None] if checked_list[17][0] == 'None' else [float(i) for i in checked_list[17]],
        }

    # 8개 파라미터
    elif checked_list[3] == 'SVM':
        for i in range(13, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "loss": [str(i) for i in checked_list[13]],
            "penalty": [str(i) for i in checked_list[14]],
            "alpha": [float(i) for i in checked_list[15]],
            "l1_ratio": [float(i) for i in checked_list[16]],
            "max_iter": [int(i) for i in checked_list[17]],
            "random_state": [int(i) for i in checked_list[18]],
            "learning_rate": [str(i) for i in checked_list[19]],
            "eta0": [float(i) for i in checked_list[20]],
        }

    # 3개 파라미터
    elif checked_list[3] == 'RandomForest':
        for i in range(13, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "n_estimators": [int(i) for i in checked_list[13]],
            "max_depth": [None] if checked_list[14][0] == 'None' else [int(i) for i in checked_list[14]],
            "random_state": [int(i) for i in checked_list[15]],
        }

    # 8개 파라미터
    elif checked_list[3] == 'FNN':
        for i in range(13, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "input_layer_units": [int(i) for i in checked_list[13]],
            "hidden_layer_units": [int(i) for i in checked_list[14]],
            "input_layer_activation": [str(i) for i in checked_list[15]],
            "hidden_layer_activation": [str(i) for i in checked_list[16]],
            "output_layer_activation": [str(i) for i in checked_list[17]],
            "optimizer": [str(i) for i in checked_list[18]],
            "epochs": [int(i) for i in checked_list[19]],
            "batch_size": [int(i) for i in checked_list[20]],
        }

    elif checked_list[3] == 'user_defined_machine_learning':
        pass

    return embed_params, machine_params


def get_params2(checked_list):

    if checked_list[3] == 'Logistic':
        for i in range(7, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "penalty": [str(i) for i in checked_list[7]],
            "C": [float(i) for i in checked_list[8]],
            "random_state": [int(i) for i in checked_list[9]],
            "max_iter": [int(i) for i in checked_list[10]],
            "l1_ratio": [None] if checked_list[11][0] == 'None' else [float(i) for i in checked_list[11]],
        }

    # 8개 파라미터
    elif checked_list[3] == 'SVM':
        for i in range(7, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "loss": [str(i) for i in checked_list[7]],
            "penalty": [str(i) for i in checked_list[8]],
            "alpha": [float(i) for i in checked_list[9]],
            "l1_ratio": [float(i) for i in checked_list[10]],
            "max_iter": [int(i) for i in checked_list[11]],
            "random_state": [int(i) for i in checked_list[12]],
            "learning_rate": [str(i) for i in checked_list[13]],
            "eta0": [float(i) for i in checked_list[14]],
        }

    # 3개 파라미터
    elif checked_list[3] == 'RandomForest':
        for i in range(7, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "n_estimators": [int(i) for i in checked_list[7]],
            "max_depth": [None] if checked_list[8][0] == 'None' else [int(i) for i in checked_list[8]],
            "random_state": [int(i) for i in checked_list[9]],
        }

    # 8개 파라미터
    elif checked_list[3] == 'FNN':
        for i in range(7, len(checked_list)):
            checked_list[i] = checked_list[i].split(" ")

        machine_params = {
            "input_layer_units": [int(i) for i in checked_list[7]],
            "hidden_layer_units": [int(i) for i in checked_list[8]],
            "input_layer_activation": [str(i) for i in checked_list[9]],
            "hidden_layer_activation": [str(i) for i in checked_list[10]],
            "output_layer_activation": [str(i) for i in checked_list[11]],
            "optimizer": [str(i) for i in checked_list[12]],
            "epochs": [int(i) for i in checked_list[13]],
            "batch_size": [int(i) for i in checked_list[14]],
        }

    elif checked_list[3] == 'user_defined_machine_learning':
        pass

    return machine_params