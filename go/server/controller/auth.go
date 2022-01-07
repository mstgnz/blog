package controller

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"

	"server/dto"
	"server/entity"
	"server/helper"
	"server/service"
)

/*
çalışma mantığı
interface veri tipi üzerine inşa edilen kendi veri tipmiz (IAuthController) sadece methodların imzasını barındırıyor
struct veri tipi üzerine inşa edilen kendi veri tipimiz (authController) ise gereksinimlerimizi barındırıyor.
interface tanımımızda yer alan methodların struct tanımımızda olması gerekiyor (Login-Register), aksi takdirde hata alırız.
oluşturulan bu yapıyı dışarıdan kullanabilmek için ise (AuthController) üzerinden start veriyoruz.
(AuthController) methodu interfacede tanımlı olan method imzalarını barındıran (authController) struct'ını döner
*/

// interface yapısı sadece method imzalarını barındırır
type IAuthController interface {
	Login(ctx *gin.Context)
	Register(ctx *gin.Context)
}

// Struct yapısı kendi custom tipimizi oluşturmamızı sağlar.
// Dışarıya açık olması için ilk harf büyük olmalı hatta fieldlerin bile büyük harfle başlaması gerekir.
// küçük harfle başlayan tüm isimlendirmeler dışarıya kapalıdır. private özelliği taşır. sadece kendi dosya içersinden erişilebilir.
type authController struct {
	authService service.IAuthService
	jwtService  service.IJWTService
}

// constructor olarak düşünülebilir, struct dışarıya açık olmadığı için bu yapı ile set ediliyor. dependency injection
func AuthController(authService service.IAuthService, jwtService service.IJWTService) IAuthController {
	return &authController{
		authService: authService,
		jwtService:  jwtService,
	}
}

// authController struct'ına ait bir method, sadece struct üzerinden ulaşılabilir. authController dışarıya açık olmadığı için AuthController üzerinden erişilir.
func (c *authController) Login(ctx *gin.Context) {
	var loginDTO dto.LoginDTO
	errDTO := ctx.ShouldBind(&loginDTO)
	if errDTO != nil {
		response := helper.BuildErrorResponse("Failed to process request", errDTO.Error(), helper.EmptyObj{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}
	authResult := c.authService.VerifyCredential(loginDTO.Email, loginDTO.Password)
	if v, ok := authResult.(entity.User); ok {
		generatedToken := c.jwtService.GenerateToken(strconv.FormatUint(v.ID, 10))
		v.Token = generatedToken
		response := helper.BuildResponse(true, "OK!", v)
		ctx.JSON(http.StatusOK, response)
		return
	}
	response := helper.BuildErrorResponse("Please check again your credential", "Invalid Credential", helper.EmptyObj{})
	ctx.AbortWithStatusJSON(http.StatusUnauthorized, response)
}

func (c *authController) Register(ctx *gin.Context) {
	var registerDTO dto.RegisterDTO
	errDTO := ctx.ShouldBind(&registerDTO)
	if errDTO != nil {
		response := helper.BuildErrorResponse("Failed to process request", errDTO.Error(), helper.EmptyObj{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	if !c.authService.IsDuplicateEmail(registerDTO.Email) {
		response := helper.BuildErrorResponse("Failed to process request", "Duplicate email", helper.EmptyObj{})
		ctx.JSON(http.StatusConflict, response)
	} else {
		createdUser := c.authService.CreateUser(registerDTO)
		token := c.jwtService.GenerateToken(strconv.FormatUint(createdUser.ID, 10))
		createdUser.Token = token
		response := helper.BuildResponse(true, "OK!", createdUser)
		ctx.JSON(http.StatusCreated, response)
	}
}
