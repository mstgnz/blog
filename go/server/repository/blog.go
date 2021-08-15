package repository

import (
	"gorm.io/gorm"

	entity "../entity"
)

type IBlogRepository interface {
	InsertBlog(b entity.Blog) entity.Blog
	UpdateBlog(b entity.Blog) entity.Blog
	DeleteBlog(b entity.Blog)
	AllBlog() []entity.Blog
	FindBlogByID(blogID uint64) entity.Blog
}

type blogConnection struct {
	connection *gorm.DB
}

func BlogRepository(dbConn *gorm.DB) IBlogRepository {
	return &blogConnection{
		connection: dbConn,
	}
}

func (db *blogConnection) InsertBlog(b entity.Blog) entity.Blog {
	db.connection.Save(&b)
	db.connection.Preload("User").Find(&b)
	return b
}

func (db *blogConnection) UpdateBlog(b entity.Blog) entity.Blog {
	db.connection.Save(&b)
	db.connection.Preload("User").Find(&b)
	return b
}

func (db *blogConnection) DeleteBlog(b entity.Blog) {
	db.connection.Delete(&b)
}

func (db *blogConnection) FindBlogByID(blogID uint64) entity.Blog {
	var blog entity.Blog
	db.connection.Preload("User").Find(&blog, blogID)
	return blog
}

func (db *blogConnection) AllBlog() []entity.Blog {
	var blogs []entity.Blog
	db.connection.Preload("User").Find(&blogs)
	return blogs
}
