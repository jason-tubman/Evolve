package evolve;

/**
 * Created by Jason on 27/10/2016.
 */
public class Camera {

    private float  xOffset, yOffset;
    private double zoomfactor = 1;

    public Camera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void moveCamera(float xMove, float yMove) {

        if (xOffset + xMove > -1920/zoomfactor && xOffset + xMove < 0) {
            xOffset += xMove;
        }
        if (yOffset + yMove < 0 && yOffset + yMove > -1080/zoomfactor) {
            yOffset += yMove;
        }

    }

    public void setZoomOffset(int notches) {

        zoomfactor = 1 + (notches * 0.05);

    }
    public double getZoom() {
        return zoomfactor;
    }
    public float getXoffset() {
        return xOffset;
    }
    public void setXoffset(float offset) {
        this.xOffset = offset;
    }
    public float getYOffset() {
        return yOffset;
    }
    public void setYoffset(float offset) {
        this.yOffset = offset;
    }
}
