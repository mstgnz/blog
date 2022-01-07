package main

import (
	"github.com/gin-gonic/gin"

	"gorm.io/gorm"

	"server/config"
	"server/controller"
	"server/middleware"
	"server/repository"
	"server/service"
)

var (
	db             *gorm.DB                   = config.OpenDatabase()
	userRepository repository.IUserRepository = repository.UserRepository(db)
	blogRepository repository.IBlogRepository = repository.BlogRepository(db)
	jwtService     service.IJWTService        = service.JWTService()
	userService    service.IUserService       = service.UserService(userRepository)
	blogService    service.IBlogService       = service.BlogService(blogRepository)
	authService    service.IAuthService       = service.AuthService(userRepository)
	authController controller.IAuthController = controller.AuthController(authService, jwtService)
	userController controller.IUserController = controller.UserController(userService, jwtService)
	blogController controller.IBlogController = controller.BlogController(blogService, jwtService)
)

func main() {
	defer config.CloseDatabase(db)

	router := gin.Default()

	authRoutes := router.Group("api/auth")
	{
		authRoutes.POST("/login", authController.Login)
		authRoutes.POST("/register", authController.Register)
	}

	userRoutes := router.Group("api/user", middleware.AuthorizeJWT(jwtService))
	{
		userRoutes.GET("/profile", userController.Profile)
		userRoutes.PUT("/profile", userController.Update)
	}

	blogRoutes := router.Group("api/blogs")
	{
		blogRoutes.GET("/", blogController.All)
		blogRoutes.GET("/:id", blogController.FindByID)
	}

	blogRoutesAuth := router.Group("api/blogs", middleware.AuthorizeJWT(jwtService))
	{
		blogRoutesAuth.POST("/", blogController.Insert)
		blogRoutesAuth.PUT("/:id", blogController.Update)
		blogRoutesAuth.DELETE("/:id", blogController.Delete)
	}

	router.Run(":8585")
}
