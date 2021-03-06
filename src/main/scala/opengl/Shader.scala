package edu.stanford.cs248.project.opengl

import org.lwjgl.opengl._

class Shader(val vertName: String, val fragName: String) {
  import GL11._
  import ARBShaderObjects._
  import ARBVertexShader._
  import ARBFragmentShader._
  
  var id = 0
  
  def init() = {
    
    id = glCreateProgramObjectARB()
    
    val vertShader = makeShader(GL_VERTEX_SHADER_ARB, vertName+".vert.glsl")
    val fragShader = makeShader(GL_FRAGMENT_SHADER_ARB, fragName+".frag.glsl")
    
    if(vertShader != 0 && fragShader != 0) {
      glAttachObjectARB(id, vertShader)
      glAttachObjectARB(id, fragShader)
      
      glLinkProgramARB(id)
      if(glGetObjectParameteriARB(id, GL_OBJECT_LINK_STATUS_ARB) ==
         GL_FALSE)
      {
        printLog("Link failure", id)
        false
      } else {
        glValidateProgramARB(id)
        if(glGetObjectParameteriARB(id, GL_OBJECT_VALIDATE_STATUS_ARB) ==
           GL_FALSE)
        {
          printLog("Validate failure", id)
          false
        } else {
          true
        }
      }
    } else false
  }
  
  def makeShader(shaderType: Int, rcFilename: String) = {
    val id = glCreateShaderObjectARB(shaderType)
    
    if(id != 0) {
      val src = Shader.getSource("/shaders/"+rcFilename)
      glShaderSourceARB(id, src)
      glCompileShaderARB(id)
      
      if(glGetObjectParameteriARB(id, GL_OBJECT_COMPILE_STATUS_ARB) ==
         GL_FALSE) 
      {
        printLog("Compile failure", id)
        0
      } else {
        id
      }
      
    } else 0
  }
  
  def printLog(msg: String, objId: Int) = {
    println(msg)
    println(glGetInfoLogARB(objId, 
      glGetObjectParameteriARB(objId, GL_OBJECT_INFO_LOG_LENGTH_ARB)))
  }
  
  def use() = {
    glUseProgramObjectARB(id)
  }
  
  def stopUsing() = {
    glUseProgramObjectARB(0)
  }
}

object Shader {
  def getSource(rcPath: String) =
    scala.io.Source.fromInputStream(getClass.getResourceAsStream(rcPath))
      .getLines().mkString("\n")

}
