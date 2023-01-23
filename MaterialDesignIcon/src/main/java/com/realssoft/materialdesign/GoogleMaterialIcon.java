package com.realssoft.materialdesign;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GoogleMaterialIcon
{

    private GoogleMaterialDesignIcon icon;
    private FontType fontType;
    private GradientType gradientType;
    private Color colorPrimary;
    private Color colorSecondary;
    private int size;

    public GoogleMaterialIcon()
    {
        icon = GoogleMaterialDesignIcon.IMAGE;
        fontType = FontType.MATERIAL_SYMBOLS_OUTLINED;
        gradientType = GradientType.HORIZONTAL;
        colorPrimary = new Color(111, 111, 111);
        colorSecondary = new Color(215, 215, 215);
        size = 15;
    }

    public GoogleMaterialDesignIcon getIcon()
    {
        return icon;
    }

    public void setIcon(GoogleMaterialDesignIcon icon)
    {
        this.icon = icon;
    }

    public FontType getFontType()
    {
        return fontType;
    }

    public void setFontType(FontType fontType)
    {
        this.fontType = fontType;
    }

    public GradientType getGradientType()
    {
        return gradientType;
    }

    public void setGradientType(GradientType gradientType)
    {
        this.gradientType = gradientType;
    }

    public Color getColorPrimary()
    {
        return colorPrimary;
    }

    public void setColorPrimary(Color unfocucedColor)
    {
        this.colorPrimary = unfocucedColor;
    }

    public Color getColorSecondary()
    {
        return colorSecondary;
    }

    public void setColorSecondary(Color focucedColor)
    {
        this.colorSecondary = focucedColor;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    private Font buildFont(float size)
    {
        IconFont iconFont =  GoogleMaterialDesignIcon.getIconFont(fontType);
        Font font;
        try
        {
            font = Font.createFont(Font.TRUETYPE_FONT, iconFont.getFontInputStream());
        }
        catch (FontFormatException | IOException e)
        {
            throw new RuntimeException(e);
        }
        return font.deriveFont(size);
    }

    private BufferedImage buildImage(String text, Font font, Color color1,
                                     Color color2, GradientType type)
    {
        JLabel label = new JLabel(text);
        label.setForeground(color1);
        label.setFont(font);
        Dimension dim = label.getPreferredSize();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize(width, height);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics();
        g2d.setFont(font);
        FontMetrics ft = g2d.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(text, g2d);
        double x = (width - r2.getWidth()) / 2;
        double y = (height - r2.getHeight()) / 2;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        int x1, x2, y1, y2;
        if (type == GradientType.HORIZONTAL || type == null)
        {
            x1 = 0;
            y1 = 0;
            x2 = width;
            y2 = 0;
        }
        else if (type == GradientType.VERTICAL)
        {
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = height;
        }
        else if (type == GradientType.DIAGONAL)
        {
            x1 = 0;
            y1 = height;
            x2 = width;
            y2 = 0;
        }
        else
        {
            x1 = 0;
            y1 = 0;
            x2 = width;
            y2 = height;
        }
        GradientPaint gra = new GradientPaint(x1, y1, color1, x2, y2,color2);
        g2d.setPaint(gra);
        g2d.drawString(text, (int) x, (int) (y + ft.getAscent()));
        g2d.dispose();
        return bufImage;
    }

    //2
    public Image buildImage(IconCode iconCode, float size, Color color1, Color color2, GradientType type)
    {
        Font font = buildFont(size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color1, color2, type);
    }

    //1
    public Icon buildIcon(IconCode iconCode, float size, Color color1, Color color2, GradientType type)
    {
        return new ImageIcon(buildImage(iconCode, size, color1, color2, type));
    }

    public Icon toIcon()
    {
        return buildIcon(icon, size, colorPrimary, colorSecondary, gradientType);
    }

}
