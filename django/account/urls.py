from django.urls import path
from account import views

app_name = 'account'

urlpatterns = [
    path('', views.index, name='index'),
    path('login', views.login_screen, name='login'),
    path('register', views.register_screen, name='register'),
    path('logout', views.logout_screen, name='logout')
]
