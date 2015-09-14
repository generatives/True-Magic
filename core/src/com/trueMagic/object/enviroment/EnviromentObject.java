/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.enviroment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trueMagic.maps.MapGenerator;
import com.trueMagic.object.GameObject;
import com.trueMagic.utils.math.MathUtils;
import com.trueMagic.world.TrueMagicWorld;
import java.util.List;
import java.util.Random;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Declan Easton
 */
public class EnviromentObject extends GameObject {

    private final List<EnviromentPoint> edgePoints;
    private final Color color;
    private static final Random rand = new Random();

    public EnviromentObject(TrueMagicWorld world, List<EnviromentPoint> edgePoints) {
        super(world, GroundFactory.buildGroundBody(world.getPhysicsWorld(), edgePoints), GroundFactory.buildComplexGroundFixture(edgePoints));
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        color = new Color(r, g, b, 0);
        this.edgePoints = edgePoints;
    }

    @Override
    public void draw(Batch batch, ShapeRenderer renderer) {
        EnviromentPoint lastPoint = edgePoints.get(edgePoints.size() - 1);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        for (EnviromentPoint point : edgePoints) {
            if (lastPoint != null) {
                renderer.line(point.x * 64, point.y * 64, lastPoint.x * 64, lastPoint.y * 64);
                //renderer.circle(point.x * 64, point.y * 64, 2);
            }
            lastPoint = point;
        }
        renderer.end();
    }
    
    public boolean onEdge(float x, float y) {
        int numPoints = edgePoints.size();
        int j = numPoints - 1;
        int i;
        for (i = 0; i < numPoints; i++) {
            float X_j = edgePoints.get(j).x;
            float Y_j = edgePoints.get(j).y;
            float X_i = edgePoints.get(i).x;
            float Y_i = edgePoints.get(i).y;
            
            if(MathUtils.between(x, X_i, X_j) && MathUtils.between(y, Y_i, Y_j)) {
                if(X_i == X_j || Y_i == Y_j) {
                    return true;
                }
                
                float ratio = Math.abs((X_j - X_i) / (Y_j - Y_i));
                if(ratio >= 1) {
                    float diffAbove = Math.abs((x - X_i) / (y - Y_i + MapGenerator.enviromentPointSpacing)) - ratio;
                    float diffBelow = Math.abs((x - X_i) / (y - Y_i - MapGenerator.enviromentPointSpacing)) - ratio;
                    if(diffBelow <= 0.025f && diffAbove >= -0.025f) {
                        return true;
                    }
                } else {
                    float diffRight = Math.abs((x - X_i + MapGenerator.enviromentPointSpacing) / (y - Y_i)) - ratio;
                    float diffLeft = Math.abs((x - X_i - MapGenerator.enviromentPointSpacing) / (y - Y_i))- ratio;
                    if(diffRight <= 0.025f && diffLeft >= -0.025f) {
                        return true;
                    }
                }
                
                ratio = Math.abs((X_i - X_j) / (Y_i - Y_j));
                if(ratio >= 1) {
                    float diffAbove = Math.abs((x - X_j) / (y - Y_j + MapGenerator.enviromentPointSpacing)) - ratio;
                    float diffBelow = Math.abs((x - X_j) / (y - Y_j - MapGenerator.enviromentPointSpacing)) - ratio;
                    if(diffBelow <= 0.025f && diffAbove >= -0.025f) {
                        return true;
                    }
                } else {
                    float diffRight = Math.abs((x - X_j + MapGenerator.enviromentPointSpacing) / (y - Y_j)) - ratio;
                    float diffLeft = Math.abs((x - X_j - MapGenerator.enviromentPointSpacing) / (y - Y_j))- ratio;
                    if(diffRight <= 0.025f && diffLeft >= -0.025f) {
                        return true;
                    }
                }
            }
            
            j = i;
        }
        return false;
    }

    public static class GroundFactory {

        public static BodyDef buildGroundBody(World world, List<EnviromentPoint> points) {
            BodyDef def = new BodyDef();
            def.type = BodyType.STATIC;
            float avgX = 0, avgY = 0;
            for (EnviromentPoint pointForAvg : points) {
                avgX += pointForAvg.x;
                avgY += pointForAvg.y;
            }
            def.position = new Vec2(avgX / points.size(), avgY / points.size());
            def.fixedRotation = true;
            return def;
        }

        public static FixtureDef[] buildComplexGroundFixture(List<EnviromentPoint> points) {
            float avgX = 0, avgY = 0;
            for (EnviromentPoint pointForAvg : points) {
                avgX += pointForAvg.x;
                avgY += pointForAvg.y;
            }
            avgX /= points.size();
            avgY /= points.size();

            Vec2[] vecs = new Vec2[points.size()];

            for (int i = 0; i < points.size(); i++) {
                vecs[i] = new Vec2(points.get(i).x - avgX, points.get(i).y - avgY);
            }

            ChainShape chain = new ChainShape();
            chain.createLoop(vecs, vecs.length);

            FixtureDef def = new FixtureDef();
            def.shape = chain;
            def.density = 10f;
            def.restitution = 0f;
            def.friction = 2f;
            FixtureDef[] defs = {def};
            return defs;
        }

    }
}
