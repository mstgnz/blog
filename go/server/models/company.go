package model

import (
	"encoding/json"
)

type Company struct {
	Name    string
	Domain  string
	Phones  Phone
	Address []Address
}

func (c Company) ToString() string {
	text, err := json.MarshalIndent(c, "", "\t")
	if err != nil {
		return err.Error()
	}
	return string(text)
}
