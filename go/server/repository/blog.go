package repository

import (
	"gorm.io/gorm"

	entity "../entity"
)

type IBlogRepository interface {
	InsertBlog(b entity.Blog) (entity.Blog, error)
	UpdateBlog(b entity.Blog) entity.Blog
	DeleteBlog(b entity.Blog)
	AllBlog() []entity.Blog
	FindBlogByID(blogID uint64) entity.Blog
}

type blogRepository struct {
	connection *gorm.DB
}

func BlogRepository(dbConn *gorm.DB) IBlogRepository {
	return &blogRepository{
		connection: dbConn,
	}
}

func (db *blogRepository) InsertBlog(b entity.Blog) (entity.Blog, error) {
	if err := db.connection.Save(&b).Error; err != nil {
		return b, err
	}
	db.connection.Save(&b)
	return b, nil
}

func (db *blogRepository) UpdateBlog(b entity.Blog) entity.Blog {
	db.connection.Save(&b)
	return b
}

func (db *blogRepository) DeleteBlog(b entity.Blog) {
	db.connection.Delete(&b)
}

func (db *blogRepository) FindBlogByID(blogID uint64) entity.Blog {
	var blog entity.Blog
	db.connection.Find(&blog, blogID)
	return blog
}

func (db *blogRepository) AllBlog() []entity.Blog {
	var blogs []entity.Blog
	db.connection.Preload("User").Find(&blogs)
	return blogs
}
