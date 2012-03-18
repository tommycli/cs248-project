package edu.stanford.cs248.project

import scala.math._

import org.lwjgl.opengl._
import java.nio.ByteBuffer

import edu.stanford.cs248.project.entity._
import edu.stanford.cs248.project.opengl._

import org.lwjgl._
import input._
import Keyboard._
import scala.math._
import com.threed.jpct._
import com.threed.jpct.util._
import math._

class Block(val x: Float, val y: Float, var z: Float) extends VBOModelEntity {
	val model = new SquareModel(x, y, z)
	val width = 0.5f

	// TODO: Could emit particles.
	def select(selected: Boolean) = {
		if (selected) {
			z = 2.0f
		} else {
			z = 0.0f
		}
	}

	def intersect(px: Float, py: Float, pz: Float) = {
		(px > x - width) && (px < x + width) &&
		(py > y - width) && (py < y + width) &&
		(pz > z - width) && (pz < z + width)
	}

	override def traits() = List("render", "update", "block")
}