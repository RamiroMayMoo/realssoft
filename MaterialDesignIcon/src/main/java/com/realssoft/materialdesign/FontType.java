package com.realssoft.materialdesign;

public enum FontType
{

    MATERIAL_SYMBOLS_OUTLINED("font/MaterialSymbolsOutlined.ttf"),
    MATERIAL_SYMBOLS_SHARP("font/MaterialSymbolsSharp.ttf"),
    MATERIAL_SYMBOLS_ROUNDED("font/MaterialSymbolsRounded.ttf");

    private String path;

    FontType(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
