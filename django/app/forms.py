from django import forms
from .models import Blog, Comment

class BlogForm(forms.ModelForm):
    class Meta:
        model = Blog
        fields = ['title','content','image']

    def __init__(self, *args, **kwargs):
        super(BlogForm, self).__init__(*args, **kwargs)
        instance = getattr(self, 'instance', None)
        if instance:
            for field in self.fields:
                self.fields[field].widget.attrs['class'] = 'form-control'

class CommentForm(forms.ModelForm):
    class Meta:
        model = Comment
        fields = ['name','text']

    def __init__(self, *args, **kwargs):
        super(CommentForm, self).__init__(*args, **kwargs)
        instance = getattr(self, 'instance', None)
        if instance:
            for field in self.fields:
                if field == "text":
                    self.fields[field].widget.attrs['class'] = 'form-control comment'
                else:
                    self.fields[field].widget.attrs['class'] = 'form-control'