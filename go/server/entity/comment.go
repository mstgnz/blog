package entity

// Comment Entity
type Comment struct {
	ID         uint64 `json:"id"`
	UserID     uint64 `json:"user_id"`
	BlogID     uint64 `json:"blog_id"`
	Content    string `json:"content"`
	CreateDate string `gorm:"->" json:"create_date"`
}
