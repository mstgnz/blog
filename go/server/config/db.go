package config

import (
	"fmt"
	"os"

	"github.com/mstgnz/blog/entity"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

// OpenDatabase is creating a new connection to our database
func OpenDatabase() *gorm.DB {
	/*errEnv := godotenv.Load()
	if errEnv != nil {
		panic("Failed to load env file")
	}*/

	dbUser := os.Getenv("DB_USER")
	dbPass := os.Getenv("DB_PASS")
	dbHost := os.Getenv("DB_HOST")
	dbName := os.Getenv("DB_NAME")

	dsn := fmt.Sprintf("%s:%s@tcp(%s:3306)/%s?charset=utf8&parseTime=True&loc=Local", dbUser, dbPass, dbHost, dbName)
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("Failed to create a connection to database")
	}
	_ = db.AutoMigrate(&entity.User{}, &entity.Blog{}, &entity.Comment{})
	return db
}

// CloseDatabase method is closing a connection between your app and your db
func CloseDatabase(db *gorm.DB) {
	dbSQL, err := db.DB()
	if err != nil {
		panic("Failed to close connection from database")
	}
	_ = dbSQL.Close()
}
