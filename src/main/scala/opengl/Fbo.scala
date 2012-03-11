package edu.stanford.cs248.project.opengl

import org.lwjgl.opengl._

trait Fbo {
  import EXTFramebufferObject._ 
  
  def fboId: Int 
  def use() = {
    glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboId)
  }
}

object screenFbo extends Fbo {
  def fboId = 0
}

class MrtFbo(nTargets: Int) extends Fbo {
  import EXTFramebufferObject._
  
  val fboId = glGenFramebuffersEXT()
  
  
}
