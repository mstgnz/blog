from django import forms
from django.contrib.auth import authenticate
from django.contrib.auth.models import User

class LoginForm(forms.Form):
    username = forms.CharField(max_length=50, label="Kullanıcı Adınız")
    password = forms.CharField(max_length=50, label="Şifreniz", widget=forms.PasswordInput)

    def clean(self):
        username = self.cleaned_data.get('username')
        password = self.cleaned_data.get('password')

        if username and password:
            user = authenticate(username=username, password=password)
            if not user:
                raise forms.ValidationError("Hatalı Kullanıcı Bilgileri")
        return super(LoginForm, self).clean()

    def __init__(self, *args, **kwargs):
        super(LoginForm, self).__init__(*args, **kwargs)
        for field in self.fields:
            self.fields[field].widget.attrs['class'] = 'form-control'


class RegisterForm(forms.ModelForm):
    username = forms.CharField(max_length=50, label="Kullanıcı Adınız")
    password1 = forms.CharField(max_length=50, label="Şifreniz", widget=forms.PasswordInput)
    password2 = forms.CharField(max_length=50, label="Şifreniz Tekrar", widget=forms.PasswordInput)

    class Meta:
        model = User
        fields = ['username','password1','password2',]

    def clean_password2(self):
        username = self.cleaned_data.get('username')
        password1 = self.cleaned_data.get('password')
        password2 = self.cleaned_data.get('password2')

        if password1 and password2 and password1 != password2:
            raise form.ValidationError("Şifreler Eşleşmiyor")

    def __init__(self, *args, **kwargs):
        super(RegisterForm, self).__init__(*args, **kwargs)
        for field in self.fields:
            self.fields[field].widget.attrs['class'] = 'form-control'
            