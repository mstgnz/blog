import os
from django.db import models
from django.utils.text import slugify
from django.urls import reverse
from django.core.validators import RegexValidator


class Blog(models.Model):
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey('auth.User', verbose_name='Yazar',
                             related_name="blogs", on_delete=models.CASCADE)
    title = models.CharField(max_length=50, verbose_name="Başlık")
    slug = models.SlugField(unique=True, editable=False, max_length=50)
    content = models.TextField(verbose_name="İçerik")
    image = models.ImageField(blank=True, null=True, verbose_name='Resim')
    create_date = models.DateTimeField(
        auto_now_add=True, editable=False, verbose_name="Oluşturulma Tarihi")
    update_date = models.DateTimeField(
        auto_now_add=True, editable=False, verbose_name="Güncelleme Tarihi")

    class Meta:
        db_table = 'Blog'
        ordering = ['-id']

    def __str__(self):
        return self.title

    def get_absolute_url(self):
        return reverse('blog:detail', kwargs={'slug': self.slug})

    def get_image_or_default(self):
        if self.image and hasattr(self.image, 'url'):
            return self.image.url
        return '/static/site/img/user.png'

    def get_unique_slug(self):
        slug = slugify(self.title.replace('ı', 'i'))
        unique_slug = slug
        counter = 1
        while Blog.objects.filter(slug=unique_slug).exists():
            unique_slug = '{}-{}'.format(slug, counter)
            counter += 1
        return unique_slug

    def save(self, *args, **kwargs):
        if not self.slug:
            self.slug = self.get_unique_slug()
        if self.image:
            self.image.name = self.slug+os.path.splitext(self.image.name)[1]
        return super(Blog, self).save(*args, **kwargs)


class Comment(models.Model):
    id = models.AutoField(primary_key=True)
    blog = models.ForeignKey('Blog', verbose_name='Blog',
                             related_name="comments", on_delete=models.CASCADE)
    name = models.CharField(max_length=50, verbose_name="Ad Soyad")
    text = models.TextField(max_length=250, verbose_name="Yorum")
    create_date = models.DateTimeField(
        auto_now_add=True, editable=False, verbose_name="Oluşturulma Tarihi")

    class Meta:
        db_table = 'Comment'
        ordering = ['-id']

    def __str__(self):
        return self.name
