package arte921.collisions

import Cube
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import java.util.*
import kotlin.math.pow

class CanvasView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private val bgColor = ResourcesCompat.getColor(resources,R.color.bgColor,null)
    private val blockColor = ResourcesCompat.getColor(resources,R.color.blockColor,null)

    private var maxx: Int = 0
    private var maxy: Int = 0

    private var deltat: Int = 1
    private var go: Long = 1
    private var simSteps: Int = 0
    private var restSteps: Int = 0

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val blockPaint = Paint().apply {
        color = blockColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 10f
    }

    private val textPaint = Paint().apply {
        color = blockColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 2f
        textSize = 100f
    }

    override fun onSizeChanged(width:Int,height:Int,oldWidth:Int,oldHeight:Int) {
        super.onSizeChanged(width,height,oldWidth,oldHeight)

        if(::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(bgColor)
        maxx = width
        maxy = height

        go = Calendar.getInstance().timeInMillis
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap,0F,0F,null)

        simulate(simSteps)

        canvas.drawRect(bigCube.x.toFloat(),10F,(bigCube.x + bigCube.w).toFloat(),100F,blockPaint)
        canvas.drawRect(smallCube.x.toFloat(),10F,(smallCube.x + smallCube.w).toFloat(),100F,blockPaint)
        canvas.drawText(totalCollisions.toString(),20f,200f,textPaint)

        deltat = (Calendar.getInstance().timeInMillis - go).toInt()
        go = Calendar.getInstance().timeInMillis
        simSteps = deltat*10
        invalidate()
    }




}
