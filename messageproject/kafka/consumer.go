package kafka

import (
	"context"
	"encoding/json"
	"log"
	"MESSAGEPROJECT/controller"
	"MESSAGEPROJECT/model"

	"github.com/segmentio/kafka-go"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

func StartConsumer() {
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers:  []string{"localhost:9092"},
		Topic:    "user_messages",
		GroupID:  "go-msg-group",
		MinBytes: 10e3,
		MaxBytes: 10e6,
	})

	log.Println("Kafka Consumer started, waiting for messages...")

	for {
		m, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Println("Error reading Kafka message:", err)
			break
		}

		// Unmarshal the JSON from Java
		var msg models.Message
		if err := json.Unmarshal(m.Value, &msg); err != nil {
			log.Println("Error decoding message:", err)
			continue
		}

		// Set MongoDB specific fields
		msg.ID = primitive.NewObjectID()
		
		// Save to MongoDB using your existing controller's collection
		_, err = controller.MsgCollection.InsertOne(context.TODO(), msg)
		if err != nil {
			log.Println("Failed to save Kafka message to Atlas:", err)
		} else {
			log.Printf("Message from %s saved to MongoDB Atlas!\n", msg.Number)
		}
	}
}