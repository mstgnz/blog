package entity

type Blog struct {
	ID         uint64 `json:"id"`
	UserID     uint64 `json:"-"`
	Title      string `json:"title"`
	Slug       string `json:"slug"`
	ShortText  string `json:"short_text"`
	LongText   string `json:"long_text"`
	CreateDate string `gorm:"->" json:"create_date"`
	UpdateDate string `gorm:"->" json:"update_date"`
}

/*
	//User       User   `gorm:"->" json:"user"`
	gorm preload özelliği ile ilişkili olan tablo inject edilebiliyor ancak tüm alanları eklediği için ve entity objesi gerçek tablo alanlarımızla birebir eşleşmesine engel olduğu için tamamen dto üzerinden çalışmak daha mantıklı.
*/
