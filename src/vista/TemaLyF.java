package vista;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class TemaLyF extends DefaultMetalTheme {

    public String getName() { return "Toms"; }

    private final ColorUIResource primary1 = new ColorUIResource(150, 180, 150);
    private final ColorUIResource primary2 = new ColorUIResource(100, 150, 180);
    private final ColorUIResource primary3 = new ColorUIResource(120,166, 150);
    private final ColorUIResource primary4 = new ColorUIResource(50,175, 155);
    private final ColorUIResource primary5 = new ColorUIResource(70,130, 150);

    protected ColorUIResource getPrimary1() { return primary1; }
    protected ColorUIResource getPrimary2() { return primary2; }
    protected ColorUIResource getPrimary3() { return primary3; }
    protected ColorUIResource getPrimary4() { return primary4; }
    protected ColorUIResource getPrimary5() { return primary5; }

}
