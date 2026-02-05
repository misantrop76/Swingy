package fr.swingy.rpg.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MapPanel extends JPanel
{

    private String[] map;
    private int rows, cols;
    private boolean drawGrid = true;
    private Font cellFont;
    private final Map<Character, Image> iconMap = new HashMap<>();

    public MapPanel(String[] initialMap)
    {
        setBackground(Color.BLACK);
        setOpaque(true);
        this.cellFont = new Font("Monospaced", Font.BOLD, 18);
        setMap(initialMap);

        setIconFor('P', loadImageFromResources("/cowboy.png"));
        setIconFor('?', loadImageFromResources("/question.png"));
        setIconFor('O', loadImageFromResources("/yellow_square.png"));
    }

    public final void setMap(String[] newMap)
    {
        if (newMap == null)
        {
            this.map = new String[0];
            this.rows = 0;
            this.cols = 0;
        }
        else
        {
            this.map = new String[newMap.length];
            int maxLen = 0;
            for (int i = 0; i < newMap.length; i++)
            {
                String line = newMap[i];
                if (line == null) line = "";
                this.map[i] = line;
                if (line.length() > maxLen) maxLen = line.length();
            }
            this.rows = this.map.length;
            this.cols = maxLen;
        }
        revalidate();
        repaint();
    }

    public void setIconFor(char c, Image img)
    {
        if (img != null)
        {
            iconMap.put(c, img);
        }
    }

    public void setDrawGrid(boolean draw)
    {
        this.drawGrid = draw;
        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        int baseCell = 32;
        return new Dimension(Math.max(1, cols) * baseCell, Math.max(1, rows) * baseCell);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (rows == 0 || cols == 0)
            return;

        Graphics2D g2 = (Graphics2D) g.create();
        try
        {
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            double cellW = (double) w / cols;
            double cellH = (double) h / rows;
            int cw = (int) Math.floor(Math.min(cellW, cellH));
            int ch = cw;

            int gridW = cw * cols;
            int gridH = ch * rows;
            int offsetX = (w - gridW) / 2;
            int offsetY = (h - gridH) / 2;

            g2.setColor(getBackground());
            g2.fillRect(0, 0, w, h);

            int fontSize = Math.max(12, (int) Math.round(cw * 0.7));
            g2.setFont(cellFont.deriveFont((float) fontSize));
            FontMetrics fm = g2.getFontMetrics();

            for (int r = 0; r < rows; r++)
            {
                String line = map[r];
                int lineLen = line.length();

                for (int c = 0; c < cols; c++) {
                    char chCell = (c < lineLen) ? line.charAt(c) : ' ';

                    int x = offsetX + c * cw;
                    int y = offsetY + r * ch;

                    g2.setColor(Color.DARK_GRAY);
                    g2.fillRect(x, y, cw, ch);

                    Image icon = iconMap.get(chCell);
                    if (icon != null)
                    {
                        int pad = Math.max(2, (int) Math.round(cw * 0.1));
                        int availW = cw - 2 * pad;
                        int availH = ch - 2 * pad;

                        int iw = icon.getWidth(null);
                        int ih = icon.getHeight(null);
                        if (iw > 0 && ih > 0)
                        {
                            double scale = Math.min((double) availW / iw, (double) availH / ih);
                            int sw = (int) Math.round(iw * scale);
                            int sh = (int) Math.round(ih * scale);
                            int ix = x + (cw - sw) / 2;
                            int iy = y + (ch - sh) / 2;
                            g2.drawImage(icon, ix, iy, sw, sh, null);
                        }
                    }
                    else
                    {
                        if (chCell != ' ')
                        {
                            g2.setColor(Color.WHITE);
                            String s = String.valueOf(chCell);
                            int textW = fm.stringWidth(s);
                            int textH = fm.getAscent();
                            int tx = x + (cw - textW) / 2;
                            int ty = y + (ch + textH) / 2 - fm.getDescent();
                            g2.drawString(s, tx, ty);
                        }
                    }

                    if (drawGrid)
                    {
                        g2.setColor(new Color(255, 255, 255, 50));
                        g2.drawRect(x, y, cw, ch);
                    }
                }
            }

        }
        finally
        {
            g2.dispose();
        }
    }

    public static Image loadImageFromResources(String resourcePath)
    {
        URL url = MapPanel.class.getResource(resourcePath);
        if (url == null)
        {
            System.err.println("[MapPanel] Ressource introuvable: " + resourcePath);
            return null;
        }
        return new ImageIcon(url).getImage();
    }

    public static Image scaleImageHQ(Image src, int w, int h)
    {
        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dst.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(src, 0, 0, w, h, null);
        g2.dispose();
        return dst;
    }
}