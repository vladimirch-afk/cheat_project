package com.example.cheat_project

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import kotlin.random.Random
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class HelloController {
    lateinit var toMenu2: Button
    lateinit var connectButton: Button
    lateinit var getCode: TextField
    lateinit var back: Button
    lateinit var codeInsertButton: Button
    lateinit var codeGenButton: Button
    @FXML
    private lateinit var codeInsertText: TextField
    @FXML
    private lateinit var codeGenText: TextField
    @FXML
    private lateinit var welcomeText: Label
    @FXML
    private lateinit var nextButton: Button
    @FXML
    private lateinit var choiceBox: ChoiceBox<String>

    private lateinit var code: String

    @FXML
    private fun onHelloButtonClick() {
        nextButton.scene.window.hide()
        val fxmlLoader : FXMLLoader
        if (choiceBox.value == "Отправитель") {
            fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("generate-code.fxml"))
        } else {
            fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("input-code.fxml"))
        }
        var scene = Scene(fxmlLoader.load(), 800.0, 600.0)
        var stage = Stage()
        stage.scene = scene
        stage.show()
    }

    @FXML
    private fun choiceBoxClick() {
        nextButton.isDisable = false;
    }

    @FXML
    private fun goBack() {
        back.scene.window.hide()
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val loader = fxmlLoader.getController<RecieverController>()
        code = getCode.text
        val scene = Scene(fxmlLoader.load(), 800.0, 600.0)
        val stage = Stage()
        stage.scene = scene
        stage.show()
    }

    @FXML
    private fun goToMenu() {
        toMenu2.scene.window.hide()
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 800.0, 600.0)
        val stage = Stage()
        stage.scene = scene
        stage.show()
    }

    @FXML
    private fun generateCode() {
        var code = ""
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        code = (1..10).map { allowedChars.random() }.joinToString("")
        codeGenText.text = code;
    }

    @FXML
    private fun inputCode() {
        code = getCode.text
        getCode.scene.window.hide()
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("getter-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        val stage = Stage()
        stage.scene = scene
        stage.setOnShown {
            fxmlLoader.getController<RecieverController>().displayCode(code)
        }
        stage.show()
    }
    private fun screenShot() {
        try {
            val robot = Robot()
            val screenSize = Toolkit.getDefaultToolkit().screenSize
            val screenRect = Rectangle(screenSize)
            val screenshot = robot.createScreenCapture(screenRect)
            val outputFile = File("screenshot.png")
            ImageIO.write(screenshot, "png", outputFile)
            println("Скриншот сохранен в: ${outputFile.absolutePath}")
        } catch (e: Exception) {
            println("Ошибка при создании скриншота: ${e.message}")
        }
    }
}