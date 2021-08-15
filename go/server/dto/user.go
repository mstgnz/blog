package dto

type RegisterDTO struct {
	FirstName string `json:"firstname" form:"firstname" binding:"required"`
	LastName  string `json:"lastname" form:"lastname" binding:"required"`
	Email     string `json:"email" form:"email" binding:"required,email" `
	Password  string `json:"password" form:"password" binding:"required"`
}

type UserUpdateDTO struct {
	ID        uint64 `json:"id" form:"id"`
	FirstName string `json:"firstname" form:"firstname" binding:"required"`
	LastName  string `json:"lastname" form:"lastname" binding:"required"`
	Email     string `json:"email" form:"email" binding:"required,email"`
	Password  string `json:"password,omitempty" form:"password,omitempty"`
}

type LoginDTO struct {
	Email    string `json:"email" form:"email" binding:"required,email"`
	Password string `json:"password" form:"password" binding:"required"`
}
