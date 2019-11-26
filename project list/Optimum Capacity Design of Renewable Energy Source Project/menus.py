from flask import Blueprint, render_template

menus_blueprint = Blueprint('menus', __name__)

@menus_blueprint.route('/home')
def home():
    return render_template(
        'home.html', nav_menu="home"
    )

@menus_blueprint.route('/dashboard')
def dashboard():
    return render_template(
        'dashboard.html', nav_menu="dashboard"
    )

@menus_blueprint.route('/icons')
def icons():
    return render_template(
        'icons.html', nav_menu="icons"
    )


if __name__ == '__main__':
    menus_blueprint.run(host="localhost", port="5050")