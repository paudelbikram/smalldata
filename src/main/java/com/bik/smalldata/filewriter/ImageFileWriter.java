package com.bik.smalldata.filewriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileWriter implements CustomFileWriter
{
    private final String outputPath;
    private final String inputPath;

    public ImageFileWriter(final String outputPath, final String inputPath)
    {
        this.outputPath = outputPath;
        this.inputPath = inputPath;
    }
    @Override
    public void write()
    {
        try {
            final BufferedImage source = ImageIO.read(new File(this.inputPath));
            final BufferedImage out = rotate(source);
            final File outputImageFile = new File(this.outputPath);
            final boolean succeed_write = ImageIO.write(out, "PNG", outputImageFile);
            System.out.println("Writing file status: "+ succeed_write);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage rotate(final BufferedImage source)
    {
        final int width = source.getWidth();
        final int height = source.getHeight();
        BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int rgb = source.getRGB(i, j);
                output.setRGB(j, i, rgb);
            }
        }
        return output;
    }
}
