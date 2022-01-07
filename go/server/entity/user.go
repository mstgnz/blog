package entity

// User Entity
type User struct {
	ID         uint64 `json:"id"`
	FirstName  string `json:"firstname"`
	LastName   string `json:"lastname"`
	Email      string `json:"email"`
	Password   string `json:"-"`
	Token      string `json:"token"`
	CreateDate string `gorm:"->" json:"create_date"`
}
