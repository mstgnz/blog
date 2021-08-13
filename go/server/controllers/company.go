package controllers

import (
	"fmt"
	"net/http"

	model "../models"
)

func CompanyIndex(w http.ResponseWriter, r *http.Request) {
	cmpny := model.Company{
		Name:   "MstGnz",
		Domain: "msgnz.com",
		Phones: model.Phone{
			Home:   "234324333",
			Mobile: "324234333",
		},
		Address: []model.Address{
			{
				Type:       "Home",
				Country:    "Türkiye",
				City:       "İstanbul",
				District:   "Ümraniye",
				PostalCode: "340000",
			},
			{
				Type:       "Work",
				Country:    "Türkiye",
				City:       "İstanbul",
				District:   "Sarıyer",
				PostalCode: "340000",
			},
		},
	}

	fmt.Fprintf(w, (cmpny.ToString()))
}
