from flask import Flask, render_template
from menus import menus_blueprint

app = Flask(__name__)
app.register_blueprint(menus_blueprint, url_prefix='/menus')

@app.route('/')
def hello_html():
    return render_template(
        'template.html', nav_menu='home'
    )

if __name__ == '__main__':
    app.config['TEMPLATES_AUTO_RELOAD'] = True
    app.debug = True # application.config['DEBUG'] = True
    app.run(host="localhost", port="5050")