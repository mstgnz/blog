package service

import (
	"log"

	"github.com/mashingan/smapping"

	"blog/dto"
	"blog/entity"
	"blog/repository"
)

type IUserService interface {
	Update(user dto.UserUpdateDTO) entity.User
	Profile(userID string) entity.User
}

type userService struct {
	userRepository repository.IUserRepository
}

func UserService(userRepo repository.IUserRepository) IUserService {
	return &userService{
		userRepository: userRepo,
	}
}

func (service *userService) Update(user dto.UserUpdateDTO) entity.User {
	userToUpdate := entity.User{}
	err := smapping.FillStruct(&userToUpdate, smapping.MapFields(&user))
	if err != nil {
		log.Fatalf("Failed map %v:", err)
	}
	updatedUser := service.userRepository.UpdateUser(userToUpdate)
	return updatedUser
}

func (service *userService) Profile(userID string) entity.User {
	return service.userRepository.ProfileUser(userID)
}
