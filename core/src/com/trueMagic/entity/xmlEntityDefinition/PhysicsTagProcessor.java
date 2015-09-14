package com.trueMagic.entity.xmlEntityDefinition;

import com.artemis.*;
import com.trueMagic.component.Physics;
import com.trueMagic.entity.ProcessedArchetypeBuilder;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.World;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Declan Easton on 2015-09-08.
 */
public class PhysicsTagProcessor implements XmlTagProcessor {

    private World pWorld;
    private com.artemis.World ceWorld;

    public PhysicsTagProcessor(World pWorld, com.artemis.World ceWorld) {
        this.pWorld = pWorld;
        this.ceWorld = ceWorld;
    }

    @Override
    public void process(ProcessedArchetypeBuilder builder, Element element) {
        Element bodyNode = (Element)element.getElementsByTagName("body").item(0);
        BodyDef bdef = new BodyDef();
        bdef.fixedRotation = bodyNode.getAttribute("fixedRotation").equalsIgnoreCase("true");
        bdef.type = BodyType.valueOf(bodyNode.getAttribute("bodyType"));

        ArrayList<FixtureDef> fdefs = new ArrayList<>();
        NodeList fixtureNodes = bodyNode.getElementsByTagName("fixture");
        for (int j = 0; j < fixtureNodes.getLength(); j++) {
            Element fixtureNode = (Element)fixtureNodes.item(j);
            FixtureDef fdef = new FixtureDef();
            fdef.density = Float.parseFloat(fixtureNode.getAttribute("density"));
            fdef.friction = Float.parseFloat(fixtureNode.getAttribute("friction"));
            fdef.restitution = Float.parseFloat(fixtureNode.getAttribute("restitution"));
            fdef.isSensor = fixtureNode.getAttribute("isSensor").equalsIgnoreCase("true");

            Shape shape = null;
            Element shapeNode = (Element)fixtureNode.getElementsByTagName("shape").item(0);
            String type = shapeNode.getAttribute("type");
            if(type.equalsIgnoreCase("circle")) {
                CircleShape circle = new CircleShape();
                circle.m_radius = Float.parseFloat(shapeNode.getAttribute("radius"));
                shape = circle;

            } else if(type.equalsIgnoreCase("rectangle")) {
                PolygonShape rect = new PolygonShape();
                if(shapeNode.hasAttribute("centroid")) {
                    String[] centroidStrings = shapeNode.getAttribute("centroid").split(",");
                    rect.setAsBox(Float.parseFloat(shapeNode.getAttribute("width")), Float.parseFloat(shapeNode.getAttribute("height")), new Vec2(Float.parseFloat(centroidStrings[0]), Float.parseFloat(centroidStrings[1])), 0);
                } else {
                    rect.setAsBox(Float.parseFloat(shapeNode.getAttribute("width")), Float.parseFloat(shapeNode.getAttribute("height")));
                }
                shape = rect;

            } else if(type.equalsIgnoreCase("polygon")) {
                PolygonShape poly = new PolygonShape();
                String vertsString = shapeNode.getAttribute("vertices");
                String[] splitVerts = vertsString.split(";");
                ArrayList<Vec2> verts = new ArrayList<>();
                for(String vertString : splitVerts) {
                    String[] vertStrings = vertString.split(",");
                    verts.add(new Vec2(Float.parseFloat(vertStrings[0]), Float.parseFloat(vertStrings[1])));
                }
                shape = poly;
            }

            fdef.shape = shape;
            fdefs.add(fdef);
        }

        builder.add(Physics.class);
        builder.add(new EntityProcessor(ceWorld, pWorld, bdef, (FixtureDef[])fdefs.toArray()));
    }

    @Override
    public String getTagName() {
        return "Physics";
    }

    private class EntityProcessor implements com.trueMagic.entity.EntityProcessor {

        private ComponentMapper<Physics> physicsComponentMapper;
        private BodyDef bodyDef;
        private FixtureDef[] fixtureDefs;
        private World pWorld;

        public EntityProcessor(com.artemis.World ceWorld, World pWorld, BodyDef bodyDef, FixtureDef[] fixtureDefs) {
            this.fixtureDefs = fixtureDefs;
            this.bodyDef = bodyDef;
            physicsComponentMapper = ComponentMapper.getFor(Physics.class, ceWorld);
            this.pWorld = pWorld;
        }

        @Override
        public void Finish(Entity entity) {
            Body b = pWorld.createBody(bodyDef);
            for(FixtureDef fdef : fixtureDefs) {
                b.createFixture(fdef);
            }
            physicsComponentMapper.get(entity).body = b;
        }
    }
}
