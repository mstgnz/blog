package helper

import (
	"strings"
)

// Response helper
type Response struct {
	Status  bool        `json:"status"`
	Message string      `json:"message"`
	Errors  interface{} `json:"errors"`
	Data    interface{} `json:"data"`
}

// EmptyObj Empty
type EmptyObj struct{}

func GenerateResponse(data interface{}, err error) Response {
	response := Response{}
	if err != nil {
		response = BuildErrorResponse("ERROR", err.Error(), err)
	}
	response = BuildResponse(true, "OK", data)

	return response
}

func BuildResponse(status bool, message string, data interface{}) Response {
	res := Response{
		Status:  status,
		Message: message,
		Errors:  nil,
		Data:    data,
	}
	return res
}

func BuildErrorResponse(message string, err string, data interface{}) Response {
	splitError := strings.Split(err, "\n")
	res := Response{
		Status:  false,
		Message: message,
		Errors:  splitError,
		Data:    data,
	}
	return res
}
