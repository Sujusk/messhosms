package models

import "go.mongodb.org/mongo-driver/bson/primitive"

type Message struct {
	ID      primitive.ObjectID `bson:"_id,omitempty" json:"id"`
	Number  string             `bson:"number" json:"number"` 
	Content string             `bson:"content" json:"content"`
	Time    string             `bson:"time" json:"time"`
}