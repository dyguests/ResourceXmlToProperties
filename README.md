# ResourceXmlToProperties

This is a java file to transform `xxx.xml` to `xxx.properties`.

此工具用于将`xml`资源文件转换成`properties`文件。


before:

    // colors.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <!-- Palette generated by Material Palette - materialpalette.com/red/purple -->
        <color name="primary">#F44336</color>
        <color name="primary_dark">#D32F2F</color>
        <color name="primary_light">#FFCDD2</color>
        <color name="accent">#E040FB</color>
        <color name="primary_text">#500C0C</color>
        <color name="secondary_text">#3D3535</color>
        <color name="icons">#E7D5D5</color>
        <color name="divider">#948F8F</color>
    
        <color name="test_color_1">#246B30</color>
        <color name="text_bg_1">#E9CC65</color>
    </resources>

after:

    // colors.properties

    primary=#F44336
    primary_dark=#D32F2F
    primary_light=#FFCDD2
    accent=#E040FB
    primary_text=#500C0C
    secondary_text=#3D3535
    icons=#E7D5D5
    divider=#948F8F
    test_color_1=#246B30
    text_bg_1=#E9CC65

## Usage

    Transformer.kt