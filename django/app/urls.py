from django.urls import path
from app import views

app_name = 'blog'

urlpatterns = [
    path('', views.index, name='index'),
    path('create', views.create, name='create'),
    path('detail/<slug:slug>', views.detail, name='detail'),
    path('update/<slug:slug>', views.update, name='update'),
    path('delete/<slug:slug>', views.delete, name='delete')
]
