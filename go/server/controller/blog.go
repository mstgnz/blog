package controller

import (
	"fmt"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"github.com/golang-jwt/jwt/v5"

	"github.com/mstgnz/blog/dto"
	"github.com/mstgnz/blog/entity"
	"github.com/mstgnz/blog/helper"
	"github.com/mstgnz/blog/service"
)

// IBlogController interface
type IBlogController interface {
	All(context *gin.Context)
	FindByID(context *gin.Context)
	Insert(context *gin.Context)
	Update(context *gin.Context)
	Delete(context *gin.Context)
}

// blogController struct
type blogController struct {
	blogService service.IBlogService
	jwtService  service.IJWTService
}

// BlogController instance
func BlogController(blogServ service.IBlogService, jwtServ service.IJWTService) IBlogController {
	return &blogController{
		blogService: blogServ,
		jwtService:  jwtServ,
	}
}

// All get all blogs
func (c *blogController) All(context *gin.Context) {
	var blogs = c.blogService.All()
	res := helper.BuildResponse(true, "OK", blogs)
	context.JSON(http.StatusOK, res)
}

// FindByID get by id for blog
func (c *blogController) FindByID(context *gin.Context) {
	id, err := strconv.ParseUint(context.Param("id"), 0, 0)
	if err != nil {
		res := helper.BuildErrorResponse("No param id was found", err.Error(), helper.EmptyObj{})
		context.AbortWithStatusJSON(http.StatusBadRequest, res)
		return
	}

	var blog = c.blogService.FindByID(id)
	if (blog == entity.Blog{}) {
		res := helper.BuildErrorResponse("Data not found", "No data with given id", helper.EmptyObj{})
		context.JSON(http.StatusNotFound, res)
	} else {
		res := helper.BuildResponse(true, "OK", blog)
		context.JSON(http.StatusOK, res)
	}
}

// Insert create blog
func (c *blogController) Insert(context *gin.Context) {
	var blogCreateDTO dto.BlogCreateDTO
	errDTO := context.ShouldBind(&blogCreateDTO)
	if errDTO != nil {
		res := helper.BuildErrorResponse("Failed to process request", errDTO.Error(), helper.EmptyObj{})
		context.JSON(http.StatusBadRequest, res)
	} else {
		authHeader := context.GetHeader("Authorization")
		userID := c.getUserIDByToken(authHeader)
		convertedUserID, err := strconv.ParseUint(userID, 10, 64)
		if err == nil {
			blogCreateDTO.UserID = convertedUserID
		}
		result, err := c.blogService.Insert(blogCreateDTO)
		if err != nil {
			response := helper.BuildErrorResponse("ERROR", err.Error(), err.Error())
			context.JSON(http.StatusBadRequest, response)
		} else {
			response := helper.BuildResponse(true, "OK", result)
			context.JSON(http.StatusCreated, response)
		}
	}
}

// Update blog
func (c *blogController) Update(context *gin.Context) {
	var blogUpdateDTO dto.BlogUpdateDTO
	errDTO := context.ShouldBind(&blogUpdateDTO)
	if errDTO != nil {
		res := helper.BuildErrorResponse("Failed to process request", errDTO.Error(), helper.EmptyObj{})
		context.JSON(http.StatusBadRequest, res)
		return
	}

	authHeader := context.GetHeader("Authorization")
	token, errToken := c.jwtService.ValidateToken(authHeader)
	if errToken != nil {
		panic(errToken.Error())
	}
	claims := token.Claims.(jwt.MapClaims)
	userID := fmt.Sprintf("%v", claims["user_id"])
	if c.blogService.IsAllowedToEdit(userID, blogUpdateDTO.ID) {
		id, errID := strconv.ParseUint(userID, 10, 64)
		if errID == nil {
			blogUpdateDTO.UserID = id
		}
		result := c.blogService.Update(blogUpdateDTO)
		response := helper.BuildResponse(true, "OK", result)
		context.JSON(http.StatusOK, response)
	} else {
		response := helper.BuildErrorResponse("You dont have permission", "You are not the owner", helper.EmptyObj{})
		context.JSON(http.StatusForbidden, response)
	}
}

// Delete blog
func (c *blogController) Delete(context *gin.Context) {
	var blog entity.Blog
	id, err := strconv.ParseUint(context.Param("id"), 0, 0)
	if err != nil {
		response := helper.BuildErrorResponse("Failed tou get id", "No param id were found", helper.EmptyObj{})
		context.JSON(http.StatusBadRequest, response)
	}
	blog.ID = id
	authHeader := context.GetHeader("Authorization")
	token, errToken := c.jwtService.ValidateToken(authHeader)
	if errToken != nil {
		panic(errToken.Error())
	}
	claims := token.Claims.(jwt.MapClaims)
	userID := fmt.Sprintf("%v", claims["user_id"])
	if c.blogService.IsAllowedToEdit(userID, blog.ID) {
		c.blogService.Delete(blog)
		res := helper.BuildResponse(true, "Deleted", helper.EmptyObj{})
		context.JSON(http.StatusOK, res)
	} else {
		response := helper.BuildErrorResponse("You dont have permission", "You are not the owner", helper.EmptyObj{})
		context.JSON(http.StatusForbidden, response)
	}
}

// getUserIDByToken token
func (c *blogController) getUserIDByToken(token string) string {
	aToken, err := c.jwtService.ValidateToken(token)
	if err != nil {
		panic(err.Error())
	}
	claims := aToken.Claims.(jwt.MapClaims)
	id := fmt.Sprintf("%v", claims["user_id"])
	return id
}
