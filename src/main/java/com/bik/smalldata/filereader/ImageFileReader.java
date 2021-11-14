package com.bik.smalldata.filereader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageFileReader implements CustomFileReader
{

    private final String inputPath;

    public ImageFileReader(final String inputPath)
    {
        this.inputPath = inputPath;
    }

    /**
     * We are going to read ImageFile here
     * @return
     */
    @Override
    public List<Double> readFile()
    {
        BufferedImage img;
        List<Double> grayScales = new ArrayList<>();
        try
        {
            img = ImageIO.read(new File(this.inputPath));
            final int height = img.getHeight();
            final int width = img.getWidth();
            int[][] data = new int [width] [height];

            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    int rgb = img.getRGB(i, j);
                    data [i] [j] = rgb;
                    //converts rgb to components
                    final int blue = 0x0000ff & rgb;
                    final int green = 0x0000ff & (rgb >> 8);
                    final int red = 0x0000ff & (rgb >> 16);
                    grayScales.add(getGrayScale(red, green, blue));
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return grayScales;
    }


    private double getGrayScale (final int r, final int g, final int b)
    {
        return (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255.0;
    }

    private void getRgbUsingRaster(final BufferedImage img)
    {
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < pixels.length / 3 ; i++)
        {
            int blue = Byte.toUnsignedInt(pixels[3*i]);
            int green = Byte.toUnsignedInt(pixels[3*i+1]);
            int red = Byte.toUnsignedInt(pixels[3*i+2]);
        }
    }
}
