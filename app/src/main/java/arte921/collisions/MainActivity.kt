package arte921.collisions

import Cube
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

var quality: Double = 100.0

var bigCube: Cube = Cube(1000.0,100.0,-1.0,100.0.pow(4),1)
var smallCube: Cube = Cube(500.0,50.0,0.0,1.0,0)
var totalCollisions: Int = 0
var currentStep: Int = 0

fun simulate(steps: Int){
    //totalCollisions = 0
    currentStep = 0

    while(currentStep<steps && (smallCube.x <= maxx || !bigCube.isDone(smallCube))){
        bigCube.move()
        smallCube.move()
        bigCube.checkcollide(smallCube)
        smallCube.checkWall()
        currentStep++
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}