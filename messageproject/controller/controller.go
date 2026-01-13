package controller

import (
	"MESSAGEPROJECT/model"
	"context"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
    "go.mongodb.org/mongo-driver/bson"           
    "go.mongodb.org/mongo-driver/bson/primitive" 
    "go.mongodb.org/mongo-driver/mongo"
)

var MsgCollection *mongo.Collection

func CreateMessage(c *gin.Context) {
	var msg models.Message
	if err := c.BindJSON(&msg); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid input"})
		return
	}

	msg.ID = primitive.NewObjectID()
	msg.Time = time.Now().Format(time.RFC3339)

	_, err := MsgCollection.InsertOne(context.TODO(), msg)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to save message"})
		return
	}

	c.JSON(http.StatusCreated, msg)
}

func GetMessagesByNumber(c *gin.Context) {
	number := c.Param("number")
	var messages []models.Message

	cursor, err := MsgCollection.Find(context.TODO(), bson.M{"number": number})
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Database error"})
		return
	}
	defer cursor.Close(context.TODO())

	if err = cursor.All(context.TODO(), &messages); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Error decoding data"})
		return
	}

	c.JSON(http.StatusOK, messages)
}