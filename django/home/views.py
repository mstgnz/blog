from django.shortcuts import render, HttpResponse

def index(request):
    context = {

    }
    return render(request, 'home/index.html', context)
