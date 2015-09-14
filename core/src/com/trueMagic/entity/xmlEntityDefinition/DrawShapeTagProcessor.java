package com.trueMagic.entity.xmlEntityDefinition;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trueMagic.component.DrawShape;
import com.trueMagic.entity.ProcessedArchetypeBuilder;
import com.trueMagic.utils.math.shape.Circle;
import com.trueMagic.utils.math.shape.Rectangle;
import com.trueMagic.utils.math.shape.Shape;
import org.w3c.dom.Element;

/**
 * Created by Declan Easton on 2015-09-08.
 */
public class DrawShapeTagProcessor implements XmlTagProcessor {

    private World ceWorld;

    public DrawShapeTagProcessor(World ceWorld) {
        this.ceWorld = ceWorld;
    }

    @Override
    public void process(ProcessedArchetypeBuilder archetypeBuilder, Element element) {

        Shape shape = null;
        int shapeType = Integer.parseInt(element.getAttribute("shapeType"));
        if(shapeType == Shape.CIRCLE) {
            Circle circle = new Circle();
            circle.radius = Float.parseFloat(element.getAttribute("radius"));
            shape = circle;
        } else if(shapeType == Shape.RECTANGLE) {
            Rectangle rect = new Rectangle();
            rect.width = Float.parseFloat(element.getAttribute("width"));
            rect.height = Float.parseFloat(element.getAttribute("height"));
            shape = rect;
        }
        ShapeRenderer.ShapeType drawType = ShapeRenderer.ShapeType.valueOf(element.getAttribute("drawType"));
        Color color = Color.valueOf(element.getAttribute("color"));
        archetypeBuilder.add(DrawShape.class);
        archetypeBuilder.add(new EntityProcessor(ceWorld, shape, drawType, color));
    }

    @Override
    public String getTagName() {
        return null;
    }

    private class EntityProcessor implements com.trueMagic.entity.EntityProcessor {

        private ComponentMapper<DrawShape> drawShapeComponentMapper;
        private Shape shape;
        private ShapeRenderer.ShapeType shapeType;
        private Color color;

        public EntityProcessor(com.artemis.World ceWorld, Shape shape, ShapeRenderer.ShapeType shapeType, Color color) {
            this.shape = shape;
            this.shapeType = shapeType;
            this.color = color;
            drawShapeComponentMapper = ComponentMapper.getFor(DrawShape.class, ceWorld);
        }

        @Override
        public void Finish(Entity entity) {
            DrawShape drawShapeComp = drawShapeComponentMapper.get(entity);
            drawShapeComp.shape = shape;
            drawShapeComp.shapeType = shapeType;
            drawShapeComp.color = color;
        }
    }
}
