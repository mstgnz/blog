from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login, logout
from .forms import LoginForm, RegisterForm


def index(request):
    return render(request, 'account/index.html', {})


def login_screen(request):
    form = LoginForm(request.POST or None)
    if form.is_valid():
        username = form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        user = authenticate(username=username, password=password)
        login(request, user)
        return redirect('/')
    return render(request, 'account/login.html', {'form': form})


def register_screen(request):
    form = RegisterForm(request.POST or None)
    if form.is_valid():
        user = form.save(commit=False)
        password = form.cleaned_data.get('password1')
        user.set_password(password)
        user.save()
        check_user = authenticate(username=user.username, password=password)
        login(request, check_user)
        return redirect('blog:index')
    return render(request, 'account/register.html', {'form': form})


def logout_screen(request):
    logout(request)
    return redirect('/')
