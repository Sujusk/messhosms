package main

import (
	"context"
	"fmt"
	"log"
	"time"

	"MESSAGEPROJECT/controller"
	"MESSAGEPROJECT/router"   
	"MESSAGEPROJECT/kafka"     
	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func main() {

	// 1. MongoDB Atlas Connection
	uri := "mongodb+srv://sujal:sujal@cluster0.vx9y7dx.mongodb.net/?appName=Cluster0"
	clientOptions := options.Client().ApplyURI(uri)

	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	client, err := mongo.Connect(ctx, clientOptions)
	if err != nil {
		log.Fatal("Connection Error:", err)
	}

	err = client.Ping(ctx, nil)
	if err != nil {
		log.Fatal("Could not ping Atlas:", err)
	}
	fmt.Println("Successfully connected to MongoDB Atlas!")

	// 2. Initialize Collections
	db := client.Database("messenger_db")
	controller.MsgCollection = db.Collection("messages")

	// 3. Create Index
	createIndex(controller.MsgCollection)

	// 4. Start Kafka Consumer in a background Goroutine
	go kafka.StartConsumer()

	// 5. Setup Gin Router
	r := gin.Default()
	
	// Ensure the package name in your routeR folder is actually 'routeR'
	router.SetupRoutes(r)

	// 6. Start the Server 
	fmt.Println("Server is running on port 8085...")
	
	if err := r.Run(":8085"); err != nil {
	log.Fatal("Failed to start server:", err)
	}

	
}

func createIndex(col *mongo.Collection) {
	indexModel := mongo.IndexModel{
		Keys: bson.D{{Key: "number", Value: 1}},
	}
	_, err := col.Indexes().CreateOne(context.TODO(), indexModel)
	if err != nil {
		fmt.Println("Index creation failed:", err)
	}
}