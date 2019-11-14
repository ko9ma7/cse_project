from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def html():
    return render_template(
        'template.html'
    )

if __name__ == '__main__':
    app.debug = True
    app.run(host='localhost', port='5000')
