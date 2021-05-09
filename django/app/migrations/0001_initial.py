# Generated by Django 3.2.2 on 2021-05-09 16:08

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Blog',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('title', models.CharField(max_length=50, verbose_name='Başlık')),
                ('slug', models.SlugField(editable=False, unique=True)),
                ('content', models.TextField(verbose_name='İçerik')),
                ('image', models.ImageField(blank=True, null=True, upload_to='', verbose_name='Resim')),
                ('create_date', models.DateTimeField(auto_now_add=True, verbose_name='Oluşturulma Tarihi')),
                ('update_date', models.DateTimeField(auto_now_add=True, verbose_name='Güncelleme Tarihi')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='blogs', to=settings.AUTH_USER_MODEL, verbose_name='Yazar')),
            ],
            options={
                'db_table': 'Blog',
                'ordering': ['-id'],
            },
        ),
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=50, verbose_name='Ad Soyad')),
                ('text', models.TextField(max_length=250, verbose_name='Yorum')),
                ('create_date', models.DateTimeField(auto_now_add=True, verbose_name='Oluşturulma Tarihi')),
                ('blog', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='comments', to='app.blog', verbose_name='Blog')),
            ],
            options={
                'db_table': 'Comment',
                'ordering': ['-id'],
            },
        ),
    ]