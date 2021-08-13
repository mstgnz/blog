package model

import "encoding/json"

type Phone struct {
	Home   string
	Mobile string
}

func (p Phone) ToString() string {
	text, err := json.MarshalIndent(p, "", "\t")
	if err != nil {
		return err.Error()
	}
	return string(text)
}
