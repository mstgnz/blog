package service

import (
	"log"

	"github.com/mashingan/smapping"

	"github.com/mstgnz/blog/dto"
	"github.com/mstgnz/blog/entity"
	"github.com/mstgnz/blog/repository"
)

// IUserService interface
type IUserService interface {
	Update(user dto.UserUpdateDTO) entity.User
	Profile(userID string) entity.User
}

// userService struct
type userService struct {
	userRepository repository.IUserRepository
}

// UserService user
func UserService(userRepo repository.IUserRepository) IUserService {
	return &userService{
		userRepository: userRepo,
	}
}

// Update user
func (service *userService) Update(user dto.UserUpdateDTO) entity.User {
	userToUpdate := entity.User{}
	err := smapping.FillStruct(&userToUpdate, smapping.MapFields(&user))
	if err != nil {
		log.Fatalf("Failed map %v:", err)
	}
	updatedUser := service.userRepository.UpdateUser(userToUpdate)
	return updatedUser
}

// Profile user
func (service *userService) Profile(userID string) entity.User {
	return service.userRepository.ProfileUser(userID)
}
