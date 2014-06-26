# set the path of your project
PATH_PROJECT=/Users/mike/src/sports-corp-backend/app

cd ~/Downloads

#
# Installing Flask: https://github.com/mitsuhiko/flask/tags
#
wget https://github.com/mitsuhiko/flask/archive/0.9.zip
unzip 0.9.zip
mv flask-0.9/flask $PATH_PROJECT/flask

#
# Installing Werkzeug: https://github.com/mitsuhiko/werkzeug/tags
#
wget https://github.com/mitsuhiko/werkzeug/archive/0.8.3.zip
unzip 0.8.3.zip
mv werkzeug-0.8.3/werkzeug $PATH_PROJECT/werkzeug

#
# Installing Jinja2: https://github.com/mitsuhiko/jinja2/tags
#
wget https://github.com/mitsuhiko/jinja2/archive/2.6.zip
unzip 2.6.zip
mv jinja2-2.6/jinja2 $PATH_PROJECT/jinja2

#
# Installing SimpleJson: https://github.com/simplejson/simplejson/tags
#
wget https://github.com/simplejson/simplejson/archive/v3.0.5.zip
unzip v3.0.5.zip
mv simplejson-3.0.5/simplejson $PATH_PROJECT/simplejson
