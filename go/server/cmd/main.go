package main

import (
	"github.com/gin-gonic/gin"

	"github.com/mstgnz/blog/config"
	"github.com/mstgnz/blog/controller"
	"github.com/mstgnz/blog/middleware"
	"github.com/mstgnz/blog/repository"
	"github.com/mstgnz/blog/service"
)

var (
	db                = config.OpenDatabase()
	userRepository    = repository.UserRepository(db)
	blogRepository    = repository.BlogRepository(db)
	commentRepository = repository.CommentRepository(db)
	jwtService        = service.JWTService()
	userService       = service.UserService(userRepository)
	blogService       = service.BlogService(blogRepository)
	commentService    = service.CommentService(commentRepository)
	authService       = service.AuthService(userRepository)
	authController    = controller.AuthController(authService, jwtService)
	userController    = controller.UserController(userService, jwtService)
	blogController    = controller.BlogController(blogService, jwtService)
	commentController = controller.CommentController(commentService, jwtService)
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

	commentRoutesAuth := router.Group("api/comments", middleware.AuthorizeJWT(jwtService))
	{
		commentRoutesAuth.POST("/", commentController.Insert)
		commentRoutesAuth.PUT("/:id", commentController.Update)
		commentRoutesAuth.DELETE("/:id", commentController.Delete)
	}

	_ = router.Run(":8585")
}
