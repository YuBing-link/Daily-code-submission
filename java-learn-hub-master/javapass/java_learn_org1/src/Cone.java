public class Cone {
    int bottom;
    int height;
    int volume;
    int buttom;
    public Cone(int bottom, int height) {
        this.bottom = bottom;
        this.height = height;
        this.volume = (int) (Math.PI * Math.pow(bottom, 2) * height / 3);
        this.buttom = (int)(Math.PI * Math.pow(bottom, 2));
    }
    public void show() {
        System.out.println("底面积为" + buttom + "， 高为" + height + "的圆锥体积为" + volume);
    }
}
