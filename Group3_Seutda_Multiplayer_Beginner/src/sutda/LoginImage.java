package sutda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import javax.imageio.ImageIO;

/** 로그인 화면의 배경 이미지를 불러오고 그리는 패널입니다. */
public class LoginImage extends Panel {
    private Image background;

    /** 지정한 크기의 로그인 배경 패널을 만들고 이미지를 불러옵니다. */
    public LoginImage(int width, int height) {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));

        try {
            File imageFile = new File("resources/images/login_background.png");

            if (imageFile.exists()) {
                background = ImageIO.read(imageFile);
            } else {
                background = ImageIO.read(LoginImage.class.getResource("/images/login_background.png"));
            }
        } catch (Exception e) {
            System.out.println("로그인 배경 이미지를 불러오지 못했습니다.");
        }
    }

    /** 창 크기에 맞게 비율을 유지하면서 배경 이미지를 그립니다. */
    @Override
    public void paint(Graphics g) {
        if (background != null) {
            int imageWidth = background.getWidth(this);
            int imageHeight = background.getHeight(this);

            double scale = Math.max(
                    (double) getWidth() / imageWidth,
                    (double) getHeight() / imageHeight);

            int width = (int) Math.round(imageWidth * scale);
            int height = (int) Math.round(imageHeight * scale);
            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            g.drawImage(background, x, y, width, height, this);
        } else {
            g.setColor(new Color(8, 25, 55));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        super.paint(g);
    }

    /** 화면이 갱신될 때 배경이 깜빡이지 않도록 paint()를 다시 사용합니다. */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
