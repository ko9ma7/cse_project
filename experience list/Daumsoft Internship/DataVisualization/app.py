from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from dimension_reduction import dimension_reduction
from embedding import embedding
from machine_learning import machine_learning
import pandas as pd

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def main():
    if request.method == "POST":
        checked_list = request.args.get("checked_list")
        print(checked_list)

        '''
            checked_list 형태
            
            { 
                "dimension_reduction" : "PCA",
                "embedding" : "BoW",
                "machine_learning": "Logistic",
            } 
        '''

        train = pd.read_csv("train")
        test = pd.read_csv("test")

        # 첫 번째 순서: 임베딩
        embedding_train_data = embedding(checked_list["embedding"], train)
        embedding_test_data = embedding(checked_list["embedding"], test)

        # 두 번째 순서: 기계학습
        machine_learning_train_data = machine_learning(checked_list["machine_learning"], embedding_train_data)
        machine_learning_test_data = machine_learning(checked_list["machine_learning"], embedding_test_data)

        # 세 번째 순서: 차원축소
        dimension_reduction_train_data = dimension_reduction(checked_list["dimension_reduction"], machine_learning_train_data)
        dimension_reduction_test_data = dimension_reduction(checked_list["dimension_reduction"], machine_learning_test_data)

        return render_template("visualization.html")

    elif request.method == "GET":
        return render_template("index.html")

@app.route("/file_upload")
def file_upload():
    return render_template("upload.html")

# file이 submit 되면 전달되는 페이지
@app.route("/file_uploaded", methods=["GET", "POST"])
def file_uploaded():
    if request.method == "POST":
        files = request.files.getlist("file[]")
        for file in files:
            file.save(f'uploads/{secure_filename(file.filename)}')

        return render_template("index.html")

if __name__ == "__main__":
    app.run(host='localhost', port=8000, debug=True)
