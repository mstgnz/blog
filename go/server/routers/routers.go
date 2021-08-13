package routers

import (
	"fmt"
	"log"
	"net/http"

	controller "../controllers"
	"github.com/gorilla/mux"
)

func HandleRouters() {
	router := mux.NewRouter().StrictSlash(true)
	router = router.PathPrefix("/api").Subrouter()
	router.HandleFunc("/", controller.HelloWorld).Methods("GET")
	router.HandleFunc("/company/{id}", controller.CompanyIndex).Methods("GET")

	fmt.Print("Listening : http://localhost:8001")
	log.Fatal(http.ListenAndServe(":8001", router))
}
