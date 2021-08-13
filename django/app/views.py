from django.shortcuts import render, HttpResponse, get_object_or_404, HttpResponseRedirect, redirect, Http404
from django.contrib import messages
from django.db.models import Q
from .models import Blog
from .forms import BlogForm, CommentForm


def index(request):
    blogs = Blog.objects.all()
    query = request.GET.get('q')
    if query:
        blogs = blogs.filter(
            Q(title__icontains=query) |
            Q(content__icontains=query) |
            Q(user__first_name__icontains=query) |
            Q(user__last_name__icontains=query)
        ).distinct()
    return render(request, 'blog/index.html', {'blogs': blogs})


def create(request):
    if not request.user.is_authenticated:
        raise Http404()

    form = BlogForm(request.POST or None, request.FILES or None)
    if form.is_valid():
        blog = form.save(commit=False)
        blog.user = request.user
        blog.save()
        messages.success(request, "Blog Kaydedildi")
        return HttpResponseRedirect(blog.get_absolute_url())

    context = {
        "title": "Blog",
        "form": form
    }
    return render(request, 'blog/create.html', context)


def detail(request, slug):
    blog = get_object_or_404(Blog, slug=slug)

    form = CommentForm(request.POST or None)
    if form.is_valid():
        comment = form.save(commit=False)
        comment.blog = blog
        comment.save()
        messages.success(request, "Yorum Kaydedildi")
        return HttpResponseRedirect(blog.get_absolute_url())
    context = {
        'blog': blog,
        'form': form
    }
    return render(request, 'blog/detail.html', context)


def update(request, slug):
    if not request.user.is_authenticated:
        raise Http404()

    blog = get_object_or_404(Blog, slug=slug)
    form = BlogForm(request.POST or None, request.FILES or None, instance=blog)
    if form.is_valid():
        form.save()
        messages.success(request, "Blog Güncellendi")
        return HttpResponseRedirect(blog.get_absolute_url())

    context = {
        "title": "Blog",
        "form": form
    }
    return render(request, 'blog/create.html', context)


def delete(request, slug):
    if not request.user.is_authenticated:
        raise Http404()

    blog = get_object_or_404(Blog, slug=slug)
    blog.delete()
    messages.success(request, "Blog Silindi")
    return redirect('blog:index')
