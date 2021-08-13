from django.contrib import admin
from .models import Blog


class BlogAdmin(admin.ModelAdmin):
    class Meta:
        model = Blog

    list_display = ['title', 'create_date', 'update_date']
    list_display_links = ['title', 'create_date']
    list_filter = ['create_date', 'update_date']
    search_fields = ['title']


admin.site.register(Blog, BlogAdmin)
