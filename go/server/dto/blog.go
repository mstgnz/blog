package dto

type BlogCreateDTO struct {
	UserID    uint64 `json:"user_id" form:"user_id"`
	Title     string `json:"title" form:"title" binding:"required"`
	ShortText string `json:"short_text" form:"short_text" binding:"required"`
	LongText  string `json:"long_text" form:"long_text" binding:"required"`
}

type BlogUpdateDTO struct {
	ID        uint64 `json:"id" form:"id" binding:"required"`
	UserID    uint64 `json:"user_id" form:"user_id"`
	Title     string `json:"title" form:"title" binding:"required"`
	ShortText string `json:"short_text" form:"short_text" binding:"required"`
	LongText  string `json:"long_text" form:"long_text" binding:"required"`
}

type BlogListDTO struct {
	ID         uint64 `json:"id"`
	UserID     uint64 `json:"userID"`
	Title      string `json:"title"`
	Slug       string `json:"slug"`
	ShortText  string `json:"shortText"`
	LongText   string `json:"longText"`
	CreateDate string `json:"createDate"`
	UpdateDate string `json:"updateDate"`
}
