package JavaCool303;

public class Cool303Root extends Cool303Container {
    private Cool303Theme theme;
    private int desiredWidth, desiredHeight;

    public Cool303Root(Cool303Theme theme) {
        super();

        if (theme == null)
            throw new IllegalArgumentException("Need to specify a theme.");

        this.theme = theme;

        getComponent().setPreferredSize(null);
    }

    public Cool303Root(int width, int height, Cool303Theme theme) {
        this(theme);

        desiredWidth = width;
        desiredHeight = height;

        setSize(width, height);
    }

    @Override
    public void add(Cool303Component c) {
        if (c instanceof Cool303Container)
            super.add(c);

        if (desiredWidth < getWidth() || desiredHeight < getHeight())
            getComponent().setPreferredSize(null);
    }

    @Override
    public int getWidth() {
        int width = 0;

        for (Cool303Component c: getChildren())
            width += c.getWidth();

        return width;
    }

    @Override
    public int getHeight() {
        int height = 0;

        for (Cool303Component c: getChildren())
            height += c.getHeight();

        return height;
    }

    public void paint() {
        paint(theme);
    }
}
