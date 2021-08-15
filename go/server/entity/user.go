package entity

type User struct {
	ID        uint64  `gorm:"primary_key:auto_increment" json:"id"`
	FirstName string  `gorm:"type:varchar(55);column:firstname" json:"firstname"`
	LastName  string  `gorm:"type:varchar(55);column:lastname" json:"lastname"`
	Email     string  `gorm:"uniqueIndex;type:varchar(255)" json:"email"`
	Password  string  `gorm:"->;<-;not null" json:"-"`
	Token     string  `gorm:"-" json:"token,omitempty"`
	Blogs     *[]Blog `json:"blogs,omitempty"`
}
