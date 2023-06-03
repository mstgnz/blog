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

// ICommentController interface
type ICommentController interface {
	Insert(context *gin.Context)
	Update(context *gin.Context)
	Delete(context *gin.Context)
}

// commentController struct
type commentController struct {
	commentService service.ICommentService
	jwtService     service.IJWTService
}

// CommentController instance
func CommentController(commentSrv service.ICommentService, jwtServ service.IJWTService) ICommentController {
	return &commentController{
		commentService: commentSrv,
		jwtService:     jwtServ,
	}
}

// Insert create comment
func (c *commentController) Insert(context *gin.Context) {
	var commentCreateDTO dto.CommentCreateDTO
	errDTO := context.ShouldBind(&commentCreateDTO)
	if errDTO != nil {
		res := helper.BuildErrorResponse("Failed to process request", errDTO.Error(), helper.EmptyObj{})
		context.JSON(http.StatusBadRequest, res)
	} else {
		authHeader := context.GetHeader("Authorization")
		userID := c.getUserIDByToken(authHeader)
		convertedUserID, err := strconv.ParseUint(userID, 10, 64)
		if err == nil {
			commentCreateDTO.UserID = convertedUserID
		}
		result, err := c.commentService.Insert(commentCreateDTO)
		if err != nil {
			response := helper.BuildErrorResponse("ERROR", err.Error(), err.Error())
			context.JSON(http.StatusBadRequest, response)
		} else {
			response := helper.BuildResponse(true, "OK", result)
			context.JSON(http.StatusCreated, response)
		}
	}
}

// Update comment
func (c *commentController) Update(context *gin.Context) {
	var commentUpdateDTO dto.CommentUpdateDTO
	errDTO := context.ShouldBind(&commentUpdateDTO)
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
	if c.commentService.IsAllowedToEdit(userID, commentUpdateDTO.ID) {
		id, errID := strconv.ParseUint(userID, 10, 64)
		if errID == nil {
			commentUpdateDTO.UserID = id
		}
		result := c.commentService.Update(commentUpdateDTO)
		response := helper.BuildResponse(true, "OK", result)
		context.JSON(http.StatusOK, response)
	} else {
		response := helper.BuildErrorResponse("You dont have permission", "You are not the owner", helper.EmptyObj{})
		context.JSON(http.StatusForbidden, response)
	}
}

// Delete comment
func (c *commentController) Delete(context *gin.Context) {
	var comment entity.Comment
	id, err := strconv.ParseUint(context.Param("id"), 0, 0)
	if err != nil {
		response := helper.BuildErrorResponse("Failed tou get id", "No param id were found", helper.EmptyObj{})
		context.JSON(http.StatusBadRequest, response)
	}
	comment.ID = id
	authHeader := context.GetHeader("Authorization")
	token, errToken := c.jwtService.ValidateToken(authHeader)
	if errToken != nil {
		panic(errToken.Error())
	}
	claims := token.Claims.(jwt.MapClaims)
	userID := fmt.Sprintf("%v", claims["user_id"])
	if c.commentService.IsAllowedToEdit(userID, comment.ID) {
		c.commentService.Delete(comment)
		res := helper.BuildResponse(true, "Deleted", helper.EmptyObj{})
		context.JSON(http.StatusOK, res)
	} else {
		response := helper.BuildErrorResponse("You dont have permission", "You are not the owner", helper.EmptyObj{})
		context.JSON(http.StatusForbidden, response)
	}
}

// getUserIDByToken token
func (c *commentController) getUserIDByToken(token string) string {
	aToken, err := c.jwtService.ValidateToken(token)
	if err != nil {
		panic(err.Error())
	}
	claims := aToken.Claims.(jwt.MapClaims)
	id := fmt.Sprintf("%v", claims["user_id"])
	return id
}
