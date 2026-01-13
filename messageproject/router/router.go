package router

import (
	"MESSAGEPROJECT/controller"
	"github.com/gin-gonic/gin"
)

func SetupRoutes(router *gin.Engine) {
	router.POST("/messages", controller.CreateMessage)
	router.GET("/messages/:number", controller.GetMessagesByNumber)
}