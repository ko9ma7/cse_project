from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from dimension_reduction import dimension_reduction
from embedding import embedding
from machine_learning import machine_learning
import pandas as pd
import os

UPLOAD_FOLDER = 'C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/uploads'
ALLOWED_EXTENSIONS = set(['csv'])

app = Flask(__name__)

app.secret_key = "secret key"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route("/", methods=["GET", "POST"])
def main():
    if request.method == "POST":

        train_file = request.files["train_file"]
        test_file = request.files["test_file"]

        if train_file and allowed_file(train_file.filename):
            filename1 = secure_filename(train_file.filename)
            train_file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename1))
            return render_template('/index.html', filename1=filename1)

        if test_file and allowed_file(test_file.filename):
            filename2 = secure_filename(test_file.filename)
            test_file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename2))
            return render_template('/index.html', filename2=filename2)

        checked_list = request.form.getlist('checked_list')
        print(checked_list)

        # train = pd.read_csv("C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/uploads/train.csv")
        # test = pd.read_csv("C:/Users/battl/PycharmProjects/ComputerScienceEngineering/experience list/Daumsoft Internship/DataVisualization/uploads/test.csv")
        #
        # print(train)
        #
        # # 첫 번째 순서: 임베딩
        # embedding_train_data = embedding(checked_list[0], train)
        # embedding_test_data = embedding(checked_list[0], test)
        #
        # # 두 번째 순서: 기계학습
        # machine_learning_train_data = machine_learning(checked_list[1], embedding_train_data)
        # machine_learning_test_data = machine_learning(checked_list[1], embedding_test_data)
        #
        # # 세 번째 순서: 차원축소
        # dimension_reduction_train_data = dimension_reduction(checked_list[2], machine_learning_train_data)
        # dimension_reduction_test_data = dimension_reduction(checked_list[2], machine_learning_test_data)

        return render_template("index.html")

    elif request.method == "GET":
        return render_template("index.html")

@app.route("/file_upload")
def file_upload():
    return render_template("upload.html")

if __name__ == "__main__":
    app.run(host='localhost', port=8000, debug=True)
