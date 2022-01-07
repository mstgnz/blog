package service

import (
	"fmt"
	"log"

	"github.com/mashingan/smapping"

	"blog/dto"
	"blog/entity"
	"blog/repository"
)

type IBlogService interface {
	Insert(b dto.BlogCreateDTO) (entity.Blog, error)
	Update(b dto.BlogUpdateDTO) entity.Blog
	Delete(b entity.Blog)
	All() []dto.BlogListDTO
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

func (service *blogService) Insert(b dto.BlogCreateDTO) (entity.Blog, error) {
	blog := entity.Blog{}
	err := smapping.FillStruct(&blog, smapping.MapFields(&b))
	if err != nil {
		log.Fatalf("Failed map %v: ", err)
	}
	return service.blogRepository.InsertBlog(blog)
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

func (service *blogService) All() []dto.BlogListDTO {
	list := []dto.BlogListDTO{}
	getList := service.blogRepository.AllBlog()
	for i := 0; i < len(getList); i++ {
		list = append(list, dto.BlogListDTO{
			ID:         getList[i].ID,
			Slug:       getList[i].Slug,
			UserID:     getList[i].UserID,
			Title:      getList[i].Title,
			ShortText:  getList[i].ShortText,
			LongText:   getList[i].LongText,
			CreateDate: getList[i].CreateDate,
			UpdateDate: getList[i].UpdateDate,
		})
	}
	return list
}

func (service *blogService) FindByID(blogID uint64) entity.Blog {
	return service.blogRepository.FindBlogByID(blogID)
}

func (service *blogService) IsAllowedToEdit(userID string, blogID uint64) bool {
	b := service.blogRepository.FindBlogByID(blogID)
	id := fmt.Sprintf("%v", b.UserID)
	return userID == id
}
