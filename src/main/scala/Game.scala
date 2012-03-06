package edu.stanford.cs248.project

import org.lwjgl._
import opengl.{Display,GL11,DisplayMode, GLContext}
import GL11._
import input._
import math._
import Keyboard._
import scala.util.control.Breaks._

object Main{
  val GAME_TITLE = "My Game"
  val FRAMERATE = 60
  val width = 1280
  val height = 720
  
  val cam = new Camera()
  
  def main(args:Array[String]) = {
    var fullscreen = false
    for(arg <- args){
      arg match{
        case "-fullscreen" =>
          fullscreen = true
      }
    }
    
    init(fullscreen)
    run()
    gameOver()
  }

  def init(fullscreen:Boolean) = {
    Display.setTitle(GAME_TITLE)
    Display.setFullscreen(fullscreen)
    Display.setVSyncEnabled(true)
    Display.setDisplayMode(new DisplayMode(width,height))
    Display.create()
    
    GLContext.useContext()
    if(!GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
      println("OpenGL context doesn't support VBOs.")
      System.exit(-1)
    }
    
    glViewport(0, 0, width, height)
  }

  def gameOver() = {
    Display.destroy()
    System.exit(0)
  }
  
  def updateGame() = {
  }
  
  def renderGame() = {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    
    cam.loadGLMatrices()
    
  }  

  def run() = {
    while(!(isKeyDown(KEY_ESCAPE) || Display.isCloseRequested)) {
      updateGame()
      
      Display.update()
      
      renderGame()      
      
      Display.sync(FRAMERATE)
    }
  }
}