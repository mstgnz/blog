package service

import (
	"fmt"
	"log"

	"github.com/mashingan/smapping"

	dto "../dto"
	entity "../entity"
	repository "../repository"
)

type IBlogService interface {
	Insert(b dto.BlogCreateDTO) entity.Blog
	Update(b dto.BlogUpdateDTO) entity.Blog
	Delete(b entity.Blog)
	All() []entity.Blog
	FindByID(blogID uint64) entity.Blog
	IsAllowedToEdit(userID string, blogID uint64) bool
}

type blogService struct {
	blogRepository repository.IBlogRepository
}

func BlogService(blogRepo repository.IBlogRepository) IBlogService {
	return &blogService{
		blogRepository: blogRepo,
	}
}

func (service *blogService) Insert(b dto.BlogCreateDTO) entity.Blog {
	blog := entity.Blog{}
	err := smapping.FillStruct(&blog, smapping.MapFields(&b))
	if err != nil {
		log.Fatalf("Failed map %v: ", err)
	}
	res := service.blogRepository.InsertBlog(blog)
	return res
}

func (service *blogService) Update(b dto.BlogUpdateDTO) entity.Blog {
	blog := entity.Blog{}
	err := smapping.FillStruct(&blog, smapping.MapFields(&b))
	if err != nil {
		log.Fatalf("Failed map %v: ", err)
	}
	res := service.blogRepository.UpdateBlog(blog)
	return res
}

func (service *blogService) Delete(b entity.Blog) {
	service.blogRepository.DeleteBlog(b)
}

func (service *blogService) All() []entity.Blog {
	return service.blogRepository.AllBlog()
}

func (service *blogService) FindByID(blogID uint64) entity.Blog {
	return service.blogRepository.FindBlogByID(blogID)
}

func (service *blogService) IsAllowedToEdit(userID string, blogID uint64) bool {
	b := service.blogRepository.FindBlogByID(blogID)
	id := fmt.Sprintf("%v", b.UserID)
	return userID == id
}
