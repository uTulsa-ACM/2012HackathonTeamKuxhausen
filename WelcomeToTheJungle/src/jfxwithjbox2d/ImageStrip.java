package jfxwithjbox2d;

import javafx.scene.image.Image;

public class ImageStrip {
	public final Image image;
	public final int columns;
	public final int count;
	public final int xOffset;
	public final int yOffset;
	public final int width;
	public final int height;
	public final float duration;

	public ImageStrip() {
		this("http://upload.wikimedia.org/wikipedia/commons/7/73/The_Horse_in_Motion.jpg",
				4, 10, 18, 25, 374, 243, 5.f);
	}

	public ImageStrip(String path, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		this(new Image(path), columns, count, xOffset, yOffset, width, height, duration);
	}

	public ImageStrip(Image image, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		this.image = image;
		this.columns = columns;
		this.count = count;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		this.duration = duration;
	}
}
