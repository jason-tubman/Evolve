package evolve;

/**
 * Created by Jason on 27/10/2016.
 */
public class Camera {

    private float  xOffset, yOffset;

    public Camera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void moveCamera(float xMove, float yMove) {
        xOffset += xMove;
        yOffset += yMove;
    }

    public float getXoffset() {
        return xOffset;
    }
    public void setXoffset(float offset) {
        this.xOffset = offset;
    }
    public float getyOffset() {
        return yOffset;
    }
    public void setYoffset(float offset) {
        this.yOffset = offset;
    }
}
