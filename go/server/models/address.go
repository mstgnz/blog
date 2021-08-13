package model

import "encoding/json"

type Address struct {
	Type       string
	Country    string
	City       string
	District   string
	PostalCode string
}

func (a Address) ToString() string {
	text, err := json.MarshalIndent(a, "", "\t")
	if err != nil {
		return err.Error()
	}
	return string(text)
}
