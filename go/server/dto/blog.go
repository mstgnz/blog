package dto

type BlogCreateDTO struct {
	UserID  uint64 `json:"user_id,omitempty"  form:"user_id,omitempty"`
	Title   string `json:"title" form:"title" binding:"required"`
	Content string `json:"content" form:"content" binding:"required"`
}

type BlogUpdateDTO struct {
	ID      uint64 `json:"id" form:"id" binding:"required"`
	Title   string `json:"title" form:"title" binding:"required"`
	Content string `json:"content" form:"content" binding:"required"`
	UserID  uint64 `json:"user_id,omitempty"  form:"user_id,omitempty"`
}
