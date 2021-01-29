package com.zel.game.sprites;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zel.commonutils.FileUtils;
import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.Log;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class Tetris extends Sprite {

    private List<GlPoint> shape;
    private static Map<Tetrominoe, Map<Integer, List<GlPoint>>> shapeMap;
    private Color color;
    private int angle;
    private Tetrominoe tetrominoe;

    /**
     * seven Tetris shape names and the empty shape called NoShape. NoShape,
     */
    public enum Tetrominoe {
        ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape;

        public static int size() {
            return Tetrominoe.values().length;
        }

        public static Tetrominoe valueOf(int index) {
            return Tetrominoe.values()[index];
        }
    }

    static {
        shapeMap = setShapeMap();
    }

    public Tetris(Tetrominoe tetrominoe) {
        super();
        //this.shape = new GlSquare(30, 30);
        this.w = 20;
        this.h = 20;

        Color colors[] = {
                new Color(0, 0, 0),
                //new Color(204, 102, 102),
                //new Color(102, 204, 102),
                //new Color(102, 102, 204),
                //new Color(204, 204, 102),
                //new Color(204, 102, 204),
                //new Color(102, 204, 204),
                //new Color(218, 170, 0)
        };

        this.tetrominoe = tetrominoe;
        this.shape = shapeMap.get(tetrominoe).get(0);
        //this.color = colors[tetrominoe.ordinal()];
        this.color = colors[0];
    }

    public static Tetris randomTetris() {
        int index = new Random().nextInt(Tetris.Tetrominoe.size());
        return new Tetris(Tetris.Tetrominoe.valueOf(index));
    }

    public void draw(Graphics g) {

        for (GlPoint point : shape) {
            GlSquare square = new GlSquare(w, h, color);
            square.draw(g, x + point.x * w, y + point.y * h);
        }
    }

    public void rotateRight() {
        this.angle += 90;
        if (this.angle == 360) {
            this.angle = 0;
        }
        this.shape = shapeMap.get(this.tetrominoe).get(this.angle);
    }



    private static Map<Tetrominoe, Map<Integer, List<GlPoint>>> setShapeMap() {
        // t, angle, points
        Map<Tetrominoe, Map<Integer, List<GlPoint>>> map = new LinkedHashMap<>();

        String filePath = "./game/src/main/resources/tetris/shape.json";
        String json = "";
        try {
            json = FileUtils.read(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Map<String, List<Integer>>> dto = JsonHelper.read(json, new TypeReference<Map<String, Map<String, List<Integer>>>>() {
        });

        for (String key : dto.keySet()) {
            Map<String, List<Integer>> angles = dto.get(key);

            Map<Integer, List<GlPoint>> mm = new LinkedHashMap<>();
            for (String angle : angles.keySet()) {
                List<Integer> integers = angles.get(angle);
                List<GlPoint> lineShape = new ArrayList<>();

                for (int i = 0; i < integers.size(); i += 2) {
                    int x = integers.get(i);
                    int y = integers.get(i + 1);
                    GlPoint point = new GlPoint(x, y);
                    lineShape.add(point);
                }
                mm.put(Integer.parseInt(angle), lineShape);
            }
            // 注意 key 要和 Tetrominoe 枚举名字一样
            Tetrominoe tetrominoe = Tetrominoe.valueOf(key);
            map.put(tetrominoe, mm);
        }

        return map;
    }


}
