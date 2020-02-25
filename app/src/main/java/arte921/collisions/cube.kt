import arte921.collisions.totalCollisions

class Cube(xcoord: Double, width: Double, velocity: Double, mass: Double, id: Int) {
    var x: Double = 0.0 //locatie
    var w: Double = 0.0 //breedte
    var v: Double = 0.0 //snelheid
    var m: Double = 0.0 //massa
    var u: Double = 0.0 //backup snelheid
    var id: Int = 0


    init {
        this.x = xcoord
        this.w = width
        this.v = velocity
        this.m = mass
        this.id = id
    }

    fun checkcollide(other: Cube){
        if((this.id < other.id && this.x + this.w >= other.x)|| (this.id > other.id && other.x + other.w >= this.x)){
            collided(other)
        }
    }

    fun collided(other: Cube){
        this.u = this.v
        other.u = other.v

        this.v = (this.m - other.m)/(this.m + other.m)*this.u + (2*other.m)/(this.m + other.m) * other.u
        other.v = (other.m - this.m)/(other.m + this.m)*other.u + (2*this.m)/(other.m + this.m) * this.u

        if(this.id < other.id){
            this.x = (this.x+this.w+other.x)/2-this.w
        }else{
            other.x = (other.x+other.w+this.x)/2-other.w
        }

        totalCollisions++
    }

    fun checkWall(){
        if(this.x <= 0) this.collideWall()
    }

    fun move(){
        this.x += this.v * 0.01
    }

    fun isDone(other: Cube): Boolean{
        return other.v <= this.v && this.v > 0 && other.v > 0
    }

    fun collideWall(){
        this.v *= -1.0
        this.x = 0.0
        totalCollisions++
    }

}